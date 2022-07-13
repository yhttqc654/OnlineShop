package edu.wdu.helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MySqlHelper {
	private static String url ="jdbc:mysql://localhost:3306/shop?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useOldAliasMetadataBehavior=true";
	private static String username = "root";
	private static String password = "123";
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	static {
		// 1. 注册数据库的驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int executeUpdate(String sql,Object[] params) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			//3.循环注入参数值
			if(params != null) {
				for(int i=0 ; i<params.length ;i++) {
					pstmt.setObject(i+1, params[i].toString());
				}
			}
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return result;
	}

	public static ArrayList<HashMap<String, Object>> executeQuery(String sql,Object[] params) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			//3.循环注入参数值
			if(params != null) {
				for(int i=0 ; i<params.length ;i++) {
					pstmt.setObject(i+1, params[i].toString());
				}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNum = rsmd.getColumnCount();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < columnNum; i++) {
					String columnName = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(i + 1);
					map.put(columnName, value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void closeAll() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
