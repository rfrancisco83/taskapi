package com.rf.tasks.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.rf.tasks.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

	Collection<Task> findByTaskListId(Long taskListId);
}
