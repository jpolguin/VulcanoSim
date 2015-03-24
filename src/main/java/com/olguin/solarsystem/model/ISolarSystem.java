package com.olguin.solarsystem.model;

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
	public abstract boolean planetsAlignedAt(int day);

	
	/**
	 *  Analyze the planets alignment between all of them and the Sun
	 * @param day the day the planet alignment is analyzed
	 * @return true if all planets and The Sun are aligned along a straight line with a tolerance of PRECISION_DELTA
	 */
	public abstract boolean solarAligmentAt(int day);

    /**
     * Analyze whether the sun falls into the triangle formed by the first 3 planets in the planetary system, as specified when 
     * the solar system was created.
     * @param day the day the analysis is taken place
     * @return true if the sun falls into the triangle, false otherwise
     */
	public abstract boolean sunInsideTriangleAtDay(int day);

   /**
    * Analyze Solar System's condition to forecast possible weather according to orbits relations between planets and Sun
    * @param day  the day to analyze the solar system conditions
    * @return the likely weather that the system will have.
    */
	public abstract Weather weatherAtDay(int day);

    /**
     * Return the perimeter of the triangle formed with the first 3 planets of the Solar System
     * @param day day of the year to calculate the triangle perimeter.
     * @return the calculated perimeter
     */
    public abstract double trianglePerimeterAtDay(int day);

}