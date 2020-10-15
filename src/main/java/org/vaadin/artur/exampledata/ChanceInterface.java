package org.vaadin.artur.exampledata;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.LoggerFactory;

public class ChanceInterface {

    private static final DateTimeFormatter OUTPUT_DATE_PATTERN = DateTimeFormatter.ofPattern("M/d/y");
    private static NodeUtil node = new NodeUtil();

    private static void init() throws InterruptedException, IOException {
        waitForFile("node_modules/chance", 120);
        node.initialize("const c = require('chance')");
    }

    private static void waitForFile(String fileNameInProject, int maxTime) {
        File f = new File(fileNameInProject);
        if (!f.exists()) {
            LoggerFactory.getLogger(ChanceInterface.class)
                    .info("Waiting for " + fileNameInProject + " to become available");
        }
        while (!f.exists() && maxTime-- > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
            LoggerFactory.getLogger(ChanceInterface.class)
                    .debug("Waiting for " + fileNameInProject + " to become available");
        }
    }

    public static String getString(int seed, String type, String options) {
        try {
            init();
            String result = node.runScript("c.Chance(" + seed + ")." + type + "(" + options + ")");
            return result.substring(1, result.length() - 1);
        } catch (InterruptedException | IOException e) {
            LoggerFactory.getLogger(ChanceInterface.class).error("Unable to generate value of type '" + type + "'", e);
            return "Error";
        }
    }

    public static LocalDate getLocalDate(int seed, String type, String options) {
        try {
            init();
            String result = node.runScript("c.Chance(" + seed + ")." + type + "(" + options + ")");
            return LocalDate.parse(result.substring(1, result.length() - 1), OUTPUT_DATE_PATTERN);
        } catch (InterruptedException | IOException e) {
            LoggerFactory.getLogger(ChanceInterface.class).error("Unable to generate value of type '" + type + "'", e);
            return LocalDate.now();
        }
    }

}
