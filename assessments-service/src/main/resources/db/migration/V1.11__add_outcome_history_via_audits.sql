SET FOREIGN_KEY_CHECKS=0;
truncate Outcome;
SET FOREIGN_KEY_CHECKS=1;

DROP TABLE `AssessmentOutcome`;

ALTER TABLE `Outcome`
ADD CONSTRAINT `fk_assessment_outcome_id` FOREIGN KEY (`id`) REFERENCES `Assessment` (`id`);

CREATE TABLE `REVINFO` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Outcome_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `academicCurriculumAssessed` varchar(255) DEFAULT NULL,
  `academicOutcome` varchar(255) DEFAULT NULL,
  `addCommentsFromPanel` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `competencesToBeDeveloped` varchar(255) DEFAULT NULL,
  `detailedReasons` varchar(255) DEFAULT NULL,
  `extendedTrainingCompletionDate` date DEFAULT NULL,
  `extendedTrainingTimeInMonths` int(11) DEFAULT NULL,
  `externalTrainer` bit(1) DEFAULT NULL,
  `intrepidId` varchar(255) DEFAULT NULL,
  `mitigatingCircumstances` varchar(255) DEFAULT NULL,
  `nextReviewDate` date DEFAULT NULL,
  `nextRotationGradeId` bigint(20) DEFAULT NULL,
  `nextRotationGradeName` varchar(255) DEFAULT NULL,
  `otherRecommendedActions` varchar(255) DEFAULT NULL,
  `outcome` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `recommendedAddtnlTrainingTime` varchar(255) DEFAULT NULL,
  `tenPercentAudit` bit(1) DEFAULT NULL,
  `traineeNotifiedOfOutcome` bit(1) DEFAULT NULL,
  `trainingCompletionDate` date DEFAULT NULL,
  `underAppeal` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `outcome_rev` (`REV`),
  CONSTRAINT `outcome_rev` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
