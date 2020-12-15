ALTER TABLE `Outcome`
ADD COLUMN uuid varchar(36);

UPDATE `Outcome` SET uuid =(SELECT uuid());
CREATE TRIGGER before_insert_Outcome
BEFORE INSERT ON `Outcome`
FOR EACH ROW
SET new.uuid = uuid();

CREATE UNIQUE index idx_uuid_unique on `Outcome` (uuid);

ALTER TABLE `OutcomeReasons`
ADD COLUMN uuid varchar(36);

UPDATE `OutcomeReasons` SET uuid =(SELECT uuid());
CREATE TRIGGER before_insert_OutcomeReasons
BEFORE INSERT ON `OutcomeReasons`
FOR EACH ROW
SET new.uuid = uuid();

CREATE UNIQUE index idx_uuid_unique on `OutcomeReasons` (uuid);

ALTER TABLE `Reason`
ADD COLUMN uuid varchar(36);

UPDATE `Reason` SET uuid =(SELECT uuid());
CREATE TRIGGER before_insert_Reason
BEFORE INSERT ON `Reason`
FOR EACH ROW
SET new.uuid = uuid();

CREATE UNIQUE index idx_uuid_unique on `Reason` (uuid);
