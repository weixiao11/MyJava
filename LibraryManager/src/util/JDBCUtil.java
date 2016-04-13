package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class JDBCUtil {

	public static final String DRIVE = "com.mysql.jdbc.Driver";   //加载驱动
	public static final String URL = "jdbc:mysql://localhost:3306/welcome?useUnicode=true&characterEncoding=UTF-8";//链接语句
	public static final String NAME = "root";
	public static final String PWD = "2307";
	Connection con = null; //链接对象
	PreparedStatement ps = null; //预编译语句对象
	ResultSet rs = null;  //结果集对象
	
	/**
	 * 建立数据链接
	 * 
	 * @return 链接对象
	 */
	public void GetConnect(){
		
		try {
			Class.forName(DRIVE);
			con = (Connection) DriverManager.getConnection(URL,NAME,PWD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭所有链接
	 */
	public void CloseAll(){
		
		try {

			if (rs != null) {

				rs.close();
			}

			if (ps != null) {

				ps.close();
			}

			if (con != null) {

				con.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * 对数据库信息进行增删改通用方法
	 * 
	 * @param sql
	 *            数据库操作语句
	 * @param obj
	 *            操作时需要用到的数据
	 * @return 返回受影响的行数
	 */
	public int Update(String sql,Object...obj){
		int result = 0;
		GetConnect();
		try {
			ps = con.prepareStatement(sql);
			if(obj!=null){
				
				for (int i = 0; i < obj.length; i++) {
					
					ps.setObject(i+1, obj[i]);
				}
			}
			result = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 对数据库信息进行查询通用方法
	 * 
	 * @param sql
	 *            数据库操作语句
	 * @param obj
	 *            操作时需要用到的数据
	 * @return 返回结果集
	 */
	public ResultSet Query(String sql,Object... obj){
		
	    GetConnect();
	    try {
			ps = con.prepareStatement(sql);
			if(obj !=  null){
				
				for (int i = 0; i < obj.length; i++) {
					
					ps.setObject(i+1, obj[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return rs;
	}
}
