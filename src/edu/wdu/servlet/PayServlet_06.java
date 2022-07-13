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
import java.util.Date;
import java.util.HashMap;

@WebServlet("/pay")
public class PayServlet_06 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Result result = new Result();
        String orderIdStr = request.getParameter("orderId");
        int orderId = Integer.parseInt(orderIdStr);
        String email = request.getParameter("email");
        ArrayList<HashMap<String, Object>> list = Order_infoDAO.order_infoList();
        if (list!=null){
            for (HashMap<String, Object> map:
            list){
                if (map!=null){
                    Integer id = (Integer) map.get("id");
                    if (id.equals(orderId)){
                        Date date = new Date();
                        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateStr = smdf.format(date);
                        Order_infoDAO.updateOrder_info_Partial(id,dateStr,1);

//                        float oldPrice = (float) map.get("orderPrice");
//                        Integer goodsNum = (Integer) map.get("goodsNum");
//                        float newPrice=oldPrice*goodsNum;
//                        Order_infoDAO.updateOrder_info((Integer)map.get("id"),newPrice);

                        result.setFlag("success");
                        result.setData("付款成功!!!");
                    }
                }
            }
        }
        else {
            result.setFlag("fail");
            result.setData("付款失败!!!");
        }

        response.getWriter().append(JSON.toJSONString(result));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
