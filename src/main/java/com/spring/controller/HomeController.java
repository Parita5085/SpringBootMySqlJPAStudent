package com.spring.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.exceptions.StudentNotFoundException;
import com.spring.model.Student;
import com.spring.repo.StudentRepository;

@RestController
public class HomeController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	@PostMapping("/save")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
//		return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteStudentById(@PathVariable("id") int id) {
		studentRepository.deleteById(id);
	}
	
	@GetMapping("/student/{id}")
	public Student findStudentById(@PathVariable("id") int id) {
		
		Optional<Student> studentById = studentRepository.findById(id);
		
		if(!studentById.isPresent()) {
			throw new StudentNotFoundException("id-" + id);
		}
		return studentById.get();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int id) {
		
		Optional<Student> findStudent = studentRepository.findById(id);
		if(!findStudent.isPresent()) {
//			throw new StudentNotFoundException("id-" + id);
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		student.setId(id);
		studentRepository.save(student);
		return new ResponseEntity<Student>(HttpStatus.OK);
		
	}
	
//	@PutMapping("/students/{id}")
//	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {
//		Optional<Student> studentOptional = studentRepository.findById(id);
//		if (!studentOptional.isPresent())
//			return ResponseEntity.notFound().build();
//		student.setId(id);
//		studentRepository.save(student);
//		return ResponseEntity.noContent().build();
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
