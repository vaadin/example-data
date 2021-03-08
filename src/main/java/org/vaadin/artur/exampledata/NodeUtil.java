package org.vaadin.artur.exampledata;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.vaadin.flow.server.frontend.FrontendTools;
import com.vaadin.flow.server.frontend.FrontendUtils;

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
            FrontendTools tools = new FrontendTools("", () -> FrontendUtils.getVaadinHomeDirectory().getAbsolutePath());
            String node = tools.getNodeExecutable();
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
                reader.read();
                skipToken = false;
            }
            nodeScanner = new Scanner(reader);
            nodeScanner.useDelimiter("> ");
            if (skipToken) {
                nodeScanner.next();
            }
        }
        nodeWriter.println(script);
        return nodeScanner.next().replaceAll("\n$", "");
    }

}
