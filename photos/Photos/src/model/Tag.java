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
	public boolean equals(Tag d){
		if (this.name.equals(d.name) && this.value.equals(d.value)){
			return true;
		}
		return false;
	}
}
