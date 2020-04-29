INSERT INTO `Reason` (`code`, `label`, `requireOther`)
VALUES
  ('C1', 'Trainee at critical training progression point (but NOT at CCT) and derogation provided for the required exam', 0),
  ('C2', 'Trainee at critical training progression point (but NOT at CCT) and derogation provided for the applicable element of mandatory training', 0),
  ('C3', 'Redeployment could not acquire required experience', 0);

INSERT INTO `OutcomeReasons`
VALUES
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_1'), (SELECT `id` FROM `Reason` WHERE `code`='C1')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_1'), (SELECT `id` FROM `Reason` WHERE `code`='C2')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_1'), (SELECT `id` FROM `Reason` WHERE `code`='C3')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C3'));
