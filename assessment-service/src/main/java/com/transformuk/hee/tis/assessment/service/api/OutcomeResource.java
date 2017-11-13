package com.transformuk.hee.tis.assessment.service.api;

import com.codahale.metrics.annotation.Timed;
import com.transformuk.hee.tis.assessment.service.exception.BadRequestAlertException;
import com.transformuk.hee.tis.assessment.service.api.util.HeaderUtil;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.service.service.OutcomeService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
 * REST controller for managing Outcome.
 */
@RestController
@RequestMapping("/api")
public class OutcomeResource {

  private static final String ENTITY_NAME = "outcome";
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
  public ResponseEntity<Void> deleteOutcome(@PathVariable Long id) {
    log.debug("REST request to delete Outcome : {}", id);
    outcomeService.delete(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
  }
}
