package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.AssessmentOutcomeService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST controller for managing AssessmentOutcome.
 */
@RestController
@RequestMapping("/api/trainee")
public class AssessmentOutcomeResource {

  private static final String ENTITY_NAME = "outcome";

  private final Logger log = LoggerFactory.getLogger(AssessmentOutcomeResource.class);

  @Autowired
  private AssessmentOutcomeService assessmentOutcomeService;
  @Autowired
  private AssessmentService assessmentService;

  /**
   * GET  /:traineeId/assessments/:assessmentId/outcomes : get the an outcome that's linked to a trainee's assessment.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @GetMapping("/{traineeId}/assessments/{assessmentId}/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentOutcomeDTO> getTraineeAssessmentOutcomes(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to get Assessment AssessmentOutcome");
    Optional<AssessmentDTO> traineeAssessment = assessmentService.findTraineeAssessmentDTO(traineeId, assessmentId);
    AssessmentOutcomeDTO outcome = null;
    if (traineeAssessment.isPresent()) {
      outcome = traineeAssessment.get().getOutcome();
    }
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(outcome));
  }

  /**
   * POST  /:traineeId/assessments/:assessmentId/outcomes : create the an outcome that's linked to a trainee's assessment.
   *
   * @param assessmentOutcomeDTO the outcome to create
   * @param traineeId            the id of the trainee
   * @param assessmentId         the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PostMapping("/{traineeId}/assessments/{assessmentId}/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<AssessmentOutcomeDTO> createTraineeAssessmentOutcomes(@RequestBody @Validated(Create.class) AssessmentOutcomeDTO assessmentOutcomeDTO,
                                                                              @PathVariable Long traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to create AssessmentOutcome : {}", assessmentOutcomeDTO);
    Optional<Assessment> traineeAssessment = assessmentService.findTraineeAssessment(traineeId, assessmentId);
    AssessmentOutcomeDTO savedOutcome = null;
    if (traineeAssessment.isPresent()) {
      savedOutcome = assessmentOutcomeService.create(traineeAssessment.get(), assessmentOutcomeDTO);
    }
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(savedOutcome));
  }

  /**
   * PUT  /:traineeId/assessments/:assessmentId/outcomes : update the an outcome that's linked to a trainee's assessment.
   *
   * @param assessmentOutcomeDTO the outcome to update
   * @param traineeId            the id of the trainee
   * @param assessmentId         the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PutMapping("/{traineeId}/assessments/{assessmentId}/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<AssessmentOutcomeDTO> updateTraineeAssessmentOutcomes(@RequestBody @Validated(Update.class) AssessmentOutcomeDTO assessmentOutcomeDTO,
                                                                              @PathVariable Long traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to create AssessmentOutcome : {}", assessmentOutcomeDTO);
    Optional<Assessment> traineeAssessment = assessmentService.findTraineeAssessment(traineeId, assessmentId);
    AssessmentOutcomeDTO savedOutcome = null;
    if (traineeAssessment.isPresent()) {
      savedOutcome = assessmentOutcomeService.save(traineeAssessment.get(), assessmentOutcomeDTO);
    }
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(savedOutcome));
  }

  //Kept to allow compatibility with audit service
  private ResponseEntity<AssessmentOutcomeDTO> getAssessmentOutcome(Long outcomeId) {
    Optional<AssessmentOutcomeDTO> outcomeDTO = assessmentOutcomeService.findOne(outcomeId);
    return ResponseUtil.wrapOrNotFound(outcomeDTO);
  }
}
