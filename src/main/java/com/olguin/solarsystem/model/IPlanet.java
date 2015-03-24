package com.olguin.solarsystem.model;

import com.olguin.solarsystem.math.PolarCoord;

public interface IPlanet {

	public abstract PolarCoord getInitialPosition();

	public abstract double getAngularVelocity();

	public abstract PolarCoord positionAtDay(int day);

	public abstract int revolutionPeriodInDays();

}