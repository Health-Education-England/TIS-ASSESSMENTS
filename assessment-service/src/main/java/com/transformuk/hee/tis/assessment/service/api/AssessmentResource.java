package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.service.exception.BadRequestAlertException;
import com.transformuk.hee.tis.assessment.service.api.util.HeaderUtil;
import com.transformuk.hee.tis.assessment.service.api.util.PaginationUtil;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Assessment.
 */
@RestController
@RequestMapping("/api")
public class AssessmentResource {

  private static final String ENTITY_NAME = "assessment";
  private final Logger log = LoggerFactory.getLogger(AssessmentResource.class);
  private final AssessmentService assessmentService;

  public AssessmentResource(AssessmentService assessmentService) {
    this.assessmentService = assessmentService;
  }

  /**
   * POST  /assessments : Create a new assessment.
   *
   * @param assessmentDTO the assessmentDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new assessmentDTO, or with status 400 (Bad Request) if the assessment has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/assessments")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<AssessmentDTO> createAssessment(@RequestBody AssessmentDTO assessmentDTO) throws URISyntaxException {
    log.debug("REST request to save Assessment : {}", assessmentDTO);
    if (assessmentDTO.getId() != null) {
      throw new BadRequestAlertException("A new assessment cannot already have an ID", ENTITY_NAME, "idexists");
    }
    AssessmentDTO result = assessmentService.save(assessmentDTO);
    return ResponseEntity.created(new URI("/api/assessments/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT  /assessments : Updates an existing assessment.
   *
   * @param assessmentDTO the assessmentDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated assessmentDTO,
   * or with status 400 (Bad Request) if the assessmentDTO is not valid,
   * or with status 500 (Internal Server Error) if the assessmentDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/assessments")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<AssessmentDTO> updateAssessment(@RequestBody AssessmentDTO assessmentDTO) throws URISyntaxException {
    log.debug("REST request to update Assessment : {}", assessmentDTO);
    if (assessmentDTO.getId() == null) {
      return createAssessment(assessmentDTO);
    }
    AssessmentDTO result = assessmentService.save(assessmentDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assessmentDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET  /assessments : get all the assessments.
   *
   * @param pageable the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of assessments in body
   */
  @GetMapping("/assessments")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<List<AssessmentDTO>> getAllAssessments(@ApiParam Pageable pageable) {
    log.debug("REST request to get a page of Assessments");
    Page<AssessmentDTO> page = assessmentService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/assessments");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * GET  /assessments/:id : get the "id" assessment.
   *
   * @param id the id of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @GetMapping("/assessments/{id}")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentDTO> getAssessment(@PathVariable Long id) {
    log.debug("REST request to get Assessment : {}", id);
    AssessmentDTO assessmentDTO = assessmentService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(assessmentDTO));
  }

  /**
   * DELETE  /assessments/:id : delete the "id" assessment.
   *
   * @param id the id of the assessmentDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/assessments/{id}")
  @Timed
  @PreAuthorize("hasAuthority('assessment:delete:entities')")
  public ResponseEntity<Void> deleteAssessment(@PathVariable Long id) {
    log.debug("REST request to delete Assessment : {}", id);
    assessmentService.delete(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
  }
}
