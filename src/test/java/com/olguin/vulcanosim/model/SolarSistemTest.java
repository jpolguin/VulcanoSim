package com.olguin.vulcanosim.model;

import junit.framework.Assert;

import org.junit.Test;

import com.olguin.vulcano.math.PolarCoord;

public class SolarSistemTest {

	
	@Test
	public void testSolarSystemCreation(){
		double initialAngularPosition = PolarCoord.PI_2;
	
		Planet planetFerengi = new Planet(new PolarCoord(500, initialAngularPosition),  Planet.ANGULAR_ONE_GRADE_CLOCKWISE);
		Planet planetVulcano = new Planet(new PolarCoord(1000, initialAngularPosition),  Planet.ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE);
		Planet planetBetasoide = new Planet(new PolarCoord(2000, PolarCoord.PI_2), Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		

		Planet[] planets = new Planet[] {planetFerengi,planetVulcano, planetBetasoide};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertEquals(3, solarSystem.numberOfplanets());
		
	
	}
	
	
	@Test
	public void testPlanetAligmentIsTrueWithAnyTwoplanetsSolarSystem(){
		double initialAngularPosition1 = PolarCoord.PI_180;
		double initialAngularPosition2 = PolarCoord.PI_4;
	
		Planet planet1 = new Planet(new PolarCoord(563, initialAngularPosition1),  -PolarCoord.PI_180*4);
		Planet planet2 = new Planet(new PolarCoord(6789, initialAngularPosition2),  PolarCoord.PI_180*20);
		
		Planet planet3 = new Planet(new PolarCoord(20567, PolarCoord.PI_2), Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		

		Planet[] planets = new Planet[] {planet1, planet2};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertTrue(solarSystem.planetsAlignedAt(0));
		Assert.assertTrue(solarSystem.planetsAlignedAt(20));
		Assert.assertTrue(solarSystem.planetsAlignedAt(345));
		
	    Planet[] planets2 = new Planet[] {planet2, planet3};
		
		ISolarSystem solarSystem2= new SolarSystem(planets2);
		
		Assert.assertTrue(solarSystem2.planetsAlignedAt(20));
		Assert.assertTrue(solarSystem2.planetsAlignedAt(21));
		Assert.assertTrue(solarSystem2.planetsAlignedAt(22));
		
	}
	
	
	
	@Test
	public void testPlanetsAlignedAttInitialPositionPI_2(){
		 ISolarSystem vulcanoSystem = SolarSystem.createVulcanoSystem();
		 
		 Assert.assertTrue(vulcanoSystem.planetsAlignedAt(0));
		 
	}
	
	@Test
	public void testPlanetsNotAlignedInVulcanoAtInitialPositionPlusOneDay(){
		 ISolarSystem vulcanoSystem = SolarSystem.createVulcanoSystem();
		 
		 Assert.assertFalse(vulcanoSystem.planetsAlignedAt(1));
	}
	
	
	@Test
	public void testPlanetAligmentIsTrueIn180And0WithOneGradeADayAngularVelocityAndSameInitialPos(){
		double initialAngularPosition1 = PolarCoord.PI;
		double initialAngularPosition2 = PolarCoord._0PI;
	
		Planet planet1 = new Planet(new PolarCoord(563, initialAngularPosition1),  -PolarCoord.PI_180);
		Planet planet2 = new Planet(new PolarCoord(6789, initialAngularPosition2),  PolarCoord.PI_180);
		
		Planet planet3 = new Planet(new PolarCoord(20567, initialAngularPosition1), PolarCoord.PI_180);
		Planet planet4 = new Planet(new PolarCoord(25, initialAngularPosition2), -PolarCoord.PI_180);
		

		Planet[] planets = new Planet[] {planet1, planet2, planet3, planet4};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertTrue(solarSystem.planetsAlignedAt(0));
		Assert.assertFalse(solarSystem.planetsAlignedAt(1));
		Assert.assertFalse(solarSystem.planetsAlignedAt(345));	
		Assert.assertTrue(solarSystem.planetsAlignedAt(planet1.revolution()));
		
	}
	
	
	
	@Test
	public void testSolarSystemAligmentIsTrueIn180And0WithOneGradeADayAngularVelocityAndSameInitialPos(){
		double initialAngularPosition1 = PolarCoord.PI;
		double initialAngularPosition2 = PolarCoord._0PI;
	
		Planet planet1 = new Planet(new PolarCoord(563, initialAngularPosition1),  -PolarCoord.PI_180);
		Planet planet2 = new Planet(new PolarCoord(6789, initialAngularPosition2),  PolarCoord.PI_180);
		
		Planet planet3 = new Planet(new PolarCoord(20567, initialAngularPosition1), PolarCoord.PI_180);
		Planet planet4 = new Planet(new PolarCoord(25, initialAngularPosition2), -PolarCoord.PI_180);
		

		Planet[] planets = new Planet[] {planet1, planet2, planet3, planet4};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertTrue(solarSystem.solarAligmentAt(0));
		Assert.assertFalse(solarSystem.solarAligmentAt(1));
		Assert.assertFalse(solarSystem.solarAligmentAt(345));	
		//After one year they meet again in pi/0 pi position
		Assert.assertTrue(solarSystem.solarAligmentAt(planet1.revolution()));
		
		//But Also at half the revolution, they should meet in PI/2 and 3/2 PI
		
		Assert.assertTrue(solarSystem.solarAligmentAt(planet1.revolution()/2));
		
		
	}
	
	@Test
	public void testSunInsideTriangleIsTrueWhenInsideThreeFirstPlanetsTriangle() {
		double initialAngularPosition1 = PolarCoord.PI_4;
		double initialAngularPosition2 = PolarCoord.PI;
		double initialAngularPosition3 = PolarCoord._3PI_2;
	
		Planet planet1 = new Planet(new PolarCoord(563, initialAngularPosition1), -PolarCoord.PI_4);
		Planet planet2 = new Planet(new PolarCoord(6789, initialAngularPosition2),  PolarCoord.PI_180);
		
		Planet planet3 = new Planet(new PolarCoord(20567, initialAngularPosition3), -PolarCoord.PI_180);
		Planet planet4 = new Planet(new PolarCoord(54666, initialAngularPosition2), -PolarCoord.PI_180);
		

		Planet[] planets = new Planet[] {planet1, planet2, planet3, planet4};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertTrue(solarSystem.sunInsideTriangleAtDay(0));
		
		//Planet1 moves two times PI/4 counterClockwise so it is now in Quadrant IV, planet2 and planet 3 in Quandrant III  
		Assert.assertFalse(solarSystem.sunInsideTriangleAtDay(2));
		
	}
	
	
	@Test
	public void testSunInsideTriangleIsFalseWhen3PlanetsInFirstOrSecondQuadrant() {
		double initialAngularPosition1 = PolarCoord.PI_4;
		double initialAngularPosition2 = PolarCoord.PI_4 + PolarCoord.PI_2;
		double initialAngularPosition3 = PolarCoord.PI_6; 
	
		Planet planet1 = new Planet(new PolarCoord(563, initialAngularPosition1), -PolarCoord.PI_180);
		Planet planet2 = new Planet(new PolarCoord(6789, initialAngularPosition2),  PolarCoord.PI_180);
		Planet planet3 = new Planet(new PolarCoord(20567, initialAngularPosition3), -PolarCoord.PI_180);
		

		Planet[] planets = new Planet[] {planet1, planet2, planet3};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertFalse(solarSystem.sunInsideTriangleAtDay(0));
		
			
	}
	
	

	@Test
	public void testSunInsideTriangleIsFalseWhenSolarSystemAlignedAtPI_2() {
		 ISolarSystem vulcanoSystem = SolarSystem.createVulcanoSystem();
		
		Assert.assertFalse(vulcanoSystem.sunInsideTriangleAtDay(0));
	}
	
	
	
}
