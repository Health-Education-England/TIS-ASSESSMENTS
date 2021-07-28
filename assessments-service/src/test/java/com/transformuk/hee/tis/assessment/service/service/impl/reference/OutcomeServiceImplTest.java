package com.transformuk.hee.tis.assessment.service.service.impl.reference;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.repository.reference.OutcomeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@RunWith(MockitoJUnitRunner.class)
public class OutcomeServiceImplTest {

  private static final int PAGE = 0;
  private static final int SIZE = 20;
  private static final String OUTCOME_LABEL_1 = "outcomeLabel1";
  private static final String OUTCOME_CODE_1 = "outcomeCode1";
  private static final long OUTCOME_ID1 = 1L;
  private static final long OUTCOME_ID2 = 2L;
  private static final String OUTCOME_CODE_2 = "outcomeCode2";
  private static final String OUTCOME_LABEL_2 = "outcomeLabel2";
  @InjectMocks
  private OutcomeServiceImpl testObj;

  @Mock
  private OutcomeRepository outcomeRepositoryMock;

  private Pageable pageable;

  @Before
  public void setup() {
    pageable = PageRequest.of(PAGE, SIZE);
  }

  @Test(expected = NullPointerException.class)
  public void advancedSearchShouldThrowExceptionWhenSearchStringIsNull() {
    try {
      testObj.advancedSearch(null, pageable);
    } catch (Exception e) {
      verify(outcomeRepositoryMock, never()).findAll(any(Specification.class), any(Pageable.class));
      throw e;
    }
  }

  @Test
  public void advancedSearchShouldReturnSeachResults() {
    String searchString = "searchString";

    Outcome outcome1 = new Outcome(), outcome2 = new Outcome();
    outcome1.id(OUTCOME_ID1).code(OUTCOME_CODE_1).label(OUTCOME_LABEL_1);
    outcome2.id(OUTCOME_ID2).code(OUTCOME_CODE_2).label(OUTCOME_LABEL_2);

    Page<Outcome> pagedOutcomes = new PageImpl<>(Lists.newArrayList(outcome1, outcome2));

    when(outcomeRepositoryMock.findAll(any(Specification.class), eq(pageable)))
        .thenReturn(pagedOutcomes);

    Page<Outcome> result = testObj.advancedSearch(searchString, pageable);

    Assert.assertEquals(2, result.getTotalElements());
    Assert.assertTrue(result.getContent().contains(outcome1));
    Assert.assertTrue(result.getContent().contains(outcome2));

  }
}
