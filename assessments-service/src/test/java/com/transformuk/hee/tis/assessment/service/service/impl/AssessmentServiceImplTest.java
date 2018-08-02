package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentListMapper;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentMapper;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specifications;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentServiceImplTest {

  private static final Long TRAINEE_ID = 11111L;
  private static final Long ASSESSMENT_ID = 1L;
  @InjectMocks
  private AssessmentServiceImpl testObj;
  @Mock
  private AssessmentRepository assessmentRepositoryMock;
  @Mock
  private AssessmentMapper assessmentMapperMock;
  @Mock
  private AssessmentListMapper assessmentListMapperMock;

  @Mock
  private AssessmentDTO assessmentDTOMock, savedAssessmentDTOMock, assessmentDTOMock1, assessmentDTOMock2, assessmentDTOMock3;
  @Mock
  private Assessment assessmentMock, assessmentMock1, assessmentMock2, assessmentMock3;
  @Mock
  private AssessmentListDTO assessmentListDTOMock1, assessmentListDTOMock2, assessmentListDTOMock3;

  @Captor
  private ArgumentCaptor<Example<Assessment>> assessmentCaptor;
  @Captor
  private ArgumentCaptor<Specifications<Assessment>> specificationsArgumentCaptor;

  @Test
  public void saveShouldSaveAssessment() {
    when(assessmentMapperMock.toEntity(assessmentDTOMock)).thenReturn(assessmentMock);
    when(assessmentRepositoryMock.saveAndFlush(assessmentMock)).thenReturn(assessmentMock);
    when(assessmentMapperMock.toDto(assessmentMock)).thenReturn(savedAssessmentDTOMock);

    AssessmentDTO result = testObj.save(assessmentDTOMock);

    Assert.assertEquals(savedAssessmentDTOMock, result);

    verify(assessmentDTOMock).setDetail(null);
    verify(assessmentDTOMock).setOutcome(null);
    verify(assessmentDTOMock).setRevalidation(null);

    verify(assessmentMapperMock).toEntity(assessmentDTOMock);
    verify(assessmentRepositoryMock).saveAndFlush(assessmentMock);
    verify(assessmentMapperMock).toDto(assessmentMock);
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionWhenAssessmentIsNull() {
    try {
      testObj.save(null);
    } catch (Exception e) {
      verify(assessmentMapperMock, never()).toEntity(any(AssessmentDTO.class));
      verify(assessmentRepositoryMock, never()).save(any(Assessment.class));
      verify(assessmentMapperMock, never()).toDto(any(Assessment.class));
      throw e;
    }
  }

  @Test
  public void findAllShouldSearchAsPerPageable() {
    Pageable pageableMock = mock(Pageable.class);
    List<Assessment> assessments = Lists.newArrayList(assessmentMock1, assessmentMock2, assessmentMock3);
    Page<Assessment> pagedAssessments = new PageImpl<>(assessments, pageableMock, 3);

    when(assessmentRepositoryMock.findAll(pageableMock)).thenReturn(pagedAssessments);
    when(assessmentListMapperMock.toDto(assessmentMock1)).thenReturn(assessmentListDTOMock1);
    when(assessmentListMapperMock.toDto(assessmentMock2)).thenReturn(assessmentListDTOMock2);
    when(assessmentListMapperMock.toDto(assessmentMock3)).thenReturn(assessmentListDTOMock3);

    Page<AssessmentListDTO> result = testObj.findAll(pageableMock);

    List<AssessmentListDTO> content = result.getContent();
    Assert.assertEquals(assessmentListDTOMock1, content.get(0));
    Assert.assertEquals(assessmentListDTOMock2, content.get(1));
    Assert.assertEquals(assessmentListDTOMock3, content.get(2));
  }

  @Test(expected = NullPointerException.class)
  public void findAllPageableShouldThrowExceptionWhenPageableIsNull() {
    try {
      testObj.findAll(null);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findAll(any(Pageable.class));
      verify(assessmentListMapperMock, never()).toDto(any(Assessment.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void findOneShouldThrowExceptionWhenIdIsNull() {
    try {
      testObj.findOne(null);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findOne(anyLong());
      verify(assessmentListMapperMock, never()).toDto(any(Assessment.class));
      throw e;
    }
  }


  @Test
  public void findOneShouldReturnAssessmentDTO() {
    when(assessmentRepositoryMock.findOne(1L)).thenReturn(assessmentMock);
    when(assessmentMapperMock.toDto(assessmentMock)).thenReturn(assessmentDTOMock);

    AssessmentDTO result = testObj.findOne(1L);

    Assert.assertEquals(assessmentDTOMock, result);
  }

  @Test
  public void findTraineeAssessmentShouldReturnAssessment() {
    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(assessmentMock);

    Optional<Assessment> result = testObj.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);

    assertTrue(result.isPresent());
    assertEquals(assessmentMock, result.get());

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
    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(assessmentMock);
    when(assessmentMapperMock.toDto(assessmentMock)).thenReturn(assessmentDTOMock);

    Optional<AssessmentDTO> result = testObj.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID);

    assertTrue(result.isPresent());
    assertEquals(assessmentDTOMock, result.get());

    Example<Assessment> capturedValue = assessmentCaptor.getValue();
    Assessment probedAssessment = capturedValue.getProbe();
    assertEquals(TRAINEE_ID, probedAssessment.getTraineeId());
    assertEquals(ASSESSMENT_ID, probedAssessment.getId());
  }

  @Test
  public void findTraineeAssessmentDTOShouldReturnEmptyAssessment() {
    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(null);
    when(assessmentMapperMock.toDto((Assessment) null)).thenReturn(null);

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
      verify(assessmentMapperMock, never()).toDto(any(Assessment.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void findTraineeAssessmentDTOShouldThrowExceptionWhenAssessmentIdNull() {
    try {
      testObj.findTraineeAssessmentDTO(TRAINEE_ID, null);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findOne(any(Example.class));
      verify(assessmentMapperMock, never()).toDto(any(Assessment.class));
      throw e;
    }
  }

  @Test
  public void findAllForTraineeShouldReturnAllAssessmentsByPage() {
    Pageable pageableMock = mock(Pageable.class);
    List<Assessment> assessments = Lists.newArrayList(assessmentMock1, assessmentMock2, assessmentMock3);
    Page<Assessment> pagedAssessments = new PageImpl<>(assessments, pageableMock, 3);

    when(assessmentRepositoryMock.findAll(assessmentCaptor.capture(), Matchers.eq(pageableMock))).thenReturn(pagedAssessments);
    when(assessmentMapperMock.toDto(assessmentMock1)).thenReturn(assessmentDTOMock1);
    when(assessmentMapperMock.toDto(assessmentMock2)).thenReturn(assessmentDTOMock2);
    when(assessmentMapperMock.toDto(assessmentMock3)).thenReturn(assessmentDTOMock3);

    Page<AssessmentDTO> result = testObj.findForTrainee(TRAINEE_ID, pageableMock);

    List<AssessmentDTO> content = result.getContent();
    Assert.assertEquals(assessmentDTOMock1, content.get(0));
    Assert.assertEquals(assessmentDTOMock2, content.get(1));
    Assert.assertEquals(assessmentDTOMock3, content.get(2));
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
    when(assessmentRepositoryMock.findOne(assessmentCaptor.capture())).thenReturn(assessmentMock);

    boolean result = testObj.deleteTraineeAssessment(ASSESSMENT_ID, TRAINEE_ID);

    verify(assessmentRepositoryMock).delete(assessmentMock);
    Assert.assertTrue(result);

    Example<Assessment> capturedExample = assessmentCaptor.getValue();
    Assessment probe = capturedExample.getProbe();
    Assert.assertEquals(ASSESSMENT_ID, probe.getId());
    Assert.assertEquals(TRAINEE_ID, probe.getTraineeId());
  }

  @Test
  public void findAllForTraineeShouldReturnAllAssessmentsForATrainee() {

    List<Assessment> traineeAssessments = Lists.newArrayList(assessmentMock1, assessmentMock2);
    List<AssessmentDTO> traineeAssessmentDtos = Lists.newArrayList(assessmentDTOMock1, assessmentDTOMock2);

    Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "reviewDate"));
    when(assessmentRepositoryMock.findAll(assessmentCaptor.capture(), eq(sort))).thenReturn(traineeAssessments);
    when(assessmentMapperMock.toDto(traineeAssessments)).thenReturn(traineeAssessmentDtos);

    List<AssessmentDTO> result = testObj.findAllForTrainee(TRAINEE_ID, sort);

    Assert.assertEquals(2, result.size());
    Assert.assertTrue(result.contains(assessmentDTOMock1));
    Assert.assertTrue(result.contains(assessmentDTOMock2));

    Example<Assessment> captorValue = assessmentCaptor.getValue();
    Assessment assessment = captorValue.getProbe();
    Assert.assertEquals(TRAINEE_ID, assessment.getTraineeId());
  }

  @Test(expected = IllegalStateException.class)
  public void advancedSearchShouldThrowExceptionIfBothSearchStringAndFiltersIsEmpty() {
    List<ColumnFilter> columnFilters = Lists.newArrayList();
    Pageable pageable = new PageRequest(0, 20);
    String searchQuery = StringUtils.EMPTY;

    try {
      testObj.advancedSearch(searchQuery, columnFilters, pageable);
    } catch (Exception e) {
      verify(assessmentRepositoryMock, never()).findAll(any(Specifications.class), any(Pageable.class));
      verify(assessmentListMapperMock, never()).toDto(any(Assessment.class));
      throw e;
    }

  }

  @Test
  public void advancedSearchShouldSearchUsingSpecifications() {
    List<ColumnFilter> columnFilters = Lists.newArrayList();
    Pageable pageable = new PageRequest(0, 20);
    String searchQuery = "search query";

    List<Assessment> foundAssessments = Lists.newArrayList(assessmentMock1, assessmentMock2);
    Page<Assessment> pagedFoundAssessments = new PageImpl<>(foundAssessments);

    when(assessmentRepositoryMock.findAll(specificationsArgumentCaptor.capture(), eq(pageable))).thenReturn(pagedFoundAssessments);
    when(assessmentListMapperMock.toDto(assessmentMock1)).thenReturn(assessmentListDTOMock1);
    when(assessmentListMapperMock.toDto(assessmentMock2)).thenReturn(assessmentListDTOMock2);

    Page<AssessmentListDTO> result = testObj.advancedSearch(searchQuery, columnFilters, pageable);

    Assert.assertEquals(2, result.getTotalElements());
    Assert.assertTrue(result.getContent().contains(assessmentListDTOMock1));
    Assert.assertTrue(result.getContent().contains(assessmentListDTOMock2));
  }
}
