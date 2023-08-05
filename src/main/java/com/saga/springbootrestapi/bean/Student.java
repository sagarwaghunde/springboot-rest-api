package com.saga.springbootrestapi.bean;

public record Student(int id, String firstName, String lastName) {
	public Student{
		if(id <= 0) {
			throw new RuntimeException("invalid id");
		}
	}
}
