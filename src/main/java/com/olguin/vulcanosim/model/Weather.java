package com.olguin.vulcanosim.model;

public enum Weather {
 Draught ("Sequia"), 
 Rainny ("Lluvioso"), 
 Perfect ("Condiciones Perfectas de Presion y temperatura"), 
 Regular ("Tiempo Regular");
 
 private String _description;
 Weather (String description) {
	 _description = description;
 }
 
 public String toClima() {
	 return _description;
 }
 
 
}
