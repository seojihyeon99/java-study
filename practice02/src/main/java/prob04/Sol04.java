package prob04;
public class Sol04 {

	public static void main(String[] args) {
		char[] c1 = reverse("Hello World");
		printCharArray(c1);
		
		char[] c2 = reverse("Java Programming!");
		printCharArray(c2);
	}
	
	public static char[] reverse(String str) {
		char[] result = new char[str.length()];
		int idx = 0;
		
		for(int i=str.length()-1; i>=0; i--) {
			result[idx++] = str.charAt(i);
		}
		
		return result;
	}

	public static void printCharArray(char[] array){
		System.out.println(array);
	}
}