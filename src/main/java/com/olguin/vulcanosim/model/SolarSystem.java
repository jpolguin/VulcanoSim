package com.olguin.vulcanosim.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolarSystem {
	
	public static final double PRECISION_DELTA = 0.0001;

	private List<Planet> _planets = new ArrayList<>();
	private PolarCoord _sun = new PolarCoord(0, 0);


	public SolarSystem(Planet[] planets) {
		_planets.addAll(Arrays.asList(planets));
		
	}

	public int numberOfplanets() {
		return _planets.size();
	}

	public static SolarSystem createVulcanoSystem() {
		double initialAngularPosition = PolarCoord.PI_2;
		
		Planet planetFerengi = new Planet(new PolarCoord(500, initialAngularPosition),  Planet.ANGULAR_ONE_GRADE_CLOCKWISE);
		Planet planetVulcano = new Planet(new PolarCoord(1000, initialAngularPosition),  Planet.ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE);
		Planet planetBetasoide = new Planet(new PolarCoord(2000, PolarCoord.PI_2), Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		

		Planet[] planets = new Planet[] {planetFerengi,planetVulcano, planetBetasoide};
		
		SolarSystem solarSystem = new SolarSystem(planets);
		return solarSystem;
	}

   	
	
	public boolean planetsAlignedAt(long day) {
		
		if(numberOfplanets()<=2) {
			return true;
		} else {
             Planet firstPlanet = getPlanets().get(0);
			 Planet secondPlanet = getPlanets().get(1);
			PolarCoord positionFirstPlanet = firstPlanet.positionAtDay(day);
			PolarCoord positionSecondPlanet = secondPlanet.positionAtDay(day);
			Line lineFirstTwoPlanets = new Line (positionFirstPlanet.toCartesianCoord(), positionSecondPlanet.toCartesianCoord() );		
		}
	
		return false;
	}

	
	private List<Planet> getPlanets() {
		return _planets;
	}
}
