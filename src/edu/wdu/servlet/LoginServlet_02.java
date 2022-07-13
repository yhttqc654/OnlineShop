package edu.wdu.servlet;

import com.alibaba.fastjson.JSON;
import edu.wdu.dao.UserDAO;
import edu.wdu.pojo.Result;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
@WebServlet("/login")
public class LoginServlet_02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Result result = new Result();
        ArrayList<HashMap<String, Object>> list = UserDAO.userList();
        for (HashMap<String, Object> map:
        list){
            if (map!=null){
                String email1 = (String) map.get("email");
                String password1 = (String) map.get("password");
                if (email.equals(email1)&&password1.equals(password)){
                    result.setFlag("success");
                    result.setData("登录成功!!!");
                }else {
                    if (!email.equals(email1)){
                        result.setFlag("fail");
                        result.setData("邮箱未被注册!!!");
                    }
                    if (!password.equals(password1)){
                        result.setFlag("fail");
                        result.setData("密码错误!!!");
                    }
                }
            }
        }
        response.getWriter().append(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
