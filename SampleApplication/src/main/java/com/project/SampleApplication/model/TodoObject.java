package com.project.SampleApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_TODO")
public class TodoObject {

	@Id
	@GeneratedValue
	@Column(name = "todo_id")
	private int todo_id;

	@Column(name = "activity_name")
	private String activity_name;

	@Column(name = "assigned_staff")
	private String assigned_staff;

	public TodoObject() {

	}

	public TodoObject(int todo_id, String activity_name, String assigned_staff) {
		this.todo_id = todo_id;
		this.activity_name = activity_name;
		this.assigned_staff = assigned_staff;
	}

	public int getTodo_id() {
		return todo_id;
	}

	public void setTodo_id(int todo_id) {
		this.todo_id = todo_id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getAssigned_staff() {
		return assigned_staff;
	}

	public void setAssigned_staff(String assigned_staff) {
		this.assigned_staff = assigned_staff;
	}

}
