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
	private String dateRange;
	
	ArrayList <Photo> Photos;
	Date minDate;
	Date maxDate;
	public Album(String name) {
		this.name = name;
		Photos = new ArrayList<Photo>();
		numPhotos=0;
		dateRange = "";
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
			dateRange="";
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
		dateRange = minDate.toString().concat("+").concat(maxDate.toString());
		
	}
	public String getName() {
		return name;
	}
	public int getNumPhotos() {
		return numPhotos;
	}
	public String getDateRange() {
		return dateRange;
	}
	public void setName(String name) {
		this.name=name;
	}
	public boolean equals(Object a) {
		if(a==null ||!(a instanceof Album)) {
			return false;
		}
		Album album= (Album) a;
		return album.getName().equals(this.name);
	}
	public ArrayList<Photo> getPhotos() {
		return Photos;
	}
}
