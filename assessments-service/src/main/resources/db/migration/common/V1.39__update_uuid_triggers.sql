DROP TRIGGER IF EXISTS before_insert_Outcome;
CREATE TRIGGER before_insert_Outcome
BEFORE INSERT ON `Outcome`
FOR EACH ROW
SET new.uuid = ifnull(new.uuid,uuid());

DROP TRIGGER IF EXISTS before_insert_OutcomeReasons;
CREATE TRIGGER before_insert_OutcomeReasons
BEFORE INSERT ON `OutcomeReasons`
FOR EACH ROW
SET new.uuid = ifnull(new.uuid,uuid());

DROP TRIGGER IF EXISTS before_insert_Reason;
CREATE TRIGGER before_insert_Reason
BEFORE INSERT ON `Reason`
FOR EACH ROW
SET new.uuid = ifnull(new.uuid,uuid());
