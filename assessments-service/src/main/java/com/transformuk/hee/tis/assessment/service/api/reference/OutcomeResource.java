package com.transformuk.hee.tis.assessment.service.api.reference;

import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import com.transformuk.hee.tis.assessment.service.api.util.HeaderUtil;
import com.transformuk.hee.tis.assessment.service.api.util.PaginationUtil;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.repository.reference.OutcomeRepository;
import com.transformuk.hee.tis.assessment.service.service.OutcomeService;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.transformuk.hee.tis.assessment.service.api.util.StringUtil.sanitize;

@RestController
@RequestMapping("/api")
public class OutcomeResource {

  private static final String ENTITY_NAME = "Outcome";
  private final Logger log = LoggerFactory.getLogger(OutcomeResource.class);
  @Autowired
  private OutcomeRepository outcomeRepository;
  @Autowired
  private OutcomeService outcomeService;

  /**
   * GET  /:id : get a outcome by id.
   *
   * @param id the id of the outcome
   * @return the ResponseEntity with status 200 (OK) and with body the outcome, or with status 404 (Not Found)
   */
  @GetMapping("/outcomes/{id}")
  public ResponseEntity<Outcome> getOutcome(@PathVariable Long id) {
    log.debug("REST request to get Outcome with id: [{}]", id);
    Optional<Outcome> outcome = outcomeRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(outcome);
  }


  /**
   * GET  / : get all the Outcomes.
   *
   * @param pageable the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of Outcome in body
   * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
   */
  @GetMapping("/outcomes")
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<List<Outcome>> outcomeSmartSearch(
    Pageable pageable,
    @RequestParam(value = "searchQuery", required = false) String searchQuery) throws IOException {
    log.debug("REST request to get a page of Curricula");
    searchQuery = sanitize(searchQuery);
    Page<Outcome> page;
    if (StringUtils.isEmpty(searchQuery)) {
      page = (outcomeRepository.findAll(pageable));
    } else {
      page = outcomeService.advancedSearch(searchQuery, pageable);
    }
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/outcomes");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  @GetMapping("/outcomes/all")
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<Set<Outcome>> allOutcomes() {
    Set<Outcome> allOutcomesWithReasons = outcomeRepository.findAllWithReasons();
    return new ResponseEntity<>(allOutcomesWithReasons, HttpStatus.OK);
  }

  /**
   * POST  / : create a new Outcome.
   *
   * @return the ResponseEntity with status 200 (OK) and with body the outcome
   */
  @PostMapping("/outcomes")
  public ResponseEntity<Outcome> createOutcome(@RequestBody @Validated(Create.class) Outcome outcome) throws URISyntaxException {
    log.debug("REST request to create new Outcome with code [{}]", outcome.getCode());
    Outcome result = outcomeRepository.save(outcome);
    return ResponseEntity.created(new URI("/api/outcomes/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);

  }

  /**
   * PUT  / create or update outcome
   * <p>
   * * @return the ResponseEntity with status 200 (OK) and with body the Outcome
   */
  @PutMapping("/outcomes")
  public ResponseEntity<Outcome> updateOrCreateOutcome(@RequestBody @Validated(Update.class) Outcome outcome) throws URISyntaxException {
    log.debug("REST request to update Outcome with code: [{}]", outcome.getCode());
    if (outcome.getId() == null) {
      return createOutcome(outcome);
    }
    Outcome result = outcomeRepository.save(outcome);
    return ResponseEntity.ok(result);
  }


}
