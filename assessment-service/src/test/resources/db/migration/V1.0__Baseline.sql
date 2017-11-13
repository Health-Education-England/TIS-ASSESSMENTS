CREATE TABLE `Event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `personId` bigint(20) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `startDate` timestamp NULL,
  `endDate` timestamp NULL,
  `programmeNumber` bigint(20) DEFAULT NULL,
  `programmeName` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Outcome` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `outcome` varchar(255) DEFAULT NULL,
  `underAppeal` bit(1) NOT NULL DEFAULT 0,
  `reason` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `trainingCompletionDate` timestamp NULL,
  `extendedTrainingCompletionDate` timestamp NULL,
  `extendedTrainingTimeInMonths` int(11) DEFAULT NULL,
  `tenPercentAudit` bit(1) NOT NULL DEFAULT 0,
  `externalTrainer` bit(1) NOT NULL DEFAULT 0,
  `nextRotationGradeId` bigint(20) DEFAULT NULL,
  `nextRotationGradeName` varchar(255) DEFAULT NULL,
  `traineeNotifiedOfOutcome` bit(1) NOT NULL DEFAULT 0,
  `nextReviewDate` timestamp NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Assessment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `curriculumId` bigint(20) DEFAULT NULL,
  `curriculumName` varchar(255) DEFAULT NULL,
  `curriculumStartDate` timestamp NULL,
  `curriculumEndDate` timestamp NULL,
  `curriculumSpecialtyId` bigint(20) DEFAULT NULL,
  `curriculumSpecialty` varchar(255) DEFAULT NULL,
  `curriculumSubType` varchar(255) DEFAULT NULL,
  `membershipType` varchar(255) DEFAULT NULL,
  `gradeAbbreviation` varchar(255) DEFAULT NULL,
  `gradeName` varchar(255) DEFAULT NULL,
  `periodCoveredFrom` timestamp NULL,
  `periodCoveredTo` timestamp NULL,
  `portfolioReviewDate` timestamp NULL,
  `monthsWteDuringPeriod` int(11) DEFAULT NULL,
  `monthsCountedToTraining` int(11) DEFAULT NULL,
  `traineeNtn` varchar(255) DEFAULT NULL,
  `pya` varchar(255) DEFAULT NULL,
  `eventId` bigint(20) DEFAULT NULL,
  `outcomeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `eventId` (`eventId`),
  KEY `outcomeId` (`outcomeId`),
  CONSTRAINT `fk_assessment_event_id` FOREIGN KEY (`eventId`) REFERENCES `Event` (`id`),
  CONSTRAINT `fk_assessment_outcome_id` FOREIGN KEY (`outcomeId`) REFERENCES `Outcome` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

