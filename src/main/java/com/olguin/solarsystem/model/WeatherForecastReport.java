package com.olguin.solarsystem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WeatherForecastReport {

	private Integer _daysOfForecast = -1;
	
    Map<Weather,List<Integer>> _reportTable = new HashMap<>();
 	Integer  _topRainnyDay =  -1;
	double _longestTrianglePerimeter = 0.0;
	

	public WeatherForecastReport(int days) {
		_daysOfForecast = days;
		_reportTable.put(Weather.Draught, new ArrayList<Integer>());
		_reportTable.put(Weather.Rainny, new ArrayList<Integer>());
		_reportTable.put(Weather.Perfect, new ArrayList<Integer>());
		
	}

	public void addForecast(Integer day, Weather weatherOfDay,
			double trianglePerimeterAtDay) {
		
		addForecast(day, weatherOfDay);
		if (trianglePerimeterAtDay> _longestTrianglePerimeter) {
		   setTopRainnyDay(day);
		   setLongestTrianglePerimeter(trianglePerimeterAtDay);
		}
		
	}

	

	public void addForecast(Integer day, Weather weatherOfDay) {
		getReportTable().get(weatherOfDay).add(day);
		
	}

	public int daysOfDraught() {
		return  getReportTable().get(Weather.Draught).size();
	}

	public int daysOfRain() {
		return  getReportTable().get(Weather.Rainny).size();
	}

	public int perfectDays() {
		return  getReportTable().get(Weather.Perfect).size();
	}

	public int  topRainnyDay() {
		return _topRainnyDay;
	}

	public int getDaysOfForecast() {
		return _daysOfForecast;
	}
	
	public int regularDays() {
		
		return getDaysOfForecast() - daysOfDraught() - daysOfRain() - perfectDays();
	}

	private void setLongestTrianglePerimeter(double trianglePerimeterAtDay) {
		_longestTrianglePerimeter = trianglePerimeterAtDay;
		
	}

	private void setTopRainnyDay(Integer day) {
		_topRainnyDay = day;
		
	}

	private Map<Weather, List<Integer>> getReportTable() {
		return _reportTable;
	}



	
}
