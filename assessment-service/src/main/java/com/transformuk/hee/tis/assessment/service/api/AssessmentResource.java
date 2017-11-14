package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.EventStatus;
import com.transformuk.hee.tis.assessment.service.api.util.ColumnFilterUtil;
import com.transformuk.hee.tis.assessment.service.exception.BadRequestAlertException;
import com.transformuk.hee.tis.assessment.service.api.util.HeaderUtil;
import com.transformuk.hee.tis.assessment.service.api.util.PaginationUtil;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import io.github.jhipster.web.util.ResponseUtil;
import io.jsonwebtoken.lang.Collections;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.transformuk.hee.tis.assessment.service.api.util.StringUtil.sanitize;

/**
 * REST controller for managing Assessment.
 */
@RestController
@RequestMapping("/api")
public class AssessmentResource {

  private static final String ENTITY_NAME = "assessment";
  private static final String REQUEST_BODY_EMPTY = "request.body.empty";
  private static final String REQUEST_BODY_CANNOT_BE_EMPTY = "The request body for this end point cannot be empty";
  private static final String BULK_UPDATE_FAILED_NOID = "bulk.update.failed.noId";
  private static final String NOID_ERR_MSG = "Some DTOs you've provided have no Id, cannot update entities that don't exist";

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
//
//  /**
//   * GET  /assessments : get all the assessments.
//   *
//   * @param pageable the pagination information
//   * @return the ResponseEntity with status 200 (OK) and the list of assessments in body
//   */
//  @GetMapping("/assessments")
//  @Timed
//  @PreAuthorize("hasAuthority('assessment:view:entities')")
//  public ResponseEntity<List<AssessmentDTO>> getAllAssessments(@ApiParam Pageable pageable) {
//    log.debug("REST request to get a page of Assessments");
//    Page<AssessmentDTO> page = assessmentService.findAll(pageable);
//    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/assessments");
//    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//  }

  /**
   * GET  /assessments : get all the assessments.
   *
   * @param pageable the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of assessments in body
   */
  @ApiOperation(value = "Lists Assessment data",
      notes = "Returns a list of assessments with support for pagination, sorting, smart search and column filters \n",
      response = ResponseEntity.class, responseContainer = "Person list")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Assessment list", response = ResponseEntity.class)})
  @GetMapping("/assessments")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<List<AssessmentDTO>> getAllAssessments(
      @ApiParam Pageable pageable,
      @ApiParam(value = "any wildcard string to be searched")
      @RequestParam(value = "searchQuery", required = false) String searchQuery,
      @ApiParam(value = "json object by column name and value. (Eg: columnFilters={ \"status\": [\"CURRENT\"]}\"")
      @RequestParam(value = "columnFilters", required = false) String columnFilterJson) throws IOException {
    log.debug("REST request to get a page of People");
    searchQuery = sanitize(searchQuery);
    List<Class> filterEnumList = Lists.newArrayList(EventStatus.class);
    List<ColumnFilter> columnFilters = ColumnFilterUtil.getColumnFilters(columnFilterJson, filterEnumList);
    Page<AssessmentDTO> page;
    if (StringUtils.isEmpty(searchQuery) && StringUtils.isEmpty(columnFilterJson)) {
      page = assessmentService.findAll(pageable);
    } else {
      page = assessmentService.advancedSearch(searchQuery, columnFilters, pageable);
    }
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/people");
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





  /**
   * POST  /bulk-assessments : Bulk create a new Assessments.
   *
   * @param assessmentDTOS List of the AssessmentDTO to create
   * @return the ResponseEntity with status 200 (Created) and with body the new assessmentDTOS, or with status 400 (Bad Request) if the Assessment already has an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/bulk-assessments")
  @Timed
  @PreAuthorize("hasAuthority('assessment:bulk:add:modify:entities')")
  public ResponseEntity<List<AssessmentDTO>> bulkCreateAssessments(@Valid @RequestBody List<AssessmentDTO> assessmentDTOS) {
    log.debug("REST request to bulk save Assessment : {}", assessmentDTOS);
    if (!Collections.isEmpty(assessmentDTOS)) {
      List<Long> entityIds = assessmentDTOS.stream()
          .filter(p -> p.getId() != null)
          .map(AssessmentDTO::getId)
          .collect(Collectors.toList());
      if (!Collections.isEmpty(entityIds)) {
        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(StringUtils.join(entityIds, ","), "ids.exist", "A new Assessment cannot already have an ID")).body(null);
      }
    }
    List<AssessmentDTO> result = assessmentService.save(assessmentDTOS);
    List<Long> ids = result.stream().map(AssessmentDTO::getId).collect(Collectors.toList());
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, StringUtils.join(ids, ",")))
        .body(result);
  }

  /**
   * PUT  /bulk-assessments : Updates a collections of existing Assessments.
   *
   * @param assessmentDTOS List of the assessmentDTOS to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated assessmentDTOS,
   * or with status 400 (Bad Request) if the assessmentDTOS is not valid,
   * or with status 500 (Internal Server Error) if the assessmentDTOS couldnt be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/bulk-assessments")
  @Timed
  @PreAuthorize("hasAuthority('assessment:bulk:add:modify:entities')")
  public ResponseEntity<List<AssessmentDTO>> bulkUpdateAssessments(@Valid @RequestBody List<AssessmentDTO> assessmentDTOS) {
    log.debug("REST request to bulk update Posts : {}", assessmentDTOS);
    if (Collections.isEmpty(assessmentDTOS)) {
      return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, REQUEST_BODY_EMPTY,
          REQUEST_BODY_CANNOT_BE_EMPTY)).body(null);
    } else if (!Collections.isEmpty(assessmentDTOS)) {
      List<AssessmentDTO> entitiesWithNoId = assessmentDTOS.stream().filter(p -> p.getId() == null).collect(Collectors.toList());
      if (!Collections.isEmpty(entitiesWithNoId)) {
        return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(StringUtils.join(entitiesWithNoId, ","),
            BULK_UPDATE_FAILED_NOID, NOID_ERR_MSG)).body(entitiesWithNoId);
      }
    }

    List<AssessmentDTO> results = assessmentService.save(assessmentDTOS);
    List<Long> ids = results.stream().map(AssessmentDTO::getId).collect(Collectors.toList());
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, StringUtils.join(ids, ",")))
        .body(results);
  }

}
