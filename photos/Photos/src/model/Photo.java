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
}
