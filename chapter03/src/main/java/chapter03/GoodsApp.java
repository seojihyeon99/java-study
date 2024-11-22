package chapter03;

public class GoodsApp {
	
	public static void main(String[] args) {
		Goods goods = new Goods();
		
//		goods.name = "nikon";
//		goods.price = 400000;
//		goods.countStock = 10;
//		goods.countSold = 20;
		goods.setName("nikon");
		goods.setPrice(400000);
		goods.setCountStock(10);
		goods.setCountSold(20);
		
//		System.out.println("상품이름 : " + goods.name + 
//				", 상품가격 : " + goods.price + 
//				", 상품재고 : " + goods.countStock +
//				", 상품판매량 : " + goods.countSold);
		
//		System.out.println("상품이름 : " + goods.getName() + 
//		", 상품가격 : " + goods.getPrice() + 
//		", 상품재고 : " + goods.getCountStock() +
//		", 상품판매량 : " + goods.getCountSold());		
		
		goods.printInfo();
		
		// 정보은닉 (객체의 상태를 보호)
		goods.setPrice(-1000);
		
		Goods goods2 = new Goods();
		Goods goods3 = new Goods();
		// static 변수 (클래스 변수)
		System.out.println(Goods.countOfGoods);
		
		goods.setPrice(400000);
		System.out.println(goods.calcDiscountPrice(0.5f));
		
	}
	
}
