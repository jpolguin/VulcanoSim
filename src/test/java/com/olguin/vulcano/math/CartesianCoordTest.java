package com.olguin.vulcano.math;

import junit.framework.Assert;

import org.junit.Test;

import com.olguin.vulcanosim.model.SolarSystem;

public class CartesianCoordTest {
	
	
	@Test
	public void testDistanceSamePointIsZero(){
		
		CartesianCoord p1 = new CartesianCoord(-4, -3);
		CartesianCoord p2 = new CartesianCoord(-4, -3);
		
		Assert.assertEquals(0, p1.distance(p2), SolarSystem.PRECISION_DELTA);
		
		CartesianCoord p3 = new CartesianCoord(2.5, 3.2);
		CartesianCoord p4 = new CartesianCoord(2.5, 3.2);
		
		Assert.assertEquals(0, p3.distance(p4), SolarSystem.PRECISION_DELTA);
		
	}
	
	@Test
	public void testDistanceIsTheSqrtOfTheOppositeSidesSquared(){
		
		CartesianCoord p1 = new CartesianCoord(-4, -3);
		CartesianCoord p2 = new CartesianCoord(6, 1);
		
		Assert.assertEquals(10.7703296, p1.distance(p2),SolarSystem.PRECISION_DELTA);
		Assert.assertEquals(Math.sqrt((p2.x()-p1.x())*(p2.x()-p1.x()) + (p2.y() -p1.y())* (p2.y() -p1.y()) ), p1.distance(p2),SolarSystem.PRECISION_DELTA);
	}

	
	
	@Test
	public void testDistanceIsSimetric(){
		
		CartesianCoord p1 = new CartesianCoord(0, 0);
		CartesianCoord p2 = new CartesianCoord(0, 3);
		
		Assert.assertEquals(3.0, p1.distance(p2),SolarSystem.PRECISION_DELTA);
		Assert.assertEquals(p1.distance(p2), p2.distance(p1),SolarSystem.PRECISION_DELTA);
	}

	
	
}
