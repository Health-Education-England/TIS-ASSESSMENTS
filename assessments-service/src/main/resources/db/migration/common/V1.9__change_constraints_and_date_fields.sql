SET FOREIGN_KEY_CHECKS=0;
truncate Assessment;
truncate AssessmentDetail;
truncate Revalidation;
SET FOREIGN_KEY_CHECKS=1;

ALTER TABLE `Assessment`
DROP FOREIGN KEY `fk_assessment_detail_id`;

ALTER TABLE `Assessment`
DROP FOREIGN KEY `fk_assessment_revalidation_id`;

ALTER TABLE `Assessment`
DROP COLUMN `detailId`;

ALTER TABLE `Assessment`
DROP COLUMN `revalidationId`;

ALTER TABLE `AssessmentDetail`
ADD CONSTRAINT `fk_assessment_detail_id` FOREIGN KEY (`id`) REFERENCES `Assessment` (`id`);

ALTER TABLE `Revalidation`
ADD CONSTRAINT `fk_revlidation_id` FOREIGN KEY (`id`) REFERENCES `Assessment` (`id`);

ALTER TABLE `Assessment`
CHANGE `reviewDate` `reviewDate` DATETIME;

ALTER TABLE `AssessmentDetail`
CHANGE `curriculumStartDate` `curriculumStartDate` DATETIME;

ALTER TABLE `AssessmentDetail`
CHANGE `curriculumEndDate` `curriculumEndDate` DATETIME;

ALTER TABLE `AssessmentDetail`
CHANGE `periodCoveredFrom` `periodCoveredFrom` DATETIME;

ALTER TABLE `AssessmentDetail`
CHANGE `periodCoveredTo` `periodCoveredTo` DATETIME;

ALTER TABLE `AssessmentDetail`
CHANGE `portfolioReviewDate` `portfolioReviewDate` DATETIME;

ALTER TABLE `Outcome`
CHANGE `trainingCompletionDate` `trainingCompletionDate` DATETIME;

ALTER TABLE `Outcome`
CHANGE `extendedTrainingCompletionDate` `extendedTrainingCompletionDate` DATETIME;

ALTER TABLE `Outcome`
CHANGE `nextReviewDate` `nextReviewDate` DATETIME;
