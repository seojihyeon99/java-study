package prob02;

public class Goods {
	private String name;
	private int price;
	private int stock;
	
	public Goods(String name, int price, int stock) {
		super();
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public void printInfo() {
		System.out.println(name + "(가격:" + price + "원)이 " + stock + "개 입고 되었습니다.");
	}
}