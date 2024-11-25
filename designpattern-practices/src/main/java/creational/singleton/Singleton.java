package creational.singleton;

public class Singleton {
	private static Singleton instance = null;
	
	private Singleton() { // new 불가능
	}

	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		
		return instance;
	}
	
}
