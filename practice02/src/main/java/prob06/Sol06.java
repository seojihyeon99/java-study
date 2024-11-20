package prob06;

import java.util.Random;
import java.util.Scanner;

public class Sol06 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int min = 1;
		int max = 100;
		
		while(true) {
			// 정답 램덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt(100) + 1;
			System.out.println(correctNumber);
			
			System.out.println("수를 결정하였습니다. 맞추어 보세요:");

			for(int i=1; ; i++) {
				System.out.println(min + "-" + max);
				System.out.print(i + ">>");
				int num = scanner.nextInt();
				
				if(num == correctNumber) {
					System.out.println("맞췄습니다.");
					break;
				}
				else if(num > correctNumber) {
					System.out.println("더 낮게");
					max = num;
				}
				else {
					System.out.println("더 높게");
					min = num;
				}
			}
			
			//새 게임 여부 확인하기
			System.out.print("다시 하겠습니까(y/n)>>");
			String answer = scanner.next();
			if("y".equals(answer) == false) {
				break;
			}
		}
		
		scanner.close();
	}
}