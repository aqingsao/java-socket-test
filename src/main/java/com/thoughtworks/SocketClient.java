package com.thoughtworks;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketClient {
    private Socket socket;
    private InputStream in;
    private Thread runnable;
    private int index;

    private boolean errorOccurred = false;

    public SocketClient(int index) {
        this.index = index;
    }

    public SocketClient connect(final String host, final int port) {
        runnable = new Thread() {
            @Override
            public void run() {
                openSocket(host, port);
            }
        };
        runnable.start();
        return this;
    }

    private void openSocket(String host, int port) {
        try {
            socket = new Socket(host, port);

            in = socket.getInputStream();
            Utils.log("Open socket %d successfully.", index);
        } catch (IOException e) {
            Utils.log("failed to open socket %d: %s", index, e.getMessage(), e);
            errorOccurred = true;
        }
    }

    public void closeQuietly() {
        Utils.closeQuietly(socket);
        Utils.closeQuietly(in);
    }

    public boolean isErrorOccurred() {
        return errorOccurred;
    }

    public int getIndex() {
        return index;
    }
}
