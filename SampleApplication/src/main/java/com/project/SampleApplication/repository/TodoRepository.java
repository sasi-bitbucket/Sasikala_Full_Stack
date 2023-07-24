package com.project.SampleApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SampleApplication.model.TodoObject;

@Repository
public interface TodoRepository extends JpaRepository<TodoObject, Integer> {

}
