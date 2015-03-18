package com.olguin.vulcanosim.model;

public class CartesianCoord {

	private double _x;
	private double _y;

	public CartesianCoord(double x, double y) {
		_x = x;
		_y = y;
	}

	public double x(){
		return _x;
	}
	
	public double y() {
		return _y;
	}
	
	@Override
	public boolean equals(Object anotherPoint) {
		if (anotherPoint instanceof CartesianCoord) {
			CartesianCoord point = (CartesianCoord)anotherPoint;
			return (Math.abs(this.x()-point.x())<= Planet.PRECISION_DELTA) && (Math.abs(this.y()-point.y())<= Planet.PRECISION_DELTA);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return (int) x() + (int) y();
		
	}
	
	
	@Override
	public String toString() {
		return "(" + x()+", "+ y() + ")";
	}
}
