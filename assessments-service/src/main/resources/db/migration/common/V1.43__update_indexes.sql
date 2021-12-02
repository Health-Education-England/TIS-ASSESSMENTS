SET @x := (SELECT count(*) FROM information_schema.statistics
 WHERE table_schema = database()
 AND table_name = 'Assessment'
 AND index_name = 'assessment_programme_membership_id');
SET @sql := if( @x > 0, 'SELECT ''Index exists.''',
  'ALTER TABLE `Assessment` ADD INDEX `assessment_programme_membership_id` (`programmeMembershipId`);');
PREPARE stmt FROM @sql;
EXECUTE stmt;

SET @x := (SELECT count(*) FROM information_schema.statistics
 WHERE table_schema = database()
 AND table_name = 'Assessment'
 AND index_name = 'assessment_review_date');
SET @sql := if( @x > 0, 'SELECT ''Index exists.''',
  'ALTER TABLE `Assessment` ADD INDEX `assessment_review_date` (`reviewDate` ASC);');
PREPARE stmt FROM @sql;
EXECUTE stmt;

SET @x := (SELECT count(*) FROM information_schema.statistics
 WHERE table_schema = database()
 AND table_name = 'AssessmentOutcome'
 AND index_name = 'assessment_outcome_outcome');
SET @sql := if( @x > 0, 'SELECT ''Index exists.''',
  'ALTER TABLE `AssessmentOutcome` ADD INDEX `assessment_outcome_outcome` (`outcome` ASC);');
PREPARE stmt FROM @sql;
EXECUTE stmt;

