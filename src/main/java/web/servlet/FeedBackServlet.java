package web.servlet;

import entity.FeedbackMail;
import org.apache.commons.beanutils.BeanUtils;
import service.FeedBackService;
import service.impl.FeedBackServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/FeedBackServlet")
public class FeedBackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        FeedbackMail mail = new FeedbackMail();
        FeedBackService feedBackService = new FeedBackServiceImpl();
        try {
            BeanUtils.populate(mail, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        feedBackService.feedBack(mail.getEname(), mail.getEmail(), mail.getSubject(), mail.getMessage());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
