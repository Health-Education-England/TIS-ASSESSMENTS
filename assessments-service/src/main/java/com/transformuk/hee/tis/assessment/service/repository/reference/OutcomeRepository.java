package com.transformuk.hee.tis.assessment.service.repository.reference;

import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the AssessmentOutcome entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long>, JpaSpecificationExecutor<Outcome> {

}
