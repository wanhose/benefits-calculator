package utils;

import java.io.File;

public abstract class FileUtils {

    /**
     * This method extracts extension from file.
     * @param file
     * @return extension
     */
    public static String getExtension(File file) {
        String filename = file.getName();
        String extension = "";

        if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf("0") != 0) {
            extension = filename.substring(filename.lastIndexOf(".") + 1);
        }

        return extension;
    }
}