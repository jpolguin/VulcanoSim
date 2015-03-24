package com.olguin.solarsystem.model;

import com.olguin.solarsystem.math.PolarCoord;



public class Planet implements IPlanet {

	public static final double ANGULAR_ONE_GRADE_CLOCKWISE = -PolarCoord.PI_180;
	public static final double ANGULAR_THREE_GRADE_CLOCKWISE = -PolarCoord.PI_180 * 3;
	public static final double ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE = PolarCoord.PI_180 * 5;
	
	private PolarCoord _initialPosition;
	private double _angularVelocity;
	

	/**
	 * Create a planet with an initial position, represented as a Polar coordinate (radius, angleInRads) and an angularVelocity
	 * in rads. If the angular velocity is negative it means the velocity is clockwise. If it is positive the velocity is counterclockwise 
	 * @param initialPosition
	 * @param angularVelocity
	 */
	public Planet(PolarCoord initialPosition, double angularVelocity) {
		_initialPosition = initialPosition;
		_angularVelocity = angularVelocity;
	}

	/* (non-Javadoc)
	 * @see com.olguin.vulcanosim.model.IPlanet#getInitialPosition()
	 */
	@Override
	public PolarCoord getInitialPosition() {
		
		return _initialPosition;
	}

	/* (non-Javadoc)
	 * @see com.olguin.vulcanosim.model.IPlanet#getAngularVelocity()
	 */
	@Override
	public double getAngularVelocity() {
		
		return _angularVelocity;
	}

	

	/* (non-Javadoc)
	 * @see com.olguin.vulcanosim.model.IPlanet#positionAtDay(int)
	 */
	@Override
	public PolarCoord positionAtDay(int day) {
		
		 return new PolarCoord(getInitialPosition().getRadius(), getInitialPosition().getAngularPosInRads() + getAngularVelocity() * day);
	}

	/* (non-Javadoc)
	 * @see com.olguin.vulcanosim.model.IPlanet#revolutionPeriodInDays()
	 */
	@Override
	public int revolutionPeriodInDays() {
		
		return  (int) Math.abs(Math.round(PolarCoord.TWO_PI /getAngularVelocity())) ;
	}
	
	
	
	

}
