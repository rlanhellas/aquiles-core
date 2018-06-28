package br.com.aquiles.core.util;

import java.io.*;
import java.text.Normalizer;
import java.util.Base64;
import java.util.stream.Collectors;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String ZERO = "0";

    public static String leftPadWithZeros(String input, int expectedSize) {
        if (input == null) {
            return leftPadWithZeros("", expectedSize);
        }

        StringBuilder sb = new StringBuilder(expectedSize);

        for (int i = expectedSize - input.length(); i > 0; i--) {
            sb.append(ZERO);
        }

        sb.append(input);
        return sb.toString();
    }

    public static String leftPadWithSpaces(String input, int expectedSize) {
        if (input == null) {
            return leftPadWithZeros("", expectedSize);
        }

        StringBuilder sb = new StringBuilder(expectedSize);

        for (int i = expectedSize - input.length(); i > 0; i--) {
            sb.append(" ");
        }

        sb.append(input);
        return sb.toString();
    }

    /**
     * Método para auxiliar a verificacao de String nula e adicionar um prefixo caso não seja.
     *
     * @param str        Campo desejado para fazer a verificacao.
     * @param defaultStr Caso a str for nula, esse campo sera usado no lugar.
     * @param prefix     Adicionar um prefixo apenas se a str não for nula.
     * @return string tratada
     */
    public static String prefixNotNullStringOrDefault(String str, String defaultStr, String prefix) {
        if (str == null) {
            return defaultStr;
        }
        return prefix + str;
    }

    /**
     * Método para auxiliar a verificacao de String nula e adicionar um prefixo caso não seja.
     *
     * @param str        Campo desejado para fazer a verificacao.
     * @param defaultStr Caso a str for nula, esse campo sera usado no lugar.
     * @param suffix     Adicionar um sufixo apenas se a str não for nula.
     * @return string tratada
     */
    public static String suffixNotNullStringOrDefault(String str, String defaultStr, String suffix) {
        if (str == null) {
            return defaultStr;
        }
        return str + suffix;
    }


    public static String removerMascaras(String campo) {
        if (campo != null) {
            return campo.replaceAll("[^a-zA-Z0-9]", "");
        } else {
            return null;
        }
    }

    public static String removerAcentos(String str) {
        if (str != null && !str.isEmpty())
            return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        else
            return str;
    }

    public static boolean containsHTMLTag(String str) {
        return str.matches(".*\\<[^>]+>.*");
    }

    public static boolean checkSize(String string, int size) {
        return string.length() == size;
    }

    public static String replaceNullOrEmpty(String valor, String ifNull) {
        return valor != null && !valor.isEmpty() ? valor : ifNull;
    }

    /**
     * Read the object from Base64 string.
     */
    public static Object fromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    /**
     * Write the object to a Base64 string.
     */
    public static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static String getFirstLine(String s) {
        LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(s));
        return lineNumberReader.lines().findFirst().orElse(null);
    }

    public static String getLastLine(String s) {
        LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(s));
        return lineNumberReader.lines().reduce((first,second)-> second).orElse(null);
    }

    public static String getLine(String s,int nrLinha) {
        LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(s));
        return lineNumberReader.lines().filter(s1 -> lineNumberReader.lines().collect(Collectors.toList()).indexOf(s)==nrLinha).findFirst().orElse(null);
    }

    public static int checkFirstLineLength(String s) {
        if(s!=null) {
            return getFirstLine(s).length();
        }else{
            return 0;
        }
    }

    public static int checkLineLength(String s,int nrLinha) {
        if(s!=null) {
            return getLine(s, nrLinha).length();
        }else {
            return 0;
        }
    }
}
