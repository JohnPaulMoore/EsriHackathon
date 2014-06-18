package com.esri.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Marker {

	private int markerId;
	private String latitude;
	private String longitude;
	private String title;
	private String mainText;
	
	
	
	public int getMarkerId() {
		return markerId;
	}
	public void setMarkerId(int markerId) {
		this.markerId = markerId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMainText() {
		return mainText;
	}
	public void setMainText(String mainText) {
		this.mainText = mainText;
	}



}