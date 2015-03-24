package com.olguin.solarsystem.model;

import org.junit.Assert;
import org.junit.Test;

import com.olguin.solarsystem.math.PolarCoord;
import com.olguin.solarsystem.model.IPlanet;
import com.olguin.solarsystem.model.ISolarSystem;
import com.olguin.solarsystem.model.Planet;



public class PlanetTest {

	@Test
	public void createPlanetWithInitialValues() {
		
		PolarCoord initialPosition = new PolarCoord(500, PolarCoord.PI_2);
		IPlanet planetFerengi = new Planet(initialPosition, -PolarCoord.PI_180  );
		Assert.assertNotNull(planetFerengi);
		Assert.assertEquals(new PolarCoord(500, PolarCoord.PI_2), planetFerengi.getInitialPosition());
		Assert.assertEquals(-PolarCoord.PI_180,planetFerengi.getAngularVelocity(), ISolarSystem.PRECISION_DELTA);
	}
	
	
	@Test
	public void testRevolutionInDays() {
		IPlanet planetBetasoide = new Planet(new PolarCoord(2000, PolarCoord.PI_2), Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		Assert.assertEquals(120, planetBetasoide.revolutionPeriodInDays());
	
		IPlanet planetFerengi = new Planet(new PolarCoord(500,  PolarCoord.PI_2), Planet.ANGULAR_ONE_GRADE_CLOCKWISE  );
		Assert.assertEquals(360,planetFerengi.revolutionPeriodInDays());
		
		
		IPlanet planetVulcano = new Planet(new PolarCoord(1000,  PolarCoord.PI_2),  Planet.ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE);
		Assert.assertEquals(72,planetVulcano.revolutionPeriodInDays());
	}
	
	@Test
	public void positionAtDayZeroIsInitialPosition() {
		PolarCoord initialPosition = new PolarCoord(2000, PolarCoord.PI_2);
		IPlanet planetBetasoide = new Planet(initialPosition, Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		
		Assert.assertEquals(initialPosition,planetBetasoide.positionAtDay(0));
	}
	
	@Test
	public void positionAtDayOneIsInitialPositionPlus1AngularVelocity() {
		long radius = 2000;
		PolarCoord initialPosition = new PolarCoord(radius, PolarCoord.PI_2);
		IPlanet planetBetasoide = new Planet(initialPosition, Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		
		Assert.assertEquals(new PolarCoord(radius, PolarCoord.PI_2 +  Planet.ANGULAR_THREE_GRADE_CLOCKWISE ),planetBetasoide.positionAtDay(1));
	}
	
	
	@Test
	public void positionAfterOneRevolutionClockwiseIsInitialPositionMinusTwoPI() {
		double initialAngularPosition = PolarCoord.PI_2;
		PolarCoord initialPosition = new PolarCoord(500, initialAngularPosition);
		IPlanet planetFerengi = new Planet(initialPosition,  Planet.ANGULAR_ONE_GRADE_CLOCKWISE   );
		
		
		Assert.assertEquals(initialAngularPosition - PolarCoord.TWO_PI, planetFerengi.positionAtDay(planetFerengi.revolutionPeriodInDays()).getAngularPosInRads(), ISolarSystem.PRECISION_DELTA );

	}
	
	
	@Test
	public void positionAfterOneRevolutionCounterClockwiseIsInitialPositionPlusTwoPI() {
		double initialAngularPosition = PolarCoord.PI_2;
		PolarCoord initialPosition = new PolarCoord(1000, initialAngularPosition);
		IPlanet planetVulcano = new Planet(initialPosition,  Planet.ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE   );
		
		
		Assert.assertEquals(initialAngularPosition + PolarCoord.TWO_PI, planetVulcano.positionAtDay(planetVulcano.revolutionPeriodInDays()).getAngularPosInRads(), ISolarSystem.PRECISION_DELTA );

	}
	
	
}
