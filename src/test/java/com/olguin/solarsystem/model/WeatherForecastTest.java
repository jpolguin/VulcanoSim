package com.olguin.solarsystem.model;

import junit.framework.Assert;

import org.junit.Test;

import com.olguin.solarsystem.math.PolarCoord;
import com.olguin.solarsystem.model.ISolarSystem;
import com.olguin.solarsystem.model.IWeatherForecast;
import com.olguin.solarsystem.model.Planet;
import com.olguin.solarsystem.model.SolarSystem;
import com.olguin.solarsystem.model.Weather;
import com.olguin.solarsystem.model.WeatherForecast;
import com.olguin.solarsystem.model.WeatherForecastReport;

public class WeatherForecastTest {

	@Test
	public void testWeatherForecastOneDayOnlyDraught() {
		
        double initialAngularPosition = PolarCoord.PI_2;
		
		Planet planetFerengi = new Planet(new PolarCoord(500, initialAngularPosition),  Planet.ANGULAR_ONE_GRADE_CLOCKWISE);
		Planet planetVulcano = new Planet(new PolarCoord(1000, initialAngularPosition),  Planet.ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE);
		Planet planetBetasoide = new Planet(new PolarCoord(2000, PolarCoord.PI_2), Planet.ANGULAR_THREE_GRADE_CLOCKWISE);
		

		Planet[] planets = new Planet[] {planetFerengi,planetVulcano, planetBetasoide};
		
		ISolarSystem vulcanoSolarSystem = new SolarSystem(planets);
		
		IWeatherForecast vulcanoWeatherForecast = new WeatherForecast(vulcanoSolarSystem );
		
		WeatherForecastReport weatherReport = vulcanoWeatherForecast.forecast(1);
		Assert.assertEquals(1, weatherReport.daysOfDraught());		
		Assert.assertEquals(0, weatherReport.daysOfRain());
		Assert.assertEquals(0, weatherReport.perfectDays());
		Assert.assertEquals(-1,weatherReport.topRainnyDay());
		
	}
	
	@Test
	public void testWeatherIsRainnyWhenSunInsideTheTriangleElseIsRegular() {
			
		
		double initialAngularPosition1 = PolarCoord.PI_4;
		double initialAngularPosition2 = PolarCoord.PI;
		double initialAngularPosition3 = PolarCoord._3PI_2; 
	
		Planet planet1 = new Planet(new PolarCoord(563, initialAngularPosition1), PolarCoord.PI_4);
		Planet planet2 = new Planet(new PolarCoord(6789, initialAngularPosition2),  PolarCoord.PI_180);
		Planet planet3 = new Planet(new PolarCoord(20567, initialAngularPosition3), -PolarCoord.PI_180);
		
		
	
		Planet[] planets = new Planet[] {planet1, planet2, planet3};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertEquals(Weather.Rainny, solarSystem.weatherAtDay(0));
		Assert.assertEquals(Weather.Regular, solarSystem.weatherAtDay(1));
		Assert.assertEquals(Weather.Regular, solarSystem.weatherAtDay(7));
		Assert.assertEquals(Weather.Rainny, solarSystem.weatherAtDay(8));
		
		IWeatherForecast weatherForecast = new WeatherForecast(solarSystem );
		
		WeatherForecastReport weatherReport = weatherForecast.forecast(9);
		Assert.assertEquals(0, weatherReport.daysOfDraught());		
		Assert.assertEquals(2, weatherReport.daysOfRain());
		Assert.assertEquals(0, weatherReport.perfectDays());
		Assert.assertEquals(9 - 2, weatherReport.regularDays());
		Assert.assertEquals(0, weatherReport.topRainnyDay());
		
	}
	
	
	@Test
	public void testWeatherIsPerfectOrDraughInPerfectDraughtSystem() {
		
		
		double initialAngularPosition1 = PolarCoord.PI_4;
		double initialAngularPosition2 = PolarCoord._0PI;
	
	
		Planet planet1 = new Planet(new PolarCoord(563, initialAngularPosition1), PolarCoord.PI_4);
		Planet planet2 = new Planet(new PolarCoord(6789, initialAngularPosition2),  -PolarCoord.PI_2);
	
		

		Planet[] planets = new Planet[] {planet1, planet2};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertEquals(Weather.Perfect, solarSystem.weatherAtDay(0));
		Assert.assertEquals(Weather.Draught, solarSystem.weatherAtDay(1));
		Assert.assertEquals(Weather.Perfect, solarSystem.weatherAtDay(2));
		Assert.assertEquals(Weather.Perfect, solarSystem.weatherAtDay(3));
		Assert.assertEquals(Weather.Perfect, solarSystem.weatherAtDay(4));
		Assert.assertEquals(Weather.Draught, solarSystem.weatherAtDay(5));
		Assert.assertEquals(Weather.Perfect, solarSystem.weatherAtDay(6));
		
		IWeatherForecast weatherForecast = new WeatherForecast(solarSystem);
		
		WeatherForecastReport weatherReport = weatherForecast.forecast(7);
		Assert.assertEquals(2, weatherReport.daysOfDraught());		
		Assert.assertEquals(0, weatherReport.daysOfRain());
		Assert.assertEquals(5, weatherReport.perfectDays());
		Assert.assertEquals(0 , weatherReport.regularDays());
		Assert.assertEquals(-1, weatherReport.topRainnyDay());
		
	}
	
	
	@Test
	public void testWeatherIsAlwaysDraughtWhenPlanetsAndSunAlignedAllTheTime() {
		double initialAngularPosition1 = PolarCoord._0PI;
		double initialAngularPosition2 = PolarCoord.PI;
		double initialAngularPosition3 = PolarCoord.PI; 
	
		Planet planet1 = new Planet(new PolarCoord(563, initialAngularPosition1), PolarCoord.PI_4);
		Planet planet2 = new Planet(new PolarCoord(6789, initialAngularPosition2),  PolarCoord.PI_4);
		Planet planet3 = new Planet(new PolarCoord(20567, initialAngularPosition3), PolarCoord.PI_4);
		

		Planet[] planets = new Planet[] {planet1, planet2, planet3};
		
		ISolarSystem solarSystem = new SolarSystem(planets);
		
		Assert.assertEquals(Weather.Draught, solarSystem.weatherAtDay(0));
		Assert.assertEquals(Weather.Draught, solarSystem.weatherAtDay(19));
		Assert.assertEquals(Weather.Draught, solarSystem.weatherAtDay(245));
		Assert.assertEquals(Weather.Draught, solarSystem.weatherAtDay(330));
		
	    IWeatherForecast weatherForecast = new WeatherForecast(solarSystem);
		
		WeatherForecastReport weatherReport = weatherForecast.forecast(720);
		Assert.assertEquals(720, weatherReport.daysOfDraught());		
		Assert.assertEquals(0, weatherReport.daysOfRain());
		Assert.assertEquals(0, weatherReport.perfectDays());
		Assert.assertEquals(0 , weatherReport.regularDays());
		Assert.assertEquals(-1, weatherReport.topRainnyDay());
			
	}
	
	@Test
	public void testWeatherForecastInVulcanoTenYears() {
			ISolarSystem vulcanoSolarSystem = SolarSystem.createVulcanoSystem();
		
		IWeatherForecast vulcanoWeatherForecast = new WeatherForecast(vulcanoSolarSystem );
		double initialAngularPosition = PolarCoord.PI_2;
		Planet planetVulcano = new Planet(new PolarCoord(1000, initialAngularPosition),  Planet.ANGULAR_FIVE_GRADE_COUNTERCLOCKWISE);
		
		WeatherForecastReport weatherReport = vulcanoWeatherForecast.forecast(planetVulcano.revolutionPeriodInDays()*10);
		Assert.assertEquals(8, weatherReport.daysOfDraught());		
		Assert.assertEquals(234, weatherReport.daysOfRain());
		Assert.assertEquals(478 , weatherReport.regularDays());
		Assert.assertEquals(0, weatherReport.perfectDays());
		Assert.assertEquals(108,weatherReport.topRainnyDay());
		
	}
	
	
	@Test
	public void testWeatherForecastInFerengiTenYears() {
			ISolarSystem vulcanoSolarSystem = SolarSystem.createVulcanoSystem();
		
		IWeatherForecast vulcanoWeatherForecast = new WeatherForecast(vulcanoSolarSystem );
		double initialAngularPosition = PolarCoord.PI_2;
		
		Planet planetFerengi = new Planet(new PolarCoord(500, initialAngularPosition),  Planet.ANGULAR_ONE_GRADE_CLOCKWISE);
		
		WeatherForecastReport weatherReport = vulcanoWeatherForecast.forecast(planetFerengi.revolutionPeriodInDays()*10);
		Assert.assertEquals(40, weatherReport.daysOfDraught());		
		Assert.assertEquals(1178, weatherReport.daysOfRain());
		Assert.assertEquals(2382 , weatherReport.regularDays());
		Assert.assertEquals(0, weatherReport.perfectDays());
		Assert.assertEquals(3168,weatherReport.topRainnyDay());
		
	}
	

}
