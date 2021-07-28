package com.transformuk.hee.tis.assessment.service.service.impl.reference;

import static com.transformuk.hee.tis.assessment.service.service.impl.SpecificationFactory.containsLike;

import com.google.common.base.Preconditions;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import com.transformuk.hee.tis.assessment.service.repository.reference.ReasonRepository;
import com.transformuk.hee.tis.assessment.service.service.ReasonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReasonServiceImpl implements ReasonService {

  private static final String CODE_COLUMN = "code";
  private static final String LABEL_COLUMN = "label";

  @Autowired
  private ReasonRepository reasonRepository;

  @Override
  @Transactional(readOnly = true)
  public Page<Reason> advancedSearch(String searchString, Pageable pageable) {
    Preconditions.checkNotNull(searchString);
    Preconditions.checkNotNull(pageable);

    List<Specification<Reason>> specs = new ArrayList<>();
    //add the text search criteria
    specs.add(Specification.where(containsLike(CODE_COLUMN, searchString))
        .or(containsLike(LABEL_COLUMN, searchString)));

    Specification<Reason> fullSpec = Specification.where(specs.get(0));
    //add the rest of the specs that made it in
    for (int i = 1; i < specs.size(); i++) {
      fullSpec = fullSpec.and(specs.get(i));
    }
    return reasonRepository.findAll(fullSpec, pageable);
  }

}
