package org.vaadin.artur.exampledata;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.vaadin.flow.server.frontend.FrontendTools;
import com.vaadin.flow.server.frontend.FrontendUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeUtil {

    private Process nodeProcess = null;
    private PrintWriter nodeWriter;
    private Scanner nodeScanner;

    public void initialize(String initScript) throws InterruptedException, IOException {
        if (nodeProcess != null) {
            return;
        }
        runScript(initScript);
    }

    public synchronized String runScript(String script) throws InterruptedException, IOException {
        if (nodeProcess == null) {
            getLogger().debug("Node is not running, finding binary");
            FrontendTools tools = new FrontendTools("", () -> FrontendUtils.getVaadinHomeDirectory().getAbsolutePath());
            String node = tools.getNodeExecutable();
            getLogger().debug("Node is at '{}'", node);
            List<String> command = new ArrayList<>();
            command.add(node);
            command.add("-i");
            ProcessBuilder builder = FrontendUtils.createProcessBuilder(command);
            nodeProcess = builder.start();
            nodeWriter = new PrintWriter(nodeProcess.getOutputStream(), true);
            InputStreamReader reader = new InputStreamReader(nodeProcess.getInputStream(), "UTF-8");
            boolean skipToken = true;
            if (reader.read() == '>') {
                // Node 10, prints only "> " on startup
                getLogger().debug("First output is '>', assuming Node 10");
                reader.read();
                skipToken = false;
            }
            nodeScanner = new Scanner(reader);
            nodeScanner.useDelimiter("> ");
            if (skipToken) {
                getLogger().debug("Skipping initial token");
                String token = nodeScanner.next();
                getLogger().debug("Skipped: " + token);
            }
        }
        getLogger().debug("Executing script: '{}'", script);
        nodeWriter.println(script);
        String token = nodeScanner.next();
        getLogger().debug("Got token: '{}'", token);
        String value = token.replaceAll("[\r\n]*$", "");
        getLogger().debug("Returning: '{}'", value);
        return value;
    }

    private static Logger getLogger() {
        return LoggerFactory.getLogger(NodeUtil.class);
    }

}
