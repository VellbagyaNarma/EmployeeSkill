package com.niit.hrfrontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.hrbackend.model.Employee;
import com.niit.hrbackend.model.Skills;
import com.niit.hrbackend.service.EmployeeService;
import com.niit.hrbackend.service.SkillsService;

@RestController
@RequestMapping("/api/emp")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SkillsService skillsService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping
	public List<Employee> listAllEmployee() {
		List<Employee> employee = employeeService.getAllEmployees();
		return employee;
	}

	@GetMapping("/getAllSkills/")
	public List<Skills> getAllSkills() {
		List<Skills> skillsList = skillsService.getAllSkilsOfAllEmployees(); 
		return skillsList;
	}
	
	@GetMapping("/getEmployee/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") int employeeId) {
		if (employeeService.getEmployeeById(employeeId)  != null) {
			return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getSkill/{employeeId}")
	public ResponseEntity<Skills> getSkillById(@PathVariable("employeeId") int employeeId) {
		
		if(skillsService.getSkillsByEmployeeId(employeeId) !=null)
		{
			return new ResponseEntity<Skills>(skillsService.getSkillsByEmployeeId(employeeId), HttpStatus.OK);
		}else {
			return new ResponseEntity<Skills>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping(path ={"/del/{employeeId}"})
	public ResponseEntity<Void> deleteEmployeeById(@PathVariable("employeeId") int employeeId) {
		
			employeeService.deleteEmployee(employeeId);
			return new ResponseEntity<Void>(HttpStatus.OK);

		
	}
	
	@DeleteMapping(path ={"/delSkill/{skillId}"})
	public ResponseEntity<Void> deleteSkillsById(@PathVariable("skillId") int skillId) {
			skillsService.deleteSkillsOfEmployee(skillId); 
			return new ResponseEntity<Void>(HttpStatus.OK);

			
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
		
			employeeService.saveOrUpdateEmployee(employee); 
			return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	
	
	@PostMapping("/addSkills")
	public ResponseEntity<Void> addSkills(@RequestBody Skills skills) {
			System.out.println("Post Mapping received");
			skillsService.saveOrUpdateSkills(skills);
				System.out.println("From: DAO => ADD SKILLS METHOD INVOKED AND SUCESSFULLY INSERTE SKILLS IN DB");
			return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
		
		if(employeeService.getEmployeeById(employee.getEmployeeId())!=null) {
			employeeService.saveOrUpdateEmployee(employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateSkills")
	public ResponseEntity<Void> updateSkills(@RequestBody Skills skills) {
		
		skillsService.saveOrUpdateSkills(skills);
		System.out.println("From : UPDATE SKILLS DAO => Successfully updated Skills details :) ");
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
}
