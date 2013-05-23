package com.thoughtworks.socket;

import java.util.ArrayList;
import java.util.List;

public class SocketClients {
    private final IReporter reporter;
    private int connectionLimit;
    private List<SocketClient> clients = new ArrayList<SocketClient>();
    private int retryTimes = 0;

    private int connectedCount = 0;

    public SocketClients(int connectionLimit, IReporter reporter) {
        this.connectionLimit = connectionLimit;
        this.reporter = reporter;
    }

    public void tryToConnect(String host, int port) {
        reporter.reportStarted();

        while (connectedCount < connectionLimit) {
            Utils.log("Start to open socket %d", connectedCount);
            SocketClient client = new SocketClient(connectedCount);
            if (client.connect(host, port)) {
                clients.add(client);
                if (++connectedCount % 50 == 0) {
                    reporter.reportStatus(connectedCount);
                }
                retryTimes = 0;
            } else {
                Utils.log("Connection failed, will retry for %d times after %d seconds", ++retryTimes, 15);
                Utils.sleepInSeconds(15);
            }
            if(retryTimes > 10){
                Utils.log("\nWarning: \nStill failed after retrying %d times, will not retry again", retryTimes);
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
