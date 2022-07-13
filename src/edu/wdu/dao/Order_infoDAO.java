package edu.wdu.dao;

import edu.wdu.helper.MySqlHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Order_infoDAO {

    public static int addOrder_info(String imgUrl, String goodsName, int goodsNum, float orderPrice, String buyTime, String payTime, int status, String email) {
        String sql = "insert into order_info (imgUrl,goodsName,goodsNum,orderPrice,buyTime,payTime,status,email) values (?,?,?,?,?,?,?,?);";
        return MySqlHelper.executeUpdate(sql,new Object[] {imgUrl,goodsName,goodsNum,orderPrice,buyTime,payTime,status,email});
    }//ok


    public static int updateOrder_info(Integer id,float orderPrice) {
        String sql = " update order_info set orderPrice=? where id=?; ";
        return MySqlHelper.executeUpdate(sql,new Object[] {orderPrice,id});
    }
    //XXX

    public static int updateOrder_info_Partial(Integer id,String payTime,int status) {
        String sql = " update order_info set payTime=?,status=? where id=?; ";
        return MySqlHelper.executeUpdate(sql,new Object[] {payTime,status,id});
    }


    public static int deleteOrder_info(int id) {
        String sql = "delete from order_info where id=?;";
        return MySqlHelper.executeUpdate(sql,new Object[] {id});
    }
    //ok


    public static ArrayList<HashMap<String, Object>> order_infoList() {
        String sql = "select * from order_info;";
        return MySqlHelper.executeQuery(sql,null);
    }
    //ok


    public static HashMap<String, Object> getById(int id) {
        HashMap<String, Object> order_info = null;
        String sql = "select goodsName, goodsNum,orderPrice,buyTime,payTime,status from order_info where id=?;";
        ArrayList<HashMap<String, Object>> list = MySqlHelper.executeQuery(sql,new Object[] {id});
        if(list !=null && list.size()>0) {
            order_info = list.get(0);
        }
        return order_info;
    }
    //ok



}
