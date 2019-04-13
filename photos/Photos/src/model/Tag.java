/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author Imrhankhan Shajahan & Eshil Patel
 *
 */
public class Tag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2158173759556195914L;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String value;
	
	/**
	 * @param name
	 * @param value
	 */
	public Tag(String name, String value){
		this.name = name;
		this.value = value;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean equals(Object a) {		
		if (a.getClass() != this.getClass()){
			return false;
		}
		Tag b = (Tag)a;
		if (this.name.equals(b.name) && this.value.equals(b.value)){
			return true;
		}
		return false;
	}
	public String toString(){
		return ("(" + this.name+ ", " + this.value+")");
	}
}
