package com.thoughtworks;

public class Main {
    public static final int SECONDS = 15;
    private static boolean continueToOpenSocket = true;

    private void run() {
        int count = 240;
        String host = "10.18.2.163";
        int port = 7222;
        SocketClients clients = new SocketClients(count, seconds);
        clients.tryToConnect(host, port);

        int sleepBeforeClose = 1;
        if (continueToOpenSocket || clients.size() > 10) {
            sleepBeforeClose = 60 * 60;
        }
        Utils.log("Will sleep for %d seconds before close all sockets", sleepBeforeClose);
        Utils.sleepInSeconds(sleepBeforeClose);
        clients.closeAll();
    }

    public static void main(String[] args) {
        new Main().run();
    }

}
