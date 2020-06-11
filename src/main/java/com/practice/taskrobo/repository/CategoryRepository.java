package com.practice.taskrobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.taskrobo.domain.Category;


public interface CategoryRepository extends JpaRepository<Category, String> {

}
