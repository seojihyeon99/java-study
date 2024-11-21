package prob06;

public class Sub {
	private static int a;
	private static int b;
	
	public static void setValue(int a, int b) {
		Sub.a = a;
		Sub.b = b;
	}
	
	public static int calculate() {
		return a - b;
	}
}
