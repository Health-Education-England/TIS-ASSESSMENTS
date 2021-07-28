package com.transformuk.hee.tis.assessment.service.service.impl.reference;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import com.transformuk.hee.tis.assessment.service.repository.reference.ReasonRepository;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@RunWith(MockitoJUnitRunner.class)
public class ReasonServiceImplTest {

  public static final String SEARCH_STRING = "search string";
  @InjectMocks
  private ReasonServiceImpl testObj;

  @Mock
  private ReasonRepository reasonRepositoryMock;

  @Mock
  private Pageable pageableMock;

  @Captor
  private ArgumentCaptor<Specification<Reason>> specificationsArgumentCaptor;

  @Test(expected = NullPointerException.class)
  public void advanceSearchShouldThrowExceptionWhenNoSearchString() {
    try {
      testObj.advancedSearch(null, pageableMock);
    } catch (Exception e) {
      verify(reasonRepositoryMock, never()).findAll(any(Specification.class), any(Pageable.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void advanceSearchShouldThrowExceptionWhenNoPageable() {
    try {
      testObj.advancedSearch(SEARCH_STRING, null);
    } catch (Exception e) {
      verify(reasonRepositoryMock, never()).findAll(any(Specification.class), any(Pageable.class));
      throw e;
    }
  }

  @Test
  public void advancedSearchShouldReturnPagedResultsUsingSpecs() {
    Reason reason1 = new Reason().id(1L).code("reason1").label("a reason 1");
    Reason reason2 = new Reason().id(2L).code("reason2").label("a reason 2");
    List<Reason> reasons = Lists.newArrayList(reason1, reason2);
    Page<Reason> pagedResult = new PageImpl<>(reasons);
    when(reasonRepositoryMock.findAll(any(Specification.class), eq(pageableMock)))
        .thenReturn(pagedResult);

    Page<Reason> result = testObj.advancedSearch(SEARCH_STRING, pageableMock);

    Assert.assertEquals(2, result.getTotalElements());
    Assert.assertEquals(reason1, result.getContent().get(0));
    Assert.assertEquals(reason2, result.getContent().get(1));
  }

}
