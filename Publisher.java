package com.ss.lms2.classes;
import java.io.Serializable;

public class Publisher implements Serializable {
	
	private static final long serialVersionUID = -5472002974161780821L;
	
	private int publisherID;
	private String name;
	
	public int getPublisherID() {
		return publisherID;
	}
	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override //hashCode generated based on name
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override //equals generated based on name
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publisher other = (Publisher) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	

}
