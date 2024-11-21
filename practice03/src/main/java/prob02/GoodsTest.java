package prob02;

import java.util.Scanner;

public class GoodsTest {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력
		for(int i=0; i<goods.length; i++) {
			String line = scanner.nextLine();
			String[] info = line.split(" ");
			goods[i] = new Goods(info[0], Integer.valueOf(info[1]), Integer.valueOf(info[2]));			
		}
		
		System.out.println();
		
		// 상품 출력
		for(int i=0; i<goods.length; i++) {
			goods[i].printInfo();
		}
		
		scanner.close();
	}
}