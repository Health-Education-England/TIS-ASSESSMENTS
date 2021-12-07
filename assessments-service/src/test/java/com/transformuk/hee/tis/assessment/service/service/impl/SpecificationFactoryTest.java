package com.transformuk.hee.tis.assessment.service.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.time.LocalDate;
import java.util.List;

@RunWith(JUnit4.class)
public class SpecificationFactoryTest {

  @Test
  public void shouldGetLocalDateSpecificationMapCorrectly() {
    //given
    ColumnFilter columnFilter = new ColumnFilter("reviewDate",
        Lists.newArrayList("2021-01-01"));

    //when
    List<Object> valuesList = SpecificationFactory
        .getDateAwareValuesFromColumnFilter(columnFilter);

    //then
    assertThat("Values list has incorrect date class",
        valuesList.get(0) instanceof LocalDate, is(true));
  }

  @Test
  public void shouldGetTextSpecificationMapCorrectly() {
    //given
    ColumnFilter columnFilter = new ColumnFilter("firstName",
        Lists.newArrayList("text"));

    //when
    List<Object> valuesList = SpecificationFactory
        .getDateAwareValuesFromColumnFilter(columnFilter);

    //then
    assertThat("Values list has incorrect string class",
        valuesList.get(0) instanceof String, is(true));
  }


  @Test
  public void shouldGetSubItemSpecificationMapCorrectly() {
    //given
    ColumnFilter columnFilterOutcome = new ColumnFilter("outcome.trainingCompletionDate",
        Lists.newArrayList("2021-01-01"));
    ColumnFilter columnFilterDetail = new ColumnFilter("detail.curriculumId",
        Lists.newArrayList("1"));
    ColumnFilter columnFilterRevalidation = new ColumnFilter("revalidation.intrepidId",
        Lists.newArrayList("1a"));

    //when
    List<Object> valuesListOutcome = SpecificationFactory
        .getDateAwareValuesFromColumnFilter(columnFilterOutcome);
    List<Object> valuesListDetail = SpecificationFactory
        .getDateAwareValuesFromColumnFilter(columnFilterDetail);
    List<Object> valuesListRevalidation = SpecificationFactory
        .getDateAwareValuesFromColumnFilter(columnFilterRevalidation);

    //then
    assertThat("Values list has incorrect date class",
        valuesListOutcome.get(0) instanceof LocalDate, is(true));

    assertThat("Values list has incorrect string class",
        valuesListDetail.get(0) instanceof String, is(true));

    assertThat("Values list has incorrect string class",
        valuesListRevalidation.get(0) instanceof String, is(true));
  }

  @Test
  public void shouldGetExistingValuesForUnknownField() {
    //given
    ColumnFilter columnFilter = new ColumnFilter("unknown",
        Lists.newArrayList("any value"));
    ColumnFilter columnFilterDotted = new ColumnFilter("unknown.value",
        Lists.newArrayList("another value"));

    //when
    List<Object> valuesList = SpecificationFactory
        .getDateAwareValuesFromColumnFilter(columnFilter);
    List<Object> valuesListDotted = SpecificationFactory
        .getDateAwareValuesFromColumnFilter(columnFilterDotted);

    //then
    assertThat("Unknown field values list has incorrect value",
        valuesList.get(0), is("any value"));
    assertThat("Unknown dotted field values list has incorrect value",
        valuesListDotted.get(0), is("another value"));
  }

  @Test
  public void shouldGetNullForInvalidDates() {
    //given
    ColumnFilter columnFilter = new ColumnFilter("reviewDate",
        Lists.newArrayList("2021-01-01", "bad date"));

    //when
    List<Object> valuesList = SpecificationFactory
        .getDateAwareValuesFromColumnFilter(columnFilter);

    //then
    assertNull(valuesList);
  }
}
