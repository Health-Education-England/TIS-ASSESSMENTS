package com.transformuk.hee.tis.assessment.service.model.type;

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
    LocalDateTime value = type.seed(null);

    assertNotNull(value);
    assertEquals(0, value.getNano() % NANOS_PER_MILLI);
  }

  @Test
  public void shouldTruncateNextToMilliseconds() {
    LocalDateTime value = type.next(LocalDateTime.now(), null);

    assertNotNull(value);
    assertEquals(0, value.getNano() % NANOS_PER_MILLI);
  }
}
