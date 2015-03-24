package com.olguin.solarsystem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.olguin.solarsystem.math.CartesianCoordTest;
import com.olguin.solarsystem.math.LineTest;
import com.olguin.solarsystem.math.PolarCoordTest;
import com.olguin.solarsystem.math.TriangleTest;
import com.olguin.solarsystem.model.PlanetTest;
import com.olguin.solarsystem.model.SolarSistemTest;
import com.olguin.solarsystem.model.WeatherForecastTest;

@RunWith(Suite.class)
@SuiteClasses({ LineTest.class, PlanetTest.class, PolarCoordTest.class,
		SolarSistemTest.class, TriangleTest.class, CartesianCoordTest.class, WeatherForecastTest.class})
public class AllTests {

}
