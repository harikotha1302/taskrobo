package com.practice.taskrobo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.taskrobo.domain.Task;
import com.practice.taskrobo.exception.TaskAlreadyExistException;
import com.practice.taskrobo.exception.TaskDoesNotExistException;
import com.practice.taskrobo.service.TaskService;



@RestController
@PropertySource("classpath:application.properties")
@RequestMapping("/tasks")
public class TaskController {
   
    @Autowired
	private TaskService taskService;

    @PostMapping("/addTask")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<String> saveTask(@ModelAttribute("task") Task task) {
    	try {
			taskService.saveTask(task);
			return new ResponseEntity<String>(task.getTaskTitle(),HttpStatus.OK);
		} catch (TaskAlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FOUND);
		}
    }



    @GetMapping("/deleteTask")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<String> deleteTask(@RequestParam("taskId") int taskId, ModelMap modelMap)  {
    	try {
			taskService.deleteTask(taskId);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (TaskDoesNotExistException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
    }


    @PostMapping("/updateTask")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<String> updateTask(@ModelAttribute("task") Task task, ModelMap model) {
    	try {
			taskService.updateTask(task);
			return new ResponseEntity<String>(task.getTaskTitle(),HttpStatus.OK);
		} catch (TaskDoesNotExistException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
    }


    @GetMapping("/getTask")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public  ResponseEntity<Task> getTaskById(@RequestParam int taskId, ModelMap model)  {
    	try {
			Task task=taskService.getTaskById(taskId);
			return new ResponseEntity<Task>(task,HttpStatus.NOT_FOUND);
		} catch (TaskDoesNotExistException e) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
    }
}
