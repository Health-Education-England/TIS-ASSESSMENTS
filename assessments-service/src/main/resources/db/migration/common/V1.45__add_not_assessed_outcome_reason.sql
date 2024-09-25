INSERT INTO `Reason` (`code`, `label`) VALUES
('N23', 'Assessed in another specialty (dual/triple CCT)');

INSERT INTO `OutcomeReasons` (`outcomeId`, `reasonId`)
SELECT o.`id`, r.`id`
FROM `Outcome` o, `Reason` r
WHERE o.`label` = 'Not Assessed' AND r.`code` = 'N23';