package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.api.dto.EventStatus;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeStatus;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import com.transformuk.hee.tis.assessment.service.api.util.ColumnFilterUtil;
import com.transformuk.hee.tis.assessment.service.api.util.HeaderUtil;
import com.transformuk.hee.tis.assessment.service.api.util.PaginationUtil;
import com.transformuk.hee.tis.assessment.service.exception.BadRequestAlertException;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uk.nhs.tis.StringConverter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Assessment.
 */
@RestController
@RequestMapping("/api/trainee")
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
  public ResponseEntity<List<AssessmentListDTO>> getAllAssessments(
      @ApiParam Pageable pageable,
      @ApiParam(value = "any wildcard string to be searched")
      @RequestParam(value = "searchQuery", required = false) String searchQuery,
      @ApiParam(value = "json object by column name and value. (Eg: columnFilters={ \"status\": [\"CURRENT\"]}\"")
      @RequestParam(value = "columnFilters", required = false) String columnFilterJson) throws IOException {
    log.debug("REST request to get a page of People");
    searchQuery = StringConverter.getConverter(searchQuery).fromJson().decodeUrl().escapeForSql().toString();
    List<Class> filterEnumList = Lists.newArrayList(EventStatus.class, OutcomeStatus.class);
    List<ColumnFilter> columnFilters = ColumnFilterUtil.getColumnFilters(columnFilterJson, filterEnumList);
    Page<AssessmentListDTO> page;
    if (StringUtils.isEmpty(searchQuery) && StringUtils.isEmpty(columnFilterJson)) {
      page = assessmentService.advancedSearch(null, null, pageable);
    } else {
      page = assessmentService.advancedSearch(searchQuery, columnFilters, pageable);
    }
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/assessments");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * GET  /:traineeId/assessments : get assessments for a trainee in a paginated format
   *
   * @param traineeId the id of the trainee
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @GetMapping("/{traineeId}/assessments")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<List<AssessmentDTO>> getTraineeAssessments(@PathVariable Long traineeId, @ApiParam Pageable page) {
    Page<AssessmentDTO> assessmentForTrainee = assessmentService.findForTrainee(traineeId, page);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(assessmentForTrainee, "/api/" + traineeId + "/assessments");
    return new ResponseEntity<>(assessmentForTrainee.getContent(), headers, HttpStatus.OK);
  }

  /**
   * GET  /:traineeId/assessments/all : get all assessments for a trainee
   *
   * @param traineeId the id of the trainee
   * @param sort the sort order of the assessments. Defaults to reviewDate desc if none is provided
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @GetMapping("/{traineeId}/assessments/all")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<List<AssessmentDTO>> getAllTraineeAssessments(@PathVariable Long traineeId,
                                                                      @SortDefault.SortDefaults({
                                                                          @SortDefault(sort = "reviewDate", direction = Sort.Direction.DESC),
                                                                      }) Sort sort) {
    List<AssessmentDTO> assessmentForTrainee = assessmentService.findAllForTrainee(traineeId, sort);
    return new ResponseEntity<>(assessmentForTrainee, HttpStatus.OK);
  }

  /**
   * POST  /:traineeId/assessments : Create a new assessment.
   *
   * @param traineeId     the trainee Id to link the new assessment with
   * @param assessmentDTO the assessmentDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new assessmentDTO, or with status 400 (Bad Request) if the assessment has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/{traineeId}/assessments")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<AssessmentDTO> createTraineeAssessment(@RequestBody @Validated(Create.class) AssessmentDTO assessmentDTO, @PathVariable Long traineeId) throws URISyntaxException {
    log.debug("REST request to save Assessment : {}", assessmentDTO);
    if (assessmentDTO.getId() != null) {
      throw new BadRequestAlertException("A new assessment cannot already have an ID", ENTITY_NAME, "idexists");
    }

    if (!traineeId.equals(assessmentDTO.getTraineeId())) {
      throw new BadRequestAlertException("A new assessment does not have the same trainee id as uri path", ENTITY_NAME, "idconflict");
    }
    AssessmentDTO result = assessmentService.save(assessmentDTO);
    return ResponseEntity.created(new URI("/api/assessments/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT  /assessments : Updates an existing assessment.
   *
   * @param traineeId     the trainee Id to link the new assessment with
   * @param assessmentDTO the assessmentDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated assessmentDTO,
   * or with status 400 (Bad Request) if the assessmentDTO is not valid,
   * or with status 500 (Internal Server Error) if the assessmentDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/{traineeId}/assessments")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<AssessmentDTO> updateTraineeAssessment(@RequestBody @Validated(Update.class) AssessmentDTO assessmentDTO,
                                                               @PathVariable Long traineeId) throws URISyntaxException {
    log.debug("REST request to update Assessment : {}", assessmentDTO);
    if (assessmentDTO.getId() == null) {
      return createTraineeAssessment(assessmentDTO, traineeId);
    }
    AssessmentDTO result = assessmentService.save(assessmentDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assessmentDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET  /:traineeId/assessments/:assessmentId : get the an assessment thats linked to a trainee.
   *
   * @param traineeId    the assessmentId of the trainee
   * @param assessmentId the assessmentId of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @GetMapping("/{traineeId}/assessments/{assessmentId}")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentDTO> getTraineeAssessment(@PathVariable Long traineeId, @PathVariable Long assessmentId) {
    log.debug("REST request to get Assessment : {}", assessmentId);
    Optional<AssessmentDTO> assessmentDTO = assessmentService.findTraineeAssessmentDTO(traineeId, assessmentId);
    return ResponseUtil.wrapOrNotFound(assessmentDTO);
  }

  /**
   * POST  /:traineeId/assessments/:assessmentId : get the an assessment thats linked to a trainee.
   *
   * @param traineeId     the id of the trainee
   * @param assessmentId  the id of the assessmentDTO to retrieve
   * @param assessmentDTO the payload of the assessment to create
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PostMapping("/{traineeId}/assessments/{assessmentId}")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<AssessmentDTO> createTraineeAssessment(@RequestBody @Validated(Create.class) AssessmentDTO assessmentDTO,
                                                               @PathVariable Long traineeId, @PathVariable Long assessmentId) throws URISyntaxException {
    if (!traineeId.equals(assessmentDTO.getTraineeId()) || !assessmentId.equals(assessmentDTO.getId())) {
      throw new BadRequestAlertException("Trainee Id or assessment Id do not match the payload Ids", ENTITY_NAME, "idexists");
    }

    return createTraineeAssessment(assessmentDTO, traineeId);
  }

  /**
   * PUT  /:traineeId/assessments/:assessmentId : get the an assessment thats linked to a trainee.
   *
   * @param traineeId     the assessmentId of the trainee
   * @param assessmentId  the assessmentId of the assessmentDTO to retrieve
   * @param assessmentDTO the payload of the assessmentDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @PutMapping("/{traineeId}/assessments/{assessmentId}")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<AssessmentDTO> updateTraineeAssessment(@RequestBody @Validated(Update.class) AssessmentDTO assessmentDTO,
                                                               @PathVariable Long traineeId, @PathVariable Long assessmentId) throws URISyntaxException {
    if (!traineeId.equals(assessmentDTO.getTraineeId()) || !assessmentId.equals(assessmentDTO.getId())) {
      throw new BadRequestAlertException("Trainee Id or assessment Id do not match the payload Ids", ENTITY_NAME, "idexists");
    }
    return updateTraineeAssessment(assessmentDTO, traineeId);
  }


  /**
   * DELETE  /:traineeId/assessments/:assessmentId : delete the a specific trainee assessment.
   *
   * @param traineeId    the assessmentId of the trainee
   * @param assessmentId the assessmentId of the assessmentDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the assessmentDTO, or with status 404 (Not Found)
   */
  @DeleteMapping("/{traineeId}/assessments/{assessmentId}")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<AssessmentDTO> deleteTraineeAssessment(@PathVariable Long traineeId, @PathVariable Long assessmentId) throws URISyntaxException {
    boolean success = assessmentService.deleteTraineeAssessment(assessmentId, traineeId);
    return new ResponseEntity<>(success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  /**
   * DELETE  /assessments/:assessmentId : delete a specific assessment.
   *
   * @param assessmentId the id of the assessment to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/assessments/{assessmentId}")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<Void> deleteAssessment(@PathVariable Long assessmentId) {

    boolean success =  assessmentService.deleteAssessment(assessmentId);

    return new ResponseEntity<>(success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  //Kept to allow compatibility with audit service
  private ResponseEntity<AssessmentDTO> getAssessment(Long assessmentId) {
    AssessmentDTO assessmentDTO = assessmentService.findOne(assessmentId);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(assessmentDTO));
  }

  /**
   * GET /assessments/{assessmentIds} : get assessments By Ids.
   *
   * @param assessmentIds assessment IDs
   * @return the ResponseEntity with status 200 (OK) and the list of assessmentDtos in body
   */
  @GetMapping("/assessments/{assessmentIds}")
  @Timed
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<List<AssessmentDTO>> getAssessmentsByIds(
      @PathVariable String assessmentIds) {

    List<AssessmentDTO> resp = new ArrayList<>();
    if (assessmentIds != null && !assessmentIds.isEmpty()) {
      Set<Long> idSet = Arrays.stream(assessmentIds.split(",")).map(Long::valueOf).collect(
          Collectors.toSet());
      log.debug("REST request to get Assessments for Ids: {}", idSet);
      if (!idSet.isEmpty()) {
        resp = assessmentService.findAssessmentsByIds(idSet);
      }
    }
    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /**
   * PUT /bulk-assessment : bulk update assessments.
   *
   * @param assessmentDtos the list of assessmentDtos
   * @return the ResponseEntity with status 200 (OK) and the list of assessmentDtos saved
   *     or assessmentDto list with error message
   */
  @PutMapping("/bulk-assessment")
  @Timed
  @PreAuthorize("hasAuthority('assessment:add:modify:entities')")
  public ResponseEntity<List<AssessmentDTO>> patchAssessment(
      @RequestBody List<AssessmentDTO> assessmentDtos) {
    log.debug("REST request to patch Assessments with ids: {}",
        assessmentDtos.stream().map(AssessmentDTO::getId).collect(Collectors.toList()));

    List<AssessmentDTO> result = assessmentService.patchAssessments(assessmentDtos);
    final List<String> ids = result.stream().map(dto -> dto.getId().toString())
        .collect(Collectors.toList());

    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, StringUtils.join(ids, ",")))
        .body(result);
  }
}
