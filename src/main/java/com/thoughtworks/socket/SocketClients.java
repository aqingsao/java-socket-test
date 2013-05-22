package com.thoughtworks.socket;

import com.sun.jersey.api.client.*;

import java.util.ArrayList;
import java.util.List;

public class SocketClients {
    private final Reporter reporter;
    private int expectedCount;
    private List<SocketClient> clients = new ArrayList<SocketClient>();
    private int intervalInSeconds;
    private int lastIntervalInSeconds;

    private int successCount = 0;

    public SocketClients(int expectedCount, int intervalInSeconds) {
        this.expectedCount = expectedCount;
        this.intervalInSeconds = intervalInSeconds;
        this.reporter = new Reporter();
    }

    public void tryToConnect(String host, int port) {
        reporter.reportStarted();

        for (int i = 0; i < expectedCount; i++) {
            Utils.log("Start to open socket %d", i);
            SocketClient client = new SocketClient(i);
            if (client.connect(host, port)) {
                clients.add(client);
                if (++successCount % 50 == 0) {
                    reporter.reportStatus(successCount);
                }
                lastIntervalInSeconds = intervalInSeconds;
            } else if (lastIntervalInSeconds >= 60) {
                reporter.reportEnded(successCount);
                lastIntervalInSeconds = lastIntervalInSeconds + 10;
            } else {
                lastIntervalInSeconds = 24 * 60 * 60;
            }
            Utils.log("Will sleep %d seconds before open next socket", lastIntervalInSeconds);
            Utils.sleepInSeconds(lastIntervalInSeconds);
        }
    }

    public void closeAll() {
        for (SocketClient client : clients) {
            client.closeQuietly();
        }
    }

    public int size() {
        return this.clients.size();
    }
}
