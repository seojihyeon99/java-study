package prob06;

public class Add {
	private static int a;
	private static int b;
	
	public static void setValue(int a, int b) {
		Add.a = a;
		Add.b = b;
	}
	
	public static int calculate() {
		return a + b;
	}
}
