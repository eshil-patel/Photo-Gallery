package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Album implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4790223858908565017L;
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
		updateDates();
	}
	public void removePhoto(Photo d){
		Photos.remove(d);
		numPhotos--;
		updateDates();
	}
	private void updateDates(){
		if (Photos.size() == 0){
			minDate = null;
			maxDate = null;
			return;
		}
		if (minDate == null || maxDate == null){
			minDate = Photos.get(0).getDate();
			maxDate = Photos.get(0).getDate();
		}
		for (Photo d:Photos){	
			if (d.getDate().compareTo(minDate)<0){
				minDate = d.getDate();
			}
			if (d.getDate().compareTo(maxDate)>1){
				maxDate = d.getDate();
			}
		}
		
	}
}
