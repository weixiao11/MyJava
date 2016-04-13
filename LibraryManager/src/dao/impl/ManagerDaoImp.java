package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCUtil;
import dao.ManagerDao;
import entity.Manager;

/**
 * @author TH 对管理员进行操作的接口实现
 */
public class ManagerDaoImp implements ManagerDao {

	JDBCUtil jdbc = new JDBCUtil();

	/**
	 * 添加管理员
	 */
	public boolean Insert(Manager m) {
		// TODO Auto-generated method stub
		int result = 0;
		result = jdbc.Update(
				"insert into manager(id,name, pwd, sex) values(?,?,?,?)",
				m.getId(), m.getName(), m.getPwd(), m.getSex());
		if (result > 0) {

			return true;
		}
		return false;
	}

	/**
	 * 修改个人信息
	 */
	public boolean Update(int id, String pwd) {
		// TODO Auto-generated method stub
		int result = 0;
		result = jdbc.Update("update manager set pwd=? where id=?",pwd,
				id);
		if (result > 0) {

			return true;
		}
		return false;
	}

	/**
	 * 登陆检测
	 */
	public Manager Login(int id, String pwd) {
		// TODO Auto-generated method stub
		Manager m = null;
		ResultSet rs = null;
		rs = jdbc.Query("select * from manager where id=? and pwd=?", id, pwd);
		try {
			if (rs.next()) {

				m = new Manager();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPwd(rs.getString("pwd"));
				m.setSex(rs.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
}
