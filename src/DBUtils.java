package 学生成绩管理系统;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	 static Connection con;
	 static public Connection getConnection() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            // TODO 自动生成的 catch 块
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