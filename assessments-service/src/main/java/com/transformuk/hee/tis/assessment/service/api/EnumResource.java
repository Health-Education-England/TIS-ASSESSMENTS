package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.EventStatus;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for retrieving enum types from Assessment.
 */
@RestController
@RequestMapping("/api")
public class EnumResource {

  @ApiOperation(value = "Lists all EventStatus types that can be associated with an Event",
      notes = "Used by clients to retrieve all EventStatus types that are currently available by this service. \n" +
          "This allows clients to dynamically list out all options for particular fields so that we do not \n" +
          "need to maintain a list on both the backend and client",
      response = ResponseEntity.class, responseContainer = "List",
      httpMethod = "GET", produces = "application/json", protocols = "http, https")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "All Assessment Types", response = ResponseEntity.class)})
  @RequestMapping(value = "/event-status", method = RequestMethod.GET)
  public ResponseEntity<EventStatus[]> getAllEventStatus() {
    return new ResponseEntity<>(EventStatus.values(), HttpStatus.OK);
  }

  @ApiOperation(value = "Lists all OutcomeStatus types that can be associated with an AssessmentOutcome",
      notes = "Used by clients to retrieve all OutcomeStatus types that are currently available by this service. \n" +
          "This allows clients to dynamically list out all options for particular fields so that we do not \n" +
          "need to maintain a list on both the backend and client",
      response = ResponseEntity.class, responseContainer = "List",
      httpMethod = "GET", produces = "application/json", protocols = "http, https")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "All Assessment Types", response = ResponseEntity.class)})
  @RequestMapping(value = "/outcome-status", method = RequestMethod.GET)
  public ResponseEntity<OutcomeStatus[]> getAllOutcomeStatus() {
    return new ResponseEntity<>(OutcomeStatus.values(), HttpStatus.OK);
  }
}
