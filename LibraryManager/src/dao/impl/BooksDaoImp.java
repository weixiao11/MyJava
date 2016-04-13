package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

import dao.BooksDao;
import entity.Books;

/**
 * @author TH 对图书操作的接口实现
 */
public class BooksDaoImp implements BooksDao {

	JDBCUtil jdbc = new JDBCUtil();

	/**
	 * 分页查询书籍信息
	 */
	public List<Books> QueryAll(int pagesize, int pagenum) {
		// TODO Auto-generated method stub
		List<Books> list = null;
		Books b = null;
		ResultSet rs = null;
		rs = jdbc.Query("select * from books order by id limit ?,?", pagesize
				* (pagenum - 1), pagesize);
		try {
			list = new ArrayList<Books>();
			while (rs.next()) {

				b = new Books();
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setIntroduce(rs.getString("introduce"));
				b.setAuther(rs.getString("auther"));
				b.setPrice(rs.getDouble("price"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 添加图书
	 */
	public boolean Insert(Books b) {
		// TODO Auto-generated method stub
		int result = 0;
		result = jdbc
				.Update("insert into books(name, introduce, auther, price) values(?,?,?,?)",
						b.getName(), b.getIntroduce(), b.getAuther(),
						b.getPrice());
		if(result>0){
			
			return true;
		}
		return false;
	}

	/**
	 * 删除图书
	 */
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		result = jdbc
				.Update("delete from books where id=?",id);
		if(result>0){
			
			return true;
		}
		return false;
	}

	/**
	 * 修改图书信息
	 */
	public boolean Update(Books b) {
		// TODO Auto-generated method stub
		
		int result = 0;
		result = jdbc
				.Update("update books set name=?,introduce=?,auther=?,price=? where id=?",
						b.getName(), b.getIntroduce(), b.getAuther(),
						b.getPrice(), b.getId());
		if(result>0){
			
			return true;
		}
		return false;
	}

	/**
	 * 图书记录的总条数
	 */
	public int Count() {
		// TODO Auto-generated method stub
		int result = 0;
		ResultSet rs = null;
		rs = jdbc.Query("select count(*) from books");
		try {
			if(rs.next()){
				
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据id来查询
	 */
	public Books SelectId(int id) {
		// TODO Auto-generated method stub
		Books b = null;
		ResultSet rs = null;
		rs = jdbc.Query("select * from books where id=?", id);
		try {
			if (rs.next()) {

				b = new Books();
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setIntroduce(rs.getString("introduce"));
				b.setAuther(rs.getString("auther"));
				b.setPrice(rs.getDouble("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}
