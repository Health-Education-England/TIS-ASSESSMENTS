package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.service.service.AssessmentDetailService;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.impl.AssessmentServiceImpl;
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
public class AssessmentDetailResource {

  private final Logger log = LoggerFactory.getLogger(AssessmentDetailResource.class);

  @Autowired
  private AssessmentDetailService assessmentDetailService;

  /**
   * GET  /:traineeId/assessments/:assessmentId/details : get the an assessment that's linked to a trainee.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @GetMapping("/{traineeId}/assessments/{assessmentId}/details")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentDetailDTO> getTraineeAssessmentDetails(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    AssessmentDetailDTO assessmentDetailDTO = assessmentDetailService.findOne(assessmentId);
    return ResponseUtil.wrapOrNotFound(Optional.of(assessmentDetailDTO));
  }

  /**
   * POST  /:traineeId/assessments/:assessmentId : get the an assessment that's linked to a trainee.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PostMapping("/{traineeId}/assessments/{assessmentId}/details")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentDetailDTO> createTraineeAssessmentDetails(@PathVariable Long traineeId,
                                                                            @PathVariable Long assessmentId,
                                                                            @RequestBody @Validated(Create.class) AssessmentDetailDTO assessmentDetailDTO) {
    AssessmentDetailDTO savedAssessmentDetail = assessmentDetailService.save(assessmentId, assessmentDetailDTO);
    return ResponseUtil.wrapOrNotFound(Optional.of(savedAssessmentDetail));
  }

  /**
   * PUT  /:traineeId/assessments/:assessmentId/details : get the an assessment that's linked to a trainee.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PutMapping("/{traineeId}/assessments/{assessmentId}/details")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentDetailDTO> updateTraineeAssessmentDetails(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    return ResponseUtil.wrapOrNotFound(Optional.empty());
  }

}
