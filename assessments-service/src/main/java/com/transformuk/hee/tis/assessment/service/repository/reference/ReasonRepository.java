package com.transformuk.hee.tis.assessment.service.repository.reference;

import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


/**
 * Spring Data JPA repository for the Reason entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReasonRepository extends JpaRepository<Reason, Long>, JpaSpecificationExecutor<Reason> {

  @Query("SELECT r FROM Reason as r WHERE :outcome MEMBER OF r.outcomes")
  Set<Reason> findByOutcome(@Param("outcome") Outcome outcome);

}
