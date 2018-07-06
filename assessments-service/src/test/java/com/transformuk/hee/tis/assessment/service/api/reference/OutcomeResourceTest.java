package com.transformuk.hee.tis.assessment.service.api.reference;

import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.exception.ExceptionTranslator;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import com.transformuk.hee.tis.assessment.service.repository.reference.OutcomeRepository;
import com.transformuk.hee.tis.assessment.service.service.OutcomeService;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
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

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OutcomeResourceTest {

  private static final Long OUTCOME_ID_1 = 1L;
  private static final Long OUTCOME_ID_2 = 2L;
  private static final String OUTCOME_CODE_1 = "outcomeCode1";
  private static final String OUTCOME_CODE_2 = "outcomeCode2";
  private static final String OUTCOME_LABEL_1 = "Outcome label 1";
  private static final String OUTCOME_LABEL_2 = "Outcome label 2";
  private static final Long REASON_ID_1 = 5L;
  private static final Long REASON_ID_2 = 6L;
  private static final String REASON_CODE_1 = "REASON_CODE_1";
  private static final String REASON_CODE_2 = "REASON_CODE_2";
  private static final String REASON_LABEL_1 = "REASON LABEL1";
  private static final String REASON_LABEL_2 = "REASON LABEL2";

  @InjectMocks
  private OutcomeResource testObj;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @MockBean
  private OutcomeRepository outcomeRepositoryMock;
  @MockBean
  private OutcomeService outcomeServiceMock;
  @Captor
  private ArgumentCaptor<Outcome> outcomeArgumentCaptor;
  @Captor
  private ArgumentCaptor<Pageable> pageableArgumentCaptor;
  @Captor
  private ArgumentCaptor<String> stringArgumentCaptor;

  private MockMvc mockMvc;
  private Outcome outcomeStub1, outcomeStub2, unsavedOutcomeStub1;
  private Reason reason1, reason2;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(testObj)
        .setCustomArgumentResolvers(pageableArgumentResolver)
        .setControllerAdvice(exceptionTranslator)
        .setConversionService(TestUtil.createFormattingConversionService())
        .setMessageConverters(jacksonMessageConverter).build();

    outcomeStub1 = new Outcome().id(OUTCOME_ID_1).code(OUTCOME_CODE_1).label(OUTCOME_LABEL_1);
    unsavedOutcomeStub1 = new Outcome().code(OUTCOME_CODE_1).label(OUTCOME_LABEL_1);
    outcomeStub2 = new Outcome().id(OUTCOME_ID_2).code(OUTCOME_CODE_2).label(OUTCOME_LABEL_2);
    reason1 = new Reason().id(REASON_ID_1).code(REASON_CODE_1).label(REASON_LABEL_1);
    reason2 = new Reason().id(REASON_ID_2).code(REASON_CODE_2).label(REASON_LABEL_2);
    outcomeStub1.setReasons(Sets.newLinkedHashSet(reason1, reason2));
  }

  @Test
  public void getOutcomeShouldReturnSingleOutcomeWhenFound() throws Exception {
    when(outcomeRepositoryMock.findOne(OUTCOME_ID_1)).thenReturn(outcomeStub1);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/outcomes/{id}", OUTCOME_ID_1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(OUTCOME_ID_1.intValue()))
        .andExpect(jsonPath("$.code").value(OUTCOME_CODE_1))
        .andExpect(jsonPath("$.label").value(OUTCOME_LABEL_1));
  }


  @Test
  public void getOutcomeShouldReturnNotFoundWhenNoOutcomeWithId() throws Exception {
    when(outcomeRepositoryMock.findOne(OUTCOME_ID_1)).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/outcomes/{id}", OUTCOME_ID_1))
        .andExpect(status().isNotFound());
  }

  @Test
  public void getAllOutcomesShouldReturnOutcomesWithReasons() throws Exception {
    when(outcomeRepositoryMock.findAllWithReasons()).thenReturn(Sets.newLinkedHashSet(outcomeStub1, outcomeStub2));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/outcomes/all", OUTCOME_ID_1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[*].id").value(hasItems(OUTCOME_ID_1.intValue(), OUTCOME_ID_2.intValue())))
        .andExpect(jsonPath("$.[*].code").value(hasItems(OUTCOME_CODE_1, OUTCOME_CODE_2)))
        .andExpect(jsonPath("$.[*].label").value(hasItems(OUTCOME_LABEL_1, OUTCOME_LABEL_2)))
        .andExpect(jsonPath("$.[0].reasons.[*].id").value(hasItems(REASON_ID_1.intValue(), REASON_ID_2.intValue())))
        .andExpect(jsonPath("$.[0].reasons.[*].code").value(hasItems(REASON_CODE_1, REASON_CODE_2)))
        .andExpect(jsonPath("$.[0].reasons.[*].label").value(hasItems(REASON_LABEL_1, REASON_LABEL_2)));
  }

  @Test
  public void createOutcomeShouldSaveAndReturnNewOutcome() throws Exception {
    when(outcomeRepositoryMock.save(outcomeArgumentCaptor.capture())).thenReturn(outcomeStub1);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/outcomes")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(unsavedOutcomeStub1)))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/api/outcomes/" + OUTCOME_ID_1.intValue()))
        .andExpect(jsonPath("$.id").value(OUTCOME_ID_1.intValue()))
        .andExpect(jsonPath("$.code").value(OUTCOME_CODE_1))
        .andExpect(jsonPath("$.label").value(OUTCOME_LABEL_1));

    Outcome capturedOutcome = outcomeArgumentCaptor.getValue();
    Assert.assertNull(capturedOutcome.getId());
    Assert.assertEquals(OUTCOME_CODE_1, capturedOutcome.getCode());
    Assert.assertEquals(OUTCOME_LABEL_1, capturedOutcome.getLabel());
  }

  @Test
  public void updateOutcomeShouldUpdateAndReturnUpdatedOutcome() throws Exception {
    when(outcomeRepositoryMock.save(outcomeArgumentCaptor.capture())).thenReturn(outcomeStub1);

    mockMvc.perform(MockMvcRequestBuilders.put("/api/outcomes")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(outcomeStub1)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(OUTCOME_ID_1.intValue()))
        .andExpect(jsonPath("$.code").value(OUTCOME_CODE_1))
        .andExpect(jsonPath("$.label").value(OUTCOME_LABEL_1));

    Outcome capturedOutcome = outcomeArgumentCaptor.getValue();
    Assert.assertEquals(OUTCOME_ID_1, capturedOutcome.getId());
    Assert.assertEquals(OUTCOME_CODE_1, capturedOutcome.getCode());
    Assert.assertEquals(OUTCOME_LABEL_1, capturedOutcome.getLabel());
  }

  @Test
  public void updateOutcomeShouldCreateNewOutcomeWhenOutcomeDoesntExist() throws Exception {
    when(outcomeRepositoryMock.save(outcomeArgumentCaptor.capture())).thenReturn(outcomeStub1);

    mockMvc.perform(MockMvcRequestBuilders.put("/api/outcomes")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(unsavedOutcomeStub1)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(OUTCOME_ID_1.intValue()))
        .andExpect(jsonPath("$.code").value(OUTCOME_CODE_1))
        .andExpect(jsonPath("$.label").value(OUTCOME_LABEL_1));

    Outcome capturedOutcome = outcomeArgumentCaptor.getValue();
    Assert.assertNull(capturedOutcome.getId());
    Assert.assertEquals(OUTCOME_CODE_1, capturedOutcome.getCode());
    Assert.assertEquals(OUTCOME_LABEL_1, capturedOutcome.getLabel());
  }

  @Test
  public void outcomeSmartSearchShouldFindAllOutcomesPaginated() throws Exception {
    Outcome outcome1 = new Outcome(), outcome2 = new Outcome();
    outcome1.id(OUTCOME_ID_1).code(OUTCOME_CODE_1).label(OUTCOME_LABEL_1);
    outcome2.id(OUTCOME_ID_2).code(OUTCOME_CODE_2).label(OUTCOME_LABEL_2);

    Page<Outcome> pageOfOutcomes = new PageImpl<>(Lists.newArrayList(outcome1, outcome2));

    when(outcomeRepositoryMock.findAll(pageableArgumentCaptor.capture())).thenReturn(pageOfOutcomes);

    mockMvc.perform(get("/api/outcomes"))
        .andExpect(status().isOk())
        .andExpect(header().string("X-Total-Count", "2"))
        .andExpect(jsonPath("$.*.id", hasItems(OUTCOME_ID_1.intValue(), OUTCOME_ID_2.intValue())))
        .andExpect(jsonPath("$.*.code", hasItems(OUTCOME_CODE_1, OUTCOME_CODE_2)))
        .andExpect(jsonPath("$.*.label", hasItems(OUTCOME_LABEL_1, OUTCOME_LABEL_2)))
    ;

    Pageable captorValue = pageableArgumentCaptor.getValue();
    Assert.assertEquals(20, captorValue.getPageSize());
    Assert.assertEquals(0, captorValue.getPageNumber());
  }

  @Test
  public void outcomeSmartSearchShouldFindOutcomesMatchingQueryPaginated() throws Exception {
    Outcome outcome1 = new Outcome(), outcome2 = new Outcome();
    outcome1.id(OUTCOME_ID_1).code(OUTCOME_CODE_1).label(OUTCOME_LABEL_1);
    outcome2.id(OUTCOME_ID_2).code(OUTCOME_CODE_2).label(OUTCOME_LABEL_2);

    Page<Outcome> pageOfOutcomes = new PageImpl<>(Lists.newArrayList(outcome1, outcome2));

    when(outcomeServiceMock.advancedSearch(stringArgumentCaptor.capture(), pageableArgumentCaptor.capture()))
        .thenReturn(pageOfOutcomes);

    String searchParam = "outcome";
    mockMvc.perform(get("/api/outcomes?searchQuery=" + searchParam))
        .andExpect(status().isOk())
        .andExpect(header().string("X-Total-Count", "2"))
        .andExpect(jsonPath("$.*.id", hasItems(OUTCOME_ID_1.intValue(), OUTCOME_ID_2.intValue())))
        .andExpect(jsonPath("$.*.code", hasItems(OUTCOME_CODE_1, OUTCOME_CODE_2)))
        .andExpect(jsonPath("$.*.label", hasItems(OUTCOME_LABEL_1, OUTCOME_LABEL_2)))
    ;

    Pageable captorValue = pageableArgumentCaptor.getValue();
    Assert.assertEquals(20, captorValue.getPageSize());
    Assert.assertEquals(0, captorValue.getPageNumber());

    String capturedSearchString = stringArgumentCaptor.getValue();
    Assert.assertEquals(searchParam, capturedSearchString);
  }
}
