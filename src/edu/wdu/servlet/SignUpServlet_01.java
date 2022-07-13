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

@WebServlet("/signUp")
public class SignUpServlet_01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        //1.设置页面编码
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirmPassword");
        //获取网页参数
        Result result = new Result();
        //实例化结果集
        ArrayList<HashMap<String, Object>> list = UserDAO.userList();
        //查询用户列表
        boolean flag = true;
        //设置一个标记用来判断邮箱是否输入正确，正确则为true，错误则为false
        for (HashMap<String, Object> map : list) {
            if (map.get("email").equals(email)) {
                //判断输入的邮箱和数据库中的邮箱是否一致
                flag = false;
            }
        }
        if (password.equals(confirm_password) && flag == true) {

            UserDAO.addUser(name, email, password);
            result.setFlag("success");
        } else {
            if (flag == false) {
                result.setFlag("fail");
                result.setData("邮箱已注册!!!");
            }
            if (!password.equals(confirm_password)) {
                result.setFlag("fail");
                result.setData("两次密码输入不一致!!!");
            }

        }
        response.getWriter().append(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
