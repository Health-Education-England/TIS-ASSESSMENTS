package com.transformuk.hee.tis.assessment.service.repository.reference;

import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import org.assertj.core.util.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReasonRepositoryIntTest {

  @Autowired
  private ReasonRepository testObj;

  @Autowired
  private EntityManager entityManager;

  @Test
  @Transactional
  public void findByOutcomeShouldReturnReasonLinkedToOutcome() {
    Reason reason = new Reason().label("a reason").code("reason1");
    entityManager.persist(reason);

    Reason reason2 = new Reason().label("a reason 2").code("reason2");
    entityManager.persist(reason2);

    Outcome outcome = new Outcome().code("outcome1").label("a outcome").reasons(Sets.newLinkedHashSet(reason, reason2));
    entityManager.persist(outcome);

    Set<Reason> result = testObj.findByOutcome(outcome);

    Assert.assertEquals(2L, result.size());
    Assert.assertTrue(result.contains(reason));
    Assert.assertTrue(result.contains(reason2));
  }

  @Test
  @Transactional
  public void findByOutcomeShouldReturnEmtptyListWhenNoReasonsLinked() {
    Outcome outcome = new Outcome().code("outcome1").label("a outcome");
    entityManager.persist(outcome);

    Set<Reason> result = testObj.findByOutcome(outcome);

    Assert.assertEquals(0L, result.size());
  }
}