package prob03;

import java.util.Scanner;

public class Sol03 {
	
	public static void main(String[] args) {

		while(true) {
			System.out.print("수를 입력 하세요:");
			Scanner sc = new Scanner(System.in);
			
			String input = sc.next();
			if(input.equals("_")) break;
			
			int num = Integer.valueOf(input);
			
			int sum = 0;
			
			for(int i=num; i>=0; i=i-2) {
				sum += i;
			}

			System.out.println(sum);
			
			sc.close();
		}
	}
}
