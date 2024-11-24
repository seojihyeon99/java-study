package prob05;

public class Rectangle extends Shape implements Resizable {

	public Rectangle(int width, int height) {
		super(width, height);
	}
	
	@Override
	public void resize(double rate) {
		super.width = width*rate;
		super.height = height*rate;		
	}

	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public double getPerimeter() {
		return 2 * (width + height);
	}

}
