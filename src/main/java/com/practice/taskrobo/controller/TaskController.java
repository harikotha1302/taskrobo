package com.practice.taskrobo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.taskrobo.domain.Task;
import com.practice.taskrobo.exception.TaskAlreadyExistException;
import com.practice.taskrobo.exception.TaskDoesNotExistException;
import com.practice.taskrobo.service.TaskService;


/*
 * Annotate the class with @Controller annotation. @Controller annotation is used to mark
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@Controller
@PropertySource("classpath:application.properties")
public class TaskController {
    /*
     * From the problem statement, we can understand that the application requires
     * us to implement the following functionalities.
     *
     * 1. Display the list of existing Tasks from the persistence data. Each task
     * element should contain taskId, taskTitle, taskContent, and taskStatus. 2. Add a new task which 3. Delete an existing task
     *  4.Update an existing task.
     *
     */
    @Autowired
	private TaskService taskService;
    private Environment environment;

    /*
     * Autowiring should be implemented for the TaskService and Environment. (Use Constructor-based
     * autowiring) Please note that we should not create any object using the new
     * keyword.
     */


    /*
     * Define a handler method to add new task. This handler method should map to
     * the URL "/addTask". If the fields are empty, return to index. If the
     * operation success then redirect to "/". Show the errorMessage in case of any
     * exception and redirect to "index".
     */

    @PostMapping("/addTask")
    public String saveTask(@ModelAttribute("task") Task task, ModelMap model) throws TaskAlreadyExistException {
    	taskService.saveTask(task);
    	return "redirect:/";
    }


    /*
     * Define a handler method to delete existing task. This handler method should
     * map to the URL "/deleteTask". If the operation success then redirect to "/".
     * Show the errorMessage in case of any exception and return to "index"
     */

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam("taskId") int taskId, ModelMap modelMap) throws TaskDoesNotExistException {
    	taskService.deleteTask(taskId);
    	return "redirect:/";
    }


    /*
     * Define a handler method to update existing player. This handler method should
     * map to the URL "/updateTask". If the taskStatus and taskContent is empty, return to "index". If
     * the operation success then redirect to "/". Show the errorMessage in case
     * of any exception and return to "index"
     */

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute("task") Task task, ModelMap model) throws TaskDoesNotExistException {
    	taskService.updateTask(task);
    	return "redirect:/";
    }


    /*
     * Define a handler method to get an existing task based on taskId. This
     * handler method should map to the URL "/getTask". If the operation success
     * then redirect to "/". Show the errorMessage in case of any exception and return "index".
     */

    @GetMapping("/getTask")
    public String getTaskById(@RequestParam int taskId, ModelMap model) throws TaskDoesNotExistException {
    	taskService.getTaskById(taskId);
    	return "redirect:/";
    }
}
