package com.olguin.vulcanosim.model;

import junit.framework.Assert;

import org.junit.Test;

public class SolarSistemTest {

	
	@Test
	public void testSolarSystemCreation(){
		double initialAngularPosition = PolarCoord.PI_2;
	
		Planet planetFerengi = new Planet(new PolarCoord(500, initialAngularPosition),  Planet.ANGULAR_ONE_GRADE_CLOCKWISE);
		Planet planetVulcano = new Planet(new PolarCoord(1000, initialAngularPosition),  Planet.ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE);
		Planet planetBetasoide = new Planet(new PolarCoord(2000, PolarCoord.PI_2), Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		

		Planet[] planets = new Planet[] {planetFerengi,planetVulcano, planetBetasoide};
		
		SolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertEquals(3, solarSystem.numberOfplanets());
	}
	
	
	
	@Test
	public void testPlanetsAlignedAttInitialPositionPI_2(){
		 SolarSystem vulcanoSystem = SolarSystem.createVulcanoSystem();
		 
		 Assert.assertTrue(vulcanoSystem.planetsAlignedAt(0));
		 
	}
	
}
