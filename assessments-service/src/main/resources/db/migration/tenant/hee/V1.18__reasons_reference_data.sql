CREATE TABLE `Reason` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `label` varchar(255) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`code`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Reason` (`code`, `label`) VALUES
('U1', 'U1 Record keeping and evidence'),
('U2', 'U2 Inadequate experience'),
('U3', 'U3 No engagement with supervisor'),
('U4', 'U4 Trainer absence'),
('U5', 'U5 Single exam failure'),
('U6', 'U6 Continual exam failure'),
('U7', 'U7 Trainee requires Deanery support'),
('U8', 'U8 Other reason (please specify)'),
('U9', 'U9 Inadequate attendance'),
('U10', 'U10 Assessment/Curriculum outcomes not achieved'),
('N1', 'N1 Trainee sick leave'),
('N2', 'N2 Trainee maternity/paternity leave'),
('N3', 'N3 Trainee not in post long enough'),
('N4', 'N4 Trainee fell outside annual reporting period'),
('N5', 'N5 Trainee post-CCT'),
('N6', 'N6 Trainee missed review'),
('N7', 'N7 Trainee inter-Deanery transfer'),
('N8', 'N8 Trainee reviewed in other Deanery'),
('N9', 'N9 Trainee contract termination'),
('N10', 'N10 Trainee gross misconduct'),
('N11', 'N11 Trainee suspension'),
('N13', 'N13 Other reason (please specify)'),
('N14', 'N14 LTFT achieving progress at the expected rate'),
('N15', 'N15 LTFT not achieving progress at the expected rate'),
('N16', 'N16 Dismissed'),
('N17', 'N17 Dismissed no remedial training'),
('N18', 'N18 Dismissed received remedial training'),
('N19', 'N19 Dismissed no GMC referral'),
('N20', 'N20 Dismissed - following GMC referral'),
('N21', 'N21 Resignation no remedial training undertaken'),
('N22', 'N22 Resignation received remedial training');



