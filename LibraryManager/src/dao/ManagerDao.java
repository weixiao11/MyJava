package dao;
import entity.Manager;

/**
 * @author TH
 *对管理员进行操作的接口
 */
public interface ManagerDao {
	
	//登陆检测
	public Manager Login(int id, String pwd);
	//添加
	public boolean Insert(Manager m);
	//修改
	public boolean Update(int id, String pwd);
}
