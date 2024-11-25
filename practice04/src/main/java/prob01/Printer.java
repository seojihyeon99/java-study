package prob01;

public class Printer {
	
//	public void println(int n) {
//		System.out.println(n);
//	}
//	
//	public void println(boolean b) {
//		System.out.println(b);
//	}
//	
//	public void println(double d) {
//		System.out.println(d);
//	}
//	
//	public void println(String s) {
//		System.out.println(s);
//	}
	
	public <T> void println(T t) { // 제네릭 메소드를 정의할 때, 반환 타입 앞에 제네릭 타입 매개변수(T, E, K, V 등)를 명시해야 한다.
		System.out.println(t);
	}

	public int sum(Integer... nums) { // 가변 인자를 사용하여 메소드가 전달받는 인자의 개수를 유동적으로 설정 가능 => 내부적으로 배열로 처리된다.
		int sum = 0;
		for(Integer num : nums) sum += num;
		return sum;
	}
	
	public <T> void println(T... ts) {
		for(T t : ts) {
			System.out.print(t + " ");
		}
		System.out.print("\n");
	}
}
