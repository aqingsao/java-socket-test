package com.thoughtworks.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/sockets"})
public class MakeJnlp extends HttpServlet {
    private static String RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<jnlp spec=\"1.0+\" codebase=\"http://%s:%d%s/\">\n" +
            "    <information>\n" +
            "        <title>aqingsao</title>\n" +
            "        <vendor>ThoughtWorks</vendor>\n" +
            "        <homepage href=\"http://xiaoqing.me/\"/>\n" +
            "        <description>Socket test client</description>\n" +
            "        <description kind=\"short\">Socket test</description>\n" +
            "    </information>\n" +
            "    <security>\n" +
            "        <all-permissions/>\n" +
            "    </security>\n" +
            "    <update check=\"always\"/>\n" +
            "    <resources>\n" +
            "        <j2se version=\"1.7+\"/>\n" +
            "        <jar href=\"./libs/java-socket-test.jar\"/>\n" +
            "    </resources>\n" +
            "    <application-desc main-class=\"com.thoughtworks.socket.Main\"></application-desc>\n" +
            "</jnlp>";

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getServerName();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();
        response.setContentType("application/x-java-jnlp-file");
        PrintWriter out = response.getWriter();
        out.println(String.format(RESPONSE, ip, port, contextPath));

        out.flush();
        out.close();
    }
}
