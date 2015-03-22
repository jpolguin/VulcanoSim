package com.olguin.vulcano.math;

import org.junit.Assert;
import org.junit.Test;

import com.olguin.vulcano.math.CartesianCoord;
import com.olguin.vulcano.math.Line;
import com.olguin.vulcano.math.VerticalLineException;
import com.olguin.vulcanosim.model.ISolarSystem;

public class LineTest {

	
	@Test 
	public void testCreateLine1() {
		CartesianCoord coord1 = new CartesianCoord(5, 2);
		CartesianCoord coord2 = new CartesianCoord(-3, -3);
		Line line1 = new Line(coord1,coord2);
		
		Assert.assertEquals(0.625, line1.getMSlope(), ISolarSystem.PRECISION_DELTA);		
		Assert.assertEquals(-1.125, line1.getBShift(), ISolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(coord1.y(), line1.apply(coord1.x()),ISolarSystem.PRECISION_DELTA);
		
	}
	
	
	@Test 
	public void testCreateLine2() {
		CartesianCoord coord1 = new CartesianCoord(-1, -1);
		CartesianCoord coord2 = new CartesianCoord(5, -5);
		Line line1 = new Line(coord1,coord2);
		
		Assert.assertEquals(-0.66666666, line1.getMSlope(), ISolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(-1.66666666, line1.getBShift(), ISolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(coord1.y(), line1.apply(coord1.x()),ISolarSystem.PRECISION_DELTA);
		
	}
	
	
	@Test 
	public void testCreateLinethatPassFrom00() {
		CartesianCoord coord1 = new CartesianCoord(0, 0);
		CartesianCoord coord2 = new CartesianCoord(-5, 5);
		Line line1 = new Line(coord1,coord2);
		
		Assert.assertEquals(-1, line1.getMSlope(), ISolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(0, line1.getBShift(), ISolarSystem.PRECISION_DELTA);
		
		Assert.assertEquals(coord1.y(), line1.apply(coord1.x()),ISolarSystem.PRECISION_DELTA);
		
	}
	
	@Test 
	public void testCreateVerticalLineInXZero() {
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
	public void testCreateVerticalLineInXNOTZero() {
		CartesianCoord coord1 = new CartesianCoord(12, 0);
		CartesianCoord coord2 = new CartesianCoord(12, 5);
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
		Assert.assertEquals(coord1.y(), line1.apply(coord1.x()),ISolarSystem.PRECISION_DELTA);
		Assert.assertEquals(coord2.y(), line1.apply(coord2.x()),ISolarSystem.PRECISION_DELTA);
		
	}
	
	
	
	@Test
	public void testPointAligned() {
		CartesianCoord coord1 = new CartesianCoord(5, 2);
		CartesianCoord coord2 = new CartesianCoord(-3, -3);
		CartesianCoord coord3 = new CartesianCoord(8, 3.875);
		CartesianCoord coordZero = new CartesianCoord(0,0);
		
		CartesianCoord coordOut = new CartesianCoord(-2, 2);
		
		Line line = new Line(coord1,coord2);
		
		Assert.assertTrue(line.pointAligned(coord1));
		Assert.assertTrue(line.pointAligned(coord2));		
		Assert.assertTrue(line.pointAligned(coord3));
		
		Assert.assertFalse(line.pointAligned(coordOut));
		Assert.assertFalse(line.pointAligned(coordZero));
		
	}
	
	@Test
	public void testVerticalPointXNOTZeroAligment() {
		CartesianCoord coord1 = new CartesianCoord(7, 0);
		CartesianCoord coord2 = new CartesianCoord(7, 5);
		Line line = new Line(coord1,coord2);
		
		CartesianCoord coordIn = new CartesianCoord(7, 140);
		CartesianCoord coordOut = new CartesianCoord(-2, 5);
		
		CartesianCoord coordZero = new CartesianCoord(0,0);
		
		Assert.assertTrue(line.pointAligned(coord1));
		Assert.assertTrue(line.pointAligned(coord2));
		Assert.assertTrue(line.pointAligned(coordIn));
		
		Assert.assertFalse(line.pointAligned(coordOut));
		Assert.assertFalse(line.pointAligned(coordZero));
		
		
		
	}
	
	
	@Test
	public void testLineThatPassesThroughZeroAligment() {
		CartesianCoord coord1 = new CartesianCoord(-3, 3);
		CartesianCoord coord2 = new CartesianCoord(-5, 5);
		Line line = new Line(coord1,coord2);
		
		CartesianCoord coordZero = new CartesianCoord(0,0);
		
		Assert.assertTrue(line.pointAligned(coord1));
		Assert.assertTrue(line.pointAligned(coord2));
		Assert.assertTrue(line.pointAligned(coordZero));
	}
	
}
