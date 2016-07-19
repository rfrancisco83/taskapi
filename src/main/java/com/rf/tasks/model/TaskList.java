package com.rf.tasks.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TaskList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected TaskList(){}


	public TaskList(Long id, Long userId, Set<Task> task) {
		this.id = id;
		this.userId = userId;
		this.tasks = task;
	}

	@Id
    @GeneratedValue
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Task> tasks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	private Long userId;
	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTask(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	 public void addTask(Task task) {
        this.tasks.add(task);
    }
	

}
