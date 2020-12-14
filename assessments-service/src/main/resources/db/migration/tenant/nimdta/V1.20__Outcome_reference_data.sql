CREATE TABLE `Outcome` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `label` varchar(255) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`code`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `OutcomeReasons` (
  `outcomeId` bigint(20) NOT NULL,
  `reasonId` bigint(20) NOT NULL,
  PRIMARY KEY (`outcomeId`,`reasonId`),
  KEY `fk_outome_reason_outcome_id` (`outcomeId`),
  KEY `fk_outome_reason_reason_id` (`reasonId`),
  CONSTRAINT `fk_outome_reason_outcome_id` FOREIGN KEY (`outcomeId`) REFERENCES `Outcome` (`id`),
  CONSTRAINT `fk_outome_reason_reason_id` FOREIGN KEY (`reasonId`) REFERENCES `Reason` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
