package dao;

import java.util.List;

import entity.Books;

/**
 * @author TH
 *对图书操作的接口
 */
public interface BooksDao {

    //查询分页查
	public List<Books> QueryAll(int pagesize, int pagenum);
	//添加
	public boolean Insert(Books b);
	//删除
	public boolean Delete(int id);
	//修改
	public boolean Update(Books b);
	//记录数
	public int Count();
	//id查询
	public Books SelectId(int id);
}
