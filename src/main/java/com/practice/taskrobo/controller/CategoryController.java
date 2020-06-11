package com.practice.taskrobo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
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
import com.practice.taskrobo.service.TaskService;

/*
 * Annotate the class with @Controller annotation. @Controller annotation is used to mark
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@RestController
@PropertySource("classpath:application.properties")
public class CategoryController {
    
	@Autowired
    private CategoryService categoryService;
	@Autowired
	private TaskService taskService;

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
    public String deleteCategory(@RequestParam("categoryTitle") String categoryTitle) {
    	try {
			categoryService.deleteCategory(categoryTitle);
		} catch (CategoryDoesNotExistException e) {
			//throw new CategoryDoesNotExistException();
			e.printStackTrace();
		}catch(Exception exe) {
			return "redirect:/";
		}
    	return "redirect:/";
    }
    /*
     * Define a handler method to fetch all tasks of particular category. This handler
     * method should map to the URL "/getTasks". If the operation success then
     * redirect to index. Show the errorMessage in case of any exception and return to index.
     */

    @GetMapping("/getTasks")
    public String getAllTasks(@RequestParam("categoryTitle") String categoryTitle, ModelMap model) {
    	List<Task> allTasks = new ArrayList<>();
    	try {
    		allTasks = categoryService.getAllTasks(categoryTitle);
		} catch (CategoryDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	model.addAttribute("task", allTasks );
        return "index";
    }

}
