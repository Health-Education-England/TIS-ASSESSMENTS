package com.transformuk.hee.tis.assessment.service.model.type;

import static java.time.temporal.ChronoUnit.MILLIS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import org.junit.Test;

public class TruncatedLocalDateTimeTypeTest {

  private static final int NANOS_PER_MILLI = 1_000_000;

  private final TruncatedLocalDateTimeType type = new TruncatedLocalDateTimeType();

  @Test
  public void getNameShouldReturnRegisteredTypeName() {
    assertEquals("truncatedLocalDateTime", type.getName());
  }

  @Test
  public void shouldTruncateSeedToMilliseconds() {
    LocalDateTime seed = type.seed(null);

    assertNotNull(seed);
    assertEquals(0, seed.getNano() % NANOS_PER_MILLI);
  }

  @Test
  public void shouldTruncateNextToMillisecondsWhenCurrentIsNull() {
    LocalDateTime next = type.next(null, null);

    assertNotNull(next);
    assertEquals(0, next.getNano() % NANOS_PER_MILLI);
  }

  @Test
  public void shouldTruncateNextToMillisecondsWhenCurrentIsPast() {
    LocalDateTime next = type.next(LocalDateTime.now().minusDays(1), null);

    assertNotNull(next);
    assertEquals(0, next.getNano() % NANOS_PER_MILLI);
  }

  @Test
  public void shouldEnsureNextGreaterThanCurrentWhenCurrentIsFuture() {
    LocalDateTime current = LocalDateTime.now().plusDays(1);
    LocalDateTime next = type.next(current, null);

    assertNotNull(next);
    assertEquals(current.truncatedTo(MILLIS).plus(1, MILLIS), next);
  }
}
