ALTER TABLE `Reason`
ADD COLUMN `isLegacy` bit(1) NOT NULL DEFAULT 0;

UPDATE `Reason` SET `isLegacy` = 0;
