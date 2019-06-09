package com.transformuk.hee.tis.assessment.service.util;

import com.google.common.collect.Lists;
import com.transformuk.hee.tis.assessment.api.dto.EventStatus;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeStatus;
import com.transformuk.hee.tis.assessment.service.api.util.ColumnFilterUtil;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @see ColumnFilterUtil
 */
public class ColumnFilterUtilTest {

    @Test
    public void shouldSanitizeParamWhenGetColumnFilters() throws IOException {
        List<Class> filterEnumList = Lists.newArrayList(EventStatus.class, OutcomeStatus.class);
        String columnFilterJson = "{ \"firstName\": [\"Helen \\\"Moira, \\t\\nMary\\\"\"]}";
        List<ColumnFilter> cfList = ColumnFilterUtil.getColumnFilters(columnFilterJson, filterEnumList);

        Assert.assertThat("should get list of column filters", cfList.size(), CoreMatchers.equalTo(1));
        String cf = (String)cfList.get(0).getValues().get(0);
        Assert.assertThat("should sanitize param", cf, CoreMatchers.equalTo("Helen \"Moira, Mary\""));
    }

}
