package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.EventStatus;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeStatus;
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

  @RequestMapping(value = "/event-status", method = RequestMethod.GET)
  public ResponseEntity<EventStatus[]> getAllEventStatus() {
    return new ResponseEntity<>(EventStatus.values(), HttpStatus.OK);
  }

  @RequestMapping(value = "/outcome-status", method = RequestMethod.GET)
  public ResponseEntity<OutcomeStatus[]> getAllOutcomeStatus() {
    return new ResponseEntity<>(OutcomeStatus.values(), HttpStatus.OK);
  }
}
