package com.olguin.vulcanosim.model;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

	
	@Test 
	public void testCreateLine1() {
		CartesianCoord coord1 = new CartesianCoord(5, 2);
		CartesianCoord coord2 = new CartesianCoord(-3, -3);
		Line line1 = new Line(coord1,coord2);
		
		Assert.assertEquals(0.625, line1.getMSlope(), SolarSystem.PRECISION_DELTA);		
		Assert.assertEquals(-1.125, line1.getBShift(), SolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(coord1.y(), line1.apply(coord1.x()),SolarSystem.PRECISION_DELTA);
		
	}
	
	
	@Test 
	public void testCreateLine2() {
		CartesianCoord coord1 = new CartesianCoord(-1, -1);
		CartesianCoord coord2 = new CartesianCoord(5, -5);
		Line line1 = new Line(coord1,coord2);
		
		Assert.assertEquals(-0.66666666, line1.getMSlope(), SolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(-1.66666666, line1.getBShift(), SolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(coord1.y(), line1.apply(coord1.x()),SolarSystem.PRECISION_DELTA);
		
	}
	
	
	@Test 
	public void testCreateLinethatPassFrom00() {
		CartesianCoord coord1 = new CartesianCoord(0, 0);
		CartesianCoord coord2 = new CartesianCoord(-5, 5);
		Line line1 = new Line(coord1,coord2);
		
		Assert.assertEquals(-1, line1.getMSlope(), SolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(0, line1.getBShift(), SolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(coord1.y(), line1.apply(coord1.x()),SolarSystem.PRECISION_DELTA);
		
	}
	
	@Test 
	public void testCreateVerticalLine() {
		CartesianCoord coord1 = new CartesianCoord(0, 0);
		CartesianCoord coord2 = new CartesianCoord(0, 5);
		Line line1 = new Line(coord1,coord2);
		
		 try {
		   line1.getMSlope();
		     Assert.fail("It should have thrown VerticalLineException");
		 } catch (VerticalLineException e) {
			 
		 }
		 

		 try {
		   line1.apply(coord1.x());
		 Assert.fail("It should have thrown VerticalLineException");
		 } catch (VerticalLineException e) {
			 
		 }
		
		
	}
	
	
	
	@Test
	public void testCoordDefiningLineFitsEquationApply() {
		CartesianCoord coord1 = new CartesianCoord(5, 2);
		CartesianCoord coord2 = new CartesianCoord(-3, -3);
		Line line1 = new Line(coord1,coord2);
		Assert.assertEquals(coord1.y(), line1.apply(coord1.x()),SolarSystem.PRECISION_DELTA);
		Assert.assertEquals(coord2.y(), line1.apply(coord2.x()),SolarSystem.PRECISION_DELTA);
		
	}
	
	
	
	@Test
	public void testPointAligned() {
		CartesianCoord coord1 = new CartesianCoord(5, 2);
		CartesianCoord coord2 = new CartesianCoord(-3, -3);
		CartesianCoord coord3 = new CartesianCoord(8, 3.875);
		
		CartesianCoord coordOut = new CartesianCoord(-2, 2);
		
		Line line = new Line(coord1,coord2);
		
		Assert.assertTrue(line.pointAligned(coord1));
		Assert.assertTrue(line.pointAligned(coord2));
		Assert.assertFalse(line.pointAligned(coordOut));
		Assert.assertTrue(line.pointAligned(coord3));
		
	}
	
}
