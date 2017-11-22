set autocommit=0;

ALTER TABLE `Outcome`
ADD COLUMN `intrepidId` varchar(255) DEFAULT NULL;

ALTER TABLE `Assessment`
ADD COLUMN `intrepidId` varchar(255) DEFAULT NULL;

commit;
set autocommit=1;