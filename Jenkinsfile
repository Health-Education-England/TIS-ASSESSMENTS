@Library('utils@master') _

def utils = new hee.tis.utils()

node {

    if (env.BRANCH_NAME != "master") {
        // PR and branch builds are done by GitHub Actions.
        return
    }

    def service = "assessments"

    deleteDir()

    stage('Checkout Git Repo') {
    checkout scm
    }

    env.GIT_COMMIT = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
    def mvn = "${tool 'Maven 3.3.9'}/bin/mvn"
    def workspace = pwd()
    def parent_workspace = pwd()
    def repository = "${env.GIT_COMMIT}".split("TIS-")[-1].split(".git")[0]
    def buildNumber = env.BUILD_NUMBER
    def buildVersion = env.GIT_COMMIT
    def imageName = ""
    def imageVersionTag = ""
    boolean isService = false

    println "[Jenkinsfile INFO] Commit Hash is ${GIT_COMMIT}"

    if (fileExists("$workspace/$service-service/pom.xml")) {
        workspace = "$workspace/$service-service"
        env.WORKSPACE = workspace
        sh 'cd "$workspace"'
        isService = true
    }

    milestone 1


    stage('Build') {
        sh "'${mvn}' clean install -DskipTests"
    }

    stage('Unit Tests') {
        try {
            sh "'${mvn}' clean test"
        } catch (err) {
            throw err
        } finally {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }

    milestone 2

    stage('Dockerise') {
        env.VERSION = utils.getMvnToPom(workspace, 'version')
        env.GROUP_ID = utils.getMvnToPom(workspace, 'groupId')
        env.ARTIFACT_ID = utils.getMvnToPom(workspace, 'artifactId')
        env.PACKAGING = utils.getMvnToPom(workspace, 'packaging')
        imageName = env.ARTIFACT_ID
        imageVersionTag = env.GIT_COMMIT

        if (isService) {
            imageName = service
            env.IMAGE_NAME = imageName
        }

        //urghhh
        sh "mvn package -DskipTests"
        sh "cp ./assessments-service/target/assessments-service-*.war ./assessments-service/target/app.jar"

        def dockerImageName = "assessments"
        def containerRegistryLocaltion = "430723991443.dkr.ecr.eu-west-2.amazonaws.com"

        // log into aws docker
        sh "aws ecr get-login-password --region eu-west-2 | docker login --username AWS --password-stdin 430723991443.dkr.ecr.eu-west-2.amazonaws.com"

        sh "docker build -t ${containerRegistryLocaltion}/${dockerImageName}:$buildVersion -f ./assessments-service/Dockerfile ./assessments-service"
        sh "docker push ${containerRegistryLocaltion}/${dockerImageName}:$buildVersion"

        sh "docker tag ${containerRegistryLocaltion}/${dockerImageName}:$buildVersion ${containerRegistryLocaltion}/${dockerImageName}:latest"
        sh "docker push ${containerRegistryLocaltion}/${dockerImageName}:latest"

        sh "docker rmi ${containerRegistryLocaltion}/${dockerImageName}:latest"
        sh "docker rmi ${containerRegistryLocaltion}/${dockerImageName}:$buildVersion"

        println "[Jenkinsfile INFO] Stage Dockerize completed..."
    }

    milestone 3
    def healthcheckEndpoint = "/assessments/management/health"

    stage('Staging') {
        node {
            println "[Jenkinsfile INFO] Stage Deploy starting..."
            try {
                sh "ansible-playbook -i $env.DEVOPS_BASE/ansible/inventory/stage $env.DEVOPS_BASE/ansible/${service}.yml --extra-vars=\"{\'versions\': {\'${service}\': \'${env.GIT_COMMIT}\'}}\""
            } catch (err) {
                throw err
            } finally {
                println "[Jenkinsfile INFO] Stage Deploy completed..."
            }
        }
    }
    stage('Health check on STAGE') {
        withEnv(["endpoint=${healthcheckEndpoint}"]) {
            def httpStatus=sh(returnStdout: true, script: 'sleep 30; curl -m 300 -s -o /dev/null -w "%{http_code}" 10.160.0.137:8097${endpoint}').trim()
            if("200" == "${httpStatus}")  println "Status is 200"
            else  throw new Exception("health check failed on STAGE with http status: $httpStatus")
        }
    }

    milestone 4

    stage('Approval') {
        timeout(time: 5, unit: 'HOURS') {
            input message: 'Deploy to production?', ok: 'Deploy!'
        }
    }

    milestone 5

    stage('Production') {
        node {
            try {
                sh "ansible-playbook -i $env.DEVOPS_BASE/ansible/inventory/prod $env.DEVOPS_BASE/ansible/${service}.yml --extra-vars=\"{\'versions\': {\'${service}\': \'${env.GIT_COMMIT}\'}}\""
                withEnv(["endpoint=${healthcheckEndpoint}"]) {
                    def httpStatus=sh(returnStdout: true, script: 'sleep 30; curl -m 300 -s -o /dev/null -w "%{http_code}" 10.170.0.137:8097${endpoint}').trim()
                    if("200" == "${httpStatus}")  println "Status is 200"
                    else  throw new Exception("health check failed on HEE PROD with http status: $httpStatus")
                }

                sh "ansible-playbook -i $env.DEVOPS_BASE/ansible/inventory/nimdta $env.DEVOPS_BASE/ansible/${service}.yml --extra-vars=\"{\'versions\': {\'${service}\': \'${env.GIT_COMMIT}\'}}\""
                withEnv(["endpoint=${healthcheckEndpoint}"]) {
                    def httpStatus=sh(returnStdout: true, script: 'sleep 30; curl -m 300 -s -o /dev/null -w "%{http_code}" 10.254.1.137:8097${endpoint}').trim()
                    if("200" == "${httpStatus}")  println "Status is 200"
                    else  throw new Exception("health check failed on NIMDTA PROD with http status: $httpStatus")
                }
            } catch (err) {
                throw err
            } finally {
                println "[Jenkinsfile INFO] Production Deploy completed..."
            }
        }
    }
}
