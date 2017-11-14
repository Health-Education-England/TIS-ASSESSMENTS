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
@RequestMapping("/api")
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
   * POST  /outcomes : Create a new outcome.
   *
   * @param outcomeDTO the outcomeDTO to create
   * @return the ResponseEntity with status 201 (Created) and with body the new outcomeDTO, or with status 400 (Bad Request) if the outcome has already an ID
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PostMapping("/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('outcomes:add:modify:entities')")
  public ResponseEntity<OutcomeDTO> createOutcome(@RequestBody OutcomeDTO outcomeDTO) throws URISyntaxException {
    log.debug("REST request to save Outcome : {}", outcomeDTO);
    if (outcomeDTO.getId() != null) {
      throw new BadRequestAlertException("A new outcome cannot already have an ID", ENTITY_NAME, "idexists");
    }
    OutcomeDTO result = outcomeService.save(outcomeDTO);
    return ResponseEntity.created(new URI("/api/outcomes/" + result.getId()))
        .headers(HeaderUtil
            .createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT  /outcomes : Updates an existing outcome.
   *
   * @param outcomeDTO the outcomeDTO to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated outcomeDTO,
   * or with status 400 (Bad Request) if the outcomeDTO is not valid,
   * or with status 500 (Internal Server Error) if the outcomeDTO couldn't be updated
   * @throws URISyntaxException if the Location URI syntax is incorrect
   */
  @PutMapping("/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('outcomes:add:modify:entities')")
  public ResponseEntity<OutcomeDTO> updateOutcome(@RequestBody OutcomeDTO outcomeDTO) throws URISyntaxException {
    log.debug("REST request to update Outcome : {}", outcomeDTO);
    if (outcomeDTO.getId() == null) {
      return createOutcome(outcomeDTO);
    }
    OutcomeDTO result = outcomeService.save(outcomeDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, outcomeDTO.getId().toString()))
        .body(result);
  }

  /**
   * GET  /outcomes : get all the outcomes.
   *
   * @return the ResponseEntity with status 200 (OK) and the list of outcomes in body
   */
  @GetMapping("/outcomes")
  @Timed
  @PreAuthorize("hasAuthority('outcomes:view:entities')")
  public List<OutcomeDTO> getAllOutcomes() {
    log.debug("REST request to get all Outcomes");
    return outcomeService.findAll();
  }

  /**
   * GET  /outcomes/:id : get the "id" outcome.
   *
   * @param id the id of the outcomeDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the outcomeDTO, or with status 404 (Not Found)
   */
  @GetMapping("/outcomes/{id}")
  @Timed
  @PreAuthorize("hasAuthority('outcomes:view:entities')")
  public ResponseEntity<OutcomeDTO> getOutcome(@PathVariable Long id) {
    log.debug("REST request to get Outcome : {}", id);
    OutcomeDTO outcomeDTO = outcomeService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(outcomeDTO));
  }

  /**
   * DELETE  /outcomes/:id : delete the "id" outcome.
   *
   * @param id the id of the outcomeDTO to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/outcomes/{id}")
  @Timed
  @PreAuthorize("hasAuthority('outcomes:delete:entities')")
  public ResponseEntity<Void> deleteOutcome(@PathVariable Long id) {
    log.debug("REST request to delete Outcome : {}", id);
    outcomeService.delete(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
  }



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
