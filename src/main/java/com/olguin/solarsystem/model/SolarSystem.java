package com.olguin.solarsystem.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.olguin.solarsystem.math.Line;
import com.olguin.solarsystem.math.PolarCoord;
import com.olguin.solarsystem.math.Triangle;

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
	public boolean planetsAlignedAt(int day) {
		
		if(numberOfplanets()<=2) {
			return true;
		} else {
            Line lineFirstTwoPlanets = createLineBetweenFirstTwoPlanets(day);
			boolean aligned = true;
			for(IPlanet planet: getPlanets()) {
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
	public boolean solarAligmentAt(int day) {
		
		return planetsAlignedAt(day) && sunAligment(day);
	}

	
	

	@Override
	public boolean sunInsideTriangleAtDay(int day) {
		if (numberOfplanets()<=2) {
			return false;
		}
		
		Triangle firstThreePlanetsTriangle = firstThreePlanetTriangle(day);
		return firstThreePlanetsTriangle.pointInside(_sun.toCartesianCoord());
	}

	private Triangle firstThreePlanetTriangle(int day) {
		return new Triangle(getPlanets().get(0).positionAtDay(day), getPlanets().get(1).positionAtDay(day), getPlanets().get(2).positionAtDay(day));
	}

	@Override
	public Weather weatherAtDay(int day) {
		  if (solarAligmentAt(day)) {
			 return Weather.Draught;
		  } else {
			  if (planetsAlignedAt(day)) {
				  return Weather.Perfect;
			  } else {
				  if (sunInsideTriangleAtDay(day)) {
					  return Weather.Rainny;
				  } 
			  }
		  }
         
		  
		  return Weather.Regular;
		
		
	}
	
	@Override
	public double trianglePerimeterAtDay(int day) {
		Triangle firstThreePlanetsTriangle = firstThreePlanetTriangle(day);
		return firstThreePlanetsTriangle.perimeter();
	}

	
	
	private boolean sunAligment(int day) {
		if(numberOfplanets()<=1) {
			return true;
		} else {
			 Line lineFirstTwoPlanets = createLineBetweenFirstTwoPlanets(day);
			 return lineFirstTwoPlanets.pointAligned(_sun);
		}
		
	}
	
	private Line createLineBetweenFirstTwoPlanets(int day) {
		IPlanet firstPlanet = getPlanets().get(0);
		IPlanet secondPlanet = getPlanets().get(1);
		PolarCoord positionFirstPlanet = firstPlanet.positionAtDay(day);
		PolarCoord positionSecondPlanet = secondPlanet.positionAtDay(day);
		Line lineFirstTwoPlanets = new Line (positionFirstPlanet, positionSecondPlanet );
		return lineFirstTwoPlanets;
	}

	
	private List<Planet> getPlanets() {
		return _planets;
	}

	
	
	
}
