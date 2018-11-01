INSERT INTO `Reason` (`id`, `code`, `label`, `requireOther`, `isLegacy`)
VALUES
(1, 'U1', 'Record keeping and evidence', 0, 0),
(2, 'U2', 'Inadequate experience', 0, 0);

INSERT INTO `Assessment` (`id`, `traineeId`, `firstName`, `lastName`, `reviewDate`, `programmeNumber`, `programmeName`, `status`, `type`, `intrepidId`, `amendedDate`, `programmeId`, `softDeletedDate`)
VALUES
(1, 8502, 'FirstName1', 'LastName1', NULL, 'PROG1', 'ProgName1', 'COMPLETED', 'ARCP', '111111111', NULL, 6242, NULL),
(2, 9017, 'FirstName2', 'LastName2', NULL, 'PROG2', 'ProgName2', 'COMPLETED', 'ARCP', '222222222', NULL, 937, NULL);

INSERT INTO `AssessmentDetail` (`id`, `curriculumId`, `curriculumName`, `curriculumStartDate`, `curriculumEndDate`, `curriculumSpecialtyId`, `curriculumSpecialty`, `curriculumSubType`, `membershipType`, `gradeAbbreviation`, `gradeName`, `periodCoveredFrom`, `periodCoveredTo`, `portfolioReviewDate`, `monthsWteDuringPeriod`, `monthsCountedToTraining`, `traineeNtn`, `amendedDate`, `daysOutOfTraining`, `intrepidId`, `pya`, `gradeId`)
VALUES
(1, 149, 'CurricName1', NULL, NULL, NULL, NULL, NULL, NULL, 'ST1', 'STT1', NULL, NULL, NULL, 0, 36, 'LDN/111/111/C', NULL, 0, '111111111', NULL, 247),
(2, 261, 'CurricName2', NULL, NULL, NULL, NULL, NULL, NULL, 'ST1', 'STT1', NULL, NULL, NULL, 0, 0, 'LDN/222/222/C', NULL, NULL, '222222222', NULL, 247);

INSERT INTO `AssessmentOutcome`
(`id`, `outcome`, `underAppeal`, `comments`, `trainingCompletionDate`, `extendedTrainingCompletionDate`, `extendedTrainingTimeInMonths`, `tenPercentAudit`, `externalTrainer`, `nextRotationGradeName`, `traineeNotifiedOfOutcome`, `nextReviewDate`, `intrepidId`, `academicCurriculumAssessed`, `academicOutcome`, `detailedReasons`, `mitigatingCircumstances`, `competencesToBeDeveloped`, `otherRecommendedActions`, `addCommentsFromPanel`, `reason`, `nextRotationGradeAbbr`, `amendedDate`, `legacy`, `recommendedAddtnlTrainingTime`, `outcomeId`, `reasonId`, `nextRotationGradeId`)
VALUES
(1, '1', 0, '', NULL, NULL, 0, 0, 0, NULL, 0, NULL, '333333333', NULL, NULL, 'NA', NULL, NULL, NULL, NULL, NULL, 'ST7', NULL, 0, NULL, 2, NULL, 343),
(2, '5', 0, '', NULL, NULL, 0, 0, 0, NULL, 0, NULL, '444444444', NULL, NULL, 'NA', 'NA', 'NA', 'NA', NULL, 'Record keeping and evidence. ', NULL, NULL, 0, NULL, 6, NULL, NULL);

INSERT INTO `Revalidation` (`id`, `concernSummary`, `responsibleOfficerComments`, `amendedDate`, `intrepidId`, `knownConcerns`)
VALUES
(1, NULL, NULL, NULL, '111111111', 0),
(2, NULL, NULL, NULL, '222222222', 0);


INSERT INTO `AssessmentOutcomeReason` (`id`, `assessmentOutcomeId`, `reasonId`, `other`)
VALUES
(1, 1, 2, 'other reason text2'),
(2, 1, 1, 'other reason text1');