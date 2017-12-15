package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.Revalidation;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.repository.RevalidationRepository;
import com.transformuk.hee.tis.assessment.service.service.mapper.RevalidationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RevalidationServiceImplTest {

  private static final Long ASSESSMENT_ID = 1L;
  private static final String TRAINEE_ID = "TRAINEE_ID";

  @InjectMocks
  private RevalidationServiceImpl testObj;
  @Mock
  private AssessmentRepository assessmentRepositoryMock;
  @Mock
  private RevalidationMapper revalidationMapperMock;
  @Mock
  private RevalidationRepository revalidationRepositoryMock;
  @Mock
  private Assessment assessmentMock;
  @Mock
  private Revalidation revalidationMock;
  @Mock
  private RevalidationDTO revalidationDTOMock;

  @Captor
  private ArgumentCaptor<Example<Assessment>> exampleArgumentCaptor;

  @Test
  public void findAssessmentRevalidationByShouldReturnRevalidation() {
    when(assessmentRepositoryMock.findOne(exampleArgumentCaptor.capture())).thenReturn(assessmentMock);
    when(assessmentMock.getRevalidation()).thenReturn(revalidationMock);
    when(revalidationMapperMock.toDto(revalidationMock)).thenReturn(revalidationDTOMock);

    Optional<RevalidationDTO> result = testObj.findAssessmentRevalidationBy(TRAINEE_ID, ASSESSMENT_ID);

    assertTrue(result.isPresent());
    RevalidationDTO revalidationDTO = result.get();
    assertEquals(revalidationDTOMock, revalidationDTO);

    Example<Assessment> capturedValue = exampleArgumentCaptor.getValue();
    Assessment probedAssessment = capturedValue.getProbe();
    assertEquals(TRAINEE_ID, probedAssessment.getTraineeId());
    assertEquals(ASSESSMENT_ID, probedAssessment.getId());
  }

  @Test
  public void findAssessmentRevalidationByShouldReturnEmptyOptionalWhenNoRevalidationFound() {
    when(assessmentRepositoryMock.findOne(exampleArgumentCaptor.capture())).thenReturn(assessmentMock);
    when(assessmentMock.getRevalidation()).thenReturn(null);
    when(revalidationMapperMock.toDto((Revalidation) null)).thenReturn(null);

    Optional<RevalidationDTO> result = testObj.findAssessmentRevalidationBy(TRAINEE_ID, ASSESSMENT_ID);

    assertFalse(result.isPresent());

    Example<Assessment> capturedValue = exampleArgumentCaptor.getValue();
    Assessment probedAssessment = capturedValue.getProbe();
    assertEquals(TRAINEE_ID, probedAssessment.getTraineeId());
    assertEquals(ASSESSMENT_ID, probedAssessment.getId());
  }

  @Test(expected = NullPointerException.class)
  public void findAssessmentRevalidationByShouldThrowExceptionWhenTraineeIdIsNull() {
    testObj.findAssessmentRevalidationBy(null, ASSESSMENT_ID);
  }

  @Test(expected = NullPointerException.class)
  public void findAssessmentRevalidationByShouldThrowExceptionWhenAssessmentIsNull() {
    testObj.findAssessmentRevalidationBy(TRAINEE_ID, null);
  }

  @Test
  public void saveShouldSaveRevalidation() {
    when(revalidationMapperMock.toEntity(revalidationDTOMock)).thenReturn(revalidationMock);
    when(revalidationRepositoryMock.save(revalidationMock)).thenReturn(revalidationMock);
    when(revalidationMapperMock.toDto(revalidationMock)).thenReturn(revalidationDTOMock);

    RevalidationDTO result = testObj.save(assessmentMock, revalidationDTOMock);

    assertEquals(revalidationDTOMock, result);

    verify(revalidationMapperMock).toEntity(revalidationDTOMock);
    verify(revalidationRepositoryMock).save(revalidationMock);
    verify(revalidationMapperMock).toDto(revalidationMock);
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionWhenAssessmentIsNull() {
    testObj.save(null, revalidationDTOMock);
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionWhenRevalidationIsNull() {
    testObj.save(assessmentMock, null);
  }

  @Test(expected = IllegalStateException.class)
  public void saveShouldThrowExceptionWhenRevalidationIdDoesNotMatchAssessmentId() {
    when(assessmentMock.getId()).thenReturn(ASSESSMENT_ID);
    when(revalidationDTOMock.getId()).thenReturn(null);
    testObj.save(assessmentMock, revalidationDTOMock);
  }

  @Test(expected = IllegalStateException.class)
  public void createShouldThrowExceptionIfRevalidationHasId() {
    when(assessmentMock.getId()).thenReturn(ASSESSMENT_ID);
    when(revalidationDTOMock.getId()).thenReturn(ASSESSMENT_ID);

    try {
      testObj.create(assessmentMock, revalidationDTOMock);
    } catch (Exception e) {
      verify(revalidationMapperMock, never()).toEntity(any(RevalidationDTO.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void createShouldThrowExceptionWhenAssessmentIsNull() {
    try {
      testObj.create(null, revalidationDTOMock);
    } catch (Exception e) {
      verify(revalidationDTOMock, never()).setId(anyLong());
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void createShouldThrowExceptionWhenRevalidationIsNull() {
    try {
      testObj.create(new Assessment(), null);
    } catch (Exception e) {
      verify(revalidationMapperMock, never()).toEntity(any(RevalidationDTO.class));
      throw e;
    }
  }

  @Test
  public void createShouldCreateRevalidationWithSameIdAsAssessment() {
    Assessment assessment = new Assessment().id(ASSESSMENT_ID);
    RevalidationDTO revalidationDTO = new RevalidationDTO();

    when(revalidationMock.getId()).thenReturn(ASSESSMENT_ID);
    when(revalidationDTOMock.getId()).thenReturn(ASSESSMENT_ID);
    when(revalidationMapperMock.toEntity(revalidationDTO)).thenReturn(revalidationMock);
    when(revalidationRepositoryMock.save(revalidationMock)).thenReturn(revalidationMock);
    when(revalidationMapperMock.toDto(revalidationMock)).thenReturn(revalidationDTOMock);

    RevalidationDTO result = testObj.create(assessment, revalidationDTO);

    assertEquals(revalidationDTOMock, result);
    assertEquals(ASSESSMENT_ID, result.getId());

    verify(revalidationMapperMock).toEntity(revalidationDTO);
    verify(revalidationRepositoryMock).save(revalidationMock);
    verify(revalidationMapperMock).toDto(revalidationMock);
  }


  @Test(expected = NullPointerException.class)
  public void findOneShouldThrowExceptionIfIdIsNull() {
    testObj.findOne(null);
  }

  @Test
  public void findOneShouldReturnOutcomeWithId() {
    when(revalidationRepositoryMock.findOne(ASSESSMENT_ID)).thenReturn(revalidationMock);
    when(revalidationMapperMock.toDto(revalidationMock)).thenReturn(revalidationDTOMock);
    when(revalidationDTOMock.getId()).thenReturn(ASSESSMENT_ID);

    Optional<RevalidationDTO> result = testObj.findOne(ASSESSMENT_ID);

    assertTrue(result.isPresent());
    assertEquals(ASSESSMENT_ID, result.get().getId());
  }


  @Test
  public void findOneShouldReturnEmptyOptionalWhenOutcomeDoesntExist() {
    when(revalidationRepositoryMock.findOne(ASSESSMENT_ID)).thenReturn(null);
    when(revalidationMapperMock.toDto((Revalidation) null)).thenReturn(null);

    Optional<RevalidationDTO> result = testObj.findOne(ASSESSMENT_ID);

    assertFalse(result.isPresent());
  }
}
