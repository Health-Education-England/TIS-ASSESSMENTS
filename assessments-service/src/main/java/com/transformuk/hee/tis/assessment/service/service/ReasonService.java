package com.transformuk.hee.tis.assessment.service.service;

import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReasonService {

  Page<Reason> advancedSearch(String searchString, Pageable pageable);

}
