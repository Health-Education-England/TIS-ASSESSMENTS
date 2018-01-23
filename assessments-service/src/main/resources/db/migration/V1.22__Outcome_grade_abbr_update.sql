ALTER TABLE `AssessmentOutcome`
CHANGE `nextRotationGradeId` `nextRotationGradeAbbr` varchar(255);

ALTER TABLE `AssessmentOutcome_AUD`
CHANGE `nextRotationGradeId` `nextRotationGradeAbbr` varchar(255);
