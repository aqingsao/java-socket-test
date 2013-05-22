package com.thoughtworks.web;

import com.thoughtworks.socket.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if(isStarted(req)){
            logStarted(req);
        }
        else if(isEnded(req)){
            logEnded(req);
        }
        else if(isRunning(req)){
            logRunning(req);
        }
        else{
            Utils.log("Unknow path: ", req.getRequestURI());
        }
    }

    private boolean isRunning(HttpServletRequest req) {
        return false;  //To change body of created methods use File | Settings | File Templates.
    }

    private void logRunning(HttpServletRequest req) {
        req.getRequestURI();
    }

    private boolean isEnded(HttpServletRequest req) {
        return false;
    }

    private void logEnded(HttpServletRequest req) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private boolean isStarted(HttpServletRequest req) {
        return false;  //To change body of created methods use File | Settings | File Templates.
    }

    private void logStarted(HttpServletRequest req) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
