package model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable{
	private ArrayList<User> UL = new ArrayList<User>();
	public UserList(){
		UL = new ArrayList<User>();
	}
	public void addUser(User m){
		UL.add(m);
	}
}
