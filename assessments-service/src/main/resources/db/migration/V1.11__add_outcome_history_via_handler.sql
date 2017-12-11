SET FOREIGN_KEY_CHECKS=0;
truncate Outcome;
SET FOREIGN_KEY_CHECKS=1;

DROP TABLE `AssessmentOutcome`;

ALTER TABLE `Outcome`
ADD CONSTRAINT `fk_assessment_outcome_id` FOREIGN KEY (`id`) REFERENCES `Assessment` (`id`);

CREATE TABLE `OutcomeHistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `outcomeId` bigint(20) NOT NULL,
  `outcome` varchar(255) DEFAULT NULL,
  `underAppeal` bit(1) DEFAULT NULL,
  `comments` text,
  `trainingCompletionDate` datetime DEFAULT NULL,
  `extendedTrainingCompletionDate` datetime DEFAULT NULL,
  `extendedTrainingTimeInMonths` int(11) DEFAULT NULL,
  `tenPercentAudit` bit(1) DEFAULT NULL,
  `externalTrainer` bit(1) DEFAULT NULL,
  `nextRotationGradeId` varchar(255) DEFAULT NULL,
  `nextRotationGradeName` varchar(255) DEFAULT NULL,
  `traineeNotifiedOfOutcome` bit(1) DEFAULT NULL,
  `nextReviewDate` datetime DEFAULT NULL,
  `intrepidId` varchar(255) DEFAULT NULL,
  `academicCurriculumAssessed` varchar(255) DEFAULT NULL,
  `academicOutcome` varchar(255) DEFAULT NULL,
  `detailedReasons` text,
  `mitigatingCircumstances` text,
  `competencesToBeDeveloped` text,
  `otherRecommendedActions` text,
  `recommendedAddtnlTrainingTime` varchar(255) DEFAULT NULL,
  `addCommentsFromPanel` text,
  `reason` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6065 DEFAULT CHARSET=utf8;
