package com.transformuk.hee.tis.assessment.service.repository;

import com.transformuk.hee.tis.assessment.service.model.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Outcome entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long> {

}
