package com.practice.taskrobo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.taskrobo.domain.Category;
import com.practice.taskrobo.domain.Task;
import com.practice.taskrobo.exception.CategoryAlreadyExistException;
import com.practice.taskrobo.exception.CategoryDoesNotExistException;
import com.practice.taskrobo.service.CategoryService;

/*
 * Annotate the class with @Controller annotation. @Controller annotation is used to mark
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@RestController
@PropertySource("classpath:application.properties")
public class CategoryController {
    /*
     * From the problem statement, we can understand that the application requires
     * us to implement the following functionalities.
     *
     * 1. display the list of existing Categories from the persistence data. Each Category
     * element should contain CategoryTitle 2. Add a new category which should contain the Title
     * 3.Delete an existing Category 4.Get All Tasks associated with particular category
     *
     */
	@Autowired
    private CategoryService categoryService;

    /*
     * Constructor based Autowiring should be implemented for the CategoryService and
     * Environment.Please note that we should not create any object using the new
     * keyword.
     */


    /*
     * Define a handler method to fetch all categories. Also add category and task
     * instances to model attribute. This handler method should map to the URL "/".
     * If the operation success then return to index.
     */
    @GetMapping(value="/")
    public List<Category> getCategory() {
    	return categoryService.getAllCategories();
    }

    /*
     * Define a handler method to add new category. This handler method should map to
     * the URL "/addCategory". If the title is empty, return to index. If the
     * operation success then redirect to "/".Show the errorMessage in case of any
     * exception and return to "index"
     */
   
    @PostMapping(value = "/addCategory")
    public ResponseEntity<String> saveCategory(@RequestBody Category category) {
    	try {
			categoryService.saveCategory(category);
			return new ResponseEntity<String>(category.getCategoryTitle(), HttpStatus.OK);
		} catch (CategoryAlreadyExistException e) {
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    	
	}

    /*
     * Define a handler method to delete existing category. This handler method should
     * map to the URL "/deleteCategory". If the operation success then redirect to "/".
     * Show the errorMessage in case of any exception and return to index
     */

    @GetMapping("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestParam("categoryTitle") String categoryTitle) {
    	try {
			categoryService.deleteCategory(categoryTitle);
			return new ResponseEntity<String>(categoryTitle, HttpStatus.OK);
		} catch (CategoryDoesNotExistException e) {
			return new ResponseEntity<String>( e.getMessage(),HttpStatus.NOT_FOUND);
		}
    }
    /*
     * Define a handler method to fetch all tasks of particular category. This handler
     * method should map to the URL "/getTasks". If the operation success then
     * redirect to index. Show the errorMessage in case of any exception and return to index.
     */

    @GetMapping("/getTasks")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam("categoryTitle") String categoryTitle) {
    	try {
			List<Task> task=categoryService.getAllTasks(categoryTitle);
			return new ResponseEntity<List<Task>>(task,HttpStatus.OK);
		} catch (CategoryDoesNotExistException e) {
			return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
		}
    }
}
