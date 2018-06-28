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
public class FormatUtils {

    private static final SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat SDF_DDMMYYYY = new SimpleDateFormat("ddMMyyyy");
    private static final SimpleDateFormat SDF_DD_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat SDF_DDMMYYYY_BARRA = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat SDF_YYYY_MM_DD_HIFEN = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat SDF_YYYYMMDD_HHMMSS = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

    public static String formatCPFCNPJ(String value) {
        if (value == null){
            return "";
        }
        Formatter formatter;
        if (value.toString().length() == 11) {
            formatter = new CPFFormatter();
            return formatter.format(value.toString());
        } else if (value.toString().length() == 14) {
            formatter = new CNPJFormatter();
            return formatter.format(value.toString());
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
        return SDF_YYYYMMDD.format(value);
    }

    public static String formatDateDMY(Date value) {
        return SDF_DDMMYYYY.format(value);
    }

    public static String formatDateDMYComHifen(Date value) {
        return SDF_DD_MM_YYYY.format(value);
    }

    public static String formatDateDMYComBarra(Date value) {
        return SDF_DDMMYYYY_BARRA.format(value);
    }

    public static String formatDateYMDComHifen(Date value) {
        return SDF_YYYY_MM_DD_HIFEN.format(value);
    }

    public static String formatDateYMD(Date value) {
        return SDF_YYYY_MM_DD.format(value);
    }


}
