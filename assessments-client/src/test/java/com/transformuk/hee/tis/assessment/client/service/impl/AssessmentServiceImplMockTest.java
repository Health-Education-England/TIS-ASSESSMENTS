package com.transformuk.hee.tis.assessment.client.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentServiceImplMockTest {

  private static final String ASSESSMENT_URL = "http://localhost:8088/assessment";

  private AssessmentServiceImpl testObj;

  @Mock
  private RestTemplate restTemplateMock;

  @Before
  public void setup() {
    testObj = new AssessmentServiceImpl(1d, 1d);
    testObj.setServiceUrl(ASSESSMENT_URL);
    testObj.setAssessmentRestTemplate(restTemplateMock);
  }

  @Test
  public void shouldFindAssessmentByIds() {
    // Given.
    AssessmentDTO dto = new AssessmentDTO();
    AssessmentDetailDTO detailDto = new AssessmentDetailDTO();
    dto.id(1L).type("type").detail(detailDto);

    ResponseEntity<List<AssessmentDTO>> response = ResponseEntity.ok(Lists.newArrayList(dto));
    when(restTemplateMock.exchange(anyString(), eq(HttpMethod.GET), eq(null), any(
        ParameterizedTypeReference.class))).thenReturn(response);

    // When.
    Set<String> ids = new HashSet<>();
    ids.add("1");
    List<AssessmentDTO> returnDtos = testObj.findAssessmentByIds(ids);

    // Then.
    verify(restTemplateMock).exchange(
        eq(ASSESSMENT_URL + "/api/trainee/assessments/" + StringUtils.join(ids, ",")),
        eq(HttpMethod.GET), eq(null), any(
            ParameterizedTypeReference.class));
    assertThat("Unexpected DTOs.", returnDtos.get(0), is(dto));
  }

  @Test
  public void shouldFindAssessments() {
    // Given.
    AssessmentListDTO dto = new AssessmentListDTO();
    dto.setId(1L);
    dto.setType("type");

    String columnFiltersJson;
    Long traineeId = 1L;
    Long curriculumMembershipId = 1L;
    LocalDate reviewDate = LocalDate.parse("2021-01-01");
    String outcome = "1";
    try {
      columnFiltersJson = URLEncoder.encode("{\"traineeId\": [\"" + traineeId + "\"]"
          + ", \"curriculumMembershipId\": [\"" + curriculumMembershipId + "\"]"
          + ", \"reviewDate\": [\"" + reviewDate + "\"]"
          + ", \"outcome.outcome\": [\"" + outcome + "\"]"
          + "}", "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new AssertionError("UTF-8 is unknown");
    }
    ParameterizedTypeReference<List<AssessmentListDTO>> ptr
        = new ParameterizedTypeReference<List<AssessmentListDTO>>() {};
    ResponseEntity<List<AssessmentListDTO>> response = ResponseEntity.ok(Lists.newArrayList(dto));
    when(restTemplateMock.exchange(anyString(),
        eq(HttpMethod.GET),
        eq(null),
        eq(ptr),
        eq(columnFiltersJson))).thenReturn(response);

    // When.
    List<AssessmentListDTO> returnDtos =
        testObj.findAssessments(traineeId, curriculumMembershipId, reviewDate, outcome);

    // Then.
    verify(restTemplateMock).exchange(
        ASSESSMENT_URL + "/api/trainee/assessments/?columnFilters={columnFiltersJson}",
        HttpMethod.GET, null,
        ptr, columnFiltersJson);
    assertThat("Unexpected DTOs.", returnDtos.get(0), is(dto));
  }

  @Test
  public void getFindAssessmentsJsonShouldHandleNulls() {
    Long traineeId = 1L;
    Long curriculumMembershipId = null;
    LocalDate reviewDate = LocalDate.parse("2021-01-01");
    String outcome = null;
    String correctJson = "{\"traineeId\": [\"" + traineeId + "\"], " +
        "\"reviewDate\": [\"" + reviewDate + "\"]}";

    String columnFiltersJson = testObj.getFindAssessmentsJson(traineeId, curriculumMembershipId,
        reviewDate, outcome);

    Assert.assertThat("Unexpected columnFilters Json.", columnFiltersJson, is(correctJson));
  }

  @Test
  public void shouldPatchAssessments() {
    // Given.
    AssessmentDTO dto = new AssessmentDTO();
    AssessmentDetailDTO detailDto = new AssessmentDetailDTO();
    dto.id(1L).type("type").detail(detailDto);

    ResponseEntity<List<AssessmentDTO>> response = ResponseEntity.ok(Lists.newArrayList(dto));
    when(restTemplateMock.exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), any(
        ParameterizedTypeReference.class))).thenReturn(response);

    // When.
    List<AssessmentDTO> returnDtos = testObj.patchAssessments(Lists.newArrayList(dto));

    // Then.
    verify(restTemplateMock).exchange(eq(ASSESSMENT_URL + "/api/trainee/bulk-assessment"),
        eq(HttpMethod.PUT), any(HttpEntity.class), any(
            ParameterizedTypeReference.class));
    assertThat("Unexpected patched DTOs.", returnDtos.get(0), is(dto));
  }

  @Test
  public void shouldDeleteAssessments() {
    Long ASSESSMENT_ID = 1L;
    // Given.
    ResponseEntity<Void> response = ResponseEntity.ok().build();
    when(restTemplateMock.exchange(anyString(), eq(HttpMethod.DELETE), any(HttpEntity.class), any(
        ParameterizedTypeReference.class))).thenReturn(response);

    // When.
    testObj.deleteAssessment(ASSESSMENT_ID);

    // Then.
    verify(restTemplateMock).exchange(eq(ASSESSMENT_URL + "/api/trainee/assessments/" + ASSESSMENT_ID),
        eq(HttpMethod.DELETE), any(HttpEntity.class), any(
            ParameterizedTypeReference.class));
  }
}
