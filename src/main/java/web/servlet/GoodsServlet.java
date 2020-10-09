package web.servlet;

import entity.PageBean;
import entity.Goods;
import service.GoodsService;
import service.impl.GoodsServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/Goods/*")
public class GoodsServlet extends BaseServlet {

        private GoodsService GoodsService = new GoodsServiceImpl();

        public void queryPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
            // 1.接收参数
            String _lid = request.getParameter("lid");
            int lid = Integer.parseInt(_lid);
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
            PageBean<Goods> page = GoodsService.findByPage(lid, currentPage, pageSize);
            // 3.将结果序列化为json返回
            outputJson(request, response, page);
        }
    public void queryPageBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.接收参数
        String _bid = request.getParameter("bid");
        int bid = Integer.parseInt(_bid);
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
        PageBean<Goods> page = GoodsService.findByPageBrand(bid, currentPage, pageSize);
        // 3.将结果序列化为json返回
        outputJson(request, response, page);
    }
    public void single(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.接收参数gid
        int gid = Integer.parseInt(request.getParameter("gid"));
        // 2.调用service获得对象
        Goods goods = GoodsService.findOne(gid);
        // 3.序列化返回
        outputJson(request,response, goods);
    }
    }

