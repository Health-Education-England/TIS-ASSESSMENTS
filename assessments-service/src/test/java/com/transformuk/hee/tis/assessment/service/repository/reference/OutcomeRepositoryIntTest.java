package com.transformuk.hee.tis.assessment.service.repository.reference;

import com.google.common.collect.Sets;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OutcomeRepositoryIntTest {

  private static final long REASONS_ID_1 = 1L;
  private static final long REASONS_ID_2 = 2L;
  private static final String REASON_CODE_1 = "REASON_CODE_1";
  private static final String REASON_CODE_2 = "REASON_CODE_2";
  private static final String REASON_LABEL_1 = "REASON_LABEL_1";
  private static final String REASON_LABEL_2 = "REASON_LABEL_2";
  private static final long OUTCOME_ID_1 = 3L;
  private static final long OUTCOME_ID_2 = 4L;
  private static final String OUTCOME_CODE_1 = "OUTCOME_CODE_1";
  private static final String OUTCOME_CODE_2 = "OUTCOME_CODE_2";
  private static final String OUTCOME_LABEL_1 = "OUTCOME_LABEL_1";
  private static final String OUTCOME_LABEL_2 = "OUTCOME_LABEL_2";


  @Autowired
  private OutcomeRepository testObj;

  @Autowired
  private EntityManager em;

  @Before
  public void setup() {
    Reason reason1 = new Reason().code(REASON_CODE_1).label(REASON_LABEL_1);
    em.persist(reason1);

    Reason reason2 = new Reason().code(REASON_CODE_2).label(REASON_LABEL_2);
    em.persist(reason2);

    Outcome outcome1 = new Outcome().code(OUTCOME_CODE_1).label(OUTCOME_LABEL_1)
        .reasons(Sets.newHashSet(reason1, reason2));
    em.persist(outcome1);

    Outcome outcome2 = new Outcome().code(OUTCOME_CODE_2).label(OUTCOME_LABEL_2);
    em.persist(outcome2);

  }

  @Test
  @Transactional
  public void findAllWithReasonsShouldReturnAllOutcomesWithAttachedReasons() {
    Set<Outcome> result = testObj.findAllWithReasons();

    Assert.assertEquals(2, result.size());
    //find outcome with code 1
    Outcome withReasons = null;
    for (Outcome outcome : result) {
      if (OUTCOME_CODE_1.equals(outcome.getCode())) {
        withReasons = outcome;
        break;
      }
    }

    Assert.assertEquals(OUTCOME_CODE_1, withReasons.getCode());
    Assert.assertEquals(OUTCOME_LABEL_1, withReasons.getLabel());

    Assert.assertEquals(2, withReasons.getReasons().size());
    List<String> reasonCodes = withReasons.getReasons().stream().map(Reason::getCode).collect(Collectors.toList());
    List<String> reasonLabels = withReasons.getReasons().stream().map(Reason::getLabel).collect(Collectors.toList());

    Assert.assertTrue(reasonCodes.contains(REASON_CODE_1));
    Assert.assertTrue(reasonCodes.contains(REASON_CODE_2));
    Assert.assertTrue(reasonLabels.contains(REASON_LABEL_1));
    Assert.assertTrue(reasonLabels.contains(REASON_LABEL_2));
  }

}
