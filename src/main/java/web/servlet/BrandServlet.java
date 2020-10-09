package web.servlet;

import entity.Brand;
import entity.Label;
import service.BrandService;
import service.LabelService;
import service.impl.BrandServiceImpl;
import service.impl.LabelServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService= new BrandServiceImpl();

    public void findAllBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Brand> list = brandService.findAll();
        outputJson(request, response, list);
    }
}