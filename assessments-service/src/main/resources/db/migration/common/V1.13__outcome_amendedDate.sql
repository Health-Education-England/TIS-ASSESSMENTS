ALTER TABLE `Outcome_AUD`
ADD COLUMN `amendedDate` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3);

ALTER TABLE `Outcome`
ADD COLUMN `amendedDate` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3);