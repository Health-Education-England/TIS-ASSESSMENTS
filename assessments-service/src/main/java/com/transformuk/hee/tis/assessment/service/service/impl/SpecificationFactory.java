package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.AssessmentDetail;
import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcome;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import com.transformuk.hee.tis.assessment.service.model.Revalidation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * Contains convenience pieces used to build specifications as query building blocks.
 * For more info please {@see https://jverhoelen.github.io/spring-data-queries-jpa-criteria-api/}
 */
public final class SpecificationFactory {

  private static final String DOT = ".";
  private static final String TRUE = "true";
  private static final String FALSE = "false";

  private SpecificationFactory() {
  }

  public static Specification containsLike(String attribute, String value) {
    return (root, query, cb) -> {
      if (StringUtils.isNotEmpty(attribute) && attribute.contains(DOT)) {
        String[] joinTable = StringUtils.split(attribute, DOT);
        Join tableJoin = root.join(joinTable[0], JoinType.LEFT);
        for (int i = 1; i < joinTable.length - 1; i++) {
          tableJoin = tableJoin.join(joinTable[i], JoinType.LEFT);
        }
        return cb.like(tableJoin.get(joinTable[joinTable.length - 1]), "%" + value + "%");
      } else {
        return cb.like(root.get(attribute), "%" + value + "%");
      }
    };
  }

  public static Specification equal(String attribute, Object value) {
    return (root, query, cb) -> {
      if (StringUtils.isNotEmpty(attribute) && attribute.contains(DOT)) {
        String[] joinTable = StringUtils.split(attribute, DOT);
        Join tableJoin = root.join(joinTable[0], JoinType.LEFT);
        for (int i = 1; i < joinTable.length - 1; i++) {
          tableJoin = tableJoin.join(joinTable[i], JoinType.LEFT);
        }
        return cb.equal(tableJoin.get(joinTable[joinTable.length - 1]), value);
      } else {
        return cb.equal(root.get(attribute), value);
      }
    };
  }

  public static Specification isNull(String attribute) {
    return (root, query, cb) -> {
      if (StringUtils.isNotEmpty(attribute) && attribute.contains(DOT)) {
        String[] joinTable = StringUtils.split(attribute, DOT);
        Join tableJoin = root.join(joinTable[0], JoinType.LEFT);
        for (int i = 1; i < joinTable.length - 1; i++) {
          tableJoin = tableJoin.join(joinTable[i], JoinType.LEFT);
        }
        return cb.isNull(tableJoin.get(joinTable[joinTable.length - 1]));
      } else {
        return cb.isNull(root.get(attribute));
      }
    };
  }


  /**
   * In condition for entity property, if property is from sub entity then it should contain '.' e.g sites.siteId
   *
   * @param attribute
   * @param values
   * @return
   */
  public static Specification in(String attribute, Collection<Object> values) {
    return (root, query, cb) -> {
      CriteriaBuilder.In cbi;
      if (StringUtils.isNotEmpty(attribute) && attribute.contains(DOT)) {
        // this support multiple entity in criteria e.g specialties.specialty.name or sites.siteId
        String[] joinTable = StringUtils.split(attribute, DOT);
        Join tableJoin = root.join(joinTable[0], JoinType.INNER);
        for (int i = 1; i < joinTable.length - 1; i++) {
          tableJoin = tableJoin.join(joinTable[i], JoinType.INNER);
        }
        cbi = cb.in(tableJoin.get(joinTable[joinTable.length - 1])); // attribute
      } else {
        cbi = cb.in(root.get(attribute));
      }
      values.forEach(v -> {
        //handle booleans
        if (v.equals(TRUE) || v.equals(FALSE)) {
          v = new Boolean(v.toString());
        }
        if (StringUtils.isNumeric(v.toString())) {
          v = new Long(v.toString());
        }
        cbi.value(v);
      });
      return cbi;
    };
  }

  public static Specification isBetween(String attribute, int min, int max) {
    return (root, query, cb) -> cb.between(root.get(attribute), min, max);
  }

  public static Specification isBetween(String attribute, double min, double max) {
    return (root, query, cb) -> cb.between(root.get(attribute), min, max);
  }

  /**
   * Returns a list of values from the provided column filter.
   * Correctly handles assessment LocalDate fields, including dot-notated sub entity fields.
   *
   * @param cf The column filter to use
   * @return A list of values
   */
  public static List<Object> getDateAwareValuesFromColumnFilter(ColumnFilter cf) {
    List<Object> valuesList;
    try {
      String[] subEntity = StringUtils.split(cf.getName(), DOT);
      Field field;
      if (subEntity.length == 1) {
        field = Assessment.class.getDeclaredField(subEntity[0]);
      } else {
        switch (subEntity[0].toLowerCase()) {
          case "revalidation":
            field = Revalidation.class.getDeclaredField(subEntity[1]);
            break;
          case "outcome":
            field = AssessmentOutcome.class.getDeclaredField(subEntity[1]);
            break;
          case "detail":
            field = AssessmentDetail.class.getDeclaredField(subEntity[1]);
            break;
          default:
            throw new NoSuchFieldException(subEntity[0]);
        }
      }
      if (field.getType().equals(LocalDate.class)) {
        //dates need to be handled differently to strings / numbers
        valuesList = cf.getValues().stream()
            .map(c -> LocalDate.parse(c.toString()))
            .collect(Collectors.toList());
      } else {
        valuesList = cf.getValues();
      }
    } catch (NoSuchFieldException e) {
      valuesList = cf.getValues(); //allow calling function to handle this
    } catch (DateTimeParseException e) {
      valuesList = null; //query will thus ignore invalid date values
    }
    return valuesList;
  }
}
