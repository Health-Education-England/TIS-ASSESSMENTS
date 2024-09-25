INSERT INTO `Reason` (`code`, `label`) VALUES
('N23', 'Assessed in another specialty (dual/triple CCT)');

INSERT INTO `OutcomeReasons` (`outcomeId`, `reasonId`)
SELECT 1, `id` FROM `Reason` WHERE `code` = 'N23';