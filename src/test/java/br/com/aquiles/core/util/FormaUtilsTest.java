package br.com.aquiles.core.util;

import org.joda.time.Instant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class FormaUtilsTest {

    @Test
    public void shouldFormatCPFCNPJ() {
        Assert.assertEquals("", FormatUtils.formatCPFCNPJ(null));
        Assert.assertEquals("005.666.987-03", FormatUtils.formatCPFCNPJ("00566698703"));
        Assert.assertEquals("00.000.000/0000-00", FormatUtils.formatCPFCNPJ("00000000000000"));
        Assert.assertEquals("00000000000000XXX", FormatUtils.formatCPFCNPJ("00000000000000XXX"));
    }

    @Test
    public void shouldFormatDecimal() {
        Assert.assertEquals("10.30", FormatUtils.formatDecimal(10.3D));
        Assert.assertEquals("10.30", FormatUtils.formatDecimal(BigDecimal.valueOf(10.3D)));
    }

    @Test
    public void shouldFormaDate(){
        Date date = Instant.parse("2019-02-01").toDate();
        Assert.assertEquals("20190201",FormatUtils.formatDate(date));
        Assert.assertEquals("01022019",FormatUtils.formatDateDMY(date));
        Assert.assertEquals("01-02-2019",FormatUtils.formatDateDMYComHifen(date));
        Assert.assertEquals("01/02/2019",FormatUtils.formatDateDMYComBarra(date));
        Assert.assertEquals("2019-02-01",FormatUtils.formatDateYMDComHifen(date));
        Assert.assertEquals("20190201",FormatUtils.formatDateYMD(date));
    }
}
