package com.tsofen.noteserver;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note {
	
	private Integer id;
	private String title;
	private String text;
	private Integer priority;
	private boolean hasBeenRead;
	
	public Note() {
		
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	
	@Column
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Column
	public Integer getPriority() {
		return priority;
	}


	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Column
	public boolean getHasBeenRead() {
		return hasBeenRead;
	}

	public void setHasBeenRead(boolean read) {
		this.hasBeenRead = read;
	}
}
