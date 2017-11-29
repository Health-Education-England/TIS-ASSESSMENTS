package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.OutcomeService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST controller for managing Outcome.
 */
@RestController
@RequestMapping("/api/trainee")
public class OutcomeResource {

  private static final String ENTITY_NAME = "outcome";

  private final Logger log = LoggerFactory.getLogger(OutcomeResource.class);
  private final OutcomeService outcomeService;
  private final AssessmentService assessmentService;

  public OutcomeResource(OutcomeService outcomeService, AssessmentService assessmentService) {
    this.outcomeService = outcomeService;
    this.assessmentService = assessmentService;
  }

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
  public ResponseEntity<OutcomeDTO> getTraineeAssessmentOutcomes(@PathVariable String traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to get Assessment Outcome");
    Optional<AssessmentDTO> traineeAssessment = assessmentService.findTraineeAssessmentDTO(traineeId, assessmentId);
    OutcomeDTO outcome = null;
    if (traineeAssessment.isPresent()) {
      outcome = traineeAssessment.get().getOutcome();
    }
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(outcome));
  }

  /**
   * POST  /:traineeId/assessments/:assessmentId/outcomes : create/update the an outcome that's linked to a trainee's assessment.
   *
   * @param outcomeDTO   the outcome to create
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PostMapping("/{traineeId}/assessments/{assessmentId}/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<OutcomeDTO> createTraineeAssessmentOutcomes(@RequestBody @Validated(Create.class) OutcomeDTO outcomeDTO,
                                                                    @PathVariable String traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to create Outcome : {}", outcomeDTO);
    Optional<Assessment> traineeAssessment = assessmentService.findTraineeAssessment(traineeId, assessmentId);
    OutcomeDTO savedOutcome = null;
    if (traineeAssessment.isPresent()) {
      savedOutcome = outcomeService.save(traineeAssessment.get(), outcomeDTO);
    }
    return ResponseUtil.wrapOrNotFound(Optional.of(savedOutcome));
  }

}
