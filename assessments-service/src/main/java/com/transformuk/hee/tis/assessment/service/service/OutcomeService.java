package com.transformuk.hee.tis.assessment.service.service;

import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OutcomeService {
  Page<Outcome> advancedSearch(String searchString, Pageable pageable);
}
