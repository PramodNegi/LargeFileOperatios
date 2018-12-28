package com.csg.dts.event.constants;

public enum Constants {
	
	EOF("END-OF-FILE"),
	NEW_LINE("\n");
	
	private String value;
	
	private Constants(String val) {
		this.value = val;
	}
	
	public String value() {
		return this.value;
	}
}
