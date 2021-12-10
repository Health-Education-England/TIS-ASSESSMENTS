ALTER TABLE `Assessment` ADD INDEX `assessment_soft_deleted_date` (`softDeletedDate`);
ALTER TABLE `AssessmentOutcome` ADD INDEX `assessment_outcome_outcome` (`outcome`);
ALTER TABLE `Assessment` ADD INDEX `assessment_duplicates`
(`traineeId`, `programmeMembershipId`, `reviewDate`, `softDeletedDate`);
ALTER TABLE `Assessment` ADD INDEX `assessment_search`
(`gmcNumber`, `gdcNumber`, `publicHealthNumber`);
ALTER TABLE `AssessmentDetail` ADD INDEX `assessment_detail_curriculum_name` (`curriculumName`);
