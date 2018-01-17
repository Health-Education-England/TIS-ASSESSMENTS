package com.transformuk.hee.tis.assessment.service.repository.reference;

import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data JPA repository for the AssessmentOutcome entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long>, JpaSpecificationExecutor<Outcome> {

  @Query("FROM Outcome o JOIN FETCH o.reasons")
  List<Outcome> findAllWithReasons();
}
