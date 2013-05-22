package com.thoughtworks.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketClient {
    private Socket socket;
    private InputStream in;
    private int index;

    public SocketClient(int index) {
        this.index = index;
    }

    public boolean connect(final String host, final int port) {
        try {
            socket = new Socket(host, port);

            in = socket.getInputStream();
            Utils.log("Open socket %d successfully.", index);
            return true;
        } catch (IOException e) {
            Utils.log("failed to open socket %d: %s", index, e.getMessage(), e);
            return false;
        }
    }

    public void closeQuietly() {
        Utils.closeQuietly(socket);
        Utils.closeQuietly(in);
    }

}
