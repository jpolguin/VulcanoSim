package com.olguin.vulcanosim.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.olguin.vulcano.math.Line;
import com.olguin.vulcano.math.PolarCoord;
import com.olguin.vulcano.math.Triangle;

public class SolarSystem implements ISolarSystem {
	
	private List<Planet> _planets = new ArrayList<>();
	private PolarCoord _sun = new PolarCoord(0, 0);


	public SolarSystem(Planet[] planets) {
		_planets.addAll(Arrays.asList(planets));
		
	}

	/* (non-Javadoc)
	 * @see com.olguin.vulcanosim.model.ISolarSystem#numberOfplanets()
	 */
	@Override
	public int numberOfplanets() {
		return _planets.size();
	}

	public static ISolarSystem createVulcanoSystem() {
		double initialAngularPosition = PolarCoord.PI_2;
		
		Planet planetFerengi = new Planet(new PolarCoord(500, initialAngularPosition),  Planet.ANGULAR_ONE_GRADE_CLOCKWISE);
		Planet planetVulcano = new Planet(new PolarCoord(1000, initialAngularPosition),  Planet.ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE);
		Planet planetBetasoide = new Planet(new PolarCoord(2000, PolarCoord.PI_2), Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		

		Planet[] planets = new Planet[] {planetFerengi,planetVulcano, planetBetasoide};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		return solarSystem;
	}

   	
	
	/* (non-Javadoc)
	 * @see com.olguin.vulcanosim.model.ISolarSystem#planetsAlignedAt(long)
	 */
	@Override
	public boolean planetsAlignedAt(long day) {
		
		if(numberOfplanets()<=2) {
			return true;
		} else {
            Line lineFirstTwoPlanets = createLineBetweenFirstTwoPlanets(day);
			boolean aligned = true;
			for(Planet planet: getPlanets()) {
				PolarCoord planetPosition = planet.positionAtDay(day);
				if (!lineFirstTwoPlanets.pointAligned(planetPosition)) {
					aligned = false;
					break;
				}
			}
			return aligned;
		}
	
	}

	/* (non-Javadoc)
	 * @see com.olguin.vulcanosim.model.ISolarSystem#solarAligmentAt(long)
	 */
	@Override
	public boolean solarAligmentAt(long day) {
		
		return planetsAlignedAt(day) && sunAligment(day);
	}

	
	
	private boolean sunAligment(long day) {
		if(numberOfplanets()<=1) {
			return true;
		} else {
			 Line lineFirstTwoPlanets = createLineBetweenFirstTwoPlanets(day);
			 return lineFirstTwoPlanets.pointAligned(_sun);
		}
		
	}
	
	private Line createLineBetweenFirstTwoPlanets(long day) {
		Planet firstPlanet = getPlanets().get(0);
		Planet secondPlanet = getPlanets().get(1);
		PolarCoord positionFirstPlanet = firstPlanet.positionAtDay(day);
		PolarCoord positionSecondPlanet = secondPlanet.positionAtDay(day);
		Line lineFirstTwoPlanets = new Line (positionFirstPlanet, positionSecondPlanet );
		return lineFirstTwoPlanets;
	}

	
	private List<Planet> getPlanets() {
		return _planets;
	}

	@Override
	public boolean sunInsideTriangleAtDay(long day) {
		if (numberOfplanets()<=2) {
			return false;
		}
		
		Triangle firstThreePlanetsTriangle = new Triangle(getPlanets().get(0).positionAtDay(day), getPlanets().get(1).positionAtDay(day), getPlanets().get(2).positionAtDay(day));
		return firstThreePlanetsTriangle.pointInside(_sun.toCartesianCoord());
	}
}
