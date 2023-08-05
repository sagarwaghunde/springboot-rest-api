package com.saga.springbootrestapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.saga.springbootrestapi.bean.Student;

@RestController
@RequestMapping("students")
public class StudentController {

	// HTTP Get Request
	// http://localhost:8080/students/student
	@GetMapping("student")
	public ResponseEntity<Student> getStudent() {
		Student student = new Student(1, "Sagar", "Waghunde");
//		return new ResponseEntity<Student>(student, HttpStatus.OK);
//		return ResponseEntity.ok(student);
		return ResponseEntity.ok().header("custom-header", "test").body(student);
	}

	// HTTP Get Request
	// http://localhost:8080/students
	@GetMapping
	public List<Student> getStudents() {
		return List.of(new Student(1, "Sagar", "Waghunde"), new Student(1, "Bhagyashree", "Waghunde"),
				new Student(1, "Ramhari", "Waghunde"), new Student(1, "Suman", "Waghunde"));
	}

	// Spring Boot rest api with path variable
	// http://localhost:8080/students/88
	// {id} : uri template variable
	@GetMapping("{id}")
	public Student studentPathVariable(@PathVariable("id") int studentId) {
		return new Student(studentId, "Sagar", "Waghunde");
	}

	// Spring Boot rest api with request parameter
	// http://localhost:8080/students/query?firstname=sagar&lastname=waghunde
	@GetMapping("query")
	public Student studentRequestVariable(@RequestParam String firstname, @RequestParam String lastname) {
		return new Student(101, firstname, lastname);
	}

	// Spring Boot rest api that handles HTTP post request
	// Post http://localhost:8080/students/create
	// @PostMapping and @RequestBody
	@PostMapping("create")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		System.out.println(student);
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}

	// Spring Boot rest api that handles HTTP put request
	// Put http://localhost:8080/students/11/update
	// @PutMapping and @RequestBody
	@PutMapping("{id}/update")
	public Student updateStudent(@PathVariable("id") int studentId, @RequestBody Student student) {
		System.out.println("Student updated. id " + studentId);
		System.out.println(student);
		return student;
	}
	
	// Spring Boot rest api that handles HTTP Delete request
	// Delete http://localhost:8080/students/11
	// @DeleteMapping and @RequestBody
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
		return ResponseEntity.ok("deleted successfully :" + studentId);
	}
}
