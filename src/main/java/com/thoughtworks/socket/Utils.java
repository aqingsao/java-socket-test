package com.thoughtworks.socket;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Utils {

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void log(String msg, Object... params) {
        System.out.println(String.format(msg, params));
        for (Object param : params) {
            if (param instanceof Exception) {
                ((Exception) param).printStackTrace();
            }
        }
    }

    public static void closeQuietly(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void closeQuietly(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
        }
    }
}
