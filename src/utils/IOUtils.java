package utils;

import java.util.Scanner;

public abstract class IOUtils {

    /**
     * This methods allows text input to user.
     * @param question
     * @return answer
     */
    public static String ask(Scanner input, String question) {
        String answer = "";

        System.out.print(question);
        answer = input.nextLine();

        return answer;
    }
}