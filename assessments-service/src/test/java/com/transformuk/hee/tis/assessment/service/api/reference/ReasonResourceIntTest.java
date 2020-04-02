package com.transformuk.hee.tis.assessment.service.api.reference;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.common.collect.Sets;
import com.transformuk.hee.tis.assessment.api.dto.ReasonDTO;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.exception.ExceptionTranslator;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import com.transformuk.hee.tis.assessment.service.repository.reference.OutcomeRepository;
import com.transformuk.hee.tis.assessment.service.repository.reference.ReasonRepository;
import com.transformuk.hee.tis.assessment.service.service.ReasonService;
import com.transformuk.hee.tis.assessment.service.service.mapper.ReasonMapper;
import java.net.URI;
import java.util.List;
import java.util.Set;
import org.assertj.core.util.Lists;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReasonResourceIntTest {

  private static final long OUTCOME_ID = 1L;
  private static final Long REASON_ID = 1L;
  private static final String REASON_CODE = "reasoncode1";
  private static final String REASON_LABEL = "Reason label";
  private static final Long REASON2_ID = 2L;
  private static final String REASON2_CODE = "reasoncode2";
  private static final String REASON2_LABEL = "Reason 2 label";
  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @InjectMocks
  private ReasonResource testObj;

  @MockBean
  private ReasonRepository reasonRepositoryMock;

  @MockBean
  private OutcomeRepository outcomeRepositoryMock;

  @MockBean
  private ReasonService reasonServiceMock;

  @MockBean
  private Outcome outcomeMock;

  @MockBean
  private ReasonMapper reasonMapperMock;

  private MockMvc restReasonMockMvc;

  @Captor
  private ArgumentCaptor<String> stringCaptor;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.restReasonMockMvc = MockMvcBuilders.standaloneSetup(testObj)
        .setCustomArgumentResolvers(pageableArgumentResolver)
        .setControllerAdvice(exceptionTranslator)
        .setConversionService(TestUtil.createFormattingConversionService())
        .setMessageConverters(jacksonMessageConverter).build();

  }

  @Test
  public void getReasonByIdShouldReturnReason() throws Exception {
    Reason reason = new Reason().id(REASON_ID).code(REASON_CODE).label(REASON_LABEL);

    when(reasonRepositoryMock.findOne(REASON_ID)).thenReturn(reason);

    this.restReasonMockMvc.perform(MockMvcRequestBuilders.get("/api/reasons/{id}", 1))
        .andExpect(jsonPath("$.id").value(REASON_ID.intValue()))
        .andExpect(jsonPath("$.code").value(REASON_CODE))
        .andExpect(jsonPath("$.label").value(REASON_LABEL));

    verify(reasonRepositoryMock).findOne(REASON_ID);
  }

  @Test
  public void getReasonByIdShouldReturnNotFound() throws Exception {
    when(reasonRepositoryMock.findOne(REASON_ID)).thenReturn(null);

    this.restReasonMockMvc.perform(MockMvcRequestBuilders.get("/api/reasons/{id}", 1))
        .andExpect(status().isNotFound());

    verify(reasonRepositoryMock).findOne(REASON_ID);
  }

  @Test
  public void createReasonShouldReturnNewlyCreatedReason() throws Exception {
    Reason reason = new Reason().code(REASON_CODE).label(REASON_LABEL);
    Reason savedReason = new Reason().id(REASON_ID).code(REASON_CODE).label(REASON_LABEL);
    ReasonDTO savedReasonDto = new ReasonDTO();
    savedReasonDto.setId(REASON_ID);
    savedReasonDto.setCode(REASON_CODE);
    savedReasonDto.setLabel(REASON_LABEL);

    when(reasonMapperMock.toEntity(any())).thenReturn(reason);
    when(reasonMapperMock.toDto(any())).thenReturn(savedReasonDto);
    when(reasonRepositoryMock.save(isA(Reason.class))).thenReturn(savedReason);

    this.restReasonMockMvc.perform(MockMvcRequestBuilders.post("/api/reasons")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(reason)))
        .andExpect(status().isCreated());
  }

  @Test
  public void updateReasonWithNoIdShouldReturnNewlyCreatedReason() throws Exception {
    Reason reason = new Reason().code(REASON_CODE).label(REASON_LABEL);
    Reason savedReason = new Reason().id(REASON_ID).code(REASON_CODE).label(REASON_LABEL);
    ReasonDTO savedReasonDto = new ReasonDTO();
    savedReasonDto.setId(REASON_ID);
    savedReasonDto.setCode(REASON_CODE);
    savedReasonDto.setLabel(REASON_LABEL);

    when(reasonMapperMock.toEntity(any())).thenReturn(reason);
    when(reasonMapperMock.toDto(any())).thenReturn(savedReasonDto);
    when(reasonRepositoryMock.save(isA(Reason.class))).thenReturn(savedReason);

    this.restReasonMockMvc.perform(MockMvcRequestBuilders.put("/api/reasons")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(reason)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(REASON_ID))
        .andExpect(jsonPath("$.code").value(REASON_CODE))
        .andExpect(jsonPath("$.label").value(REASON_LABEL));
  }

  @Test
  public void updateReasonWithIdShouldReturnUpdatedReason() throws Exception {
    Reason reason = new Reason().id(REASON_ID).code(REASON_CODE).label(REASON_LABEL);
    Reason savedReason = new Reason().id(REASON_ID).code(REASON_CODE).label(REASON_LABEL);
    ReasonDTO savedReasonDto = new ReasonDTO();
    savedReasonDto.setId(REASON_ID);
    savedReasonDto.setCode(REASON_CODE);
    savedReasonDto.setLabel(REASON_LABEL);

    when(reasonMapperMock.toEntity(any())).thenReturn(reason);
    when(reasonMapperMock.toDto(any())).thenReturn(savedReasonDto);
    when(reasonRepositoryMock.save(isA(Reason.class))).thenReturn(savedReason);

    this.restReasonMockMvc.perform(MockMvcRequestBuilders.put("/api/reasons")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(reason)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(REASON_ID))
        .andExpect(jsonPath("$.code").value(REASON_CODE))
        .andExpect(jsonPath("$.label").value(REASON_LABEL));
  }

  @Test
  public void reasonSmartSearchShouldReturnAllPaginatedReasonsWhenNoCriteriaProvided()
      throws Exception {
    Reason reason1 = new Reason().id(REASON_ID).code(REASON_CODE).label(REASON_LABEL);
    Reason reason2 = new Reason().id(REASON2_ID).code(REASON2_CODE).label(REASON2_LABEL);
    List<Reason> reasonsList = Lists.newArrayList(reason1, reason2);
    Page<Reason> reasons = new PageImpl<>(reasonsList);

    when(reasonRepositoryMock.findAll(isA(Pageable.class))).thenReturn(reasons);

    this.restReasonMockMvc.perform(MockMvcRequestBuilders.get("/api/reasons")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(header().string("X-Total-Count", "2"))
        .andExpect(
            jsonPath("$.[*].id").value(hasItems(REASON_ID.intValue(), REASON2_ID.intValue())))
        .andExpect(jsonPath("$.[*].code").value(hasItems(REASON_CODE, REASON2_CODE)))
        .andExpect(jsonPath("$.[*].label").value(hasItems(REASON_LABEL, REASON2_LABEL)));

    verify(reasonServiceMock, never()).advancedSearch(anyString(), any(Pageable.class));
  }

  @Test
  public void reasonSmartSearchShouldReturnAdvanceSearchResultsWhenCriteriaProvided()
      throws Exception {
    Reason reason2 = new Reason().id(REASON2_ID).code(REASON2_CODE).label(REASON2_LABEL);
    List<Reason> reasonsList = Lists.newArrayList(reason2);
    Page<Reason> reasons = new PageImpl<>(reasonsList);

    when(reasonServiceMock.advancedSearch(eq(REASON2_CODE), any(Pageable.class)))
        .thenReturn(reasons);

    this.restReasonMockMvc
        .perform(MockMvcRequestBuilders.get("/api/reasons?searchQuery=" + REASON2_CODE)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(header().string("X-Total-Count", "1"))
        .andExpect(jsonPath("$.[*].id").value(hasItems(REASON2_ID.intValue())))
        .andExpect(jsonPath("$.[*].code").value(hasItems(REASON2_CODE)))
        .andExpect(jsonPath("$.[*].label").value(hasItems(REASON2_LABEL)));

    verify(reasonRepositoryMock, never()).findAll(any(Pageable.class));
  }

  @Test
  public void getReasonByByOutcomeIdShouldReturnAllReltedReasons() throws Exception {
    Reason reason1 = new Reason().id(REASON_ID).code(REASON_CODE).label(REASON_LABEL);
    Reason reason2 = new Reason().id(REASON2_ID).code(REASON2_CODE).label(REASON2_LABEL);
    Set<Reason> reasons = Sets.newHashSet(reason1, reason2);

    when(outcomeRepositoryMock.findOne(OUTCOME_ID)).thenReturn(outcomeMock);
    when(reasonRepositoryMock.findByOutcome(outcomeMock)).thenReturn(reasons);

    this.restReasonMockMvc.perform(MockMvcRequestBuilders.get("/api/outcomes/{id}/reasons", 1))
        .andExpect(
            jsonPath("$.[*].id").value(hasItems(REASON_ID.intValue(), REASON2_ID.intValue())))
        .andExpect(jsonPath("$.[*].code").value(hasItems(REASON_CODE, REASON2_CODE)))
        .andExpect(jsonPath("$.[*].label").value(hasItems(REASON_LABEL, REASON2_LABEL)));

    verify(outcomeRepositoryMock).findOne(OUTCOME_ID);
    verify(reasonRepositoryMock).findByOutcome(outcomeMock);
  }

  @Test
  public void getReasonByByOutcomeIdShouldReturnNotFoundWhenOutcomeDoesntExist() throws Exception {
    when(outcomeRepositoryMock.findOne(OUTCOME_ID)).thenReturn(null);

    this.restReasonMockMvc.perform(MockMvcRequestBuilders.get("/api/outcomes/{id}/reasons", 1))
        .andExpect(status().isNotFound());

    verify(outcomeRepositoryMock).findOne(OUTCOME_ID);
    verify(reasonRepositoryMock, never()).findByOutcome(any());
  }

  @Test
  public void shouldSanitizeParamWhenGetAllOutcomes() throws Exception {
    Reason reason1 = new Reason().id(REASON_ID).code(REASON_CODE).label(REASON_LABEL);
    Page<Reason> reasons = new PageImpl<>(Lists.newArrayList(reason1));

    when(reasonServiceMock.advancedSearch(stringCaptor.capture(), any(Pageable.class)))
        .thenReturn(reasons);
    this.restReasonMockMvc.perform(MockMvcRequestBuilders.get(new URI(
        "/api/reasons?searchQuery=%22Assessment%252FCurriculum%2520outcomes%2520not%2520achieved%250D%250A%22")))
        .andExpect(status().isOk());

    String converted = stringCaptor.getValue();
    Assert.assertThat("should sanitize param", converted,
        CoreMatchers.equalTo("Assessment/Curriculum outcomes not achieved"));
  }
}
