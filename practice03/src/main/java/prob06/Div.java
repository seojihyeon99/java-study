package prob06;

public class Div {
	private static int a;
	private static int b;
	
	public static void setValue(int a, int b) {
		Div.a = a;
		Div.b = b;
	}
	
	public static int calculate() {
		return a / b;
	}
}
