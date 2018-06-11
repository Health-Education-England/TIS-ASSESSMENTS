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

UPDATE `Reason` SET `label`='Record keeping and evidence' WHERE `code`='U1';
UPDATE `Reason` SET `label`='Inadequate experience' WHERE `code`='U2';
UPDATE `Reason` SET `label`='No engagement with supervisor' WHERE `code`='U3';
UPDATE `Reason` SET `label`='Trainer absence' WHERE `code`='U4';
UPDATE `Reason` SET `label`='Single exam failure' WHERE `code`='U5';
UPDATE `Reason` SET `label`='Continual exam failure' WHERE `code`='U6';
UPDATE `Reason` SET `label`='Trainee requires Deanery support' WHERE `code`='U7';
UPDATE `Reason` SET `label`='Other reason (please specify)' WHERE `code`='U8';
UPDATE `Reason` SET `label`='Inadequate attendance' WHERE `code`='U9';
UPDATE `Reason` SET `label`='Assessment/Curriculum outcomes not achieved' WHERE `code`='U10';
UPDATE `Reason` SET `label`='Trainee sick leave' WHERE `code`='N1';
UPDATE `Reason` SET `label`='Trainee maternity/paternity leave' WHERE `code`='N2';
UPDATE `Reason` SET `label`='Trainee not in post long enough' WHERE `code`='N3';
UPDATE `Reason` SET `label`='Trainee fell outside annual reporting period' WHERE `code`='N4';
UPDATE `Reason` SET `label`='Trainee post-CCT' WHERE `code`='N5';
UPDATE `Reason` SET `label`='Trainee missed review' WHERE `code`='N6';
UPDATE `Reason` SET `label`='Trainee inter-Deanery transfer' WHERE `code`='N7';
UPDATE `Reason` SET `label`='Trainee reviewed in other Deanery' WHERE `code`='N8';
UPDATE `Reason` SET `label`='Trainee contract termination' WHERE `code`='N9';
UPDATE `Reason` SET `label`='Trainee gross misconduct' WHERE `code`='N10';
UPDATE `Reason` SET `label`='Trainee suspension' WHERE `code`='N11';
UPDATE `Reason` SET `label`='Other reason (please specify)' WHERE `code`='N13';
UPDATE `Reason` SET `label`='LTFT achieving progress at the expected rate' WHERE `code`='N14';
UPDATE `Reason` SET `label`='LTFT not achieving progress at the expected rate' WHERE `code`='N15';
UPDATE `Reason` SET `label`='Dismissed' WHERE `code`='N16';
UPDATE `Reason` SET `label`='Dismissed no remedial training' WHERE `code`='N17';
UPDATE `Reason` SET `label`='Dismissed received remedial training' WHERE `code`='N18';
UPDATE `Reason` SET `label`='Dismissed no GMC referral' WHERE `code`='N19';
UPDATE `Reason` SET `label`='Dismissed - following GMC referral' WHERE `code`='N20';
UPDATE `Reason` SET `label`='Resignation no remedial training undertaken' WHERE `code`='N21';
UPDATE `Reason` SET `label`='Resignation received remedial training' WHERE `code`='N22';

UPDATE `Reason` SET `requireOther`=1 WHERE `code`='U8';
UPDATE `Reason` SET `requireOther`=1 WHERE `code`='N13';