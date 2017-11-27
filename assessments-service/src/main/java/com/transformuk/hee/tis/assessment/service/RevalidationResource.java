package com.transformuk.hee.tis.assessment.service;

import com.codahale.metrics.annotation.Timed;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.service.service.RevalidationService;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/trainee")
public class RevalidationResource {

  private RevalidationService revalidationService;

  /**
   * GET  /:traineeId/assessments/:assessmentId/revalidations : get the a revalidation that's linked to a trainee's assessment.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @GetMapping("/{traineeId}/assessments/{assessmentId}/revalidation")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<OutcomeDTO> getTraineeAssessmentRevalidation(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    return ResponseUtil.wrapOrNotFound(Optional.empty());
  }

  /**
   * POST  /:traineeId/assessments/:assessmentId/revalidations : create/update the a revalidation that's linked to a trainee's assessment.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PostMapping("/{traineeId}/assessments/{assessmentId}/revalidation")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<OutcomeDTO> createTraineeAssessmentRevalidation(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    return ResponseUtil.wrapOrNotFound(Optional.empty());
  }

  /**
   * PUT  /:traineeId/assessments/:assessmentId/revalidations : update the a revalidation that's linked to a trainee's assessment.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PutMapping("/{traineeId}/assessments/{assessmentId}/revalidations")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<OutcomeDTO> updateTraineeAssessmentRevalidation(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    return ResponseUtil.wrapOrNotFound(Optional.empty());
  }

}
