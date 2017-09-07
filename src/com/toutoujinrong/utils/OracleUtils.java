package com.toutoujinrong.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleUtils {

	public static void main(String[] args) {
		String sqlStr = "select * from nonstandard.bas_customer a where a.customerid = 10002";
		String valueStr = OracleUtils.select(sqlStr, "IDCARD");
		System.out.println(valueStr);
	}
  	 
	public static String select(String sql_query, String column) {
		ResultSet rs = null;  
		PreparedStatement pst = null;  
		Connection conn = null;
		String value = null;
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//kd_sale_dx/zxcvfr@192.168.2.51:1521/ORCL
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.20.147:1521:orcl", "dipbps", "deppon##_dipbpstest147");  
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.56:1521:ORCL", "kd_sale_dx", "zxcvfr");  
			pst = conn.prepareStatement(sql_query);
			//pst.setString(1, "078846");
			rs = pst.executeQuery();  
			while(rs.next()) {  
				value = rs.getString(column);
			}  
		} catch (ClassNotFoundException e) {  
			e.printStackTrace();  
		} catch (SQLException e) {  
			e.printStackTrace();  
		} finally {  
			try {  
				if(rs != null) {  
					rs.close();  
				}  
				if(pst != null) {  
					pst.close();  
				}  
				if(conn != null) {  
					conn.close();  
				}  
			} catch (SQLException e) { 
				e.printStackTrace();  
			}  
		}  
		return value;
	} 
}
