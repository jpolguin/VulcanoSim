package com.olguin.vulcano;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.olguin.vulcano.math.CartesianCoordTest;
import com.olguin.vulcano.math.LineTest;
import com.olguin.vulcano.math.PolarCoordTest;
import com.olguin.vulcano.math.TriangleTest;
import com.olguin.vulcanosim.model.PlanetTest;
import com.olguin.vulcanosim.model.SolarSistemTest;

@RunWith(Suite.class)
@SuiteClasses({ LineTest.class, PlanetTest.class, PolarCoordTest.class,
		SolarSistemTest.class, TriangleTest.class, CartesianCoordTest.class})
public class AllTests {

}
