package structural.decorator;

public class Client {

	public static void main(String[] args) {
		System.out.println(new ConcreteComponent("Hello World").operation());
		System.out.println(new ParenthesesDecorator(new ConcreteComponent("Hello World")).operation());

		System.out.println(new BraceDecorator(new ConcreteComponent("Hello World")).operation());
		
//		System.out.println(new BraceDecorator(new ParenthesesDecorator(new ConcreteComponent("Hello World"))).operation());

		Component c1 = new ConcreteComponent("Hello World"); // 주 스트림
		Component c2 = new ParenthesesDecorator(c1); // 보조 스트림
		Component c3 = new BraceDecorator(c2); // 보조 스트림
		System.out.println(c3.operation());

	}

}
