package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Goods;
import entity.PageBean;
import service.GoodsService;
import service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search/*")
public class GoodsSearchServlet extends BaseServlet {
    private GoodsService GoodsService = new GoodsServiceImpl();
    public void searchGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收参数
        String str = request.getParameter("search");
        String _currentPage = request.getParameter("currentPage");
        // 处理_currentPage
        int currentPage = 1;
        if (!_currentPage.equals("null") && _currentPage.length() > 0) {
            currentPage = Integer.parseInt(_currentPage);
        }
        String _pageSize = request.getParameter("pageSize");
        // 处理_pageSize
        int pageSize = 12;
        if (_pageSize != null && _pageSize.length() > 0) {
            pageSize = Integer.parseInt(_pageSize);
        }
        // 2.调用service获得pageBean对象
        PageBean<Goods> page = GoodsService.searchByPage(str, currentPage, pageSize);
        System.out.println(page.getList());
        // 3.将结果序列化为json返回
        outputJson(request, response, page);
    }
}
