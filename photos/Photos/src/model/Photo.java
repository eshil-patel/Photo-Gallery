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
	private String path;
	private ArrayList <Tag> Tags;
	private Date date;
	private String Caption;
	public Photo(String path,long longdate){
		this.path = path;
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
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCaption() {
		return Caption;
	}
	public void setCaption(String caption) {
		Caption = caption;
	}
	public void addTags(Tag m){
		Tags.add(m);
	}
}
