package com.transformuk.hee.tis.assessment.service.api.reference;

import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import com.transformuk.hee.tis.assessment.service.api.util.HeaderUtil;
import com.transformuk.hee.tis.assessment.service.api.util.PaginationUtil;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import com.transformuk.hee.tis.assessment.service.repository.reference.OutcomeRepository;
import com.transformuk.hee.tis.assessment.service.repository.reference.ReasonRepository;
import com.transformuk.hee.tis.assessment.service.service.ReasonService;
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
public class ReasonResource {

  private static final String ENTITY_NAME = "Reason";
  private final Logger log = LoggerFactory.getLogger(ReasonResource.class);
  @Autowired
  private ReasonRepository reasonRepository;
  @Autowired
  private ReasonService reasonService;
  @Autowired
  private OutcomeRepository outcomeRepository;

  /**
   * GET  /:id : get a reason by id.
   *
   * @param id the id of the reason
   * @return the ResponseEntity with status 200 (OK) and with body the reason, or with status 404 (Not Found)
   */
  @GetMapping("/reasons/{id}")
  public ResponseEntity<Reason> getReason(@PathVariable Long id) {
    log.debug("REST request to get Reason with id: [{}]", id);
    Optional<Reason> reason = Optional.ofNullable(reasonRepository.findById(id)).orElse(null);
    return ResponseUtil.wrapOrNotFound(reason);
  }


  /**
   * GET  / : get all the Reasons.
   *
   * @param pageable the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of Reason in body
   * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
   */
  @GetMapping("/reasons")
  @PreAuthorize("hasAuthority('assessment:view:entities')")
  public ResponseEntity<List<Reason>> reasonSmartSearch(
    Pageable pageable,
    @RequestParam(value = "searchQuery", required = false) String searchQuery) throws IOException {
    log.debug("REST request to get a page of Curricula");
    searchQuery = sanitize(searchQuery);
    Page<Reason> page;
    if (StringUtils.isEmpty(searchQuery)) {
      page = reasonRepository.findAll(pageable);
    } else {
      page = reasonService.advancedSearch(searchQuery, pageable);
    }
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reasons");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * POST  / : create a new Reason.
   *
   * @return the ResponseEntity with status 200 (OK) and with body the reason
   */
  @PostMapping("/reasons")
  public ResponseEntity<Reason> createReason(@RequestBody @Validated(Create.class) Reason reason) throws URISyntaxException {
    log.debug("REST request to create new Reason with code [{}]", reason.getCode());
    Reason result = reasonRepository.save(reason);
    return ResponseEntity.created(new URI("/api/reasons/" + result.getCode()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getCode()))
        .body(result);

  }

  /**
   * PUT  / create or update reason
   * <p>
   * * @return the ResponseEntity with status 200 (OK) and with body the Reason
   */
  @PutMapping("/reasons")
  public ResponseEntity<Reason> updateOrCreateReason(@RequestBody @Validated(Update.class) Reason reason) throws URISyntaxException {
    log.debug("REST request to update Reason with code: [{}]", reason.getCode());
    if (reason.getId() == null) {
      return createReason(reason);
    }
    Reason result = reasonRepository.save(reason);
    return ResponseEntity.ok(result);
  }


  /**
   * GET  /outcomes/:id/reasons : get a reasons by that are valid against an outcome.
   *
   * @param id the id of the outcome
   * @return the ResponseEntity with status 200 (OK) and with body containing a list of reasons, or with status 404 (Not Found)
   */
  @GetMapping("/outcomes/{id}/reasons")
  public ResponseEntity<Set<Reason>> getOutcomeReasons(@PathVariable Long id) {
    log.debug("REST request to get Reasons with for Outcome id: [{}]", id);

    Outcome outcome = outcomeRepository.findById(id).orElse(null);
    Set<Reason> reasons;
    if (outcome != null) {
      reasons = reasonRepository.findByOutcome(outcome);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(reasons);
  }
}
