package model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable{
	/**Constructor for Userlist 
	 * 
	 */
	private static final long serialVersionUID = 2546262621080163608L;
	private ArrayList<User> UL = new ArrayList<User>();
	public UserList(){
		UL = new ArrayList<User>();
	}
	/** Add user into the user list 
	 * @param m
	 */
	public void addUser(User m){
		UL.add(m);
	}
	/* (non-Javadoc)
	 * custom toString method
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return UL.toString();
	}
	/**get list of users in data file 
	 * @return
	 */
	public ArrayList<User> getList() {
		return UL;
	}
	/**check if the userlist contains this user 
	 * @param name
	 * @return
	 */
	public boolean hasUser(String name) {
		User t = new User(name);
		if (UL.indexOf(t)>-1) {
			return true;
		}
		return false;
	}
	/**get user from the list of users 
	 * @param name
	 * @return
	 */
	public User getUser(String name) {
		User t= new User(name);
		return UL.get(UL.indexOf(t));
	}
	/**delete the user from the userlist 
	 * @param name
	 */
	public void deleteUser(String name) {
		UL.remove(new User(name));
	}
	/**alternate delete user from the userlist 
	 * @param n
	 */
	public void deleteUser(User n) {
		UL.remove(n);
	}
	
}
