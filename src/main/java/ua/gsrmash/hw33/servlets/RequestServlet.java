package ua.gsrmash.hw33.servlets;

import ua.gsrmash.hw33.model.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "RequestServlet", value = "/RequestServlet")
public class RequestServlet extends HttpServlet {
    private final List<Info> infoList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action == null ? "request" : action) {
            case "request" -> {
                String ipAddress = req.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = req.getRemoteAddr();
                }
                String timeWithMayBeDate = req.getHeader("Date");
                if (timeWithMayBeDate == null) {
                    timeWithMayBeDate = LocalTime.now().toString();
                }
                Info info = new Info(ipAddress, req.getHeader("User-Agent"), timeWithMayBeDate);
                infoList.add(info);
                System.out.println("Request is send");
                PrintWriter responseBody = resp.getWriter();
                resp.setContentType("text/html");
                responseBody.println(String.format("<h1 align=\"center\" style=\"color:#ff0000\">Request number %s</h1>",
                        infoList.size()));
                responseBody.println("<h1 align=\"center\">from:</h1>");
                responseBody.println(String.format("<h1 align=\"center\" style=\"color:#00ff00\">IP: %s </h1>",
                        ipAddress));
                responseBody.println(String.format("<h2 align=\"center\" style=\"color:#ff8800\">User-Agent: %s</h2>",
                        req.getHeader("User-Agent")));
                responseBody.println(String.format("<h2 align=\"center\" style=\"color:#55aaff\">Time: %s</h2>",
                        timeWithMayBeDate));
                responseBody.println("<div style=\"text-align:center\">" +
                        "<INPUT TYPE=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);\">" +
                        "</div>");
            }
            case "show" -> {
                req.setAttribute("infoList", infoList);
                getServletContext().getRequestDispatcher("/showInfo.jsp").forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }
}
