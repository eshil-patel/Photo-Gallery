/**
 * @author Imrhankhan Shajahan & Eshil Patel
 */
package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3742889052001853904L;
	private String name;
	ArrayList <Album> Albums;
	public User(String name){
		this.name = name;
		Albums = new ArrayList<Album>();
	}
	public void addAlbum(Album n){
		Albums.add(n);
	}
	public String toString(){
		return name;
	}
	public boolean equals(Object user) {
		if(user==null || !(user instanceof User) ) {
			return false;
		}
		User m= (User) user;
		if(this.name.equals(m.name)) {
			return true;
		}
		return false;
	}
	public ArrayList<Album> getAlbums() {
		return Albums;
	}
	public String getName() {
		return name;
	}
	public void removeAlbum(Album album) {
		Albums.remove(album);
	}
	public void renameAlbum(Album album, String newname) {
		Albums.get(Albums.indexOf(album)).setName(newname);
	}
	public boolean hasAlbum(String name) {
		Album album= new Album(name);
		return Albums.contains(album);
	}
	public Album getAlbum(String name){
		Album n = new Album(name);
		return Albums.get(Albums.indexOf(n));
	}
	public void addPhoto(String albumName, Photo i){
		Album aN = getAlbum(albumName);
		aN.addPhoto(i);
	}
	public ArrayList<Photo> getPhotosByDate(String start,String end) throws ParseException{
		ArrayList<Photo> output=new ArrayList<Photo>();
		Date s=new SimpleDateFormat("MM/dd/yyyy").parse(start);
		Date e=new SimpleDateFormat("MM/dd/yyyy").parse(end);
		for(Album a:Albums) {
			ArrayList<Photo> x=a.getPhotosByDate(s, e);
			output.addAll(x);
		}
		return output;
	}
	public ArrayList<Photo> getPhotosByTag(String t1,String v1){
		ArrayList<Photo> output = new ArrayList<Photo>();
		for(Album a:Albums) {
			ArrayList<Photo> x=a.getPhotosByTag(t1, v1);
			output.addAll(x);
		}
		return output;
	}
	// and overloaded method for conj/disj tag searching 
	public ArrayList<Photo> getPhotosByTag(String t1,String v1, String t2,String v2,String comp){
		ArrayList<Photo> output = new ArrayList<Photo>();
		for(Album a: Albums) {
			ArrayList<Photo> x=a.getPhotosByTag(t1, v1, t2, v2, comp);
			output.addAll(x);
		}
		return output;
	}
}
