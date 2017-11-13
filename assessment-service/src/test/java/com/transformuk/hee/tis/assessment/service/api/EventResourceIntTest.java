package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.EventDTO;
import com.transformuk.hee.tis.assessment.api.dto.EventStatus;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.exception.ExceptionTranslator;
import com.transformuk.hee.tis.assessment.service.model.Event;
import com.transformuk.hee.tis.assessment.service.repository.EventRepository;
import com.transformuk.hee.tis.assessment.service.service.EventService;
import com.transformuk.hee.tis.assessment.service.service.mapper.EventMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the EventResource REST controller.
 *
 * @see EventResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EventResourceIntTest {

  private static final Long DEFAULT_PERSON_ID = 1L;
  private static final Long UPDATED_PERSON_ID = 2L;

  private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
  private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
  private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

  private static final ZonedDateTime DEFAULT_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  private static final ZonedDateTime DEFAULT_END_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_END_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  private static final Long DEFAULT_PROGRAMME_NUMBER = 1L;
  private static final Long UPDATED_PROGRAMME_NUMBER = 2L;

  private static final String DEFAULT_PROGRAMME_NAME = "AAAAAAAAAA";
  private static final String UPDATED_PROGRAMME_NAME = "BBBBBBBBBB";

  private static final EventStatus DEFAULT_STATUS = EventStatus.APPEALED;
  private static final EventStatus UPDATED_STATUS = EventStatus.ASSESSMENT_COMPLETED;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private EventMapper eventMapper;

  @Autowired
  private EventService eventService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restEventMockMvc;

  private Event event;

  /**
   * Create an entity for this test.
   * <p>
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Event createEntity(EntityManager em) {
    Event event = new Event()
        .personId(DEFAULT_PERSON_ID)
        .firstName(DEFAULT_FIRST_NAME)
        .lastName(DEFAULT_LAST_NAME)
        .startDate(DEFAULT_START_DATE)
        .endDate(DEFAULT_END_DATE)
        .programmeNumber(DEFAULT_PROGRAMME_NUMBER)
        .programmeName(DEFAULT_PROGRAMME_NAME)
        .status(DEFAULT_STATUS);
    return event;
  }

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final EventResource eventResource = new EventResource(eventService);
    this.restEventMockMvc = MockMvcBuilders.standaloneSetup(eventResource)
        .setCustomArgumentResolvers(pageableArgumentResolver)
        .setControllerAdvice(exceptionTranslator)
        .setConversionService(TestUtil.createFormattingConversionService())
        .setMessageConverters(jacksonMessageConverter).build();
  }

  @Before
  public void initTest() {
    event = createEntity(em);
  }

  @Test
  @Transactional
  public void createEvent() throws Exception {
    int databaseSizeBeforeCreate = eventRepository.findAll().size();

    // Create the Event
    EventDTO eventDTO = eventMapper.toDto(event);
    restEventMockMvc.perform(post("/api/events")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(eventDTO)))
        .andExpect(status().isCreated());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeCreate + 1);
    Event testEvent = eventList.get(eventList.size() - 1);
    assertThat(testEvent.getPersonId()).isEqualTo(DEFAULT_PERSON_ID);
    assertThat(testEvent.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
    assertThat(testEvent.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
    assertThat(testEvent.getStartDate()).isEqualTo(DEFAULT_START_DATE);
    assertThat(testEvent.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    assertThat(testEvent.getProgrammeNumber()).isEqualTo(DEFAULT_PROGRAMME_NUMBER);
    assertThat(testEvent.getProgrammeName()).isEqualTo(DEFAULT_PROGRAMME_NAME);
    assertThat(testEvent.getStatus()).isEqualTo(DEFAULT_STATUS);
  }

  @Test
  @Transactional
  public void createEventWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = eventRepository.findAll().size();

    // Create the Event with an existing ID
    event.setId(1L);
    EventDTO eventDTO = eventMapper.toDto(event);

    // An entity with an existing ID cannot be created, so this API call must fail
    restEventMockMvc.perform(post("/api/events")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(eventDTO)))
        .andExpect(status().isBadRequest());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllEvents() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get all the eventList
    restEventMockMvc.perform(get("/api/events?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(event.getId().intValue())))
        .andExpect(jsonPath("$.[*].personId").value(hasItem(DEFAULT_PERSON_ID.intValue())))
        .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
        .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
        .andExpect(jsonPath("$.[*].startDate").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_START_DATE))))
        .andExpect(jsonPath("$.[*].endDate").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_END_DATE))))
        .andExpect(jsonPath("$.[*].programmeNumber").value(hasItem(DEFAULT_PROGRAMME_NUMBER.intValue())))
        .andExpect(jsonPath("$.[*].programmeName").value(hasItem(DEFAULT_PROGRAMME_NAME.toString())))
        .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
  }

  @Test
  @Transactional
  public void getEvent() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);

    // Get the event
    restEventMockMvc.perform(get("/api/events/{id}", event.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(event.getId().intValue()))
        .andExpect(jsonPath("$.personId").value(DEFAULT_PERSON_ID.intValue()))
        .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
        .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
        .andExpect(jsonPath("$.startDate").value(TestUtil.sameInstant(DEFAULT_START_DATE)))
        .andExpect(jsonPath("$.endDate").value(TestUtil.sameInstant(DEFAULT_END_DATE)))
        .andExpect(jsonPath("$.programmeNumber").value(DEFAULT_PROGRAMME_NUMBER.intValue()))
        .andExpect(jsonPath("$.programmeName").value(DEFAULT_PROGRAMME_NAME.toString()))
        .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingEvent() throws Exception {
    // Get the event
    restEventMockMvc.perform(get("/api/events/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateEvent() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);
    int databaseSizeBeforeUpdate = eventRepository.findAll().size();

    // Update the event
    Event updatedEvent = eventRepository.findOne(event.getId());
    updatedEvent
        .personId(UPDATED_PERSON_ID)
        .firstName(UPDATED_FIRST_NAME)
        .lastName(UPDATED_LAST_NAME)
        .startDate(UPDATED_START_DATE)
        .endDate(UPDATED_END_DATE)
        .programmeNumber(UPDATED_PROGRAMME_NUMBER)
        .programmeName(UPDATED_PROGRAMME_NAME)
        .status(UPDATED_STATUS);
    EventDTO eventDTO = eventMapper.toDto(updatedEvent);

    restEventMockMvc.perform(put("/api/events")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(eventDTO)))
        .andExpect(status().isOk());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate);
    Event testEvent = eventList.get(eventList.size() - 1);
    assertThat(testEvent.getPersonId()).isEqualTo(UPDATED_PERSON_ID);
    assertThat(testEvent.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
    assertThat(testEvent.getLastName()).isEqualTo(UPDATED_LAST_NAME);
    assertThat(testEvent.getStartDate()).isEqualTo(UPDATED_START_DATE);
    assertThat(testEvent.getEndDate()).isEqualTo(UPDATED_END_DATE);
    assertThat(testEvent.getProgrammeNumber()).isEqualTo(UPDATED_PROGRAMME_NUMBER);
    assertThat(testEvent.getProgrammeName()).isEqualTo(UPDATED_PROGRAMME_NAME);
    assertThat(testEvent.getStatus()).isEqualTo(UPDATED_STATUS);
  }

  @Test
  @Transactional
  public void updateNonExistingEvent() throws Exception {
    int databaseSizeBeforeUpdate = eventRepository.findAll().size();

    // Create the Event
    EventDTO eventDTO = eventMapper.toDto(event);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restEventMockMvc.perform(put("/api/events")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(eventDTO)))
        .andExpect(status().isCreated());

    // Validate the Event in the database
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deleteEvent() throws Exception {
    // Initialize the database
    eventRepository.saveAndFlush(event);
    int databaseSizeBeforeDelete = eventRepository.findAll().size();

    // Get the event
    restEventMockMvc.perform(delete("/api/events/{id}", event.getId())
        .accept(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    // Validate the database is empty
    List<Event> eventList = eventRepository.findAll();
    assertThat(eventList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(Event.class);
    Event event1 = new Event();
    event1.setId(1L);
    Event event2 = new Event();
    event2.setId(event1.getId());
    assertThat(event1).isEqualTo(event2);
    event2.setId(2L);
    assertThat(event1).isNotEqualTo(event2);
    event1.setId(null);
    assertThat(event1).isNotEqualTo(event2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(EventDTO.class);
    EventDTO eventDTO1 = new EventDTO();
    eventDTO1.setId(1L);
    EventDTO eventDTO2 = new EventDTO();
    assertThat(eventDTO1).isNotEqualTo(eventDTO2);
    eventDTO2.setId(eventDTO1.getId());
    assertThat(eventDTO1).isEqualTo(eventDTO2);
    eventDTO2.setId(2L);
    assertThat(eventDTO1).isNotEqualTo(eventDTO2);
    eventDTO1.setId(null);
    assertThat(eventDTO1).isNotEqualTo(eventDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(eventMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(eventMapper.fromId(null)).isNull();
  }
}
