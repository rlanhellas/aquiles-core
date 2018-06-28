package br.com.aquiles.core.util;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import br.com.aquiles.core.dto.ColumnPrint;
import br.com.aquiles.core.exception.CoreException;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Classe utilitária responsável por gerar arquivos em formatos distintos (PDF, XLS, DOC ...)
 *
 * @author Ronaldo Lanhellas
 */
public abstract class PrintGeneratorUtils implements Serializable {

    private static Logger logger = Logger.getLogger(PrintGeneratorUtils.class);

    public static <Bean> ByteArrayOutputStream createHTML(List<Bean> beans, List<ColumnPrint> columns) throws IOException {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            List<String> columnsLabel = new ArrayList<String>();
            List<String> properties = new ArrayList<String>();
            for (ColumnPrint cp : columns) {
                columnsLabel.add(cp.getColumnLabel());
                properties.add(cp.getPropertyName());
            }

            StringBuilder html = new StringBuilder();
            html.append("<table style='width:95%;'>");

            html.append("<tr style='font-weight:bold;background-color:#e5e5e5;padding:3px;'>");
            for (String column : columnsLabel) {
                html.append("<td>" + column + "</td>");
            }
            html.append("</tr>");

            for (Bean bean : beans) {
                List<Object> values = valuesFromProperties(bean, properties);
                html.append("<tr>");
                for (Object o : values) {
                    html.append("<td>");
                    html.append(o.toString());
                    html.append("</td>");
                }
                html.append("</tr>");
            }
            html.append("</table>");
            baos.write(html.toString().getBytes());
            return baos;
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException("Ocorreu um erro ao tentar gerar o HTML. " + e.getCause().getMessage());
        } finally {
            if(baos!=null) {
                baos.close();
            }
        }
    }

    public static <Bean> ByteArrayOutputStream createXLS(List<Bean> beans, List<ColumnPrint> columns) throws IOException {
        ByteArrayOutputStream baos = null;
        try (HSSFWorkbook workbook = new HSSFWorkbook()){
            baos = new ByteArrayOutputStream();
            HSSFSheet sheet = workbook.createSheet("Tabela");

            Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
            List<String> columnsLabel = new ArrayList<String>();
            List<String> properties = new ArrayList<String>();
            for (ColumnPrint cp : columns) {
                columnsLabel.add(cp.getColumnLabel());
                properties.add(cp.getPropertyName());
            }

            data.put("1", columnsLabel.toArray());
            for (int i = 0; i < beans.size(); i++) {
                data.put(String.valueOf(i + 2), valuesFromProperties(beans.get(i), properties).toArray());
            }

            Set<String> keyset = data.keySet();
            int rownum = 0;
            for (String key : keyset) {
                HSSFRow row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    HSSFCell cell = row.createCell(cellnum++);
                    if (obj instanceof Date)
                        cell.setCellValue((Date) obj);
                    else if (obj instanceof Boolean)
                        cell.setCellValue((Boolean) obj);
                    else if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Double)
                        cell.setCellValue((Double) obj);
                }
            }

            //configurando tamanho de cada coluna
            for (int i = 0; i < columnsLabel.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(baos);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException("Ocorreu um erro ao tentar gerar o XLS. " + e.getCause().getMessage());
        } finally {
            if(baos!=null) {
                baos.close();
            }
        }

        return baos;
    }

    public static <Bean> ByteArrayOutputStream createPDF(List<Bean> beans, List<ColumnPrint> columns) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CoreException {

        // Set margins
        float margin = 10;

        // Initialize Document
        PDDocument doc = new PDDocument();
        ByteArrayOutputStream baos = null;
        try {
            PDPage page = addNewPage(doc);
            float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);

            // Initialize table
            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
            float yStart = yStartNewPage;
            float bottomMargin = 70;

            List<String> columnsLabel = new ArrayList<String>();
            List<String> properties = new ArrayList<String>();
            List<Integer> columnsSize = new ArrayList<>();
            boolean columnSizeInformado = false;
            for (ColumnPrint cp : columns) {
                columnsLabel.add(cp.getColumnLabel());
                properties.add(cp.getPropertyName());
                columnsSize.add(cp.getColumnSize());
                if (!columnSizeInformado && cp.getColumnSize() != null) {
                    columnSizeInformado = true;
                } else if (columnSizeInformado && cp.getColumnSize() == null) {
                    throw new CoreException("Se você informar o tamanho para pelo menos 1 coluna, então todas devem ser informadas. A soma dos tamanhos devem ser igual a 100%");
                }
            }

            BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, true);

            if (!columnSizeInformado) {
                Integer totalPorColuna = 100 / columnsSize.size();
                for (int i = 0; i < columnsSize.size(); i++) {
                    columnsSize.set(i, totalPorColuna);
                }
            }

            //cria o header
            Row<PDPage> headerRow = dataTable.createRow(15f);
            for (int i = 0; i < columnsLabel.size(); i++) {
                Cell<PDPage> cell = headerRow.createCell(Float.parseFloat(columnsSize.get(i).toString()), columnsLabel.get(i));
                cell.setFont(PDType1Font.HELVETICA_BOLD);
                cell.setFillColor(Color.LIGHT_GRAY);
            }
            dataTable.addHeaderRow(headerRow);

            //cria as linhas com os valores
            for (Bean b : beans) {
                List<Object> values = valuesFromProperties(b, properties);
                Row<PDPage> row = dataTable.createRow(10f);
                for (int i = 0; i < columnsLabel.size(); i++) {
                    row.createCell(Float.parseFloat(columnsSize.get(i).toString()), values.get(i).toString());
                }
            }

            dataTable.draw();

            baos = new ByteArrayOutputStream();
            doc.save(baos);

        } finally {
            doc.close();
        }

        return baos;
    }

    private static List<Object> valuesFromProperties(Object o, List<String> properties) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Object> values = new ArrayList<Object>();
        for (String property : properties) {
            values.add(extractValueFromObject(o, property));
        }

        return values;
    }

    private static Object extractValueFromObject(Object o, String property) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int level = property.split("\\.").length;

        if (level == 1) {
            Method m = o.getClass().getMethod(getMethodName(property));
            return executeMethod(m, o);
        } else {
            Class actualClazz = null;
            for (int i = 1; i < level; i++) {
                if (o == null) {
                    return " ";
                }
                actualClazz = o.getClass();
                o = actualClazz.getMethod(getMethodName(getPropertyByLevel(property, i))).invoke(o);
            }

            String finalProperty = property.split("\\.")[level - 1];
            if (o==null){
                return "";
            }
            Method m = o.getClass().getMethod(getMethodName(finalProperty));
            return executeMethod(m, o);
        }
    }

    private static String getPropertyByLevel(String property, int level) {
        return property.split("\\.")[level - 1];
    }

    private static String executeMethod(Method m, Object o) throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Object getReturn = m.invoke(o);
        if (getReturn instanceof Date) {
            return formatValue((Date) getReturn);
        } else {
            return formatValue(getReturn);
        }
    }

    private static String getMethodName(String property) {
        if (property.startsWith("#")) {
            return property.replace("#", "");
        } else {
            return "get" + Character.toString(property.charAt(0)).toUpperCase() + property.substring(1);
        }
    }

    private static String formatValue(Date value) {
        if (value == null) {
            return formatValue((String) null);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return formatValue(sdf.format(value));
    }

    private static String formatValue(Object value) {
        return value == null ? " " : formatValue(String.valueOf(value));
    }

    private static String formatValue(String value) {
        StringBuilder strb = new StringBuilder();
        if (isEmpty(value)) {
            strb.append(" ");
        } else {
            strb.append(value);
        }

        return strb.toString();
    }

    private static boolean isEmpty(String value) {
        return value == null || value.equals("");
    }

    private static PDPage addNewPage(PDDocument doc) {
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);
        return page;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PrintGeneratorUtils.logger = logger;
    }
}
