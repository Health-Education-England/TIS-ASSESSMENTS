ALTER TABLE `Outcome`
DROP COLUMN `reason`;

ALTER TABLE `Outcome`
ADD COLUMN `reason` TEXT DEFAULT NULL;
