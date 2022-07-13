package edu.wdu.dao;

import edu.wdu.helper.MySqlHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDAO {

    public static int addUser(String name,String email,String password) {
        String sql = "insert into user (name,email,password) values (?,?,?);";
        return MySqlHelper.executeUpdate(sql,new Object[] {name,email,password});
    }


    public static int updateUser(int id,String name,String email,String password) {
        String sql = " update user set name=?,email=? password=? where id=?; ";
        return MySqlHelper.executeUpdate(sql,new Object[] {name,email,password});
    }
    public static int deleteUser(int id) {
        String sql = "delete from user where id=?;";
        return MySqlHelper.executeUpdate(sql,new Object[] {id});
    }


    public static ArrayList<HashMap<String, Object>> userList() {
        String sql = "select * from user;";
        return MySqlHelper.executeQuery(sql,null);
    }

    public static HashMap<String, Object> getById(int id) {
        HashMap<String, Object> user = null;
        String sql = "select * from user where id=?;";
        ArrayList<HashMap<String, Object>> list = MySqlHelper.executeQuery(sql,new Object[] {id});
        if(list !=null && list.size()>0) {
            user = list.get(0);
        }
        return user;
    }





}
