package com.transformuk.hee.tis.assessment.service.service.impl.reference;

import com.google.common.base.Preconditions;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import com.transformuk.hee.tis.assessment.service.repository.reference.ReasonRepository;
import com.transformuk.hee.tis.assessment.service.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.transformuk.hee.tis.assessment.service.service.impl.SpecificationFactory.containsLike;

@Service
public class ReasonServiceImpl implements ReasonService {

  private static final String CODE_COLUMN = "code";
  private static final String LABEL_COLUMN = "label";

  @Autowired
  private ReasonRepository reasonRepository;

  @Override
  public Page<Reason> advancedSearch(String searchString, Pageable pageable) {
    Preconditions.checkNotNull(searchString);
    Preconditions.checkNotNull(pageable);

    List<Specification<Reason>> specs = new ArrayList<>();
    //add the text search criteria
    specs.add(Specifications.where(containsLike(CODE_COLUMN, searchString))
        .or(containsLike(LABEL_COLUMN, searchString)));

    Specifications<Reason> fullSpec = Specifications.where(specs.get(0));
    //add the rest of the specs that made it in
    for (int i = 1; i < specs.size(); i++) {
      fullSpec = fullSpec.and(specs.get(i));
    }
    return reasonRepository.findAll(fullSpec, pageable);
  }

}
