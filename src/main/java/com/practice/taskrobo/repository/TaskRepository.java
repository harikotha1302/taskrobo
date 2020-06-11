package com.practice.taskrobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.taskrobo.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
