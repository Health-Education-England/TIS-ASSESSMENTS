package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.service.exception.BadRequestAlertException;
import com.transformuk.hee.tis.assessment.service.api.util.HeaderUtil;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.service.service.OutcomeService;
import io.github.jhipster.web.util.ResponseUtil;
import io.jsonwebtoken.lang.Collections;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Outcome.
 */
@RestController
@RequestMapping("/api/trainee")
public class OutcomeResource {

  private static final String ENTITY_NAME = "outcome";
  private static final String REQUEST_BODY_EMPTY = "request.body.empty";
  private static final String REQUEST_BODY_CANNOT_BE_EMPTY = "The request body for this end point cannot be empty";
  private static final String BULK_UPDATE_FAILED_NOID = "bulk.update.failed.noId";
  private static final String NOID_ERR_MSG = "Some DTOs you've provided have no Id, cannot update entities that don't exist";


  private final Logger log = LoggerFactory.getLogger(OutcomeResource.class);
  private final OutcomeService outcomeService;

  public OutcomeResource(OutcomeService outcomeService) {
    this.outcomeService = outcomeService;
  }

  /**
   * GET  /:traineeId/assessments/:assessmentId/outcomes : get the an outcome that's linked to a trainee's assessment.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @GetMapping("/{traineeId}/assessments/{assessmentId}/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<OutcomeDTO> getTraineeAssessmentOutcomes(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    return ResponseUtil.wrapOrNotFound(Optional.empty());
  }

  /**
   * POST  /:traineeId/assessments/:assessmentId/outcomes : create/update the an outcome that's linked to a trainee's assessment.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PostMapping("/{traineeId}/assessments/{assessmentId}/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<OutcomeDTO> createTraineeAssessmentOutcomes(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    return ResponseUtil.wrapOrNotFound(Optional.empty());
  }

  /**
   * PUT  /:traineeId/assessments/:assessmentId/outcomes : update the an outcome that's linked to a trainee's assessment.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PutMapping("/{traineeId}/assessments/{assessmentId}/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<OutcomeDTO> updateTraineeAssessmentOutcomes(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    return ResponseUtil.wrapOrNotFound(Optional.empty());
  }



  // BULK ENDPOINTS START

  /**
   * POST  /bulk-outcomes : Bulk create a new Outcomes.
   *
   * @param outcomeDTOs List of the OutcomeDTO to create
   * @return the ResponseEntity with status 200 (Created) and with body the new outcomeDTOs, or with status 400 (Bad Request) if the Assessment already has an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/bulk-outcomes")
  @Timed
  @PreAuthorize("hasAuthority('outcomes:bulk:add:modify:entities')")
  public ResponseEntity<List<OutcomeDTO>> bulkCreateOutcomes(@Valid @RequestBody List<OutcomeDTO> outcomeDTOs) {
    log.debug("REST request to bulk save Outcome : {}", outcomeDTOs);
    if (!Collections.isEmpty(outcomeDTOs)) {
      List<Long> entityIds = outcomeDTOs.stream()
          .filter(p -> p.getId() != null)
          .map(OutcomeDTO::getId)
          .collect(Collectors.toList());
      if (!Collections.isEmpty(entityIds)) {
        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(StringUtils.join(entityIds, ","), "ids.exist", "A new Outcome cannot already have an ID")).body(null);
      }
    }
    List<OutcomeDTO> result = outcomeService.save(outcomeDTOs);
    List<Long> ids = result.stream().map(OutcomeDTO::getId).collect(Collectors.toList());
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, StringUtils.join(ids, ",")))
        .body(result);
  }

  /**
   * PUT  /bulk-outcomes : Updates a collections of existing Outcomes.
   *
   * @param outcomeDTOs List of the Outcomes to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated outcomeDTOs,
   * or with status 400 (Bad Request) if the outcomeDTOs is not valid,
   * or with status 500 (Internal Server Error) if the outcomeDTOs couldnt be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/bulk-outcomes")
  @Timed
  @PreAuthorize("hasAuthority('outcomes:bulk:add:modify:entities')")
  public ResponseEntity<List<OutcomeDTO>> bulkUpdateAssessments(@Valid @RequestBody List<OutcomeDTO> outcomeDTOs) {
    log.debug("REST request to bulk update Outcomes : {}", outcomeDTOs);
    if (Collections.isEmpty(outcomeDTOs)) {
      return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, REQUEST_BODY_EMPTY,
          REQUEST_BODY_CANNOT_BE_EMPTY)).body(null);
    } else if (!Collections.isEmpty(outcomeDTOs)) {
      List<OutcomeDTO> entitiesWithNoId = outcomeDTOs.stream().filter(p -> p.getId() == null).collect(Collectors.toList());
      if (!Collections.isEmpty(entitiesWithNoId)) {
        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(StringUtils.join(entitiesWithNoId, ","),
            BULK_UPDATE_FAILED_NOID, NOID_ERR_MSG)).body(entitiesWithNoId);
      }
    }

    List<OutcomeDTO> results = outcomeService.save(outcomeDTOs);
    List<Long> ids = results.stream().map(OutcomeDTO::getId).collect(Collectors.toList());
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, StringUtils.join(ids, ",")))
        .body(results);
  }

}
