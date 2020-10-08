package com.example.employee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	Employee employee = new Employee();

	@GetMapping("/employees")
	public Iterable<Employee> allEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public Optional<Employee> findByEmployeeId(@PathVariable long id) {
		return employeeRepository.findById(id);
	}

	@DeleteMapping("/removeEmployee/{id}")
	void deleteEmployee(@PathVariable long id) {
		employeeRepository.deleteById(id);
	}

	@PostMapping("/save")
	public Employee saveStudent(@RequestBody Employee employee) {
		Employee employeeResponse = (Employee) employeeRepository.save(employee);
		return employeeResponse;
	}

	@PostMapping("/update/{id}")
	public Employee updateStudent(@RequestBody Employee employee, @PathVariable long id) {
		Optional<Employee> existingStudent = employeeRepository.findById(id);
		if (existingStudent == null) {
			throw new RuntimeException("Record not found");
		}
		Employee employeeResponse = (Employee) employeeRepository.save(employee);
		return employeeResponse;
	}

}
