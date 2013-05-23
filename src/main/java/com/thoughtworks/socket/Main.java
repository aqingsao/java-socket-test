package com.thoughtworks.socket;

public class Main {

    private void run(String host, int port, int connectionLimit) {

        SocketClients clients = new SocketClients(connectionLimit, IReporter.DUMMY_REPORTER);
        clients.tryToConnect(host, port);

        Utils.log("");
        if (keepThisProcessForAWhile(clients)) {
            int sleepTime = 24 * 60 * 60; // will sleep 5 hours
            Utils.log("Will sleep for %d seconds before close all sockets", sleepTime);
            Utils.sleepInSeconds(sleepTime);
        }

        clients.closeAll();
    }

    private boolean keepThisProcessForAWhile(SocketClients clients) {
        return clients.size() >= 10; // We will
    }

    public static void main(String[] args) {
        String host = "10.18.2.163";
        int port = 7222;
        int connectionLimit = 1000;
        if(args.length >= 3){
            host = args[0];
            port = Integer.parseInt(args[1]);
            connectionLimit = Integer.parseInt(args[2]);
        }

        new Main().run(host, port, connectionLimit);
    }

}
