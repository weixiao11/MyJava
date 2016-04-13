package entity;


/**
 * @author TH
 *管理员类
 */
public class Manager {

	private int id;  //管理员id
	private String name;  //管理员姓名
	private String pwd;   //管理员密码
	private String sex;   //管理员性别
	public Manager(int id, String name, String pwd, String sex) {
		
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
	}
	public Manager() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
