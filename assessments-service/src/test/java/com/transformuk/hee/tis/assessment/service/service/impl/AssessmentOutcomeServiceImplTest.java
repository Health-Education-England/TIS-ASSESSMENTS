package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcome;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentOutcomeRepository;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentOutcomeMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentOutcomeServiceImplTest {

  private static final Long ASSESSMENT_ID = 1L;

  @InjectMocks
  @Spy
  private AssessmentOutcomeServiceImpl testObj;
  @Mock
  private AssessmentOutcomeRepository assessmentOutcomeRepositoryMock;
  @Mock
  private AssessmentOutcomeMapper assessmentOutcomeMapperMock;
  @Mock
  private Assessment assessmentMock;
  @Mock
  private AssessmentOutcome assessmentOutcomeMock, savedAssessmentOutcomeMock;
  @Mock
  private AssessmentOutcomeDTO assessmentOutcomeDTOMock, resultAssessmentOutcomeDTOMock;

  @Before
  public void setup() {
    when(assessmentMock.getId()).thenReturn(ASSESSMENT_ID);
    when(assessmentOutcomeDTOMock.getId()).thenReturn(ASSESSMENT_ID);
    when(resultAssessmentOutcomeDTOMock.getId()).thenReturn(ASSESSMENT_ID);
    when(savedAssessmentOutcomeMock.getId()).thenReturn(ASSESSMENT_ID);
  }

  @Test
  public void saveShouldSaveOutcome() {
    when(assessmentOutcomeMapperMock.toEntity(assessmentOutcomeDTOMock)).thenReturn(assessmentOutcomeMock);
    when(assessmentOutcomeRepositoryMock.saveAndFlush(assessmentOutcomeMock)).thenReturn(savedAssessmentOutcomeMock);
    when(assessmentOutcomeMapperMock.toDto(savedAssessmentOutcomeMock)).thenReturn(resultAssessmentOutcomeDTOMock);

    AssessmentOutcomeDTO result = testObj.save(assessmentMock, assessmentOutcomeDTOMock);

    Assert.assertEquals(ASSESSMENT_ID, result.getId());
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionWhenAssessmentIsNull() {
    try {
      testObj.save(null, assessmentOutcomeDTOMock);
    } catch (Exception e) {
      verify(assessmentOutcomeMapperMock, never()).toEntity(any(AssessmentOutcomeDTO.class));
      verify(assessmentOutcomeRepositoryMock, never()).save(any(AssessmentOutcome.class));
      verify(assessmentOutcomeMapperMock, never()).toDto(any(AssessmentOutcome.class));
      throw e;
    }
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionWhenOutcomeIsNull() {
    try {
      testObj.save(assessmentMock, null);
    } catch (Exception e) {
      verify(assessmentOutcomeMapperMock, never()).toEntity(any(AssessmentOutcomeDTO.class));
      verify(assessmentOutcomeRepositoryMock, never()).save(any(AssessmentOutcome.class));
      verify(assessmentOutcomeMapperMock, never()).toDto(any(AssessmentOutcome.class));
      throw e;
    }
  }

  @Test(expected = IllegalStateException.class)
  public void saveShouldThrowExceptionOutcomeIdDoesNotMatchAssessment() {
    when(assessmentOutcomeDTOMock.getId()).thenReturn(3L);
    try {
      testObj.save(assessmentMock, assessmentOutcomeDTOMock);
    } catch (Exception e) {
      verify(assessmentOutcomeMapperMock, never()).toEntity(any(AssessmentOutcomeDTO.class));
      verify(assessmentOutcomeRepositoryMock, never()).save(any(AssessmentOutcome.class));
      verify(assessmentOutcomeMapperMock, never()).toDto(any(AssessmentOutcome.class));
      throw e;
    }
  }

  @Test(expected = IllegalStateException.class)
  public void saveShouldThrowExceptionWhenOutcomeIsLegacy() {
    AssessmentOutcome assessmentOutcome = new AssessmentOutcome().legacy(true);
    when(assessmentOutcomeRepositoryMock.findOne(ASSESSMENT_ID)).thenReturn(assessmentOutcome);
    try {
      testObj.save(assessmentMock, assessmentOutcomeDTOMock);
    } catch (Exception e) {
      verify(assessmentOutcomeMapperMock, never()).toEntity(any(AssessmentOutcomeDTO.class));
      verify(assessmentOutcomeRepositoryMock, never()).save(any(AssessmentOutcome.class));
      verify(assessmentOutcomeMapperMock, never()).toDto(any(AssessmentOutcome.class));
      throw e;
    }
  }

  @Test
  public void createShouldCreateNewOutcome() {
    Assessment assessment = new Assessment();
    assessment.setId(ASSESSMENT_ID);
    AssessmentOutcomeDTO assessmentOutcomeDTO = new AssessmentOutcomeDTO();

    when(assessmentOutcomeMapperMock.toEntity(assessmentOutcomeDTO)).thenReturn(assessmentOutcomeMock);
    when(assessmentOutcomeRepositoryMock.saveAndFlush(assessmentOutcomeMock)).thenReturn(savedAssessmentOutcomeMock);
    when(assessmentOutcomeMapperMock.toDto(savedAssessmentOutcomeMock)).thenReturn(assessmentOutcomeDTOMock);
    when(assessmentOutcomeDTOMock.getId()).thenReturn(ASSESSMENT_ID);

    AssessmentOutcomeDTO result = testObj.create(assessment, assessmentOutcomeDTO);

    verify(testObj).save(assessment, assessmentOutcomeDTO);
    Assert.assertEquals(ASSESSMENT_ID, result.getId());
  }

  @Test(expected = NullPointerException.class)
  public void createShouldThrowExceptionWhenAssessmentIsNull() {
    testObj.create(null, assessmentOutcomeDTOMock);
  }

  @Test(expected = NullPointerException.class)
  public void createShouldThrowExceptionWhenOutcomeIsNull() {
    testObj.save(assessmentMock, null);
  }

  @Test(expected = IllegalStateException.class)
  public void createShouldThrowExceptionWhenOutcomeHasAnId() {
    when(assessmentOutcomeDTOMock.getId()).thenReturn(3L);
    testObj.save(assessmentMock, assessmentOutcomeDTOMock);
  }

  @Test(expected = NullPointerException.class)
  public void findOneShouldThrowExceptionIfIdIsNull() {
    testObj.findOne(null);
  }

  @Test
  public void findOneShouldReturnOutcomeWithId() {
    when(assessmentOutcomeRepositoryMock.findOne(ASSESSMENT_ID)).thenReturn(assessmentOutcomeMock);
    when(assessmentOutcomeMapperMock.toDto(assessmentOutcomeMock)).thenReturn(assessmentOutcomeDTOMock);
    when(assessmentOutcomeDTOMock.getId()).thenReturn(ASSESSMENT_ID);

    Optional<AssessmentOutcomeDTO> result = testObj.findOne(ASSESSMENT_ID);

    Assert.assertTrue(result.isPresent());
    Assert.assertEquals(ASSESSMENT_ID, result.get().getId());
  }


  @Test
  public void findOneShouldReturnEmptyOptionalWhenOutcomeDoesntExist() {
    when(assessmentOutcomeRepositoryMock.findOne(ASSESSMENT_ID)).thenReturn(null);
    when(assessmentOutcomeMapperMock.toDto((AssessmentOutcome) null)).thenReturn(null);

    Optional<AssessmentOutcomeDTO> result = testObj.findOne(ASSESSMENT_ID);

    Assert.assertFalse(result.isPresent());
  }
}
