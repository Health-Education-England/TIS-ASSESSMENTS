package com.transformuk.hee.tis.assessment.service.repository;

import java.time.LocalDate;

import com.transformuk.hee.tis.assessment.service.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Spring Data JPA repository for the Assessment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long>, JpaSpecificationExecutor<Assessment> {

  public Page<Assessment> findAllBySoftDeletedDate(LocalDate date, Pageable pageable);

}
