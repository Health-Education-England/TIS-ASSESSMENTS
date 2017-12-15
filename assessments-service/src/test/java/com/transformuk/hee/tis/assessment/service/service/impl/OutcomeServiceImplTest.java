package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.Outcome;
import com.transformuk.hee.tis.assessment.service.repository.OutcomeRepository;
import com.transformuk.hee.tis.assessment.service.service.mapper.OutcomeMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OutcomeServiceImplTest {

  private static final Long ASSESSMENT_ID = 1L;

  @InjectMocks
  @Spy
  private OutcomeServiceImpl testObj;
  @Mock
  private OutcomeRepository outcomeRepositoryMock;
  @Mock
  private OutcomeMapper outcomeMapperMock;
  @Mock
  private Assessment assessmentMock;
  @Mock
  private Outcome outcomeMock, savedOutcomeMock;
  @Mock
  private OutcomeDTO outcomeDTOMock, resultOutcomeDTOMock;

  @Before
  public void setup(){
    when(assessmentMock.getId()).thenReturn(ASSESSMENT_ID);
    when(outcomeDTOMock.getId()).thenReturn(ASSESSMENT_ID);
    when(resultOutcomeDTOMock.getId()).thenReturn(ASSESSMENT_ID);
    when(savedOutcomeMock.getId()).thenReturn(ASSESSMENT_ID);
  }

  @Test
  public void saveShouldSaveOutcome() {
    when(outcomeMapperMock.toEntity(outcomeDTOMock)).thenReturn(outcomeMock);
    when(outcomeRepositoryMock.save(outcomeMock)).thenReturn(savedOutcomeMock);
    when(outcomeMapperMock.toDto(savedOutcomeMock)).thenReturn(resultOutcomeDTOMock);

    OutcomeDTO result = testObj.save(assessmentMock, outcomeDTOMock);

    Assert.assertEquals(ASSESSMENT_ID, result.getId());
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionWhenAssessmentIsNull() {
    testObj.save(null, outcomeDTOMock);
  }

  @Test(expected = NullPointerException.class)
  public void saveShouldThrowExceptionWhenOutcomeIsNull() {
    testObj.save(assessmentMock, null);
  }

  @Test(expected = IllegalStateException.class)
  public void saveShouldThrowExceptionOutcomeIdDoesNotMatchAssessment() {
    when(outcomeDTOMock.getId()).thenReturn(3L);
    testObj.save(assessmentMock, outcomeDTOMock);
  }

  @Test
  public void createShouldCreateNewOutcome() {
    Assessment assessment = new Assessment();
    assessment.setId(ASSESSMENT_ID);
    OutcomeDTO outcomeDTO = new OutcomeDTO();

    when(outcomeMapperMock.toEntity(outcomeDTO)).thenReturn(outcomeMock);
    when(outcomeRepositoryMock.save(outcomeMock)).thenReturn(savedOutcomeMock);
    when(outcomeMapperMock.toDto(savedOutcomeMock)).thenReturn(outcomeDTOMock);
    when(outcomeDTOMock.getId()).thenReturn(ASSESSMENT_ID);

    OutcomeDTO result = testObj.create(assessment, outcomeDTO);

    verify(testObj).save(assessment, outcomeDTO);
    Assert.assertEquals(ASSESSMENT_ID, result.getId());
  }

  @Test(expected = NullPointerException.class)
  public void createShouldThrowExceptionWhenAssessmentIsNull() {
    testObj.create(null, outcomeDTOMock);
  }

  @Test(expected = NullPointerException.class)
  public void createShouldThrowExceptionWhenOutcomeIsNull() {
    testObj.save(assessmentMock, null);
  }

  @Test(expected = IllegalStateException.class)
  public void createShouldThrowExceptionWhenOutcomeHasAnId() {
    when(outcomeDTOMock.getId()).thenReturn(3L);
    testObj.save(assessmentMock, outcomeDTOMock);
  }

  @Test(expected = NullPointerException.class)
  public void findOneShouldThrowExceptionIfIdIsNull() {
    testObj.findOne(null);
  }

  @Test
  public void findOneShouldReturnOutcomeWithId() {
    when(outcomeRepositoryMock.findOne(ASSESSMENT_ID)).thenReturn(outcomeMock);
    when(outcomeMapperMock.toDto(outcomeMock)).thenReturn(outcomeDTOMock);
    when(outcomeDTOMock.getId()).thenReturn(ASSESSMENT_ID);

    Optional<OutcomeDTO> result = testObj.findOne(ASSESSMENT_ID);

    Assert.assertTrue(result.isPresent());
    Assert.assertEquals(ASSESSMENT_ID, result.get().getId());
  }


  @Test
  public void findOneShouldReturnEmptyOptionalWhenOutcomeDoesntExist() {
    when(outcomeRepositoryMock.findOne(ASSESSMENT_ID)).thenReturn(null);
    when(outcomeMapperMock.toDto((Outcome)null)).thenReturn(null);

    Optional<OutcomeDTO> result = testObj.findOne(ASSESSMENT_ID);

    Assert.assertFalse(result.isPresent());
  }
}
