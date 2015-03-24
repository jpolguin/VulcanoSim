package com.olguin.solarsystem.model;

public class WeatherForecast implements IWeatherForecast {

	private ISolarSystem _solarSystem;
	
	public WeatherForecast(ISolarSystem solarSystem) {
		_solarSystem = solarSystem;
		
	}

	/* (non-Javadoc)
	 * @see com.olguin.vulcanosim.model.IWeatherForecast#forecast(int)
	 */
	@Override
	public WeatherForecastReport forecast(int days) {
		
		WeatherForecastReport report = new WeatherForecastReport(days);
		
		for(int day = 0; day<days; day++) {
			Weather weatherOfDay = getSolarSystem().weatherAtDay(day);
			if (Weather.Rainny.equals(weatherOfDay)) {
				report.addForecast(day, weatherOfDay, getSolarSystem().trianglePerimeterAtDay(day));
			} else {
			    if (!Weather.Regular.equals(weatherOfDay)) {
			         report.addForecast(day, weatherOfDay);
			    }    
			}
		}
		return report;
	}

	private ISolarSystem getSolarSystem() {
		return _solarSystem;
	}
	
}
