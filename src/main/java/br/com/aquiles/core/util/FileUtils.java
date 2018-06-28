package br.com.aquiles.core.util;

import java.io.*;

/**
 * Created by Raimundo Junior on 03/03/2017.
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

    public static BufferedWriter createFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        return out;
    }

    public static void addLine(BufferedWriter out, String linha) throws IOException {
        out.write(linha);
        out.newLine();
    }

    public static void closeFile(BufferedWriter out) throws IOException {
        out.flush();
        out.close();
    }

    public static InputStream readFileFromResourceFolder(String fileName) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }


}
