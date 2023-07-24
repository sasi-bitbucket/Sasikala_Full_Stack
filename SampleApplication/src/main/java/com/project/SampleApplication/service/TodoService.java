package com.project.SampleApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SampleApplication.model.TodoObject;
import com.project.SampleApplication.repository.TodoRepository;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	
	@Autowired
	public TodoService(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	public List<TodoObject> getAllTodoObjects() {

		return todoRepository.findAll();

	}

	public List<TodoObject> updateTodoObject(int id, TodoObject todoObj) {
		
		Optional<TodoObject> updateTodo = todoRepository.findById(id);
		
		if(updateTodo.isPresent()) {
			TodoObject todo = updateTodo.get();
			todo.setTodo_id(todoObj.getTodo_id());
			todo.setActivity_name(todoObj.getActivity_name());
			todo.setAssigned_staff(todoObj.getAssigned_staff());
			todoRepository.save(todo);
		}
			return getAllTodoObjects();
		
	}

}
