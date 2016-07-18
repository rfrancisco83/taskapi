package com.rf.tasks.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rf.tasks.model.Task;
import com.rf.tasks.model.TaskList;
import com.rf.tasks.model.User;
import com.rf.tasks.repository.TaskListRepository;
import com.rf.tasks.repository.TaskRepository;
import com.rf.tasks.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class TaskController {
	
	@Autowired
	private TaskListRepository taskListRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	
//	@RequestMapping("/")
//	public DefaultResponse home(){
//		return new DefaultResponse("Task manager");
//	}
	
	@RequestMapping(value = "/{userId}/taskList", method = RequestMethod.GET)
	public Collection<TaskList> getAllTaskListsForUser(@PathVariable Long userId){
		return taskListRepository.findByUserId(userId);
	}
	
	@RequestMapping(value = "/{userId}/taskList/{taskListId}", method = RequestMethod.GET)
	public TaskList getTaskListForUser(@PathVariable Long userId, @PathVariable Long taskListId){
		return taskListRepository.findOne(taskListId);
	}
	
	@RequestMapping(value = "/{userId}/taskList/{taskListId}/tasks", method = RequestMethod.GET)
	public Collection<Task> getTasksForTaskList(@PathVariable Long userId, @PathVariable Long taskListId){
		return taskRepository.findByTaskListId(taskListId);
	}
	
	@RequestMapping(value = "/{userId}/delete/taskList/{taskListId}", method = RequestMethod.POST)
	public void deleteTaskList(@PathVariable Long userId,  @PathVariable Long taskListId){
		taskListRepository.delete(taskListId);
	}
	
	@RequestMapping(value = "/{userId}/create/taskList", method = RequestMethod.POST)
	public TaskList createTaskList(@PathVariable Long userId){
		User u = userRepository.findOne(userId);
		TaskList taskList = new TaskList(null, u, null);
		taskList = taskListRepository.save(taskList);
		u.addTaskList(taskList);
		userRepository.save(u);
		return taskList;
	}
	
	@RequestMapping(value = "/{userId}/taskList/{taskListId}/remove/task/{taskId}", method = RequestMethod.POST)
	public Collection<Task> removeTaskFromTaskList(@PathVariable Long userId,  @PathVariable Long taskListId,
			@PathVariable Long taskId){
		taskRepository.delete(taskId);
		return taskRepository.findByTaskListId(taskListId);
	}
	
	@RequestMapping(value = "/{userId}/taskList/{taskListId}/create/task/{description}/{completed}", 
			method = RequestMethod.POST)
	public Task addTask(@PathVariable Long userId, @PathVariable Long taskListId, 
			@PathVariable String description, @PathVariable boolean completed){
		Task task = new Task(null, description, completed);
		return taskRepository.save(task);
	}
	
	@RequestMapping(value = "/{userId}/taskList/{taskListId}/update/task/{taskId}/{description}/{completed}", 
			method = RequestMethod.POST)
	public Task updateTask(@PathVariable Long userId, @PathVariable Long taskListId, @PathVariable Long taskId, 
			@PathVariable String description, @PathVariable boolean completed){
		Task task = new Task(taskId, description, completed);
		return taskRepository.save(task);
	}
	
//	@RequestMapping(value = "/add/{name}", method = RequestMethod.POST)
//	public User addUser(@PathVariable String name){
//		User u = new User(null, name, null);
//		return userRepository.save(u);
//	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long userId){
		return userRepository.findOne(userId);
	}

}
