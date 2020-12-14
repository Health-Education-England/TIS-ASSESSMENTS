CREATE TABLE `AssessmentOutcomeReason` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assessmentOutcomeId` bigint(20) NOT NULL,
  `reasonId` bigint(20) NOT NULL,
  `other` TEXT DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_aor_assessment_outcome_id` FOREIGN KEY (`assessmentOutcomeId`) REFERENCES `AssessmentOutcome` (`id`),
  CONSTRAINT `fk_aor_reason_id` FOREIGN KEY (`reasonId`) REFERENCES `Reason` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `Reason`
ADD COLUMN `requireOther` bit(1) NOT NULL DEFAULT 0;
