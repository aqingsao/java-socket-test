package com.thoughtworks.socket;

import java.util.ArrayList;
import java.util.List;

public class SocketClients {
    private final IReporter reporter;
    private int expectedCount;
    private List<SocketClient> clients = new ArrayList<SocketClient>();
    private int retryCount = 0;

    private int successCount = 0;

    public SocketClients(int expectedCount, IReporter reporter) {
        this.expectedCount = expectedCount;
        this.reporter = reporter;
    }

    public void tryToConnect(String host, int port) {
        reporter.reportStarted();

        while (successCount < expectedCount) {
            Utils.log("Start to open socket %d", successCount);
            SocketClient client = new SocketClient(successCount);
            if (client.connect(host, port)) {
                clients.add(client);
                if (++successCount % 50 == 0) {
                    reporter.reportStatus(successCount);
                }
                retryCount = 0;
            } else {
                Utils.log("Connection failed, will retry for %d times after %d seconds", ++retryCount, 15);
                Utils.sleepInSeconds(15);
            }
            if(retryCount > 10){
                Utils.log("\nWarning: \nStill failed after retrying %d times, will not retry again", retryCount);
                break;
            }
        }
        Utils.sleepInSeconds(24 * 60 * 60);
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
