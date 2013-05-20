package com.thoughtworks;

public class Main {

    private void run() {
        String host = "10.18.2.163";
        int port = 7222;

        int count = 240;
        int intervalInSeconds = 15;

        SocketClients clients = new SocketClients(count, intervalInSeconds);
        clients.tryToConnect(host, port);

        if (keepThisProcessForAWhile(count, clients)) {
            int timeWillBeSpent = count * 15; // 240 will spend 1 hour(240 * 15=3600s)
            Utils.log("Will sleep for %d seconds before close all sockets", timeWillBeSpent);
            Utils.sleepInSeconds(timeWillBeSpent);
        }
        clients.closeAll();
    }

    private boolean keepThisProcessForAWhile(int count, SocketClients clients) {
        return !clients.isErrorOccurred() || count > 10; // We will
    }

    public static void main(String[] args) {
        new Main().run();
    }

}
