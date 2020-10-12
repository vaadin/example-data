package org.vaadin.artur.exampledata;

import java.io.File;
import java.io.IOException;

import org.slf4j.LoggerFactory;

public class ChanceInterface {

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

    public static String getString(int seed, String type) {
        try {
            init();
            String result = node.runScript("c.Chance(" + seed + ")." + type + "()");
            return result.substring(1, result.length() - 1);
        } catch (InterruptedException | IOException e) {
            LoggerFactory.getLogger(ChanceInterface.class).error("Unable to generate value of type '" + type + "'", e);
            return "Error";
        }
    }

}
