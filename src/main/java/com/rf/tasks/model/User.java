package com.rf.tasks.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "taskuser")
public class User implements Serializable {
	
	public User(Long id, String name, Collection<TaskList> taskList) {
		this.id = id;
		this.name = name;
		this.taskList = taskList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected User() {}
	
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private Collection<TaskList> taskList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<TaskList> getTaskList() {
		return taskList;
	}

	public void setTaskList(Collection<TaskList> taskList) {
		this.taskList = taskList;
	}
	
	 public void addTaskList(TaskList taskList) {
	        this.taskList.add(taskList);
	        if (taskList.getUser() != this) {
	            taskList.setUser(this);
	        }
	    }

	
	

}
