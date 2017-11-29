package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.service.AssessmentDetailService;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
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

  private static final String ENTITY_NAME = "AssessmentDetail";
  private final Logger log = LoggerFactory.getLogger(AssessmentDetailResource.class);
  @Autowired
  private AssessmentDetailService assessmentDetailService;
  @Autowired
  private AssessmentService assessmentService;

  /**
   * GET  /:traineeId/assessments/:assessmentId/details : get the an assessment that's linked to a trainee.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDetailDTO, or with status 404 (Not Found)
   */
  @GetMapping("/{traineeId}/assessments/{assessmentId}/details")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentDetailDTO> getTraineeAssessmentDetails(@PathVariable String traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to get AssessmentDetail");
    Optional<AssessmentDetailDTO> assessmentDetailDTO = assessmentDetailService.findAssessmentDetailBy(traineeId, assessmentId);
    return ResponseUtil.wrapOrNotFound(assessmentDetailDTO);
  }

  /**
   * POST  /:traineeId/assessments/:assessmentId : get the an assessment that's linked to a trainee.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDetailDTO, or with status 404 (Not Found)
   */
  @PostMapping("/{traineeId}/assessments/{assessmentId}/details")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentDetailDTO> createTraineeAssessmentDetails(@RequestBody @Validated(Create.class) AssessmentDetailDTO assessmentDetailDTO,
                                                                            @PathVariable String traineeId,
                                                                            @PathVariable Long assessmentId) {
    log.debug("REST request to create AssessmentDetail : {}", assessmentDetailDTO);
    Optional<Assessment> traineeAssessment = assessmentService.findTraineeAssessment(traineeId, assessmentId);
    AssessmentDetailDTO savedAssessmentDetail = null;
    if (traineeAssessment.isPresent()) {
      savedAssessmentDetail = assessmentDetailService.save(traineeAssessment.get(), assessmentDetailDTO);
    }
    return ResponseUtil.wrapOrNotFound(Optional.of(savedAssessmentDetail));

  }

  /**
   * PUT  /:traineeId/assessments/:assessmentId/details : get the an assessment that's linked to a trainee.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDetailDTO, or with status 404 (Not Found)
   */
  @PutMapping("/{traineeId}/assessments/{assessmentId}/details")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentDetailDTO> updateTraineeAssessmentDetails(@RequestBody @Validated(Update.class) AssessmentDetailDTO assessmentDetailDTO,
                                                                            @PathVariable String traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to update AssessmentDetail : {}", assessmentDetailDTO);
    Optional<Assessment> traineeAssessment = assessmentService.findTraineeAssessment(traineeId, assessmentId);
    AssessmentDetailDTO savedAssessmentDetail = null;
    if (traineeAssessment.isPresent()) {
      savedAssessmentDetail = assessmentDetailService.save(traineeAssessment.get(), assessmentDetailDTO);
    }
    return ResponseUtil.wrapOrNotFound(Optional.of(savedAssessmentDetail));

  }

  //Kept to allow compatibility with audit service
  private ResponseEntity<AssessmentDetailDTO> getAssessmentDetail(Long assessmentDetailId) {
    AssessmentDetailDTO assessmentDetailDTO = assessmentDetailService.findOne(assessmentDetailId);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(assessmentDetailDTO));
  }

}
