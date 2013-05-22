package com.thoughtworks.socket;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Reporter {
    private String ip;
    private Client client;

    void reportStatus(int successCount) {
        report(String.format("http://10.18.2.85:5120/reports/running/%s/%d", getIp(), successCount));
    }

    public void reportStarted() {
        report(String.format("http://10.18.2.85:5120/reports/started/%s", getIp()));
    }

    public void reportEnded(int successCount) {
        report(String.format("http://10.18.2.85:5120/reports/ended/%s/%d", getIp(), successCount));
    }

    private void report(String url) {
        try {
            getClient().resource(url).get(ClientResponse.class);
        } catch (Exception e) {
            Utils.log("failed to report status to server %s", "10.18.2.85:5120/reports");
        }
    }

    private Client getClient() {
        if (client == null) {
            client = Client.create();
        }
        return client;
    }

    private String getIp() {
        if (ip == null) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                Utils.log("failed to get ip address");
            }
        }
        return ip;
    }
}
