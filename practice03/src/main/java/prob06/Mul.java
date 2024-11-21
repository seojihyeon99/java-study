package prob06;

public class Mul {
	private static int a;
	private static int b;
	
	public static void setValue(int a, int b) {
		Mul.a = a;
		Mul.b = b;
	}
	
	public static int calculate() {
		return a * b;
	}
}
