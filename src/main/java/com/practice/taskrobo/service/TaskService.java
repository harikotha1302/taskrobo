package com.practice.taskrobo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.practice.taskrobo.domain.Task;
import com.practice.taskrobo.exception.TaskAlreadyExistException;
import com.practice.taskrobo.exception.TaskDoesNotExistException;
import com.practice.taskrobo.repository.TaskRepository;

/*
 * Service classes are used here to implement additional business logic/validation
 * This class has to be annotated with @Service annotation.
 */
@Service
@PropertySource("classpath:application.properties")
public class TaskService {
	@Autowired
	private TaskRepository taskDAO;


	public TaskService() {
	}

	@Value("${taskDoesNotExistException.message}")
	String taskDoesNotExistException;

	@Value("${taskAlreadyExistException.message}")
	String taskAlreadyExistException;
	/*
	 * Constructor based Autowiring should be implemented for the TaskDao and
	 * Environment.Please note that we should not create any object using the new
	 * keyword.
	 */

	/*
	 * This method should be used to save a new task.
	 */
	public boolean saveTask(Task task) throws TaskAlreadyExistException {
		if (taskDAO.findById(task.getTaskId()).isPresent()) {
			taskDAO.save(task);
			return true;
		}
		throw new TaskAlreadyExistException(taskAlreadyExistException);
	}

	/*
	 * This method should be used to update a existing task.
	 */

	public boolean updateTask(Task task) throws TaskDoesNotExistException {

		if (taskDAO.findById(task.getTaskId()).isPresent()) {
			taskDAO.save(task);
			return true;
		}
		throw new TaskDoesNotExistException(taskDoesNotExistException);
	}
	/*
	 * This method should be used to get a task by taskId.
	 */

	public Task getTaskById(int taskId) throws TaskDoesNotExistException {
		return taskDAO.findById(taskId).orElseThrow(() -> new TaskDoesNotExistException(taskDoesNotExistException));
	}

	/*
	 * This method should be used to get all the tasks.
	 */

	public List<Task> getAllTasks() {
		return taskDAO.findAll();
	}

	/* This method should be used to delete an existing task. */
	public boolean deleteTask(int taskId) throws TaskDoesNotExistException {
		if (taskDAO.findById(taskId).isPresent()) {
			taskDAO.deleteById(taskId);
			return true;
		}
		throw new TaskDoesNotExistException(taskDoesNotExistException);
	}
}
