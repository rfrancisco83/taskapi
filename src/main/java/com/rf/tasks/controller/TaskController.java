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

	// @RequestMapping("/")
	// public DefaultResponse home(){
	// return new DefaultResponse("Task manager");
	// }

	@RequestMapping(value = "/{userId}/taskList", method = RequestMethod.GET)
	public Collection<TaskList> getAllTaskListsForUser(@PathVariable Long userId) {
		return taskListRepository.findByUserId(userId);
	}

	@RequestMapping(value = "/{userId}/taskList/{taskListId}", method = RequestMethod.GET)
	public TaskList getTaskListForUser(@PathVariable Long userId,
			@PathVariable Long taskListId) {
		return taskListRepository.findByIdAndUserId(taskListId, userId);
	}

	@RequestMapping(value = "/{userId}/taskList/{taskListId}/tasks", method = RequestMethod.GET)
	public Collection<Task> getTasksForTaskList(@PathVariable Long userId,
			@PathVariable Long taskListId) {
		return taskRepository.findByTaskListId(taskListId);
	}

	@RequestMapping(value = "/{userId}/delete/taskList/{taskListId}", method = RequestMethod.POST)
	public void deleteTaskList(@PathVariable Long userId,
			@PathVariable Long taskListId) {
		
		//get the task list
		TaskList taskList = taskListRepository.findOne(taskListId);
		User u = userRepository.findOne(userId);
		
		//remove the task from the set.
		taskList.getTasks().clear();
		taskList = taskListRepository.save(taskList);
		
		//now remove the association to user
		u.getTaskList().remove(taskList);
		userRepository.save(u);
		
//		Collection<Task> tasks = taskRepository.findByTaskListId(taskListId);
//		taskRepository.delete(tasks);
		//now able to delete the list
		taskListRepository.delete(taskList);
	}

	@RequestMapping(value = "/{userId}/create/taskList", method = RequestMethod.POST)
	public TaskList createTaskList(@PathVariable Long userId) {
		User u = userRepository.findOne(userId);
		TaskList taskList = new TaskList(null, userId, null);
		taskList = taskListRepository.save(taskList);
		u.addTaskList(taskList);
		userRepository.save(u);
		return taskList;
	}

	@RequestMapping(value = "/{userId}/taskList/{taskListId}/delete/task/{taskId}", method = RequestMethod.POST)
	public Collection<Task> removeTaskFromTaskList(@PathVariable Long userId,
			@PathVariable Long taskListId, @PathVariable Long taskId) {
		//get the task list and task
		TaskList taskList = taskListRepository.findOne(taskListId);
		Task task = taskRepository.findOne(taskId);
		
		//remove the task from the set.
		taskList.getTasks().remove(task);
		taskListRepository.save(taskList);
		
		//remove the tasks and return a new task list
		taskRepository.delete(task);
		return taskRepository.findByTaskListId(taskListId);
	}

	@RequestMapping(value = "/{userId}/taskList/{taskListId}/create/task/{description}/{completed}", method = RequestMethod.POST)
	public Task addTask(@PathVariable Long userId,
			@PathVariable Long taskListId, @PathVariable String description,
			@PathVariable boolean completed) {
		TaskList taskList = taskListRepository.findOne(taskListId);
		Task task = new Task(null, taskListId, description, completed);
		task = taskRepository.save(task);

		taskList.addTask(task);
		taskListRepository.save(taskList);
		return task;
	}

	@RequestMapping(value = "/{userId}/taskList/{taskListId}/update/task/{taskId}/{description}/{completed}", method = RequestMethod.POST)
	public Task updateTask(@PathVariable Long userId,
			@PathVariable Long taskListId, @PathVariable Long taskId,
			@PathVariable String description, @PathVariable boolean completed) {
		Task task = new Task(taskId, taskListId, description, completed);
		return taskRepository.save(task);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long userId) {
		return userRepository.findOne(userId);
	}

}
