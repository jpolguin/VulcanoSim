package com.olguin.vulcano.math;

import org.junit.Assert;
import org.junit.Test;

import com.olguin.vulcanosim.model.SolarSystem;

public class TriangleTest {

	
	@Test
	public void testTriangleCreation() {
		CartesianCoord p1 = new CartesianCoord(-4, -3);
		CartesianCoord p2 = new CartesianCoord(-1, 4);
		CartesianCoord p3 = new CartesianCoord(6, 1);
		
		Triangle triangle1 = new Triangle(p1,p2,p3);
		
		Assert.assertNotNull(triangle1);
	}
	
	@Test
	public void testPoint0InsideAndPointOutsideIsOutside() {
		
		CartesianCoord p1 = new CartesianCoord(-4, -3);
		CartesianCoord p2 = new CartesianCoord(-1, 4);
		CartesianCoord p3 = new CartesianCoord(6, 1);
		
		Triangle triangle = new Triangle(p1,p2,p3);
		
		CartesianCoord point00 = new CartesianCoord(0, 0);
		CartesianCoord pointOutside = new CartesianCoord(-11, 9);
		
		Assert.assertTrue(triangle.pointInside(point00));
		Assert.assertFalse(triangle.pointInside(pointOutside));
	}
	
	@Test
    public void testPointInSideOfTriangleAndVertexAreInside() {
		
		CartesianCoord p1 = new CartesianCoord(-4, -3);
		CartesianCoord p2 = new CartesianCoord(-1, 4);
		CartesianCoord p3 = new CartesianCoord(6, 1);
		
		Triangle triangle = new Triangle(p1,p2,p3);
		
		CartesianCoord pointSidep2p3 = new CartesianCoord(0, -1.4);
	
		CartesianCoord pointOutside = new CartesianCoord(0, -2);
		
		Assert.assertTrue(triangle.pointInside(p1));
		Assert.assertTrue(triangle.pointInside(p2));
		
		Assert.assertTrue(triangle.pointInside(pointSidep2p3));
		
		Assert.assertFalse(triangle.pointInside(pointOutside));
	}
	
	
	@Test
    public void testPointZeroIsNotInsideTriangleInPositiveQuadrant() {
		
		CartesianCoord p1 = new CartesianCoord(1, 1);
		CartesianCoord p2 = new CartesianCoord(3, 4);
		CartesianCoord p3 = new CartesianCoord(9, 2);
		
		Triangle triangle = new Triangle(p1,p2,p3);
		
		CartesianCoord pointOutside = new CartesianCoord(2, 3);
		
		CartesianCoord pointInside  = new CartesianCoord(3,2);
		
		CartesianCoord point00 = new CartesianCoord(0, 0);
		
		Assert.assertTrue(triangle.pointInside(pointInside));
		Assert.assertFalse(triangle.pointInside(point00));
		Assert.assertFalse(triangle.pointInside(pointOutside));
	
	}
	
	@Test
    public void testTrianglePerimeter() {
		
		CartesianCoord p1 = new CartesianCoord(1, 1);
		CartesianCoord p2 = new CartesianCoord(3, 4);
		CartesianCoord p3 = new CartesianCoord(9, 2);
		
		Triangle triangle = new Triangle(p1,p2,p3);
		
		Assert.assertEquals(17.9923, triangle.perimeter(), SolarSystem.PRECISION_DELTA);
		
	}
	
	
	@Test
    public void testTrianglePerimeter2() {
		
		CartesianCoord p1 = new CartesianCoord(0, 0);
		CartesianCoord p2 = new CartesianCoord(3, 3);
		CartesianCoord p3 = new CartesianCoord(6, 0);
		
		Triangle triangle = new Triangle(p1,p2,p3);
		
		Assert.assertEquals(14.4852, triangle.perimeter(), SolarSystem.PRECISION_DELTA);
		
	}
	
	@Test
    public void testTrianglePerimeter3() {
		
		CartesianCoord p1 = new CartesianCoord(0, 0);
		CartesianCoord p2 = new CartesianCoord(3, -3);
		CartesianCoord p3 = new CartesianCoord(6, 0);
		
		Triangle triangle = new Triangle(p1,p2,p3);
		
		Assert.assertEquals(14.4852, triangle.perimeter(), SolarSystem.PRECISION_DELTA);
		
	}
	
	

	@Test
    public void testTrianglePerimeterOrderOfPointIsNotImportant() {
		
		CartesianCoord p1 = new CartesianCoord(0, 0);
		CartesianCoord p2 = new CartesianCoord(3, -3);
		CartesianCoord p3 = new CartesianCoord(6, 0);
		
		Triangle triangle = new Triangle(p1,p2,p3);
		Triangle triangle2 = new Triangle(p3,p1,p2);
		Triangle triangle3 = new Triangle(p2,p3,p1);
		
		Assert.assertEquals(triangle2.perimeter(), triangle.perimeter(), SolarSystem.PRECISION_DELTA);
		Assert.assertEquals(triangle2.perimeter(), triangle3.perimeter(), SolarSystem.PRECISION_DELTA);
		
	}
	
	
}
