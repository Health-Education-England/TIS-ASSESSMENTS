package com.transformuk.hee.tis.assessment.service.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.AssessmentDetail;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentDetailRepository;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentDetailMapper;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentDetailServiceImplTest {


  private static final Long ASSESSMENT_ID = 1L;
  private static final Long TRAINEE_ID = 11111L;

  @InjectMocks
  @Spy
  private AssessmentDetailServiceImpl testObj;
  @Mock
  private AssessmentRepository assessmentRepositoryMock;
  @Mock
  private AssessmentDetailMapper assessmentDetailMapperMock;
  @Mock
  private AssessmentDetailRepository assessmentDetailRepositoryMock;
  @Mock
  private Assessment assessmentMock;
  @Mock
  private AssessmentDetail assessmentDetailMock;
  @Mock
  private AssessmentDetailDTO assessmentDetailDTOMock;

  @Captor
  private ArgumentCaptor<Example<Assessment>> assessmentExampleCaptor;


  @Before
  public void setup() {
    when(assessmentMock.getDetail()).thenReturn(assessmentDetailMock);
  }

  @Test(expected = NullPointerException.class)
  public void findAssessmentDetailByShouldThrowExceptionWhenTraineeIdIsNull() {
    testObj.findAssessmentDetailBy(null, ASSESSMENT_ID);
  }

  @Test(expected = NullPointerException.class)
  public void findAssessmentDetailByShouldThrowExceptionWhenAssessmentIdIsNull() {
    testObj.findAssessmentDetailBy(TRAINEE_ID, null);
  }

  @Test
  public void findAssessmentDetailByShouldReturnPopulatedOptionalDataAvailable() {
    when(assessmentRepositoryMock.findOne(assessmentExampleCaptor.capture())).thenReturn(
        Optional.of(assessmentMock));
    when(assessmentDetailMapperMock.toDto(assessmentDetailMock))
        .thenReturn(assessmentDetailDTOMock);

    Optional<AssessmentDetailDTO> result = testObj
        .findAssessmentDetailBy(TRAINEE_ID, ASSESSMENT_ID);

    Assert.assertTrue(result.isPresent());
    AssessmentDetailDTO assessmentDetailDTO = result.get();
    Assert.assertEquals(assessmentDetailDTOMock, assessmentDetailDTO);

    Example<Assessment> capturedExample = assessmentExampleCaptor.getValue();
    Assessment capturedAssessment = capturedExample.getProbe();
    Assert.assertEquals(TRAINEE_ID, capturedAssessment.getTraineeId());
    Assert.assertEquals(ASSESSMENT_ID, capturedAssessment.getId());
  }


  @Test
  public void findAssessmentDetailByShouldReturnEmptyOptionalNoData() {
    when(assessmentRepositoryMock.findOne(assessmentExampleCaptor.capture())).thenReturn(null);

    Optional<AssessmentDetailDTO> result = testObj
        .findAssessmentDetailBy(TRAINEE_ID, ASSESSMENT_ID);

    verify(assessmentDetailMapperMock, never()).toDto(any(AssessmentDetail.class));

    Assert.assertFalse(result.isPresent());

    Example<Assessment> capturedExample = assessmentExampleCaptor.getValue();
    Assessment capturedAssessment = capturedExample.getProbe();
    Assert.assertEquals(TRAINEE_ID, capturedAssessment.getTraineeId());
    Assert.assertEquals(ASSESSMENT_ID, capturedAssessment.getId());
  }

  @Test
  public void saveShouldSaveDetailAgainstAnAssessment() {
    when(assessmentDetailMapperMock.toEntity(assessmentDetailDTOMock))
        .thenReturn(assessmentDetailMock);
    when(assessmentDetailRepositoryMock.saveAndFlush(assessmentDetailMock))
        .thenReturn(assessmentDetailMock);
    when(assessmentDetailMapperMock.toDto(assessmentDetailMock))
        .thenReturn(assessmentDetailDTOMock);

    AssessmentDetailDTO result = testObj.save(assessmentMock, assessmentDetailDTOMock);

    verify(assessmentDetailMapperMock).toDto(assessmentDetailMock);

    Assert.assertEquals(assessmentDetailDTOMock, result);
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionsWhenAssessmentNull() {
    testObj.save(null, new AssessmentDetailDTO());
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionsWhenDetailNull() {
    testObj.save(new Assessment(), null);
  }

  @Test(expected = IllegalStateException.class)
  public void saveShouldThrowExceptionsWhenAssessmentIdDoesntMatchDetail() {
    when(assessmentMock.getId()).thenReturn(ASSESSMENT_ID);
    when(assessmentDetailDTOMock.getId()).thenReturn(2L);

    testObj.save(assessmentMock, assessmentDetailDTOMock);
  }


  @Test
  public void createShouldCreateNewAssessmentDetail() {
    Assessment assessment = new Assessment().id(ASSESSMENT_ID);
    AssessmentDetailDTO assessmentDetailDTO = new AssessmentDetailDTO();
    when(assessmentDetailMapperMock.toEntity(assessmentDetailDTO)).thenReturn(assessmentDetailMock);
    when(assessmentDetailRepositoryMock.saveAndFlush(assessmentDetailMock))
        .thenReturn(assessmentDetailMock);
    when(assessmentDetailMapperMock.toDto(assessmentDetailMock)).thenReturn(assessmentDetailDTO);

    AssessmentDetailDTO result = testObj.create(assessment, assessmentDetailDTO);

    Assert.assertEquals(ASSESSMENT_ID, result.getId());
  }


  @Test(expected = NullPointerException.class)
  public void createShouldThrowExceptionsWhenAssessmentNull() {
    testObj.create(null, new AssessmentDetailDTO());
  }

  @Test(expected = NullPointerException.class)
  public void createShouldThrowExceptionsWhenDetailNull() {
    testObj.create(new Assessment(), null);
  }

  @Test(expected = IllegalStateException.class)
  public void createShouldThrowExceptionsWhenDetailIdIsNotNull() {
    when(assessmentDetailDTOMock.getId()).thenReturn(ASSESSMENT_ID);
    testObj.create(new Assessment(), assessmentDetailDTOMock);
  }


  @Test
  public void findOneShouldReturnDtoOfDetail() {
    when(assessmentDetailRepositoryMock.findById(ASSESSMENT_ID)).thenReturn(
        Optional.of(assessmentDetailMock));
    when(assessmentDetailMapperMock.toDto(assessmentDetailMock))
        .thenReturn(assessmentDetailDTOMock);

    Optional<AssessmentDetailDTO> result = testObj.findOne(ASSESSMENT_ID);

    Assert.assertTrue(result.isPresent());
    Assert.assertEquals(assessmentDetailDTOMock, result.get());
  }

  @Test
  public void findOneShouldReturnEmptyDTOWhenNoDetailMatches() {
    when(assessmentDetailRepositoryMock.findById(ASSESSMENT_ID)).thenReturn(Optional.empty());

    Optional<AssessmentDetailDTO> result = testObj.findOne(ASSESSMENT_ID);

    Assert.assertFalse(result.isPresent());
  }

  @Test(expected = NullPointerException.class)
  public void findOneShouldThrowExceptionsWhenIdIsNull() {
    testObj.findOne(null);
  }
}
