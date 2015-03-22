package com.olguin.vulcano.math;

import com.olguin.vulcanosim.model.ISolarSystem;

public class Line {
	private double _mSlope;
	private double _bShift;
    private boolean _vertical = false;
    private Double _xCoordFromVerticalLine;
	
	

	protected Line(CartesianCoord coord1, CartesianCoord coord2) {
		
		 if (sameXCoord(coord1, coord2)) {  //Same X Coord means a vertical line
			 _vertical = true;
			 _xCoordFromVerticalLine = coord1.x();			
		 } else {   //Regular, non vertical line
			  _mSlope  = (coord2.y() - coord1.y()) / (coord2.x() - coord1.x());
			  _bShift = coord1.y() - (_mSlope * coord1.x());
			 
		 }
	}


	
	public Line(PolarCoord polarPoint1, PolarCoord polarPoint2) {
         this(polarPoint1.toCartesianCoord(),polarPoint2.toCartesianCoord());
	}



	public double apply(double x) {
		if (!isVertical()) {
		   return (_mSlope*x + _bShift);
		}
		throw new VerticalLineException();
	}
	
	
	public double getMSlope() {
		if (!isVertical()) {
		   return _mSlope;
		} 
		throw new VerticalLineException();
	}

	public double getBShift() {
		if (!isVertical()) {
		  return _bShift;
		}
		throw new VerticalLineException();
	}
	
	
	public boolean isVertical() {
		return _vertical;
	}

	protected boolean pointAligned(CartesianCoord point) {
		if (!isVertical()) {
		  return equalsOrCloseEnough(point.y(),apply(point.x()));
		} else {
			return equalsOrCloseEnough(point.x(), getXCoordFromVerticalLine());
		}
		
	
	}

	private double getXCoordFromVerticalLine() {
		
		return _xCoordFromVerticalLine;
	}


	private boolean equalsOrCloseEnough(double val1, double val2) {
		
		return  Math.abs(val2-val1) < ISolarSystem.PRECISION_DELTA;
	}

	
	private boolean sameXCoord(CartesianCoord coord1, CartesianCoord coord2) {
		return equalsOrCloseEnough(coord1.x(), coord2.x());
	}



	public boolean pointAligned(PolarCoord polarPoint) {		
		return pointAligned(polarPoint.toCartesianCoord());
	}
}
