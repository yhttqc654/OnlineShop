package edu.wdu.servlet;

import com.alibaba.fastjson.JSON;
import edu.wdu.dao.Order_infoDAO;
import edu.wdu.pojo.Result;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/orderList")
public class Order_infoListServlet_05 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ArrayList<HashMap<String, Object>> list = Order_infoDAO.order_infoList();
        ArrayList<HashMap<String, Object>> newList = new ArrayList<>();
        HashMap<String, Object> map=null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)!=null){
                 map = list.get(i);
                SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String buyTime_f = smdf.format(map.get("buyTime"));
                String payTime_f = smdf.format(map.get("payTime"));
                map.put("buyTime",buyTime_f);
                map.put("payTime",payTime_f);
//                map.put("orderPrice",(float)map.get("orderPrice")*(Integer)map.get("goodsNum"));
//                Order_infoDAO.updateOrder_info((Integer) map.get("id"),(float)map.get("orderPrice")*(Integer)map.get("goodsNum"));
                newList.add(map);
            }
        }
       //xxxxxxx
        Result result = new Result("success", newList);
        response.getWriter().append(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
