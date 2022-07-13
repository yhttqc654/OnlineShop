package edu.wdu.servlet;

import com.alibaba.fastjson.JSON;
import edu.wdu.dao.GoodsDAO;
import edu.wdu.dao.Order_infoDAO;
import edu.wdu.pojo.Result;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@WebServlet("/buy")
public class Order_infoServlet_04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String goodsId = request.getParameter("goodsId");
        String goodsNumStr = request.getParameter("goodsNum");
        Integer goodsNum = Integer.parseInt(goodsNumStr);
        String email = request.getParameter("email");
        Result result = new Result();


        ArrayList<HashMap<String, Object>> list = GoodsDAO.goodsList();
        for (HashMap<String, Object> map:
                list){
            if (map!=null){
                Integer id=(Integer) map.get("id");
                String idStr = String.valueOf(id);
                if (idStr.equals(goodsId)){
                    String name = (String) map.get("name");
                    float price = (float) map.get("price");
                    String imgUrl = (String) map.get("imgUrl");
                    Date date = new Date();
                    SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateStr = smdf.format(date);
                    Order_infoDAO.addOrder_info(imgUrl,name,goodsNum,price*goodsNum,dateStr,dateStr,0,email);
                }
                result.setFlag("success");
                result.setData("购买成功！！！");
            }else {
                result.setFlag("fail");
                result.setData("购买失败！！！");
            }
        }

/*
该方法可以实现显示正确的订单价格，但是有bug
        ArrayList<HashMap<String,Object>> list1=Order_infoDAO.order_infoList();
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i)!=null){
                Integer id=(Integer) list1.get(i).get("id");
                float oldPrice = (float) list1.get(i).get("orderPrice");
                float newPrice=oldPrice*(Integer) list1.get(i).get("goodsNum");
                Order_infoDAO.updateOrder_info(id,newPrice);

                result.setFlag("success");
                result.setData("购买成功！！！");

            }else {
                result.setFlag("fail");
                result.setData("购买失败！！！");
            }
        }*/



        response.getWriter().append(JSON.toJSONString(result));




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
