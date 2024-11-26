package collection;

import java.util.Enumeration;
import java.util.Vector;

public class VectorTest01 {

	public static void main(String[] args) {
		Vector<String> v = new Vector<>();

		v.addElement("둘리");
		v.addElement("마이콜");
		v.addElement("또치");

		// 순회1
		for (int i = 0; i < v.size(); i++) {
			String s = v.elementAt(i);
			System.out.println(s);
		}
		
		// 삭제
		v.removeElementAt(2);
		
		// 순회2
		Enumeration<String> e = v.elements(); // Enumeration은 Iterator와 비슷하다고 보면 된다.
		while(e.hasMoreElements()) {
			String s = e.nextElement();
			System.out.println(s);
		}
		
	}

}
