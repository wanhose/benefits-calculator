package app;

import java.io.File;
import java.util.Scanner;
import utils.FileUtils;
import utils.IOUtils;
import utils.Reader;

public class App {

    /**
     * This method execute program functionality.
     */
    public void exec() {
        Scanner input = new Scanner(System.in);
        File csvFile = this.askFile(input, "csv", "Please, enter .csv file path: ");
        File jsonFile = this.askFile(input, "json", "Please, enter .json file path: ");
        Reader reader = new Reader(csvFile, jsonFile);

        if (csvFile != null && jsonFile != null) {
            reader.getProducts();
        } else {
            System.out.println("\nERROR. A critical error has ocurred. Please contact administrator for more information.\n");
        }

        input.close();
    }

    /**
     * This method ask for file path and return this file if exists.
     * @param input
     * @param filetype
     * @param question
     * @return file
     */
    private File askFile(Scanner input, String filetype, String question) {
        boolean loop = true;
        File file = null;
        String path = "";

        while (loop) {
            path = IOUtils.ask(input, question);
            file = new File(path);

            if (file.exists() && FileUtils.getExtension(file).equals(filetype)) {
                loop = false;
            } else {
                System.out.println("\nERROR. File specified doesn't exists or it doesn't match ." + filetype + " filetype. Please try again.\n");
            }
        }

        return file;
    }
}