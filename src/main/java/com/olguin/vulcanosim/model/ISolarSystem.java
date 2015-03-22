package com.olguin.vulcanosim.model;

public interface ISolarSystem {

	/**
	 * Precision tolerance to analyze System's distances, aligment, etc.
	 */
	public static final double PRECISION_DELTA = 0.0001;


	/**
	 * 
	 * @return the number of planets of the solar System
	 */
	public abstract int numberOfplanets();
	
	
    /**
     *  Analyze the planets alignment between all of them.
     * @param day The day the planet alignment is analyzed
     * @return true if all the planets in the solar system are aligned along a straight line with a tolerance of PRECISION_DELTA
     */
	public abstract boolean planetsAlignedAt(long day);

	
	/**
	 *  Analyze the planets alignment between all of them and the Sun
	 * @param day the day the planet alignment is analyzed
	 * @return true if all planets and The Sun are aligned along a straight line with a tolerance of PRECISION_DELTA
	 */
	public abstract boolean solarAligmentAt(long day);

    /**
     * Analyze whether the sun falls into the triangle formed by the first 3 planets in the planetary system, as specified when 
     * the solar system was created.
     * @param day the day the analysis is taken place
     * @return true if the sun falls into the triangle, false otherwise
     */
	public abstract boolean sunInsideTriangleAtDay(long day);

}