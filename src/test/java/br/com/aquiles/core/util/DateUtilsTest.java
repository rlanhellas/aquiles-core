package br.com.aquiles.core.util;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DateUtilsTest {

    @Test
    public void checkWeekend() {
        try {
            //check if any error occurrs
            DateUtils.isWeekend(new Date());

            //check a real weekend
            LocalDate ld = new LocalDate(2017,10,22);
            Assert.assertTrue(DateUtils.isWeekend(ld.toDate()));

            //check a non weekend day
            ld = new LocalDate(2017,10,23);
            Assert.assertFalse(DateUtils.isWeekend(ld.toDate()));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

}
