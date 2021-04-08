INSERT INTO `Outcome` (`code`, `label`)
VALUES
  ('OUTCOME_7_1', '7.1'),
  ('OUTCOME_7_2', '7.2'),
  ('OUTCOME_7_3', '7.3'),
  ('OUTCOME_7_4', '7.4');

INSERT INTO `OutcomeReasons` (`outcomeId`, `reasonId`)
SELECT `Outcome`.`id` as outcomeId, `Reason`.`id` as reasonId
FROM `Outcome`, `Reason`
WHERE `Outcome`.`code` IN('OUTCOME_7_2','OUTCOME_7_3')
  AND `Reason`.`code` IN('U1','U2','U3','U4','U5','U6','U7','U8','U9','C3','C4','C6','C12');
