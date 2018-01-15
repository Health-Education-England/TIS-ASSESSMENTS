package com.transformuk.hee.tis.assessment.service.repository;

import com.transformuk.hee.tis.assessment.service.model.Revalidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Revalidation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RevalidationRepository extends JpaRepository<Revalidation, Long> {

}
