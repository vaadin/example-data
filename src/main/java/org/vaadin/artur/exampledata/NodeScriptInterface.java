package org.vaadin.artur.exampledata;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.LoggerFactory;

public class NodeScriptInterface {

    private static final DateTimeFormatter OUTPUT_DATE_PATTERN = DateTimeFormatter.ofPattern("M/d/y");
    private static NodeUtil node = new NodeUtil();

    private static void init() throws InterruptedException, IOException {
        waitForFile("node_modules/chance", 120);
        node.initialize("const c = require('chance'); const cdigit = require('cdigit');");
    }

    private static void waitForFile(String fileNameInProject, int maxTime) {
        File f = new File(fileNameInProject);
        if (!f.exists()) {
            LoggerFactory.getLogger(NodeScriptInterface.class)
                    .info("Waiting for " + fileNameInProject + " to become available");
        }
        while (!f.exists() && maxTime-- > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
            LoggerFactory.getLogger(NodeScriptInterface.class)
                    .debug("Waiting for " + fileNameInProject + " to become available");
        }
    }

    public static String getChanceString(int seed, String type, String options) {
        try {
            init();
            String result = node.runScript("c.Chance(" + seed + ")." + type + "(" + options + ")");
            if (result.startsWith("\"") || result.startsWith("'")) {
                result = result.substring(1, result.length() - 1);
            }

            return result;
        } catch (InterruptedException | IOException e) {
            LoggerFactory.getLogger(NodeScriptInterface.class).error("Unable to generate value of type '" + type + "'",
                    e);
            return "Error";
        }
    }

    public static Integer getChanceInteger(int seed, String type, String options) {
        return Integer.parseInt(getChanceString(seed, type, options));
    }

    public static Double getChanceDouble(int seed, String type, String options) {
        return Double.parseDouble(getChanceString(seed, type, options));
    }

    public static LocalDate getChanceLocalDate(int seed, String type, String options) {
        try {
            init();
            String result = node.runScript("c.Chance(" + seed + ")." + type + "(" + options + ")+\"\"");
            return LocalDate.parse(result.substring(1, result.length() - 1), OUTPUT_DATE_PATTERN);
        } catch (InterruptedException | IOException e) {
            LoggerFactory.getLogger(NodeScriptInterface.class).error("Unable to generate value of type '" + type + "'",
                    e);
            return LocalDate.now();
        }
    }

    public static String getEan13(int seed) {
        try {
            init();
            String result = node
                    .runScript("cdigit.gtin.generate(c.Chance(" + seed + ").integer({min: 1, max: 999999999999}))");
            return result.substring(1, result.length() - 1);
        } catch (InterruptedException | IOException e) {
            LoggerFactory.getLogger(NodeScriptInterface.class).error("Unable to generate ean 13", e);
            return "123";
        }
    }

}
