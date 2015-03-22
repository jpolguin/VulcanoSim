package com.olguin.vulcano.math;

public class Triangle {

	private CartesianCoord _point1;
	private CartesianCoord _point2;
	private CartesianCoord _point3;

	public Triangle(CartesianCoord p1, CartesianCoord p2, CartesianCoord p3) {
		_point1 = p1;
		_point2 = p2;
		_point3 = p3;
	}

	public Triangle(PolarCoord p1, PolarCoord p2,
			PolarCoord p3) {
		this(p1.toCartesianCoord(), p2.toCartesianCoord(), p3.toCartesianCoord());
	}

	public boolean pointInside(CartesianCoord point) {
	
		return pointInTriangleUsingBarycentricMethod(_point1.x(), _point1.y(), _point2.x(), _point2.y(), _point3.x(), _point3.y(), point.x(),point.y());
	}

	private boolean pointInTriangleUsingBarycentricMethod(double x1, double y1, double x2, double y2,
			double x3, double y3, double x, double y) {
		
          double denominator = ((y2 - y3)*(x1 - x3) + (x3 - x2)*(y1 - y3));
          double a = ((y2 - y3)*(x - x3) + (x3 - x2)*(y - y3)) / denominator;
          double b = ((y3 - y1)*(x - x3) + (x1 - x3)*(y - y3)) / denominator;
          double c =  1 - a - b;
		  return 0 <= a && a <= 1 && 0 <= b && b <= 1 && 0 <= c && c <= 1;
	}

	public double perimeter() {
		
		return _point1.distance(_point2) + _point2.distance(_point3) + _point3.distance(_point1);
	}

}
