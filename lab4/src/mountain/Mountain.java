package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.*;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;

	/**
	 * Creates an object that handles the Mountain fractal
	 * 
	 * @param a, b, c starting points for order 0 triangle
	 */

	public Mountain(Point a, Point b, Point c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public String getTitle() {
		return "Mountain fractal";
	}

	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, order, a, b, c);
	}

	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
			turtle.penUp();
		} else {
			Point midAB = midPoint(a, b);
			Point midBC = midPoint(b, c);
			Point midAC = midPoint(a, c);

			fractalTriangle(turtle, order - 1, a, midAB, midAC);
			fractalTriangle(turtle, order - 1, b, midAB, midBC);
			fractalTriangle(turtle, order - 1, c, midBC, midAC);
			fractalTriangle(turtle, order - 1, midAB, midBC, midAC);
		}
	}

	private Point midPoint(Point a, Point b) {

		int midX = (a.getX() + b.getX()) / 2;
		int midY = (a.getY() + b.getY()) / 2;
		return new Point(midX, midY);
	}
}
