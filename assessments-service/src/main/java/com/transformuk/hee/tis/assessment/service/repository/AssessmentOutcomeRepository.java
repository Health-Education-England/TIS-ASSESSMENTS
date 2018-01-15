package com.transformuk.hee.tis.assessment.service.repository;

import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Assessment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssessmentOutcomeRepository extends JpaRepository<AssessmentOutcome, Long>, JpaSpecificationExecutor<AssessmentOutcome> {

}
