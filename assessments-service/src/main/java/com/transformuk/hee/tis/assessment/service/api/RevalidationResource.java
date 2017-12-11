package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.RevalidationService;
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

@RestController
@RequestMapping("/api/trainee")
public class RevalidationResource {

  private static final String ENTITY_NAME = "Revalidation";
  private final Logger log = LoggerFactory.getLogger(RevalidationResource.class);

  @Autowired
  private RevalidationService revalidationService;
  @Autowired
  private AssessmentService assessmentService;

  /**
   * GET  /:traineeId/assessments/:assessmentId/revalidations : get the a revalidation that's linked to a trainee assessment.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @GetMapping("/{traineeId}/assessments/{assessmentId}/revalidations")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<RevalidationDTO> getTraineeAssessmentRevalidation(@PathVariable String traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to get Revalidation");
    Optional<RevalidationDTO> revalidationDTO = revalidationService.findAssessmentRevalidationBy(traineeId, assessmentId);
    return ResponseUtil.wrapOrNotFound(revalidationDTO);
  }

  /**
   * POST  /:traineeId/assessments/:assessmentId/revalidations : create a revalidation that's linked to a trainees assessment.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the revalidation, or with status 404 (Not Found)
   */
  @PostMapping("/{traineeId}/assessments/{assessmentId}/revalidations")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<RevalidationDTO> createTraineeAssessmentRevalidation(@RequestBody @Validated(Create.class) RevalidationDTO revalidationDTO,
                                                                             @PathVariable String traineeId,
                                                                             @PathVariable Long assessmentId) {
    log.debug("REST request to create Revalidation : {}", revalidationDTO);
    Optional<Assessment> traineeAssessment = assessmentService.findTraineeAssessment(traineeId, assessmentId);
    RevalidationDTO savedRevalidation = null;
    if (traineeAssessment.isPresent()) {
      savedRevalidation = revalidationService.create(traineeAssessment.get(), revalidationDTO);
    }
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(savedRevalidation));

  }

  /**
   * PUT  /:traineeId/assessments/:assessmentId/revalidations : update a revalidation that's linked to a trainee.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the revalidationDTO, or with status 404 (Not Found)
   */
  @PutMapping("/{traineeId}/assessments/{assessmentId}/revalidations")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<RevalidationDTO> updateTraineeAssessmentRevalidation(@RequestBody @Validated(Update.class) RevalidationDTO revalidationDTO,
                                                                            @PathVariable String traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to update Revalidation : {}", revalidationDTO);
    Optional<Assessment> traineeAssessment = assessmentService.findTraineeAssessment(traineeId, assessmentId);
    RevalidationDTO savedRevalidation = null;
    if (traineeAssessment.isPresent()) {
      savedRevalidation = revalidationService.save(traineeAssessment.get(), revalidationDTO);
    }
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(savedRevalidation));

  }

  //Kept to allow compatibility with audit service
  private ResponseEntity<RevalidationDTO> getRevalidation(Long revalidationId) {
    RevalidationDTO revalidationDTO = revalidationService.findOne(revalidationId);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(revalidationDTO));
  }

}
