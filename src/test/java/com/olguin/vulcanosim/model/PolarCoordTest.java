package com.olguin.vulcanosim.model;

import org.junit.Test;

import junit.framework.Assert;

public class PolarCoordTest {

	@Test
	public void testPolarCoordWitheSameRadiusAndAngleAreEquals() {
		PolarCoord polarCoord1 = new PolarCoord(500, PolarCoord.PI_2);
		PolarCoord polarCoord2 = new PolarCoord(500, PolarCoord.PI_2);
		
		Assert.assertEquals(polarCoord1, polarCoord2);
	}
	
	@Test
	public void testPolarCoordSameRadiusAndDifferentAngleAreNotEquals() {
		PolarCoord polarCoord1 = new PolarCoord(500, PolarCoord.PI_2);
		PolarCoord polarCoord2 = new PolarCoord(500, PolarCoord.PI_4);
		
		Assert.assertFalse(polarCoord1.equals(polarCoord2));
	}
	
	
	@Test
	public void testPolarCoordDiffRadiusAndSameAngleAreNotEquals() {
		PolarCoord polarCoord1 = new PolarCoord(500, PolarCoord.PI_4);
		PolarCoord polarCoord2 = new PolarCoord(501, PolarCoord.PI_4);
		
		Assert.assertFalse(polarCoord1.equals(polarCoord2));
	}
	
	@Test
	public void testPolarCoordToCartesianPointAt45Degree() {
		
		PolarCoord polarCoord = new PolarCoord(500, PolarCoord.PI_4);
		
		CartesianCoord pointIn45Degree500radius = new CartesianCoord(353.5533905,353.5533905);
		
		Assert.assertEquals(pointIn45Degree500radius,polarCoord.toCartesianCoord());
	}
	
	
	@Test
	public void testPolarCoordToCartesianPointAt0Degree() {
		
		PolarCoord polarCoord = new PolarCoord(500, 0.0);
		
		CartesianCoord pointIn0Degree500radius = new CartesianCoord(500.0, 0.0);
		
		Assert.assertEquals(pointIn0Degree500radius,polarCoord.toCartesianCoord());
	}
	
	@Test
	public void testPolarCoordToCartesianPointAt90Degree() {
		
		PolarCoord polarCoord = new PolarCoord(500, PolarCoord.PI_2);
		
		CartesianCoord pointIn90Degree500radius = new CartesianCoord(0, 500.0);
		
		Assert.assertEquals(pointIn90Degree500radius,polarCoord.toCartesianCoord());
	}
	
	@Test
	public void testPolarCoordToCartesianPointAt180Degree() {
		
		PolarCoord polarCoord = new PolarCoord(500, PolarCoord.PI);
		
		CartesianCoord pointIn180Degree500radius = new CartesianCoord(-500, 0);
		
		Assert.assertEquals(pointIn180Degree500radius,polarCoord.toCartesianCoord());
	}
	
	@Test
	public void testPolarCoordToCartesianPointAt270Degree() {
		
		PolarCoord polarCoord = new PolarCoord(500, PolarCoord._3PI_2);
		
		CartesianCoord pointIn270Degree500radius = new CartesianCoord(0, -500);
		
		Assert.assertEquals(pointIn270Degree500radius,polarCoord.toCartesianCoord());
	}
	
	
	@Test
	public void testPolarCoordZEROToCartesianPoint() {
		
		PolarCoord polarCoord = new PolarCoord(0,0);
		
		CartesianCoord point00 = new CartesianCoord(0, 0);
		
		Assert.assertEquals(point00,polarCoord.toCartesianCoord());
	}
	
	
}
