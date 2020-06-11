package com.practice.taskrobo.domain;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 * The class "Category" will be acting as the data model for the Category Table in the database.
 * Please note that this class is annotated with @Entity annotation.
 */
@Entity
public class Category {
    /* categoryTitle is annotated with @Id */
	@Id
    private String categoryTitle;

    /* tasks is annotated with @OneToMany */
	@OneToMany(cascade = CascadeType.REMOVE,mappedBy = "category")	
    private List<Task> tasks;

    public Category() {
    }

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Category(String categoryTitle) {
		super();
		this.categoryTitle = categoryTitle;
	}

    /* Write parameterized constructor */

    /* Add getter and setter methods for all the properties */

}
