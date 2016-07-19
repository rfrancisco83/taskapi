package com.rf.tasks.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task implements Serializable{
	
	protected Task(){}
	
	public Task(Long id, Long taskListId, String description, boolean completed) {
		this.id = id;
		this.taskListId = taskListId;
		this.description = description;
		this.completed = completed;
	}
	@Id
    @GeneratedValue
    private Long id;
	
	@Column( nullable = false)
	private String description;
	
	@Column(nullable = false)
	//defaulting value
	private boolean completed = false;
	
	private Long taskListId;
	
	public Long getTaskListId(){
		return taskListId;
	}
	
	public void setTaskListId(Long taskListId){
		this.taskListId = taskListId;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	

}
