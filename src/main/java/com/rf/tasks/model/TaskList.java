package com.rf.tasks.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TaskList implements Serializable{
	
	public Collection<Task> getTask() {
		return task;
	}

	public void setTask(Collection<Task> task) {
		this.task = task;
	}

	protected TaskList(){}


	public TaskList(Long id, User user, Collection<Task> task) {
		this.id = id;
		this.user = user;
		this.task = task;
	}


	@Id
    @GeneratedValue
	private Long id;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private Collection<Task> task;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
