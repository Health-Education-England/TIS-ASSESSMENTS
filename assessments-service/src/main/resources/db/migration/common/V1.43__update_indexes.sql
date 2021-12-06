ALTER TABLE `Assessment` ADD INDEX `assessment_programme_membership_id` (`programmeMembershipId`);
ALTER TABLE `Assessment` ADD INDEX `assessment_review_date` (`reviewDate`);
ALTER TABLE `Assessment` ADD INDEX `assessment_soft_deleted_date` (`softDeletedDate`);
ALTER TABLE `AssessmentOutcome` ADD INDEX `assessment_outcome_outcome` (`outcome`);
