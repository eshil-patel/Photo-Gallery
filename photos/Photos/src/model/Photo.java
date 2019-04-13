/**
 * 
 */
package model;

import java.io.Serializable;
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
	public Photo(String path,long longdate){
		this.path = path;
		this.date= new Date(longdate);
		Tags = new ArrayList<Tag>();
		Caption = null;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getDate() {
		return date;
	}
	public String getCaption() {
		return Caption;
	}
	public void setCaption(String caption) {
		Caption = caption;
	}
	public void addTags(Tag m){
		if (Tags.contains(m)){
			return;
		}
		Tags.add(m);
	}
	public void removeTags(Tag m){
		if (!(Tags.contains(m))){
			return;
		}
		Tags.remove(m);
	}
	
	public ArrayList<Tag> getTags() {
		return Tags;
	}
	public boolean hasTag(Tag m){
		if (Tags.contains(m)){
			return true;
		}
		return false;
	}
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
	public void setDate(String text) {
		// TODO Auto-generated method stub
		date = new Date(text);
	}
}
