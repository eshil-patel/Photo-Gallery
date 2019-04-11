package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3742889052001853904L;
	String name;
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
	
}
