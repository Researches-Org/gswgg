package util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    public static String readFile(String path) {
        try(FileReader fileReader = new FileReader(new File(path))) {
            char[] buffer = new char[512];

            StringBuilder sb = new StringBuilder();
            int size;
            while ((size = fileReader.read(buffer)) > 0) {
                sb.append(new String(buffer, 0, size));
            }

            return sb.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

}