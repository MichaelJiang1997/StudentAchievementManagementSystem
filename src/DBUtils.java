package ѧ���ɼ�����ϵͳ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	 static Connection con;
	 static public Connection getConnection() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            // TODO �Զ����ɵ� catch ��
	            e.printStackTrace();
	        }
	        try {
	        	con = DriverManager.getConnection("jdbc:mysql://sencom.top:3306/xscjglxt?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=true", "anyone", "123456");
	        }catch(SQLException e) {
	            e.printStackTrace();
	        }
	        return con;
	    }
}