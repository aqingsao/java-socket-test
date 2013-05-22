package com.thoughtworks.socket;

import java.util.ArrayList;
import java.util.List;

public class SocketClients {
    private int expectedCount;
    private List<SocketClient> clients = new ArrayList<SocketClient>();
    private int intervalInSeconds;
    private int lastIntervalInSeconds;

    private int successCount = 0;

    public SocketClients(int expectedCount, int intervalInSeconds) {
        this.expectedCount = expectedCount;
        this.intervalInSeconds = intervalInSeconds;
    }

    public void tryToConnect(String host, int port) {
        reportStatus();

        for (int i = 0; i < expectedCount; i++) {
            Utils.log("Start to open socket %d", i);
            SocketClient client = new SocketClient(i);
            if (client.connect(host, port)) {
                clients.add(client);
                if(++successCount % 50 == 0){
                    reportStatus();
                }
                lastIntervalInSeconds = intervalInSeconds;
            }
            else{
                lastIntervalInSeconds = lastIntervalInSeconds >= 60 ? 24 * 60 * 60 : lastIntervalInSeconds + 10;

            }
            Utils.log("Will sleep %d seconds before open next socket", lastIntervalInSeconds);
            Utils.sleepInSeconds(lastIntervalInSeconds);
        }
    }

    private void reportStatus() {
//        WebResource webResource = getClient().resource(uri(path));
//
//        return webResource.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);


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
