package com.transformuk.hee.tis.assessment.service.model.type;

import static java.time.temporal.ChronoUnit.MILLIS;

import java.time.LocalDateTime;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.LocalDateTimeType;

/**
 * Hibernate {@link LocalDateTime} version type that enforces millisecond precision.
 *
 * <p>This type is required for optimistic locking when entities use {@code @Version} with
 * {@link LocalDateTime} and the backing MySQL column is {@code DATETIME(3)}.
 *
 * <p>On Java 9+, {@link LocalDateTime#now()} includes nanosecond precision. MySQL persists
 * {@code DATETIME(3)} values at millisecond precision, so the stored version is rounded/truncated
 * relative to the in-memory value. On the next flush/update, Hibernate performs optimistic locking
 * with:
 *
 * <pre>{@code
 * UPDATE ... SET ... WHERE id = ? AND amendedDate = ?
 * }</pre>
 *
 * <p>If the in-memory version still contains nanos, that predicate does not match the
 * millisecond value in the database, causing a false stale-update failure
 * ({@code ObjectOptimisticLockingFailureException}).
 *
 * <p>By truncating both {@link #seed(SharedSessionContractImplementor)} and
 * {@link #next(LocalDateTime, SharedSessionContractImplementor)} to milliseconds, Hibernate's
 * generated version value matches the persisted precision and optimistic-lock checks remain
 * stable.
 *
 * <p>Usage on entity fields:
 *
 * <pre>{@code
 * @Version
 * @Type(type = "com.transformuk.hee.tis.assessment.service.model.type.TruncatedLocalDateTimeType")
 * private LocalDateTime amendedDate;
 * }</pre>
 */
public class TruncatedLocalDateTimeType extends LocalDateTimeType {

  @Override
  public String getName() {
    return "truncatedLocalDateTime";
  }

  @Override
  public LocalDateTime seed(SharedSessionContractImplementor session) {
    return LocalDateTime.now().truncatedTo(MILLIS);
  }

  @Override
  public LocalDateTime next(LocalDateTime current, SharedSessionContractImplementor session) {
    LocalDateTime nextMillis = LocalDateTime.now().truncatedTo(MILLIS);
    LocalDateTime currentMillis = current != null ? current.truncatedTo(MILLIS) : null;

    if (currentMillis != null && !nextMillis.isAfter(currentMillis)) {
      return currentMillis.plus(1, MILLIS);
    }

    return nextMillis;
  }
}
