package edu.wdu.servlet;

import com.alibaba.fastjson.JSON;
import edu.wdu.dao.GoodsDAO;
import edu.wdu.pojo.Result;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/goodsList")
public class GoodsListServlet_03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<HashMap<String, Object>> list = GoodsDAO.goodsList();
        Result result = new Result("success",list);
        response.getWriter().append(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
