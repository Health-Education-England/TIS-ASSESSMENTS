ALTER TABLE `Outcome`
DROP COLUMN `recommendedAddtnlTrainingTime`;

ALTER TABLE `Outcome`
ADD COLUMN `recommendedAddtnlTrainingTime` TEXT NULL DEFAULT NULL;

