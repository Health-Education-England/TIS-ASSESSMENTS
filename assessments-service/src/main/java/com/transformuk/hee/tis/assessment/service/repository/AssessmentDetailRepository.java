package com.transformuk.hee.tis.assessment.service.repository;

import com.transformuk.hee.tis.assessment.service.model.AssessmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Assessment Detailentity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssessmentDetailRepository extends JpaRepository<AssessmentDetail, Long>, JpaSpecificationExecutor<AssessmentDetail> {

}
