package br.com.aquiles.core.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class contains methods to manipulate Date values, using the Apache Commons
 * as parent library.
 *
 * @author Ronaldo Lanhellas, Raimundo Junior
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * @since 23/01/2017
     */
    public static final long DIA_TIME = 86400000;

    public static GregorianCalendar zeraHora(GregorianCalendar data) {
        data.set(GregorianCalendar.HOUR_OF_DAY, 0);
        data.set(GregorianCalendar.MINUTE, 0);
        data.set(GregorianCalendar.SECOND, 0);
        data.set(GregorianCalendar.MILLISECOND, 0);

        return data;
    }

    public static long zeraHora(long data) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(data);
        gc = zeraHora(gc);

        return gc.getTimeInMillis();
    }

    public static java.sql.Date zeraHora(java.sql.Date data) {
        return new java.sql.Date(zeraHora(data.getTime()));
    }

    public static java.util.Date newDate(int ano, int mes, int dia) {
        DateTime dt = new DateTime(ano, mes, dia, 0, 0);

        return dt.toDate();
    }

    /**
     * Check if 'date' argument is Weekend
     */
    public static boolean isWeekend(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        int dayOfWeek = gc.get(GregorianCalendar.DAY_OF_WEEK);
        if (dayOfWeek == 1 || dayOfWeek == 7) {
            return true;
        } else {
            return false;
        }
    }

    public static java.util.Date strToDate(String format, String value) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        DateTime dt = DateTime.parse(value, formatter);
        return dt.toDate();
    }

    public static Date strToDate(String value) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime date = DateTime.parse(value, formatter);
        return date.toDate();
    }

    public static String dateToStr(Date value) throws ParseException {
        return dateToStr("dd/MM/yyyy", value);
    }

    public static String dateToStr(String format, Date value) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        return formatter.print(new DateTime(value));
    }

    public static String dateToStr(String format, long value) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        return formatter.print(new DateTime(value));
    }

    public static java.util.Date zeraHora(java.util.Date data) {
        return new java.util.Date(zeraHora(data.getTime()));
    }

    public static java.sql.Timestamp zeraHora(java.sql.Timestamp data) {
        return new java.sql.Timestamp(zeraHora(data.getTime()));
    }

    public Date newDate(java.util.Date data, long timeInMillis) {
        if (data instanceof java.sql.Date) {
            return new java.sql.Date(timeInMillis);
        } else if (data instanceof java.sql.Timestamp) {
            return new java.sql.Timestamp(timeInMillis);
        } else {
            return new java.util.Date(timeInMillis);
        }
    }

    private static GregorianCalendar getNewGregorianCalendar(int ano, int mes, int dia) {
        GregorianCalendar gc = new GregorianCalendar(ano, mes, dia);
        gc = zeraHora(gc);
        return gc;
    }

    public static java.sql.Date newSqlDate(int ano, int mes, int dia) {
        GregorianCalendar gc = getNewGregorianCalendar(ano, mes, dia);

        return new java.sql.Date(gc.getTimeInMillis());
    }

    public static java.sql.Timestamp newTimestamp(int ano, int mes, int dia) {
        GregorianCalendar gc = getNewGregorianCalendar(ano, mes, dia);

        return new java.sql.Timestamp(gc.getTimeInMillis());
    }

    public static java.sql.Timestamp newTimestamp(int ano, int mes, int dia, int hora, int minutos, int segundos) {
        GregorianCalendar gc = new GregorianCalendar(ano, mes, dia, hora, minutos, segundos);
        return new java.sql.Timestamp(gc.getTimeInMillis());
    }

    public static GregorianCalendar newCalendar(java.util.Date data) {
        return newCalendar(data.getTime());
    }

    public static GregorianCalendar newCalendar() {
        return newCalendar(System.currentTimeMillis());
    }

    public static GregorianCalendar newCalendar(long data) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(data);
        return gc;
    }

    public static int getField(int field) {
        return getField(System.currentTimeMillis(), field);
    }

    public static int getField(long data, int field) {
        GregorianCalendar gc = newCalendar(data);
        return gc.get(field);
    }

    public static int getField(java.util.Date data, int field) {
        return getField(data.getTime(), field);
    }

    public static int getDia(long data) {
        return getField(data, GregorianCalendar.DAY_OF_MONTH);
    }

    public static int getDia() {
        return getDia(System.currentTimeMillis());
    }

    public static int getDia(java.util.Date data) {
        return getDia(data.getTime());
    }

    public static long getTimeDia(long data) {
        return zeraHora(data);
    }

    public static long getTimeDia() {
        return getTimeDia(System.currentTimeMillis());
    }

    public static long getTimeDia(java.util.Date data) {
        return getTimeDia(data.getTime());
    }

    public static long getHoje() {
        return getTimeDia();
    }

    public static java.sql.Date dataHoje() {
        return new java.sql.Date(getHoje());
    }

    public static long getAmanha() {
        GregorianCalendar calendario = zeraHora(newCalendar());
        calendario.add(Calendar.DAY_OF_MONTH, 1);

        return calendario.getTimeInMillis();
    }

    public static java.sql.Date dataAmanha() {
        return new java.sql.Date(getAmanha());
    }

    public static long getAgora() {
        return System.currentTimeMillis();
    }

    public static java.sql.Timestamp dataAgora() {
        return new java.sql.Timestamp(getAgora());
    }

    public static long dataPrimeiroDiaMes(long data) {
        GregorianCalendar gc = newCalendar(data);
        gc.set(GregorianCalendar.DAY_OF_MONTH, 1);

        return gc.getTimeInMillis();
    }

    public static java.sql.Date dataPrimeiroDiaMes() {
        return new java.sql.Date(dataPrimeiroDiaMes(System.currentTimeMillis()));
    }

    public static long dataUltimoDiaMes(long data) {
        GregorianCalendar gc = newCalendar(data);
        gc.set(GregorianCalendar.DAY_OF_MONTH, gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));

        return gc.getTimeInMillis();
    }

    /**
     * Método para retornar o ultimo dia do mês corrente
     *
     * @return java.sql.Date
     */
    public static java.sql.Date dataUltimoDiaMes() {
        return new java.sql.Date(dataUltimoDiaMes(System.currentTimeMillis()));
    }

    /**
     * Método para retornar o ultimo dia do mês passado como parametro
     *
     * @param data java.sql.Date - Data de referência
     * @return java.sql.Date
     */
    public static java.sql.Date dataUltimoDiaMes(java.sql.Date data) {
        return new java.sql.Date(dataUltimoDiaMes(data.getTime()));
    }

    /**
     * Método para retornar o ultimo dia do mês passado como parametro
     *
     * @param data Timestamp - Data de referência
     * @return java.sql.Date
     */
    public static java.sql.Timestamp dataUltimoDiaMes(java.sql.Timestamp data) {
        return new java.sql.Timestamp(dataUltimoDiaMes(data.getTime()));
    }

    public static Date addDias(Date data, int dias) {
        return addValueField(data, Calendar.DAY_OF_MONTH, dias);
    }

    public static Date addMeses(Date data, int meses) {
        return addValueField(data, Calendar.MONTH, meses);
    }

    public static Date addAnos(Date data, int anos) {
        return addValueField(data, Calendar.MONTH, anos);
    }

    private static Date addValueField(Date data, int field, int valueToAdd) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(data);
        cal.add(field, valueToAdd);
        return cal.getTime();
    }

    public static Date setValueField(Date data, int field, int valueToAdd) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(data);
        cal.set(field, valueToAdd);
        return cal.getTime();
    }

    public static int daysBetweenDates(Date dt1, Date dt2) {
        return Days.daysBetween(new LocalDate(dt1), new LocalDate(dt2)).getDays();
    }

    public static int hoursBetweenDates(Date dt1, Date dt2) {
        return Hours.hoursBetween(new LocalDate(dt1), new LocalDate(dt2)).getHours();
    }

    public static String showDateDiffFromNow(Date date) {
        Date startTime = Calendar.getInstance().getTime();

        long difference = startTime.getTime() - date.getTime();

        long differenceSeconds = difference / 1000 % 60;
        long differenceMinutes = difference / (60 * 1000) % 60;
        long differenceHours = difference / (60 * 60 * 1000) % 24;
        long differenceDays = difference / (24 * 60 * 60 * 1000);
        long differenceMonth = difference / (24 * 60 * 60 * 1000 * 30);
        if (differenceMonth > 0) {
            return differenceMonth + " mês(es)";
        }
        if (differenceDays > 0) {
            return differenceDays + " dia(s)";
        }
        if (differenceHours > 0) {
            return differenceHours + " hora(s)";
        }
        if (differenceMinutes > 0) {
            return differenceMinutes + " minuto(s)";
        }
        if (differenceSeconds > 0) {
            return differenceSeconds + " segundo(s)";
        }
        return "agora";
    }

    /*
     * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws ParseException {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            throw new ParseException(ex.getMessage(), ex.hashCode());
        }
        return xmlCalendar;
    }

    /*
     * Converts XMLGregorianCalendar to java.util.Date in Java
     */
    public static Date toDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    public static Date proximoDiaUtil(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1); //Soma 1 dia
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, 1); //Soma 1 dia pra cair na segunda feira
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, 2); //Soma 2 dias pra cair na segunda feira
        }
        return calendar.getTime();
    }
}
