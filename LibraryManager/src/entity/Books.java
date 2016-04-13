package entity;

/**
 * @author TH
 *图书类
 */
public class Books {

	private int id;  //图书编号
	private String name; //图书名称
	private String introduce; //图书描述
	private String auther; //作者
	private double price; //价格
	public Books(int id, String name, String introduce, String auther,
			double price) {

		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.auther = auther;
		this.price = price;
	}	
	
	public Books() {

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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
