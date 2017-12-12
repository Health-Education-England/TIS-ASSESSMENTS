ALTER TABLE `Outcome_AUD`
DROP COLUMN `nextRotationGradeId`;

ALTER TABLE `Outcome_AUD`
ADD COLUMN `nextRotationGradeId` varchar(255) NULL;


ALTER TABLE `Outcome`
DROP COLUMN `nextRotationGradeId`;

ALTER TABLE `Outcome`
ADD COLUMN `nextRotationGradeId` varchar(255) NULL;
