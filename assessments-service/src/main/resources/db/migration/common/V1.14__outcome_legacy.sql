ALTER TABLE `Outcome_AUD`
ADD COLUMN `legacy` bit(1) DEFAULT NULL;

ALTER TABLE `Outcome`
ADD COLUMN `legacy` bit(1) DEFAULT NULL;
