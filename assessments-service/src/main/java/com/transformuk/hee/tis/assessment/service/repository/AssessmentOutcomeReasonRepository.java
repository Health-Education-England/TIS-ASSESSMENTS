package com.transformuk.hee.tis.assessment.service.repository;

import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcomeReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Assessment Outcome Reason entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssessmentOutcomeReasonRepository extends JpaRepository<AssessmentOutcomeReason, Long>, JpaSpecificationExecutor<AssessmentOutcomeReason> {

}
