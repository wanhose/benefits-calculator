package main;

import app.App;

public class Main {

    /**
     * This method allows program to run.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        App app = new App(args);
        app.exec();
    }
}