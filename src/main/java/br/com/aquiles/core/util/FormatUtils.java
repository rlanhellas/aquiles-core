package br.com.aquiles.core.util;

import br.com.aquiles.core.validator.format.CNPJFormatter;
import br.com.aquiles.core.validator.format.CPFFormatter;
import br.com.aquiles.core.validator.format.Formatter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rlanhellas on 30/05/2017.
 */
public abstract class FormatUtils {

    private FormatUtils() {
    }

    private static final String SDF_YYYYMMDD = "yyyyMMdd";
    private static final String SDF_DDMMYYYY = "ddMMyyyy";
    private static final String SDF_DD_MM_YYYY = "dd-MM-yyyy";
    private static final String SDF_DDMMYYYY_BARRA = "dd/MM/yyyy";
    private static final String SDF_YYYY_MM_DD_HIFEN = "yyyy-MM-dd";
    private static final String SDF_YYYY_MM_DD = "yyyyMMdd";

    public static String formatCPFCNPJ(String value) {
        if (value == null) {
            return "";
        }
        Formatter formatter;
        if (value.length() == 11) {
            formatter = new CPFFormatter();
            return formatter.format(value);
        } else if (value.length() == 14) {
            formatter = new CNPJFormatter();
            return formatter.format(value);
        }
        return value;
    }

    public static String formatDecimal(BigDecimal number) {
        return String.format("%,.2f", number);
    }

    public static String formatDecimal(Double number) {
        return String.format("%,.2f", number);
    }

    public static String formatDate(Date value) {
        return new SimpleDateFormat(SDF_YYYYMMDD).format(value);
    }

    public static String formatDateDMY(Date value) {
        return new SimpleDateFormat(SDF_DDMMYYYY).format(value);
    }

    public static String formatDateDMYComHifen(Date value) {
        return new SimpleDateFormat(SDF_DD_MM_YYYY).format(value);
    }

    public static String formatDateDMYComBarra(Date value) {
        return new SimpleDateFormat(SDF_DDMMYYYY_BARRA).format(value);
    }

    public static String formatDateYMDComHifen(Date value) {
        return new SimpleDateFormat(SDF_YYYY_MM_DD_HIFEN).format(value);
    }

    public static String formatDateYMD(Date value) {
        return new SimpleDateFormat(SDF_YYYY_MM_DD).format(value);
    }


}
