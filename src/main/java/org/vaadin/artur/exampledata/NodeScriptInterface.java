package org.vaadin.artur.exampledata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;

public class NodeScriptInterface {

    private static final DateTimeFormatter OUTPUT_DATE_PATTERN = DateTimeFormatter.ofPattern("M/d/y");
    private static NodeUtil node = new NodeUtil();

    private static void init() throws InterruptedException, IOException {
        File chanceTempFile = File.createTempFile("exampleutil-bundle", "js");
        chanceTempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(chanceTempFile)) {
            IOUtils.copyLarge(NodeScriptInterface.class.getResourceAsStream("/bundle.js"), out);
        }
        String cmd = String.format("const all = require('%s'); const c = all.chance; const cdigit = all.cdigit;",
                chanceTempFile.getAbsolutePath());
        node.initialize(cmd);
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

    public static Boolean getChanceBoolean(int seed, String type, String options) {
        return Boolean.parseBoolean(getChanceString(seed, type, options));
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
