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
	
}
