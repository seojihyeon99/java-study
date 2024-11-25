package chapter04;

public class ObjectTest03 {

	public static void main(String[] args) {
		String s1 = new String("hello"); // 0x100
		String s2 = new String("hello"); // 0x200
		
		System.out.println(s1 == s2); // fasle
		System.out.println(s1.equals(s2)); // true
		System.out.println(s1.hashCode() + ":" + s2.hashCode()); // 99162322:99162322
		System.out.println(System.identityHashCode(s1) + ":" + System.identityHashCode(s2)); // 804564176:1421795058
		
		System.out.println("======================");
		
		// 동일한 문자열 리터럴은 상수 풀에서 하나의 객체로 관리된다.
		String s3 = "hello";
		String s4 = "hello";

		System.out.println(s3 == s4); // true
		System.out.println(s3.equals(s4)); // true
		System.out.println(s3.hashCode() + ":" + s4.hashCode()); // 99162322:99162322
		// String은 hashCode를 자동으로 오버라이딩하므로, 부모의 hashCode를 확인해서 같은지/다른지
		System.out.println(System.identityHashCode(s3) + ":" + System.identityHashCode(s4)); // 1555009629:1555009629

	}

}
