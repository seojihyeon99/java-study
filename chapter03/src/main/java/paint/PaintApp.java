package paint;

public class PaintApp {

	public static void main(String[] args) {
		Point point = new Point();
		point.setX(10);
		point.setY(20);
		point.show();
		
		Point point2 = new Point(100, 200);
		point2.show(true);
		point2.show(false);
	}

}
