package com.olguin.vulcanosim.model;

public class PolarCoord {
	public static final double PI_2 = Math.PI/2;
	public static final double PI_180 =  Math.PI/(double)180;
	public static final double PI_4 = Math.PI/4.0;
	public static final double TWO_PI = Math.PI * 2.0;
	public static final double PI = Math.PI;
	public static final double _3PI_2 =  (Math.PI*3.0)/2.0;
	private long _radius;
	private double _thetaInRads;
	
	

	public PolarCoord(long radius, double thetaInRads) {
		_radius = radius;
		_thetaInRads = thetaInRads;
	}

	@Override
	public boolean equals(Object anotherPolarCoord) {
		if (anotherPolarCoord instanceof PolarCoord) {
			PolarCoord another = (PolarCoord) anotherPolarCoord;
			return (this.getRadius() == another.getRadius() && (Math.abs(this.getAngularPosInRads()-another.getAngularPosInRads()) <= SolarSystem.PRECISION_DELTA));
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (int)getRadius() + (int) getAngularPosInRads();
	};

	public long getRadius() {
		
		return _radius;
	}

	public double getAngularPosInRads() {
		return _thetaInRads;
	}
	

	public CartesianCoord toCartesianCoord() {
		
		return new CartesianCoord(calcXCoordinate(),calcYCoordinate());
	}

	
	
	
	private double calcXCoordinate() {
		
		return getRadius() * Math.cos(getAngularPosInRads());
	}

	private double calcYCoordinate() {
		
		return getRadius() * Math.sin(getAngularPosInRads());
	}
	
	@Override
	public String toString() {

		return "(Radius:"+ getRadius()+", theta:" + getAngularPosInRads()+ ")"; 
	}
}
