package com.transformuk.hee.tis.assessment.client.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.transformuk.hee.tis.assessment.api.dto.*;
import com.transformuk.hee.tis.client.impl.AbstractClientService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl extends AbstractClientService {
  private static final Logger log = LoggerFactory.getLogger(AssessmentServiceImpl.class);

  private static final String API_TRAINEE_CREATE_ASSESSMENTS = "/api/trainee/"; //{traineeId}/assessments
  private static final String API_TRAINEE_ASSESSMENTS = "/api/trainee/assessments/";
  private static final String API_ASSESSMENTS_OUTCOME_ALL = "/api/outcomes/all";
  private static final String BASIC = "/basic";
  @Autowired
  private ObjectMapper objectMapper;

  private static final Map<Class, ParameterizedTypeReference> classToParamTypeRefMap;

  static {
    classToParamTypeRefMap = Maps.newHashMap();
    classToParamTypeRefMap.put(AssessmentDTO.class, new ParameterizedTypeReference<List<AssessmentDTO>>() {
    });
    classToParamTypeRefMap.put(AssessmentDetailDTO.class, new ParameterizedTypeReference<List<AssessmentDetailDTO>>() {
    });
    classToParamTypeRefMap.put(AssessmentOutcomeDTO.class, new ParameterizedTypeReference<List<AssessmentOutcomeDTO>>() {
    });
    classToParamTypeRefMap.put(RevalidationDTO.class, new ParameterizedTypeReference<List<RevalidationDTO>>() {
    });
    classToParamTypeRefMap.put(Object.class, new ParameterizedTypeReference<Set<Object>>() {
    });
  }

  @Autowired
  private RestTemplate assessmentRestTemplate;

  @Value("${assessments.service.url}")
  private String serviceUrl;

  public AssessmentServiceImpl(@Value("${assessments.client.rate.limit}") double standardRequestsPerSecondLimit,
                               @Value("${assessments.client.bulk.rate.limit}") double bulkRequestsPerSecondLimit) {
    super(standardRequestsPerSecondLimit, bulkRequestsPerSecondLimit);
  }

  @PostConstruct
  public void init() {
    assessmentRestTemplate.setErrorHandler(new AssessmentClientErrorHandler());
  }

  private ParameterizedTypeReference<List<JsonPatchDTO>> getJsonPatchDtoReference() {
    return new ParameterizedTypeReference<List<JsonPatchDTO>>() {
    };
  }

  public List<AssessmentDTO> findAssessmentByIds(Set<String> assessmentIds) {
    String joinedIds = StringUtils.join(assessmentIds, ",");
    return assessmentRestTemplate.exchange(
        serviceUrl + API_TRAINEE_CREATE_ASSESSMENTS + "assessments/" + joinedIds,
        HttpMethod.GET, null,
        new ParameterizedTypeReference<List<AssessmentDTO>>() {
        }).getBody();
  }

  /**
   * Finds assessments that match the supplied criteria.
   * The criteria should uniquely identify an assessment: if there are multiple matches these would
   * be considered duplicates.
   *
   * @param traineeId             The trainee id
   * @param programmeMembershipId The programme membership id, derived from programme and curriculum
   * @param reviewDate            The review date
   * @param outcome               The assessment outcome value
   * @return A list of matching AssessmentDTOs
   */
  public List<AssessmentDTO> findAssessments(Long traineeId, Long programmeMembershipId,
                                             LocalDate reviewDate, String outcome) {

    String columnFiltersJson;
    try {
      columnFiltersJson = URLEncoder.encode("{\"traineeId\": [\"" + traineeId + "\"]"
          + ", \"programmeMembershipId\": [\"" + programmeMembershipId + "\"]"
          + ", \"reviewDate\": [\"" + reviewDate + "\"]"
          + ", \"outcome.outcome\": [\"" + outcome + "\"]"
          + "}", "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new AssertionError("UTF-8 is unknown");
    }

    return assessmentRestTemplate.exchange(
        serviceUrl + API_TRAINEE_ASSESSMENTS + "?columnFilters={columnFiltersJson}",
        HttpMethod.GET, null,
        new ParameterizedTypeReference<List<AssessmentDTO>>() {
        }, columnFiltersJson).getBody();
  }

  public List<AssessmentDTO> patchAssessments(List<AssessmentDTO> assessmentDtos) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<List<AssessmentDTO>> httpEntity = new HttpEntity<>(assessmentDtos, headers);
    return assessmentRestTemplate
        .exchange(serviceUrl + API_TRAINEE_CREATE_ASSESSMENTS + "bulk-assessment",
            HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<List<AssessmentDTO>>() {
            })
        .getBody();
  }

  /**
   * Delete one assessment by id.
   *
   * @param id the id of the assessment
   */
  public Void deleteAssessment(Long id) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Long> httpEntity = new HttpEntity<>(id, headers);
    return assessmentRestTemplate
        .exchange(serviceUrl + API_TRAINEE_ASSESSMENTS + id,
            HttpMethod.DELETE, httpEntity, new ParameterizedTypeReference<Void>() {
            })
        .getBody();
  }

  public AssessmentDTO createTraineeAssessment(AssessmentDTO assessmentDTO, Long traineeId) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<AssessmentDTO> httpEntity = new HttpEntity<>(assessmentDTO, headers);
    return assessmentRestTemplate
            .exchange(serviceUrl + API_TRAINEE_CREATE_ASSESSMENTS + traineeId + "/assessments",
                    HttpMethod.POST, httpEntity, new ParameterizedTypeReference<AssessmentDTO>() {
                    })
            .getBody();
  }

  public AssessmentDetailDTO createTraineeAssessmentDetails(AssessmentDetailDTO assessmentDetailDTO, Long traineeId, Long assessmentId) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<AssessmentDetailDTO> httpEntity = new HttpEntity<>(assessmentDetailDTO, headers);
    return assessmentRestTemplate
            .exchange(serviceUrl + API_TRAINEE_CREATE_ASSESSMENTS + traineeId + "/assessments/" + assessmentId + "/details",
                    HttpMethod.POST, httpEntity, new ParameterizedTypeReference<AssessmentDetailDTO>() {
                    })
            .getBody();
  }

  public AssessmentOutcomeDTO createTraineeAssessmentOutcome(AssessmentOutcomeDTO assessmentOutcomeDTO, Long traineeId, Long assessmentId) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<AssessmentOutcomeDTO> httpEntity = new HttpEntity<>(assessmentOutcomeDTO, headers);
    return assessmentRestTemplate
            .exchange(serviceUrl + API_TRAINEE_CREATE_ASSESSMENTS + traineeId + "/assessments/" + assessmentId + "/outcomes",
                    HttpMethod.POST, httpEntity, new ParameterizedTypeReference<AssessmentOutcomeDTO>() {
                    })
            .getBody();
  }

  public RevalidationDTO createTraineeAssessmentRevalidation(RevalidationDTO revalidationDTO, Long traineeId, Long assessmentId) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<RevalidationDTO> httpEntity = new HttpEntity<>(revalidationDTO, headers);
    return assessmentRestTemplate
            .exchange(serviceUrl + API_TRAINEE_CREATE_ASSESSMENTS + traineeId + "/assessments/" + assessmentId + "/revalidations",
                    HttpMethod.POST, httpEntity, new ParameterizedTypeReference<RevalidationDTO>() {
                    })
            .getBody();
  }

  /**
   * Fetches all outcome objects with reasons and then converting into Json string
   *
   * @return
   */
  public String getAllOutcomes() {
    Set<Object> allOutcomes = assessmentRestTemplate.exchange(serviceUrl + API_ASSESSMENTS_OUTCOME_ALL,
            HttpMethod.GET, null, new ParameterizedTypeReference<Set<Object>>() {
            }).getBody();
    String jsonAllOutcomes = null;
    try {
      jsonAllOutcomes = objectMapper.writeValueAsString(allOutcomes);
    } catch (JsonProcessingException e) {
      log.error("Unable to convert outcomes to json string");
    }
    return jsonAllOutcomes;
  }

  private String getIdsAsUrlEncodedCSVs(List<String> ids) {
    Set<String> urlEncodedIds = ids.stream()
            .map(this::urlEncode)
            .collect(Collectors.toSet());
    return String.join(",", urlEncodedIds);
  }

  private String urlEncode(String s) {
    try {
      return URLEncoder.encode(s, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new AssertionError("UTF-8 is unknown");
    }
  }


  public static String join(List<Long> ids) {
    return ids.stream()
            .map(Object::toString)
            .collect(Collectors.joining(","));
  }

  @Override
  public List<JsonPatchDTO> getJsonPathByTableDtoNameOrderByDateAddedAsc(String endpointUrl, Class objectDTO) {
    ParameterizedTypeReference<List<JsonPatchDTO>> typeReference = getJsonPatchDtoReference();
    ResponseEntity<List<JsonPatchDTO>> response = assessmentRestTemplate.exchange(serviceUrl + endpointUrl + objectDTO.getSimpleName(),
            HttpMethod.GET, null, typeReference);
    return response.getBody();
  }

  @Override
  public RestTemplate getRestTemplate() {
    return this.assessmentRestTemplate;
  }

  public void setAssessmentRestTemplate(RestTemplate assessmentRestTemplate) {
    this.assessmentRestTemplate = assessmentRestTemplate;
  }

  @Override
  public String getServiceUrl() {
    return this.serviceUrl;
  }

  public void setServiceUrl(String serviceUrl) {
    this.serviceUrl = serviceUrl;
  }

  @Override
  public Map<Class, ParameterizedTypeReference> getClassToParamTypeRefMap() {
    return classToParamTypeRefMap;
  }
}
