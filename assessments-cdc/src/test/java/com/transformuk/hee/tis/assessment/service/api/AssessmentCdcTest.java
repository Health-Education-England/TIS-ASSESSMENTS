package com.transformuk.hee.tis.assessment.service.api;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.MockProviderConfig;
import au.com.dius.pact.model.RequestResponsePact;
import com.transformuk.hee.tis.assessment.service.dto.IdBasedDTO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * This
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class AssessmentCdcTest {

  private static final String DEFAULT_SERVICE_STATE = "service-has-data";
  private static final String ALL_ASSESSMENTS_URI = "/assessments/api/trainee/assessments";
  private static final String TRAINEE_ASSESSMENT_URI = "/assessments/api/trainee/1/assessments/1";
  private static final String PROVIDER_NAME = "assessments-service";
  private static final String CONSUMER_NAME = "basic-cdc";
  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  public static final String HTTP_GET_METHOD = "GET";

  @Rule
  public PactProviderRuleMk2 provider = new PactProviderRuleMk2(PROVIDER_NAME, this);

  private RestTemplate restTemplate = new RestTemplate();

  @Pact(provider = PROVIDER_NAME, consumer = CONSUMER_NAME)
  public RequestResponsePact createFragment(PactDslWithProvider builder) {
    Map<String, String> requestHeaders = new HashMap<>();
    requestHeaders.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJrcEk5UC1hQ3JaTXJ4cG5aeWNnNnlISk9VZ3g0a2hUYS04TlJyMkRhY0g0In0.eyJqdGkiOiI1NzgxNzdhZi05ZGM2LTQ2NTUtYmQ0ZC1hMTJlNmJmMmQ4OTgiLCJleHAiOjE0ODMwMjA2ODgsIm5iZiI6MCwiaWF0IjoxNDgzMDIwMzg4LCJpc3MiOiJodHRwczovL2Rldi1hcHBzLmxpbi5uaHMudWsvYXV0aC9yZWFsbXMvbGluIiwiYXVkIjoiYXBpLXRva2VucyIsInN1YiI6ImZkMGJiYWVhLWJkY2UtNDFiYy04YzIzLWM4ZWE3ZTQ0MTllMSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFwaS10b2tlbnMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJiYmNmZmVlMS1hNjJjLTRjYjEtODUzNy1jNjU1MjkzOGQ2YjgiLCJhY3IiOiIxIiwiY2xpZW50X3Nlc3Npb24iOiJhNzhiNzZmNC0wZmExLTQzYTEtOTQ2MC1jNDI0NjZhNjFiNmEiLCJhbGxvd2VkLW9yaWdpbnMiOltdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsidW1hX2F1dGhvcml6YXRpb24iLCJFVEwiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwibmFtZSI6IiIsInByZWZlcnJlZF91c2VybmFtZSI6ImdtY19ldGwifQ.A8P7O_dg9JbvR6mv0HHCir9BCDLUNz7dDWunLRNqbwyTeaapwKTTPTIHmag3N1yf23EMW-Fn3ZdPNgf7hnuepOiyfE5NImagZHjTVkFnhiChW_pLLHIUDEJsTZMsT_XxAONfhFAB4eCFyhsqvNFU3D0D9gGO-pESHcoiwzDWBcuesByRJcZHJdM4IW85KhvwaCgO7WhaKp-yuau93N3avPRABzNxjyaLigVP9E2ndD_yK5SKvZ7e7GCDqxzm4R-T-OW-5vdfidncgYUj-XU5XJ03Cm7hS0HGBEQWKUTWSaUE2f9z1cLDWU24t0uuw_Ln-kpJVgVgHsWIChs016Z5AQ");
    requestHeaders.put("OIDC_access_token", "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJrcEk5UC1hQ3JaTXJ4cG5aeWNnNnlISk9VZ3g0a2hUYS04TlJyMkRhY0g0In0.eyJqdGkiOiI1NzgxNzdhZi05ZGM2LTQ2NTUtYmQ0ZC1hMTJlNmJmMmQ4OTgiLCJleHAiOjE0ODMwMjA2ODgsIm5iZiI6MCwiaWF0IjoxNDgzMDIwMzg4LCJpc3MiOiJodHRwczovL2Rldi1hcHBzLmxpbi5uaHMudWsvYXV0aC9yZWFsbXMvbGluIiwiYXVkIjoiYXBpLXRva2VucyIsInN1YiI6ImZkMGJiYWVhLWJkY2UtNDFiYy04YzIzLWM4ZWE3ZTQ0MTllMSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFwaS10b2tlbnMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJiYmNmZmVlMS1hNjJjLTRjYjEtODUzNy1jNjU1MjkzOGQ2YjgiLCJhY3IiOiIxIiwiY2xpZW50X3Nlc3Npb24iOiJhNzhiNzZmNC0wZmExLTQzYTEtOTQ2MC1jNDI0NjZhNjFiNmEiLCJhbGxvd2VkLW9yaWdpbnMiOltdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsidW1hX2F1dGhvcml6YXRpb24iLCJFVEwiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwibmFtZSI6IiIsInByZWZlcnJlZF91c2VybmFtZSI6ImdtY19ldGwifQ.A8P7O_dg9JbvR6mv0HHCir9BCDLUNz7dDWunLRNqbwyTeaapwKTTPTIHmag3N1yf23EMW-Fn3ZdPNgf7hnuepOiyfE5NImagZHjTVkFnhiChW_pLLHIUDEJsTZMsT_XxAONfhFAB4eCFyhsqvNFU3D0D9gGO-pESHcoiwzDWBcuesByRJcZHJdM4IW85KhvwaCgO7WhaKp-yuau93N3avPRABzNxjyaLigVP9E2ndD_yK5SKvZ7e7GCDqxzm4R-T-OW-5vdfidncgYUj-XU5XJ03Cm7hS0HGBEQWKUTWSaUE2f9z1cLDWU24t0uuw_Ln-kpJVgVgHsWIChs016Z5AQ");

    Map<String, String> responseHeaders = new HashMap<>();
    responseHeaders.put(CONTENT_TYPE_HEADER, "application/json");

    return builder
//        .uponReceiving("get all assessments request")
//        .path(ALL_ASSESSMENTS_URI)
//        .query("page=0&size=2000&sort=id,desc&searchQuery=StR")
//        .method(HTTP_GET_METHOD)
//        .headers(requestHeaders)
//          .willRespondWith()
//          .status(HttpStatus.OK.value())
//          .headers(responseHeaders)
//          .body("[{\"id\": 1},{\"id\": 2}
        .given(DEFAULT_SERVICE_STATE)
        .uponReceiving("get specific trainee assessment")
        .path(TRAINEE_ASSESSMENT_URI)
        .method(HTTP_GET_METHOD)
        .headers(requestHeaders)
          .willRespondWith()
          .status(HttpStatus.OK.value())
          .headers(responseHeaders)
          .body("{\"id\": 1}")
        .toPact();
  }


  @Test
  @PactVerification(PROVIDER_NAME)
  public void getAllAssessmentsShouldReturnArrayOfAssessmentListWithIds() throws IOException, URISyntaxException {
    //ALL_ASSESSMENTS_URI
//    RequestEntity request = RequestEntity.get(URI.create(provider.getUrl() + ALL_ASSESSMENTS_URI + "?page=0&size=2000&sort=id,desc&searchQuery=StR")).build();
//    ParameterizedTypeReference<List<IdBasedDTO>> responseType = new ParameterizedTypeReference<List<IdBasedDTO>>() {};
//
//    ResponseEntity<List<IdBasedDTO>> response = restTemplate.exchange(request, responseType);
//
//    List<IdBasedDTO> responseBody = response.getBody();
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    assertEquals(2, responseBody.size());
//    assertEquals(new Long(1L), responseBody.get(0).getId());
//    assertEquals(new Long(2L), responseBody.get(1).getId());

    //TRAINEE_ASSESSMENT_URI
    Map<String, String> requestHeaders = new HashMap<>();
    requestHeaders.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJrcEk5UC1hQ3JaTXJ4cG5aeWNnNnlISk9VZ3g0a2hUYS04TlJyMkRhY0g0In0.eyJqdGkiOiI1NzgxNzdhZi05ZGM2LTQ2NTUtYmQ0ZC1hMTJlNmJmMmQ4OTgiLCJleHAiOjE0ODMwMjA2ODgsIm5iZiI6MCwiaWF0IjoxNDgzMDIwMzg4LCJpc3MiOiJodHRwczovL2Rldi1hcHBzLmxpbi5uaHMudWsvYXV0aC9yZWFsbXMvbGluIiwiYXVkIjoiYXBpLXRva2VucyIsInN1YiI6ImZkMGJiYWVhLWJkY2UtNDFiYy04YzIzLWM4ZWE3ZTQ0MTllMSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFwaS10b2tlbnMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJiYmNmZmVlMS1hNjJjLTRjYjEtODUzNy1jNjU1MjkzOGQ2YjgiLCJhY3IiOiIxIiwiY2xpZW50X3Nlc3Npb24iOiJhNzhiNzZmNC0wZmExLTQzYTEtOTQ2MC1jNDI0NjZhNjFiNmEiLCJhbGxvd2VkLW9yaWdpbnMiOltdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsidW1hX2F1dGhvcml6YXRpb24iLCJFVEwiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwibmFtZSI6IiIsInByZWZlcnJlZF91c2VybmFtZSI6ImdtY19ldGwifQ.A8P7O_dg9JbvR6mv0HHCir9BCDLUNz7dDWunLRNqbwyTeaapwKTTPTIHmag3N1yf23EMW-Fn3ZdPNgf7hnuepOiyfE5NImagZHjTVkFnhiChW_pLLHIUDEJsTZMsT_XxAONfhFAB4eCFyhsqvNFU3D0D9gGO-pESHcoiwzDWBcuesByRJcZHJdM4IW85KhvwaCgO7WhaKp-yuau93N3avPRABzNxjyaLigVP9E2ndD_yK5SKvZ7e7GCDqxzm4R-T-OW-5vdfidncgYUj-XU5XJ03Cm7hS0HGBEQWKUTWSaUE2f9z1cLDWU24t0uuw_Ln-kpJVgVgHsWIChs016Z5AQ");

    RequestEntity specificTraineeAssessmentRequest =
        RequestEntity.get(URI.create(provider.getUrl() + TRAINEE_ASSESSMENT_URI))
        .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJrcEk5UC1hQ3JaTXJ4cG5aeWNnNnlISk9VZ3g0a2hUYS04TlJyMkRhY0g0In0.eyJqdGkiOiI1NzgxNzdhZi05ZGM2LTQ2NTUtYmQ0ZC1hMTJlNmJmMmQ4OTgiLCJleHAiOjE0ODMwMjA2ODgsIm5iZiI6MCwiaWF0IjoxNDgzMDIwMzg4LCJpc3MiOiJodHRwczovL2Rldi1hcHBzLmxpbi5uaHMudWsvYXV0aC9yZWFsbXMvbGluIiwiYXVkIjoiYXBpLXRva2VucyIsInN1YiI6ImZkMGJiYWVhLWJkY2UtNDFiYy04YzIzLWM4ZWE3ZTQ0MTllMSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFwaS10b2tlbnMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJiYmNmZmVlMS1hNjJjLTRjYjEtODUzNy1jNjU1MjkzOGQ2YjgiLCJhY3IiOiIxIiwiY2xpZW50X3Nlc3Npb24iOiJhNzhiNzZmNC0wZmExLTQzYTEtOTQ2MC1jNDI0NjZhNjFiNmEiLCJhbGxvd2VkLW9yaWdpbnMiOltdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsidW1hX2F1dGhvcml6YXRpb24iLCJFVEwiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwibmFtZSI6IiIsInByZWZlcnJlZF91c2VybmFtZSI6ImdtY19ldGwifQ.A8P7O_dg9JbvR6mv0HHCir9BCDLUNz7dDWunLRNqbwyTeaapwKTTPTIHmag3N1yf23EMW-Fn3ZdPNgf7hnuepOiyfE5NImagZHjTVkFnhiChW_pLLHIUDEJsTZMsT_XxAONfhFAB4eCFyhsqvNFU3D0D9gGO-pESHcoiwzDWBcuesByRJcZHJdM4IW85KhvwaCgO7WhaKp-yuau93N3avPRABzNxjyaLigVP9E2ndD_yK5SKvZ7e7GCDqxzm4R-T-OW-5vdfidncgYUj-XU5XJ03Cm7hS0HGBEQWKUTWSaUE2f9z1cLDWU24t0uuw_Ln-kpJVgVgHsWIChs016Z5AQ")
        .header("OIDC_access_token", "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJrcEk5UC1hQ3JaTXJ4cG5aeWNnNnlISk9VZ3g0a2hUYS04TlJyMkRhY0g0In0.eyJqdGkiOiI1NzgxNzdhZi05ZGM2LTQ2NTUtYmQ0ZC1hMTJlNmJmMmQ4OTgiLCJleHAiOjE0ODMwMjA2ODgsIm5iZiI6MCwiaWF0IjoxNDgzMDIwMzg4LCJpc3MiOiJodHRwczovL2Rldi1hcHBzLmxpbi5uaHMudWsvYXV0aC9yZWFsbXMvbGluIiwiYXVkIjoiYXBpLXRva2VucyIsInN1YiI6ImZkMGJiYWVhLWJkY2UtNDFiYy04YzIzLWM4ZWE3ZTQ0MTllMSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFwaS10b2tlbnMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJiYmNmZmVlMS1hNjJjLTRjYjEtODUzNy1jNjU1MjkzOGQ2YjgiLCJhY3IiOiIxIiwiY2xpZW50X3Nlc3Npb24iOiJhNzhiNzZmNC0wZmExLTQzYTEtOTQ2MC1jNDI0NjZhNjFiNmEiLCJhbGxvd2VkLW9yaWdpbnMiOltdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsidW1hX2F1dGhvcml6YXRpb24iLCJFVEwiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwibmFtZSI6IiIsInByZWZlcnJlZF91c2VybmFtZSI6ImdtY19ldGwifQ.A8P7O_dg9JbvR6mv0HHCir9BCDLUNz7dDWunLRNqbwyTeaapwKTTPTIHmag3N1yf23EMW-Fn3ZdPNgf7hnuepOiyfE5NImagZHjTVkFnhiChW_pLLHIUDEJsTZMsT_XxAONfhFAB4eCFyhsqvNFU3D0D9gGO-pESHcoiwzDWBcuesByRJcZHJdM4IW85KhvwaCgO7WhaKp-yuau93N3avPRABzNxjyaLigVP9E2ndD_yK5SKvZ7e7GCDqxzm4R-T-OW-5vdfidncgYUj-XU5XJ03Cm7hS0HGBEQWKUTWSaUE2f9z1cLDWU24t0uuw_Ln-kpJVgVgHsWIChs016Z5AQ").build();
    ResponseEntity<IdBasedDTO> specificTraineeAssessmentResponse = restTemplate.exchange(specificTraineeAssessmentRequest, IdBasedDTO.class);

    IdBasedDTO traineeAssessmentBody = specificTraineeAssessmentResponse.getBody();
    assertEquals(HttpStatus.OK, specificTraineeAssessmentResponse.getStatusCode());
    assertEquals(new Long(1L), traineeAssessmentBody.getId());

  }

}
