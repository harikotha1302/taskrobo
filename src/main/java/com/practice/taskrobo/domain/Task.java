package com.practice.taskrobo.domain;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * The class "Task" will be acting as the data model for the Task Table in the database.
 * Please note that this class is annotated with @Entity annotation.
 */
@Entity
public class Task {

    /* TaskId is annotated with @Id */
	@Id
    private int taskId;
    private String taskTitle;
    private String taskContent;
    private String taskStatus;

    /* Category is annotated with @ManyToOne */
    @ManyToOne
    @JoinColumn(name = "categoryTitle")
    private Category category;


    public Task() {
    }


	public int getTaskId() {
		return taskId;
	}


	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}


	public String getTaskTitle() {
		return taskTitle;
	}


	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}


	public String getTaskContent() {
		return taskContent;
	}


	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}


	public String getTaskStatus() {
		return taskStatus;
	}


	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Task(int taskId, String taskTitle, String taskContent, String taskStatus) {
		super();
		this.taskId = taskId;
		this.taskTitle = taskTitle;
		this.taskContent = taskContent;
		this.taskStatus = taskStatus;
	}

    /* Write parameterized constructor */

    /* Add getter and setter methods for all the properties */


    /* Override toString() method to display the task object */

}
