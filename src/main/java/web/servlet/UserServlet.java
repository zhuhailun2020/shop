package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ResultInfo;
import entity.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    UserService us =new UserServiceImpl();

    //查询用户登录状态
    public void findUser(HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
        User user=(User)request.getSession().getAttribute("loginUser");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), user);
    }
    //删除session
    public void logout(HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
        request.getSession().removeAttribute("loginUser");
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    public void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        UserService us = new UserServiceImpl();
        boolean flag = us.checkUser(uname);
        ResultInfo info = new ResultInfo();
        info.setFlag(flag);
        ObjectMapper mapper = new ObjectMapper();
       response.setCharacterEncoding("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }

    public void checkEmail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        UserService us = new UserServiceImpl();
        boolean flag = us.checkEmail(email);
        ResultInfo info = new ResultInfo();
        info.setFlag(flag);
        ObjectMapper mapper = new ObjectMapper();
        response.setCharacterEncoding("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }


    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        UserService us=new UserServiceImpl();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = us.registUser(user);
        if(flag){
          response.sendRedirect("/shop/ok.html");
        }else {
            response.getWriter().write("注册失败");
        }
        ResultInfo info=new ResultInfo();
        info.setFlag(flag);
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserService us = new UserServiceImpl();
        ResultInfo info = new ResultInfo();
        try {
            User user = us.login(email, password);
            System.out.println(user);
            request.getSession().setAttribute("loginUser", user);
            info.setFlag(true);
        } catch (Exception e) {
            info.setFlag(false);
        }

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        UserService us = new UserServiceImpl();
        boolean flag = us.active(code);
            if(flag){
              response.sendRedirect(request.getContextPath() + "/login.html");
              }else{
               response.setContentType("html/text;charset=utf-8");
               response.getWriter().write("激活失败");
             }
       }
}
