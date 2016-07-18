package com.rf.tasks.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class TaskList implements Serializable{

	protected TaskList(){}


	public TaskList(Long id, User user, Collection<Task> task) {
		this.id = id;
		this.user = user;
		this.tasks = task;
	}


	@Id
    @GeneratedValue
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy = "taskList")
	@JsonManagedReference
	private Collection<Task> tasks;

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
		if (!user.getTaskList().contains(this)) {
			user.getTaskList().add(this);
        }
	}
	
	public Collection<Task> getTasks() {
		return tasks;
	}

	public void setTask(Collection<Task> tasks) {
		this.tasks = tasks;
	}
	
	 public void addTask(Task task) {
	        this.tasks.add(task);
	        if (task.getTaskList() != this) {
	            task.setTaskList(this);
	        }
	    }
	

}
