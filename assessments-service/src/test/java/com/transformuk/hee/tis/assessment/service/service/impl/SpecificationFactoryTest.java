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
import java.util.Map;

@RunWith(JUnit4.class)
public class SpecificationFactoryTest {

  @Test
  public void shouldGetLocalDateSpecificationMapCorrectly() {
    //given
    ColumnFilter columnFilter = new ColumnFilter("reviewDate",
        Lists.newArrayList("2021-01-01"));

    //when
    Map<String, List<Object>> specMap = SpecificationFactory
        .getAssessmentSpecFromColumnFilter(columnFilter);

    //then
    for ( String key : specMap.keySet() ) {
      List<Object> criterion = specMap.get(key);
      assertThat("Specification map has incorrect date class",
          criterion.get(0) instanceof LocalDate, is(true));
    }
  }

  @Test
  public void shouldGetTextSpecificationMapCorrectly() {
    //given
    ColumnFilter columnFilter = new ColumnFilter("firstName",
        Lists.newArrayList("text"));

    //when
    Map<String, List<Object>> specMap = SpecificationFactory
        .getAssessmentSpecFromColumnFilter(columnFilter);

    //then
    for ( String key : specMap.keySet() ) {
      List<Object> criterion = specMap.get(key);
      assertThat("Specification map has incorrect string class",
          criterion.get(0) instanceof String, is(true));
    }
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
    Map<String, List<Object>> specMapOutcome = SpecificationFactory
        .getAssessmentSpecFromColumnFilter(columnFilterOutcome);
    Map<String, List<Object>> specMapDetail = SpecificationFactory
        .getAssessmentSpecFromColumnFilter(columnFilterDetail);
    Map<String, List<Object>> specMapRevalidation = SpecificationFactory
        .getAssessmentSpecFromColumnFilter(columnFilterRevalidation);

    //then
    for ( String key : specMapOutcome.keySet() ) {
      List<Object> criterion = specMapOutcome.get(key);
      assertThat("Specification map has incorrect date class",
          criterion.get(0) instanceof LocalDate, is(true));
    }
    for ( String key : specMapDetail.keySet() ) {
      List<Object> criterion = specMapDetail.get(key);
      assertThat("Specification map has incorrect string class",
          criterion.get(0) instanceof String, is(true));
    }
    for ( String key : specMapRevalidation.keySet() ) {
      List<Object> criterion = specMapRevalidation.get(key);
      assertThat("Specification map has incorrect string class",
          criterion.get(0) instanceof String, is(true));
    }
  }

  @Test
  public void shouldGetNullSpecificationMapOnError() {
    //given
    ColumnFilter columnFilter = new ColumnFilter("invalid key",
        Lists.newArrayList("any value"));

    //when
    Map<String, List<Object>> specMap = SpecificationFactory
        .getAssessmentSpecFromColumnFilter(columnFilter);

    //then
    assertNull(specMap);
  }
}
