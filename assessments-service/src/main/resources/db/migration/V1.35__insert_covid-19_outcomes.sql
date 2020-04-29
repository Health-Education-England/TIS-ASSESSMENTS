INSERT INTO `Outcome` (`code`, `label`)
VALUES
  ('OUTCOME_10_1', '10.1'),
  ('OUTCOME_10_2', '10.2');

INSERT INTO `Reason` (`code`, `label`, `requireOther`)
VALUES
  ('C4', 'Prolonged self-isolation needed during COVID-19', 0),
  ('C5', 'Inadequate progress in this training year prior to COVID-19', 0),
  ('C6', 'Incomplete evidence due to COVID-19', 0),
  ('C7', 'Foundation - extra time required to gain F1CC/FPCC', 0),
  ('C8', 'Royal College or Faculty exam cancelled with trainee at CCT date', 0),
  ('C9', 'Royal College or Faculty mandatory training course cancelled with trainee at CCT date', 0),
  ('C10', 'Royal College or Faculty exam cancelled with trainee at critical progression point (not CCT) with no derogation', 0),
  ('C11', 'Royal College or Faculty mandatory training course cancelled with trainee at critical progression point (not CCT) with no derogation', 0),
  ('C12', 'Other issues related to COVID-19 (please describe)', 1);

INSERT INTO `OutcomeReasons`
VALUES
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_1'), (SELECT `id` FROM `Reason` WHERE `code`='C4')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_1'), (SELECT `id` FROM `Reason` WHERE `code`='C5')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_1'), (SELECT `id` FROM `Reason` WHERE `code`='C6')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_1'), (SELECT `id` FROM `Reason` WHERE `code`='C12')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C4')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C5')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C6')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C7')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C8')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C9')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C10')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C11')),
  ((SELECT `id` FROM `Outcome` WHERE `code`='OUTCOME_10_2'), (SELECT `id` FROM `Reason` WHERE `code`='C12'));
