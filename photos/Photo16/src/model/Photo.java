/**
 * 
 */
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Imrhankhan Shajahan & Eshil Patel
 *
 */
public class Photo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5506513628209394044L;
	private String path;
	private ArrayList <Tag> Tags;
	private Date date;
	private String Caption;
	/**Constructor for photo
	 * @param path filepath of photo
	 * @param longdate date in long millisecond form 
	 */
	public Photo(String path,long longdate){
		this.path = path;
		this.date= new Date(longdate);
		Tags = new ArrayList<Tag>();
		Caption = null;
	}
	/**Alternate constructor for photo
	 * @param path filepath of the photo
	 * @param date date of photo (data)
	 */
	public Photo(String path, Date date){
		this.path = path;
		this.date= date;
		Tags = new ArrayList<Tag>();
		Caption = null;
	}
	/**Returns the filepath where the image is located 
	 * @return
	 */
	public String getPath() {
		return path;
	}
	/**Set the filepath of the image 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**Returns the date of photo
	 * @return
	 */
	public Date getDate() {
		return date;
	}
	/**Gets the caption of the photo
	 * @return
	 */
	public String getCaption() {
		return Caption;
	}
	/**Set the caption for the photo
	 * @param caption-caption of the photo
	 */
	public void setCaption(String caption) {
		Caption = caption;
	}
	/**Add tag to the photo 
	 * @param m tag to add 
	 */
	public void addTags(Tag m){
		if (Tags.contains(m)){
			return;
		}
		Tags.add(m);
	}
	/**Remove tag from the photo
	 * @param m tag to remove 
	 */
	public void removeTags(Tag m){
		if (!(Tags.contains(m))){
			return;
		}
		Tags.remove(m);
	}
	
	/**Get the list of tags from the photo
	 * @return
	 */
	public ArrayList<Tag> getTags() {
		return Tags;
	}
	/**Check if the photo has specific tag 
	 * @param m tag to check if it has 
	 * @return
	 */
	public boolean hasTag(Tag m){
		if (Tags.contains(m)){
			return true;
		}
		return false;
	}
	/* (non-Javadoc)
	 * equals method customized 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object a) {		
		if (a.getClass() != this.getClass()){
			return false;
		}
		Photo b = (Photo)a;
		if(b.path.equals(this.path)){
			return true;
		}
		return false;
	}
	/**set date of the photo
	 * @param text date of photo
	 */
	public void setDate(String text) {
		// TODO Auto-generated method stub
		date = new Date(text);
	}
	/* (non-Javadoc)
	 * toString method customized 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getPath();
	}
	public boolean hasLocation(){
		for (Tag i: Tags){
			if (i.getName().equals("Location") || i.getName().equals("location")){
				return true;
			}
		}
		return false;
	}
}
