package com.rf.tasks.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.rf.tasks.model.TaskList;

public interface TaskListRepository extends CrudRepository<TaskList, Long> {
	
	Collection<TaskList> findByUserId(Long id);
	
//	TaskList findByTaskListId(Long id);

}
