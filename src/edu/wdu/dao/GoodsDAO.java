package edu.wdu.dao;

import edu.wdu.helper.MySqlHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsDAO {

    public static int addGoods(String name,String price,String imgUrl) {
        String sql = "insert into goods (name,price,imgUrl) values (?,?,?);";
        return MySqlHelper.executeUpdate(sql,new Object[] {name,price,imgUrl});
    }


    public static int updateUser(int id,String name,String price,String imgUrl) {
        String sql = " update goods set name=?,price=? imgUrl=? where id=?; ";
        return MySqlHelper.executeUpdate(sql,new Object[] {name,price,imgUrl});
    }


    public static int deleteUser(int id) {
        String sql = "delete from goods where id=?;";
        return MySqlHelper.executeUpdate(sql,new Object[] {id});
    }


    public static ArrayList<HashMap<String, Object>> goodsList() {
        String sql = "select * from goods;";
        return MySqlHelper.executeQuery(sql,null);
    }

    public static HashMap<String, Object> getById(int id) {
        HashMap<String, Object> goods = null;
        String sql = "select * from goods where id=?;";
        ArrayList<HashMap<String, Object>> list = MySqlHelper.executeQuery(sql,new Object[] {id});
        if(list !=null && list.size()>0) {
            goods = list.get(0);
        }
        return goods;
    }



}
