package com.ss.lms2.classes;
import java.io.Serializable;

public class Book implements Serializable {
	
	
	private static final long serialVersionUID = 692378881295856541L;
	
	private int bookID;
	private String name;
	private int publisherID;
	private int authorID;
	
	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	@Override // hashCode generated based on authorID, name, and publisherID
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + publisherID;
		return result;
	}

	@Override // equals based on authorID, name, and publisherID
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authorID != other.authorID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (publisherID != other.publisherID)
			return false;
		return true;
	}


	

}
