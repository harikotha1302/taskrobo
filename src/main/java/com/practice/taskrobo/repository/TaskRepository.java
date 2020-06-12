package com.practice.taskrobo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practice.taskrobo.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query("FROM  Task WHERE category_title=?1")
	public List<Task> getAllTaskByCatogery(String catogeryTitle);

}
