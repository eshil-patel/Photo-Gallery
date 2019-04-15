package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public void removePhoto(int d){
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
			if (d.getDate().compareTo(maxDate)>0){
				maxDate = d.getDate();
			}
		}
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String min=format.format(minDate);
		String max=format.format(maxDate);
		//dateRange = minDate.toString().concat("+").concat(maxDate.toString());
		dateRange=min.concat("-").concat(max);
		
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
	public ArrayList<Photo> getPhotosByDate(Date start,Date end) throws ParseException{
		ArrayList<Photo> output=new ArrayList<Photo>();
		for (Photo p: Photos) {
			if(p.getDate().compareTo(start)>=0 && p.getDate().compareTo(end)<=0) {
				output.add(p);
			}
		}
		return output;
	}
	public ArrayList<Photo> getPhotosByTag(String t1,String v1){
		ArrayList<Photo> output = new ArrayList<Photo>();
		Tag tag = new Tag(t1,v1);
		for(Photo p:Photos) {
			if(p.getTags().contains(tag)) {
				output.add(p);
			}
		}
		return output;
	}
	public ArrayList<Photo> getPhotosByTag(String t1, String v1, String t2, String v2, String comp){
		ArrayList<Photo> output = new ArrayList<Photo>();
		Tag tag1=new Tag(t1,v1);
		Tag tag2=new Tag(t2,v2);
		for(Photo p:Photos) {
			ArrayList<Tag> tags = p.getTags();
			if(comp.equals("or")) {
				if(tags.contains(tag1) || tags.contains(tag2)) {
					output.add(p);
				}
			}
			else {
				if(tags.contains(tag1) && tags.contains(tag2)) {
					output.add(p);
				}
			}
		}
		return output;
	}
}
