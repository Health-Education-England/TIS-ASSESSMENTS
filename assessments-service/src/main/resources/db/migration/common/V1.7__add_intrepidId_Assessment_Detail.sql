ALTER TABLE `AssessmentDetail`
ADD COLUMN `intrepidId` varchar(255) NULL;

CREATE UNIQUE INDEX assessment_detail_intrepid_id ON `AssessmentDetail` (`intrepidId`);
