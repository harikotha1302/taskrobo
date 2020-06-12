package com.practice.taskrobo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.practice.taskrobo.domain.Category;
import com.practice.taskrobo.domain.Task;
import com.practice.taskrobo.exception.CategoryAlreadyExistException;
import com.practice.taskrobo.exception.CategoryDoesNotExistException;
import com.practice.taskrobo.repository.CategoryRepository;
import com.practice.taskrobo.repository.TaskRepository;

/*
 * Service classes are used here to implement additional business logic/validation
 * This class has to be annotated with @Service annotation.
 */

@Service
@PropertySource("classpath:application.properties")
public class CategoryService {
	
	@Value("${categoryAlreadyExistException.message}")
	String categoryAlreadyExistException;
	
	@Value("${categoryDoesNotExistException.message}")
	String categoryDoesNotExistException;
	
	@Autowired
    private CategoryRepository categoryDao;
	

	@Autowired
    private TaskRepository taskDao;
    

    public boolean saveCategory(Category category) throws CategoryAlreadyExistException {
    	if(categoryDao.findById(category.getCategoryTitle())!=null) {
    		throw new CategoryAlreadyExistException(categoryAlreadyExistException);
    	}
    	categoryDao.save(category);    	
        return true;
    }

    /*
     * This method should be used to get a category by categoryTitle.
     */

    public Category getCategoryByTitle(String categoryTitle) throws CategoryDoesNotExistException {    	
        return categoryDao.findById(categoryTitle).orElseThrow(()->new CategoryDoesNotExistException(categoryDoesNotExistException));
        }

    /*
     * This method should be used to get all the tasks for particular Category.
     */
    public List<Task> getAllTasks(String categoryTitle) throws CategoryDoesNotExistException {
    	if(categoryDao.findById(categoryTitle).isPresent())
    	{
    		//return taskDao.getAllTaskByCatogery(categoryTitle);
    		return null;
    	}
        throw new CategoryDoesNotExistException(categoryDoesNotExistException);
    }

    /*
     * This method should be used to get all the categories.
     */

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    /* This method should be used to delete an existing category. */

    public boolean deleteCategory(String categoryTitle) throws CategoryDoesNotExistException {
    	if(categoryDao.findById(categoryTitle).isPresent())
    	{
    	categoryDao.deleteById(categoryTitle);
    	}
        throw new CategoryDoesNotExistException(categoryDoesNotExistException);
    }
}
