package com.transformuk.hee.tis.assessment.service.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentDetailMapperImpl;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentListMapperImpl;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentMapper;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentMapperImpl;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentOutcomeMapper;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentOutcomeMapperImpl;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentOutcomeReasonMapperImpl;
import com.transformuk.hee.tis.assessment.service.service.mapper.RevalidationMapperImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentServiceImplTest {

  private static final Long TRAINEE_ID = 11111L;
  private static final Long ASSESSMENT_ID = 1L;

  private AssessmentServiceImpl testObj;

  @Mock
  private AssessmentRepository assessmentRepositoryMock;

  @Captor
  private ArgumentCaptor<Example<Assessment>> assessmentCaptor;
  @Captor
  private ArgumentCaptor<Specifications<Assessment>> specificationsArgumentCaptor;

  @Mock
  private PermissionService permissionServiceMock;

  @Before
  public void setUpBefore() {
    AssessmentOutcomeMapper assessmentOutcomeMapper = new AssessmentOutcomeMapperImpl();
    ReflectionTestUtils.setField(assessmentOutcomeMapper, "assessmentOutcomeReasonMapper",
        new AssessmentOutcomeReasonMapperImpl());

    AssessmentMapper assessmentMapper = new AssessmentMapperImpl();
    ReflectionTestUtils
        .setField(assessmentMapper, "assessmentDetailMapper", new AssessmentDetailMapperImpl());
    ReflectionTestUtils
        .setField(assessmentMapper, "assessmentOutcomeMapper", assessmentOutcomeMapper);
    ReflectionTestUtils
        .setField(assessmentMapper, "revalidationMapper", new RevalidationMapperImpl());

    testObj = new AssessmentServiceImpl(assessmentRepositoryMock, assessmentMapper,
        new AssessmentListMapperImpl(), permissionServiceMock);
  }

  @Test
  public void saveShouldSaveAssessment() {
    AssessmentDTO assessmentDto = new AssessmentDTO();
    assessmentDto.setFirstName("firstName");
    assessmentDto.setLastName("lastName");
    assessmentDto.setDetail(new AssessmentDetailDTO());
    assessmentDto.setOutcome(new AssessmentOutcomeDTO());
    assessmentDto.setRevalidation(new RevalidationDTO());

    when(assessmentRepositoryMock.saveAndFlush(any(Assessment.class))).thenAnswer(i -> {
      Assessment arg = (Assessment) i.getArguments()[0];
      arg.setId(ASSESSMENT_ID);
      return arg;
    });

    AssessmentDTO result = testObj.save(assessmentDto);

    assertThat("Unexpected ID.", result.getId(), is(ASSESSMENT_ID));
    assertThat("Unexpected first name.", result.getFirstName(), is("firstName"));
    assertThat("Unexpected last name.", result.getLastName(), is("lastName"));
    assertThat("Unexpected detail.", result.getDetail(), nullValue());
    assertThat("Unexpected outcome.", result.getOutcome(), nullValue());
    assertThat("Unexpected revalidation.", result.getRevalidation(), nullValue());
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionWhenAssessmentIsNull() {
    try {
      testObj.save(null);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).save(any(Assessment.class));
      throw e;
    }
  }

  @Test
  public void findAllShouldSearchAsPerPageable() {
    Assessment assessment1 = new Assessment();
    assessment1.setId(1L);
    assessment1.setFirstName("firstName1");
    Assessment assessment2 = new Assessment();
    assessment2.setId(2L);
    assessment2.setFirstName("firstName2");
    Assessment assessment3 = new Assessment();
    assessment3.setId(3L);
    assessment3.setFirstName("firstName3");

    List<Assessment> assessments = Arrays.asList(assessment1, assessment2, assessment3);
    Pageable pageableMock = mock(Pageable.class);
    Page<Assessment> pagedAssessments = new PageImpl<>(assessments, pageableMock, 3);

    when(assessmentRepositoryMock.findAllBySoftDeletedDate(null, pageableMock))
        .thenReturn(pagedAssessments);

    Page<AssessmentListDTO> result = testObj.findAll(pageableMock);

    List<AssessmentListDTO> content = result.getContent();
    AssessmentListDTO assessmentListDto = content.get(0);
    assertThat("Unexpected ID.", assessmentListDto.getId(), is(1L));
    assertThat("Unexpected first name.", assessmentListDto.getFirstName(), is("firstName1"));

    assessmentListDto = content.get(1);
    assertThat("Unexpected ID.", assessmentListDto.getId(), is(2L));
    assertThat("Unexpected first name.", assessmentListDto.getFirstName(), is("firstName2"));

    assessmentListDto = content.get(2);
    assertThat("Unexpected ID.", assessmentListDto.getId(), is(3L));
    assertThat("Unexpected first name.", assessmentListDto.getFirstName(), is("firstName3"));
  }

  @Test(expected = NullPointerException.class)
  public void findAllPageableShouldThrowExceptionWhenPageableIsNull() {
    try {
      testObj.findAll(null);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findAll(any(Pageable.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void findOneShouldThrowExceptionWhenIdIsNull() {
    try {
      testObj.findOne(null);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findOne(anyLong());
      throw e;
    }
  }


  @Test
  public void findOneShouldReturnAssessmentDTO() {
    Assessment assessment = new Assessment();
    assessment.setId(ASSESSMENT_ID);
    assessment.setFirstName("firstName");
    assessment.setLastName("lastName");

    when(assessmentRepositoryMock.findOne(1L)).thenReturn(assessment);

    AssessmentDTO result = testObj.findOne(1L);

    assertThat("Unexpected ID.", result.getId(), is(ASSESSMENT_ID));
    assertThat("Unexpected first name.", result.getFirstName(), is("firstName"));
    assertThat("Unexpected last name.", result.getLastName(), is("lastName"));
  }

  @Test
  public void findTraineeAssessmentShouldReturnAssessment() {
    Assessment assessment = new Assessment();
    assessment.setId(ASSESSMENT_ID);
    assessment.setTraineeId(TRAINEE_ID);
    assessment.setFirstName("firstName");
    assessment.setLastName("lastName");

    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(assessment);

    Optional<Assessment> result = testObj.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);

    assertTrue(result.isPresent());
    assertEquals(assessment, result.get());

    Example<Assessment> capturedValue = assessmentCaptor.getValue();
    Assessment probedAssessment = capturedValue.getProbe();
    assertEquals(TRAINEE_ID, probedAssessment.getTraineeId());
    assertEquals(ASSESSMENT_ID, probedAssessment.getId());
  }

  @Test
  public void findTraineeAssessmentShouldReturnEmptyOptional() {
    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(null);

    Optional<Assessment> result = testObj.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);

    assertFalse(result.isPresent());

    Example<Assessment> capturedValue = assessmentCaptor.getValue();
    Assessment probedAssessment = capturedValue.getProbe();
    assertEquals(TRAINEE_ID, probedAssessment.getTraineeId());
    assertEquals(ASSESSMENT_ID, probedAssessment.getId());
  }

  @Test(expected = NullPointerException.class)
  public void findTraineeAssessmentShouldThrowExceptionWhenTraineeIdNull() {
    try {
      testObj.findTraineeAssessment(null, ASSESSMENT_ID);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findOne(any(Example.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void findTraineeAssessmentShouldThrowExceptionWhenAssessmentIdNull() {
    try {
      testObj.findTraineeAssessment(TRAINEE_ID, null);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findOne(any(Example.class));
      throw e;
    }
  }

  @Test
  public void findTraineeAssessmentDTOShouldReturnAssessment() {
    Assessment assessment = new Assessment();
    assessment.setId(ASSESSMENT_ID);
    assessment.setTraineeId(TRAINEE_ID);
    assessment.setFirstName("firstName");
    assessment.setLastName("lastName");

    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(assessment);

    Optional<AssessmentDTO> result = testObj.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID);

    assertTrue(result.isPresent());

    Example<Assessment> capturedValue = assessmentCaptor.getValue();
    Assessment probedAssessment = capturedValue.getProbe();
    assertEquals(TRAINEE_ID, probedAssessment.getTraineeId());
    assertEquals(ASSESSMENT_ID, probedAssessment.getId());

    AssessmentDTO assessmentDto = result.get();
    assertThat("Unexpected ID.", assessmentDto.getId(), is(ASSESSMENT_ID));
    assertThat("Unexpected trainee ID.", assessmentDto.getTraineeId(), is(TRAINEE_ID));
    assertThat("Unexpected first name.", assessmentDto.getFirstName(), is("firstName"));
    assertThat("Unexpected last name.", assessmentDto.getLastName(), is("lastName"));
  }

  @Test
  public void findTraineeAssessmentDTOShouldReturnEmptyAssessment() {
    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(null);

    Optional<AssessmentDTO> result = testObj.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID);

    assertFalse(result.isPresent());

    Example<Assessment> capturedValue = assessmentCaptor.getValue();
    Assessment probedAssessment = capturedValue.getProbe();
    assertEquals(TRAINEE_ID, probedAssessment.getTraineeId());
    assertEquals(ASSESSMENT_ID, probedAssessment.getId());
  }

  @Test(expected = NullPointerException.class)
  public void findTraineeAssessmentDTOShouldThrowExceptionWhenTraineeIdNull() {
    try {
      testObj.findTraineeAssessmentDTO(null, ASSESSMENT_ID);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findOne(any(Example.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void findTraineeAssessmentDTOShouldThrowExceptionWhenAssessmentIdNull() {
    try {
      testObj.findTraineeAssessmentDTO(TRAINEE_ID, null);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findOne(any(Example.class));
      throw e;
    }
  }

  @Test
  public void findAllForTraineeShouldReturnAllAssessmentsByPage() {
    Assessment assessment1 = new Assessment();
    assessment1.setId(1L);
    assessment1.setTraineeId(10L);
    assessment1.setFirstName("firstName1");
    Assessment assessment2 = new Assessment();
    assessment2.setId(2L);
    assessment2.setTraineeId(20L);
    assessment2.setFirstName("firstName2");
    Assessment assessment3 = new Assessment();
    assessment3.setId(3L);
    assessment3.setTraineeId(30L);
    assessment3.setFirstName("firstName3");

    Pageable pageableMock = mock(Pageable.class);
    List<Assessment> assessments = Arrays.asList(assessment1, assessment2, assessment3);
    Page<Assessment> pagedAssessments = new PageImpl<>(assessments, pageableMock, 3);

    when(assessmentRepositoryMock.findAll(assessmentCaptor.capture(), Matchers.eq(pageableMock)))
        .thenReturn(pagedAssessments);

    Page<AssessmentDTO> result = testObj.findForTrainee(TRAINEE_ID, pageableMock);

    List<AssessmentDTO> content = result.getContent();
    AssessmentDTO assessmentDto = content.get(0);
    assertThat("Unexpected ID.", assessmentDto.getId(), is(1L));
    assertThat("Unexpected trainee ID.", assessmentDto.getTraineeId(), is(10L));
    assertThat("Unexpected first name.", assessmentDto.getFirstName(), is("firstName1"));

    assessmentDto = content.get(1);
    assertThat("Unexpected ID.", assessmentDto.getId(), is(2L));
    assertThat("Unexpected trainee ID.", assessmentDto.getTraineeId(), is(20L));
    assertThat("Unexpected first name.", assessmentDto.getFirstName(), is("firstName2"));

    assessmentDto = content.get(2);
    assertThat("Unexpected ID.", assessmentDto.getId(), is(3L));
    assertThat("Unexpected trainee ID.", assessmentDto.getTraineeId(), is(30L));
    assertThat("Unexpected first name.", assessmentDto.getFirstName(), is("firstName3"));
  }


  @Test(expected = NullPointerException.class)
  public void findAllForTraineeShouldThrowExceptionWhenTraineeIdIsNull() {
    try {
      Pageable pageableMock = mock(Pageable.class);
      testObj.findForTrainee(null, pageableMock);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findAll(any(Example.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void findAllForTraineeShouldThrowExceptionWhenPageableIsNull() {
    try {
      testObj.findForTrainee(TRAINEE_ID, null);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findOne(any(Example.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void deleteTraineeAssessementShouldThrowExceptionWhenTraineeIdIsNull() {
    testObj.deleteTraineeAssessment(ASSESSMENT_ID, null);
    verify(assessmentRepositoryMock, never()).delete(anyLong());
  }

  @Test(expected = NullPointerException.class)
  public void deleteTraineeAssessementShouldThrowExceptionWhenAssessmentIdIsNull() {
    testObj.deleteTraineeAssessment(null, TRAINEE_ID);
    verify(assessmentRepositoryMock, never()).delete(anyLong());
  }

  @Test
  public void deleteTraineeAssessementShouldReturnFalseWhenAssessmentCannotBeFound() {
    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(null);

    boolean result = testObj.deleteTraineeAssessment(ASSESSMENT_ID, TRAINEE_ID);

    verify(assessmentRepositoryMock, never()).delete(anyLong());
    Assert.assertFalse(result);

    Example<Assessment> capturedExample = assessmentCaptor.getValue();
    Assessment probe = capturedExample.getProbe();
    Assert.assertEquals(ASSESSMENT_ID, probe.getId());
    Assert.assertEquals(TRAINEE_ID, probe.getTraineeId());
  }

  @Test
  public void deleteTraineeAssessementShouldReturnTrueWhenAssessmentIsDeleted() {
    Assessment assessment = new Assessment();
    assessment.setId(ASSESSMENT_ID);
    assessment.setTraineeId(TRAINEE_ID);

    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(assessment);

    boolean result = testObj.deleteTraineeAssessment(ASSESSMENT_ID, TRAINEE_ID);

    verify(assessmentRepositoryMock).delete(assessment);
    Assert.assertTrue(result);

    Example<Assessment> capturedExample = assessmentCaptor.getValue();
    Assessment probe = capturedExample.getProbe();
    Assert.assertEquals(ASSESSMENT_ID, probe.getId());
    Assert.assertEquals(TRAINEE_ID, probe.getTraineeId());
  }

  @Test
  public void findAllForTraineeShouldReturnAllAssessmentsForATrainee() {
    Assessment assessment1 = new Assessment();
    assessment1.setId(1L);
    assessment1.setTraineeId(TRAINEE_ID);
    assessment1.setFirstName("firstName1");
    Assessment assessment2 = new Assessment();
    assessment2.setId(2L);
    assessment2.setTraineeId(TRAINEE_ID);
    assessment2.setFirstName("firstName2");

    List<Assessment> traineeAssessments = Arrays.asList(assessment1, assessment2);

    Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "reviewDate"));
    when(assessmentRepositoryMock.findAll(specificationsArgumentCaptor.capture(), eq(sort)))
        .thenReturn(traineeAssessments);
    when(permissionServiceMock.isProgrammeObserver()).thenReturn(false);

    List<AssessmentDTO> result = testObj.findAllForTrainee(TRAINEE_ID, sort);

    AssessmentDTO assessmentDto = result.get(0);
    assertThat("Unexpected ID.", assessmentDto.getId(), is(1L));
    assertThat("Unexpected trainee ID.", assessmentDto.getTraineeId(), is(TRAINEE_ID));
    assertThat("Unexpected first name.", assessmentDto.getFirstName(), is("firstName1"));

    assessmentDto = result.get(1);
    assertThat("Unexpected ID.", assessmentDto.getId(), is(2L));
    assertThat("Unexpected trainee ID.", assessmentDto.getTraineeId(), is(TRAINEE_ID));
    assertThat("Unexpected first name.", assessmentDto.getFirstName(), is("firstName2"));
  }


  @Test
  public void advancedSearchShouldSearchUsingSpecifications() {
    Assessment assessment1 = new Assessment();
    assessment1.setId(1L);
    assessment1.setTraineeId(TRAINEE_ID);
    assessment1.setFirstName("firstName1");
    Assessment assessment2 = new Assessment();
    assessment2.setId(2L);
    assessment2.setTraineeId(TRAINEE_ID);
    assessment2.setFirstName("firstName2");

    List<ColumnFilter> columnFilters = Lists.newArrayList();
    Pageable pageable = new PageRequest(0, 20);
    String searchQuery = "search query";

    List<Assessment> foundAssessments = Lists.newArrayList(assessment1, assessment2);
    Page<Assessment> pagedFoundAssessments = new PageImpl<>(foundAssessments);

    when(assessmentRepositoryMock.findAll(specificationsArgumentCaptor.capture(), eq(pageable)))
        .thenReturn(pagedFoundAssessments);
    when(permissionServiceMock.isProgrammeObserver()).thenReturn(false);

    Page<AssessmentListDTO> result = testObj.advancedSearch(searchQuery, columnFilters, pageable);

    assertThat("Unexpected result count.", result.getTotalElements(), is(2L));

    List<AssessmentListDTO> content = result.getContent();
    AssessmentListDTO assessmentListDto = content.get(0);
    assertThat("Unexpected ID.", assessmentListDto.getId(), is(1L));
    assertThat("Unexpected trainee ID.", assessmentListDto.getTraineeId(), is(TRAINEE_ID));
    assertThat("Unexpected first name.", assessmentListDto.getFirstName(), is("firstName1"));

    assessmentListDto = content.get(1);
    assertThat("Unexpected ID.", assessmentListDto.getId(), is(2L));
    assertThat("Unexpected trainee ID.", assessmentListDto.getTraineeId(), is(TRAINEE_ID));
    assertThat("Unexpected first name.", assessmentListDto.getFirstName(), is("firstName2"));
  }
}
