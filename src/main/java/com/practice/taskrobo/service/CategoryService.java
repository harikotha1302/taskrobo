package com.practice.taskrobo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.practice.taskrobo.domain.Category;
import com.practice.taskrobo.domain.Task;
import com.practice.taskrobo.exception.CategoryAlreadyExistException;
import com.practice.taskrobo.exception.CategoryDoesNotExistException;
import com.practice.taskrobo.repository.CategoryRepository;

/*
 * Service classes are used here to implement additional business logic/validation
 * This class has to be annotated with @Service annotation.
 */

@Service
@PropertySource("classpath:application.properties")
public class CategoryService {
	@Autowired
    private CategoryRepository categoryDao;
    /* Do not hardcode exception message. Get it from application.properties using environment variables. */
	

    /*
     * Constructor based Autowiring should be implemented for the CategoryDao and
     * Environment.Please note that we should not create any object using the new
     * keyword.
     */


    /*
     * This method should be used to save a new category.
     */

    public boolean saveCategory(Category category) throws CategoryAlreadyExistException {
    	categoryDao.save(category);    	
        return true;
    }

    /*
     * This method should be used to get a category by categoryTitle.
     */

    public Category getCategoryByTitle(String categoryTitle) throws CategoryDoesNotExistException {    	
        return categoryDao.findById(categoryTitle).orElseGet(null);
        }

    /*
     * This method should be used to get all the tasks for particular Category.
     */
    public List<Task> getAllTasks(String categoryTitle) throws CategoryDoesNotExistException {
        return null;
    }

    /*
     * This method should be used to get all the categories.
     */

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    /* This method should be used to delete an existing category. */

    public boolean deleteCategory(String categoryTitle) throws CategoryDoesNotExistException {
    	categoryDao.deleteById(categoryTitle);
        return true;
    }
}
