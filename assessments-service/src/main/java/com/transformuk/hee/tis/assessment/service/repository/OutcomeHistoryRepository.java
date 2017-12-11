package com.transformuk.hee.tis.assessment.service.repository;

import com.transformuk.hee.tis.assessment.service.model.Outcome;
import com.transformuk.hee.tis.assessment.service.model.OutcomeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the OutcomeHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutcomeHistoryRepository extends JpaRepository<OutcomeHistory, Long> {

}
