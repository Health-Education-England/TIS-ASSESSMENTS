ALTER TABLE `AssessmentOutcome`
ADD COLUMN `nextRotationGradeId` bigint(20);

ALTER TABLE `AssessmentOutcome_AUD`
ADD COLUMN `nextRotationGradeId` bigint(20);
