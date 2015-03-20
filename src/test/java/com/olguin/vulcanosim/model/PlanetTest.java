package com.olguin.vulcanosim.model;

import org.junit.Assert;
import org.junit.Test;



public class PlanetTest {

	@Test
	public void createPlanetWithInitialValues() {
		
		PolarCoord initialPosition = new PolarCoord(500, PolarCoord.PI_2);
		Planet planetFerengi = new Planet(initialPosition, -PolarCoord.PI_180  );
		Assert.assertNotNull(planetFerengi);
		Assert.assertEquals(new PolarCoord(500, PolarCoord.PI_2), planetFerengi.getInitialPosition());
		Assert.assertEquals(-PolarCoord.PI_180,planetFerengi.getAngularVelocity(), SolarSystem.PRECISION_DELTA);
	}
	
	
	@Test
	public void testRevolutionInDays() {
		Planet planetBetasoide = new Planet(new PolarCoord(2000, PolarCoord.PI_2), Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		Assert.assertEquals(120, planetBetasoide.revolution());
	
		Planet planetFerengi = new Planet(new PolarCoord(500,  PolarCoord.PI_2), Planet.ANGULAR_ONE_GRADE_CLOCKWISE  );
		Assert.assertEquals(360,planetFerengi.revolution());
	}
	
	@Test
	public void positionAtDayZeroIsInitialPosition() {
		PolarCoord initialPosition = new PolarCoord(2000, PolarCoord.PI_2);
		Planet planetBetasoide = new Planet(initialPosition, Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		
		Assert.assertEquals(initialPosition,planetBetasoide.positionAtDay(0));
	}
	
	@Test
	public void positionAtDayOneIsInitialPositionPlus1AngularVelocity() {
		long radius = 2000;
		PolarCoord initialPosition = new PolarCoord(radius, PolarCoord.PI_2);
		Planet planetBetasoide = new Planet(initialPosition, Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		
		Assert.assertEquals(new PolarCoord(radius, PolarCoord.PI_2 +  Planet.ANGULAR_THREE_GRADE_CLOCKWISE ),planetBetasoide.positionAtDay(1));
	}
	
	
	@Test
	public void positionAfterOneRevolutionClockwiseIsInitialPositionMinusTwoPI() {
		double initialAngularPosition = PolarCoord.PI_2;
		PolarCoord initialPosition = new PolarCoord(500, initialAngularPosition);
		Planet planetFerengi = new Planet(initialPosition,  Planet.ANGULAR_ONE_GRADE_CLOCKWISE   );
		
		
		Assert.assertEquals(initialAngularPosition - PolarCoord.TWO_PI, planetFerengi.positionAtDay(planetFerengi.revolution()).getAngularPosInRads(), SolarSystem.PRECISION_DELTA );

	}
	
	
	@Test
	public void positionAfterOneRevolutionCounterClockwiseIsInitialPositionPlusTwoPI() {
		double initialAngularPosition = PolarCoord.PI_2;
		PolarCoord initialPosition = new PolarCoord(1000, initialAngularPosition);
		Planet planetVulcano = new Planet(initialPosition,  Planet.ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE   );
		
		
		Assert.assertEquals(initialAngularPosition + PolarCoord.TWO_PI, planetVulcano.positionAtDay(planetVulcano.revolution()).getAngularPosInRads(), SolarSystem.PRECISION_DELTA );

	}
	
	
}
