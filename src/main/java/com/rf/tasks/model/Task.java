package com.rf.tasks.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task implements Serializable{
	
	protected Task(){}
	
	public Task(Long id, String description, boolean completed) {
		this.id = id;
		this.description = description;
		this.completed = completed;
	}
	@Id
    @GeneratedValue
//    @Column(name="ID")
    private Long id;
	
	@Column( nullable = false)
	private String description;
	
	@Column(nullable = false)
	//defaulting value
	private boolean completed = false;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TaskList taskList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TaskList getTaskList() {
		return taskList;
	}

	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
		if (!taskList.getTasks().contains(this)) { 
			taskList.getTasks().add(this);
        }
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
