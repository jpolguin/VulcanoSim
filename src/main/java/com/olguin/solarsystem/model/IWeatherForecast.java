package com.olguin.solarsystem.model;

public interface IWeatherForecast {

	public abstract WeatherForecastReport forecast(int days);

}