package web.servlet;

import entity.Label;
import service.LabelService;
import service.impl.LabelServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/label/*")
public class LabelServlet extends BaseServlet {
    private LabelService labelService = new LabelServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Label> list = labelService.findAll();
        outputJson(request, response, list);
    }
}