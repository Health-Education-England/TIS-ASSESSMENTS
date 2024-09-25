INSERT INTO `Reason` (`code`, `label`) VALUES
('N23', 'Assessed in another specialty (dual/triple CCT)');

SET @reason_id = (SELECT id from Reason where code = 'N23');

INSERT INTO OutcomeReasons (`outcomeId`, `reasonId`) VALUES
(1, @reason_id);