package chapter04;

import java.util.Arrays;

public class StringTest03 {

	public static void main(String[] args) {
//		String s1 = "Hello " + "World " + "Java" + 21;

//		String s1 = new StringBuilder("Hello ")
//			.append("World ")
//			.append("Java")
//			.append(21)
//			.toString();

		String s1 = new StringBuffer("Hello ")
				.append("World ")
				.append("Java")
				.append(21)
				.toString();

		System.out.println(s1);

//		String s2 = "";
//		for (int i = 0; i < 1000000; i++) { // new 연산 및 해제는 CPU 많이 쓰게됨 
//			s2 = s2 + "h";
//			s2 = new StringBuffer(s2).append("h").toString();
//		}

		// 문자열 더하기 연산 많은 경우, 명시적으로 StringBuffer/StringBuilder 쓰는게 좋다.
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 1000000; i++) {
			sb.append("h");
		}
		String s2 = sb.toString();
		
		// String methods들...
		String s4 = "abcABCabcAbc";
		System.out.println(s4.length()); // 12
		System.out.println(s4.charAt(2)); // c
		System.out.println(s4.indexOf("abc")); // 0
		System.out.println(s4.indexOf("abc", 6)); // 6
		System.out.println(s4.substring(3)); // ABCabcAbc
		System.out.println(s4.substring(3, 5)); // AB
		
		String s5 = "    ab    cd  ";
		String s6 = "abc,def,ghi";
		
		String s7 = s5.concat(s6);
		System.out.println(s7);
		
		System.out.println("---" + s5.trim() + "---"); // ---ab    cd---
		System.out.println("---" + s5.replaceAll(" ", "") + "---"); // ---ab    cd---
		
		String[] tokens = s6.split(",");
		System.out.println(Arrays.toString(tokens));
		
		String[] tokens2 = s6.split(" ");
		System.out.println(Arrays.toString(tokens2));
	}

}
