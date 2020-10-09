package web.servlet;

import entity.Cart;
import entity.Goods;
import entity.PageBean;
import entity.User;
import org.apache.commons.beanutils.BeanUtils;
import service.CartService;
import service.GoodsService;
import service.impl.CartServiceImpl;
import service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {

    //查找购物车中的所有商品
    public void findAll(HttpServletRequest request, HttpServletResponse response){
        //创建cart对象集合
        List<Cart> cartList = new ArrayList<>();
        //1.判断其是否登录
        User user = (User) request.getSession().getAttribute("loginUser");

        if (user == null){
            //1.1未登录状态从cookie中获取购物车商品id与数量
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                //用正则表达式查询gid列表 gid\d+
                String regx = "gid\\d+";
                if (cookie.getName().matches(regx)){
                    String _gid = cookie.getName();
                    String _number = cookie.getValue();
                    //获取gid 以及对应数目 创建cart对象加入cartList中
                    int gid = Integer.parseInt(_gid.substring(3));
                    int number = Integer.parseInt(_number);

                    GoodsService gs = new GoodsServiceImpl();
                    Goods goods = gs.findOne(gid);
                    //封装cart对象
                    Cart cart = new Cart();
                    cart.setGid(gid);
                    cart.setNumber(number);
                    cart.setGoods(goods);
                    cartList.add(cart);
                }
            }
//            System.out.println(cartList + "test");

            outputJson(request,response,cartList);
        }else {
            //1.2登录状态下从数据库中查询购物车中的商品信息
            int uid = user.getUid();
//            System.out.println(user.getUid());
            CartService cs = new CartServiceImpl();
            cartList = cs.findByUid(uid);
//            System.out.println(cartList);
            outputJson(request, response, cartList);
        }


    }
    //清空购物车
    public void clearCart(HttpServletRequest request, HttpServletResponse response){
        //判断其是否登录
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                //用正则表达式查询gid列表 gid\d+
                String regx = "gid\\d+";
                if (cookie.getName().matches(regx)){
//                    System.out.println(cookie.getName() + ":" + cookie.getValue());
                    //如果是购物车商品信息的cookie则将生命周期归零后重新设置进cookie
                    cookie.setPath("/shop");//设置携带路径
                    cookie.setMaxAge(0);//生命周期归零
                    response.addCookie(cookie);//覆盖原cookie
                }
            }
        }else {
            int uid = user.getUid();
            CartService cs = new CartServiceImpl();
            cs.deleteCartByUid(uid);
        }
    }
    //添加一种商品
    public void addOne(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //判断其是否登录
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null){
            //获取参数
            Map<String, String[]> map = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart, map);

            //1.1未登录状态从cookie中获取购物车商品id与数量
            Cookie[] cookies = request.getCookies();
            Boolean flag = false;
            for (Cookie cookie : cookies){
                //查找是否已经有对应的商品
                if (cookie.getName().equals("gid" + cart.getGid())){
                    //如果有则将对应商品cookie的数字加一
                    String _number = cookie.getValue();
                    int number = Integer.parseInt(_number) + 1;
                    cookie.setValue("" + number);
                    cookie.setPath("/shop");
                    cookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);
                    flag = true;//表明cookie存在
                }
            }
            if (!flag){
                //商品不存在 则添加cookie
                Cookie cookie = new Cookie("gid" + cart.getGid(), "1");
                cookie.setPath("/shop");
                cookie.setMaxAge(60 * 60 * 24);//设置生命周期为一天
                response.addCookie(cookie);
            }
        }else{
            int uid = user.getUid();
            //封装cart对象
            Map<String, String[]> map = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart, map);
            cart.setUid(uid);
            cart.setUser(user);
//            System.out.println(cart);
            CartService cs = new CartServiceImpl();
            //查询cart 如果cart已经存在则number加一 否则在cart表中新增一条记录
            Cart _cart = cs.findCart(cart);
            if (_cart == null){
                cart.setNumber(1);
                cs.insertCart(cart);
            }else {
                cs.addNum(_cart);
            }
        }
    }

    //移除一种商品
    public void removeOne(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //判断其是否登录
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null){
            //封装cart对象
            Map<String, String[]> map = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart, map);
            System.out.println(cart.getGid());

            //因为是从前端传来的gid所以此cookie必定存在,直接覆盖即可,就算没有也没影响
            Cookie cookie = new Cookie("gid" + cart.getGid(), null);
            cookie.setPath("/shop");
            cookie.setMaxAge(0);
            response.addCookie(cookie);

        }else {
            int uid = user.getUid();
            //封装cart对象
            Map<String, String[]> map = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart, map);
            cart.setUid(uid);
            cart.setUser(user);
//            System.out.println(cart);

            //删除购物车记录
            CartService cs = new CartServiceImpl();
            cs.deleteCart(cart);
        }

    }
    //增加商品数量
    public void addNum(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //判断其是否登录
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null){
            //封装cart对象
            Map<String, String[]> map = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart, map);

            //1.1未登录状态从cookie中获取购物车商品id与数量
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("gid" + cart.getGid())){
                    //将对应商品cookie的数量加一
                    String _number = cookie.getValue();
                    int number = Integer.parseInt(_number) + 1;
                    cookie.setValue("" + number);
                    cookie.setPath("/shop");
                    cookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);
                }
            }

        }else {
            int uid = user.getUid();
            //封装cart对象
            Map<String, String[]> map = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart, map);
            cart.setUid(uid);
            cart.setUser(user);
//            System.out.println(cart);

            //增加商品数量
            CartService cs = new CartServiceImpl();
            cs.addNum(cart);
        }
    }
    //减少商品数量
    public void reduceNum(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //判断其是否登录
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null){
            //封装cart对象
            Map<String, String[]> map = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart, map);

            //1.1未登录状态从cookie中获取购物车商品id与数量
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("gid" + cart.getGid())){
                    //将对应商品cookie的数量减一
                    String _number = cookie.getValue();
                    int number = Integer.parseInt(_number);
                    if (number > 1){
                        number = number - 1;
                        cookie.setPath("/shop");
                        cookie.setValue("" + number);
                        cookie.setMaxAge(60 * 60 * 24);
                        response.addCookie(cookie);
                    }else {
                        //如果商品数量为1则删除此cookie
                        cookie.setPath("/shop");
                        cookie.setMaxAge(0);
                        cookie.setMaxAge(60 * 60 * 24);
                        response.addCookie(cookie);
                    }
                }
            }
        }else {
            int uid = user.getUid();
            //封装cart对象
            Map<String, String[]> map = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart, map);
            cart.setUid(uid);
            cart.setUser(user);
//            System.out.println(cart);

            //减少商品数量
            CartService cs = new CartServiceImpl();
            cs.reduceNum(cart);
        }
    }
    //分页
    public void findByPage(HttpServletRequest request, HttpServletResponse response){
        //获取参数
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null){
            //获取参数
            String _currentPage = request.getParameter("currentPage");
            String _pageSize = request.getParameter("pageSize");
            if (_currentPage == null || "".equals(_currentPage)){
                _currentPage = "1";
            }
            if (_pageSize == null || "".equals(_pageSize)){
                _pageSize = "3";
            }
            int currentPage = Integer.parseInt(_currentPage);
            int pageSize = Integer.parseInt(_pageSize);

            List<Cart> cartList  = new ArrayList<>();
            //1.1未登录状态从cookie中获取购物车商品id与数量
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                System.out.println(cookie.getName() + " : " + cookie.getValue());
                //用正则表达式查询gid列表 gid\d+
                String regx = "gid\\d+";
                if (cookie.getName().matches(regx)){
                    String _gid = cookie.getName();
                    String _number = cookie.getValue();
                    //获取gid 以及对应数目 创建cart对象加入cartList中
                    int gid = Integer.parseInt(_gid.substring(3));
                    int number = Integer.parseInt(_number);

                    GoodsService gs = new GoodsServiceImpl();
                    Goods goods = gs.findOne(gid);
                    //封装cart对象
                    Cart cart = new Cart();
                    cart.setGid(gid);
                    cart.setNumber(number);
                    cart.setGoods(goods);
                    cartList.add(cart);
                }
            }

            //使用sort方法将cart排序,以固定顺序
            cartList.sort(new Comparator<Cart>() {
                @Override
                public int compare(Cart o1, Cart o2) {
                    return o1.getGid() - o2.getGid();
                }
            });

            //总条数
            int totalCount = cartList.size();
            //计算总页数
            int totalPage = (totalCount + pageSize - 1) / pageSize;
            //将当前页的cart对象添加到pageBean.list中(分页)
            List<Cart> list = new ArrayList<>();
            int start = (currentPage - 1) * pageSize;
            int end = start + pageSize;
            if (totalCount - start <= pageSize){
                //如果当前页的剩余条数小于pageSize 则end为实际条数
                end = totalCount;
            }
            for (int i = start; i < end; i++){
                list.add(cartList.get(i));
            }

            //封装pageBean
            PageBean<Cart> pageBean = new PageBean<>();
            pageBean.setCurrentPage(currentPage);
            pageBean.setPageSize(pageSize);
            pageBean.setTotalCount(totalCount);
            pageBean.setTotalPage(totalPage);
            pageBean.setList(list);
            outputJson(request,response,pageBean);
        } else {
            int uid = user.getUid();
            String currentPage = request.getParameter("currentPage");
            String pageSize = request.getParameter("pageSize");

            if (currentPage == null || "".equals(currentPage)){
                currentPage = "1";
            }
            if (pageSize == null || "".equals(pageSize)){
                pageSize = "3";
            }

            //查询当前页相应商品列表
            CartService cs = new CartServiceImpl();
            PageBean<Cart> pageBean = cs.findByPage(uid, Integer.parseInt(currentPage), Integer.parseInt(pageSize));
//            System.out.println(pageBean.getList());

            //返回json对象
            outputJson(request,response,pageBean);
        }
    }

    //将cookie中的购物车商品数据加入数据库 并且清除cookie
    public void addCookies(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("loginUser");
        Cookie[] cookies = request.getCookies();
        int uid = user.getUid();

        for (Cookie cookie : cookies){
            //用正则表达式查询gid列表 gid\d+
            String regx = "gid\\d+";
            if (cookie.getName().matches(regx)){
                String _gid = cookie.getName();
                String _number = cookie.getValue();
                //获取gid 以及对应数目 创建cart对象加入cartList中
                int gid = Integer.parseInt(_gid.substring(3));
                int number = Integer.parseInt(_number);
                //封装cart对象
                Cart cart = new Cart();
                cart.setUid(uid);
                cart.setUser(user);
                cart.setGid(gid);
                cart.setNumber(number);
//            System.out.println(cart);
                //增加商品数量
                CartService cs = new CartServiceImpl();
                //查询cart 如果cart已经存在则number加上cookie中的number 否则在cart表中新增一条记录
                Cart _cart = cs.findCart(cart);
                if (_cart == null){
                    cs.insertCart(cart);
                }else {
                    //否则加上cookie中的商品数
                    cs.addCookie(_cart, number);
                }

                //清除cookie
                cookie.setPath("/shop");//设置携带路径
                cookie.setMaxAge(0);//生命周期归零
                response.addCookie(cookie);//覆盖原cookie
            }
        }
    }
}
