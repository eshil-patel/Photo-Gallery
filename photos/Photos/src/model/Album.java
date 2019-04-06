package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Album implements Serializable{
	private String name;
	private int numPhotos;
	ArrayList <Photo> Photos;
	Date minDate;
	Date maxDate;
	public Album(String name) {
		this.name = name;
		Photos = new ArrayList<Photo>();
	}
	public void addPhoto(Photo d){
		Photos.add(d);
		numPhotos++;
		if (minDate == null){
			minDate = d.getDate();
			maxDate = d.getDate();
			return;
		}
		if (d.getDate().compareTo(minDate)<0){
			minDate = d.getDate();
		}
		if (d.getDate().compareTo(minDate)>1){
			maxDate = d.getDate();
		}
	}
}
