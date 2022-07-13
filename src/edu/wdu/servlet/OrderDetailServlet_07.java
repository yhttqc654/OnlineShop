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
import java.util.HashMap;

@WebServlet("/orderDetail")
public class OrderDetailServlet_07 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Result result = new Result();
        String orderIdStr = request.getParameter("orderId");
        Integer orderId = Integer.parseInt(orderIdStr);
        ArrayList<HashMap<String, Object>> list = Order_infoDAO.order_infoList();
        String buyTime_f = "";
        String payTime_f = "";
        for (HashMap<String, Object> map :
                list) {
            if (map != null) {
                Integer id = (Integer) map.get("id");
                if (id.equals(orderId)) {
                    HashMap<String, Object> map1 = Order_infoDAO.getById(id);
                        Integer goodsId = (Integer) map.get("id");
                        if (id.equals(goodsId)) {
                            SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            buyTime_f = smdf.format(map.get("buyTime"));
                            payTime_f = smdf.format(map.get("payTime"));
                        }

                    map1.put("buyTime", buyTime_f);
                    map1.put("payTime", payTime_f);
                    result.setFlag("success");
                    result.setData(map1);
                }
            } else {
                result.setFlag("fail");
                result.setData("获取详情失败!!!");
            }
        }


        response.getWriter().append(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
