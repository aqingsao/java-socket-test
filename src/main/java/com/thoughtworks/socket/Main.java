package com.thoughtworks.socket;

public class Main {

    private void run(String host, int port, int clientCount) {

        SocketClients clients = new SocketClients(clientCount, IReporter.DUMMY_REPORTER);
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
        int maxCount = 1000;
        if(args.length > 0){
//            Utils.log("Usage: java -jar java-socket-test.jar host port maxCount");
//            Utils.log("Example: java -jar java-socket-test.jar 10.18.2.163:7222 1000\n");
            maxCount = Integer.parseInt(args[0]);
        }

        new Main().run(host, port, maxCount);
    }

}
