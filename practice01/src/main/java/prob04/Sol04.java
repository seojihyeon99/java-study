package prob04;

import java.util.Scanner;

public class Sol04 {

	public static void main(String[] args) {
		
		System.out.print("문자열을 입력하세요:");
		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		int len = str.length();
		
		for(int i=0; i<=len; i++) {
			System.out.println(str.substring(0, i));
		}
		
		sc.close();
	}
}