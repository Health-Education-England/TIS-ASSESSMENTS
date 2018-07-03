package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.service.Application;
import org.hamcrest.collection.IsArrayContaining;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EnumResourceTest {

  @InjectMocks
  private EnumResource testObj;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(testObj).build();
  }

  @Test
  public void eventStatusShouldReturnAllTheEventStatusValues() throws Exception {
    mockMvc.perform(get("/api/event-status"))
        .andExpect(content().string(StringContains.containsString("APPEALED")))
        .andExpect(content().string(StringContains.containsString("ASSESSMENT_COMPLETED")))
        .andExpect(content().string(StringContains.containsString("COMPLETED")))
        .andExpect(content().string(StringContains.containsString("OVERDUE")))
        .andExpect(content().string(StringContains.containsString("SCHEDULED")))
        .andExpect(status().isOk());
  }

  @Test
  public void outcomeStatusShouldReturnAllTheOutcomeStatusValues() throws Exception {
    mockMvc.perform(get("/api/outcome-status"))
        .andExpect(content().string(StringContains.containsString("OUTCOME_1")))
        .andExpect(content().string(StringContains.containsString("OUTCOME_2")))
        .andExpect(content().string(StringContains.containsString("OUTCOME_3")))
        .andExpect(content().string(StringContains.containsString("OUTCOME_4")))
        .andExpect(content().string(StringContains.containsString("OUTCOME_5")))
        .andExpect(content().string(StringContains.containsString("OUTCOME_6")))
        .andExpect(content().string(StringContains.containsString("OUTCOME_7")))
        .andExpect(status().isOk());
  }
}