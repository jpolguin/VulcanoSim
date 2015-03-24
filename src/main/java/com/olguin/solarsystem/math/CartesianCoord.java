package com.olguin.solarsystem.math;

import com.olguin.solarsystem.model.ISolarSystem;

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
			return (Math.abs(this.x()-point.x())<= ISolarSystem.PRECISION_DELTA) && (Math.abs(this.y()-point.y())<= ISolarSystem.PRECISION_DELTA);
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

	public double distance(CartesianCoord p2) {		
		return Math.sqrt((p2.x()-x())*(p2.x()-x()) + (p2.y() -y())* (p2.y() -y()) );
	}
}
