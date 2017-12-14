ALTER TABLE `Revalidation`
ADD COLUMN `intrepidId` varchar(255) NULL DEFAULT NULL;

ALTER TABLE `Revalidation`
DROP COLUMN `knownConcerns`;

ALTER TABLE `Revalidation`
ADD COLUMN `knownConcerns` bit(1) NULL DEFAULT NULL;

CREATE UNIQUE INDEX revalidation_intrepid_id ON `Revalidation` (`intrepidId`);
