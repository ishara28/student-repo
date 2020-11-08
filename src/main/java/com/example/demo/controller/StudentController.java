package com.example.demo.controller;

import com.example.demo.dao.StudentRepo;
import com.example.demo.model.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Student;

@Controller
public class StudentController {
	
	@Autowired
	StudentRepo repo;

	@RequestMapping("/")
	public String student() {
		return "home.jsp";
	}
	
//	@RequestMapping("/addstudent")
//	public String addStuden(Student student) {
//		repo.save(student);
//		return "home.jsp";
//	}
	
//	@RequestMapping("/getstudent")
//	public ModelAndView addStuden(@RequestParam int sid) {
//		
//		ModelAndView mv = new ModelAndView("showstudent.jsp");
//		Student student = repo.findById(sid).orElse(new Student());
//		mv.addObject(student);
//		System.out.println(repo.findByname("Ishara"));
//		return mv;
//	}
	
	//Get all students
	@RequestMapping("/students")
	@ResponseBody
	public List<Student> getStudents() {
		
		return repo.findAll();
	}
	
	//Get particluar student
	@RequestMapping("/students/{sid}")
	@ResponseBody
	public Optional<Student> getStudents(@PathVariable("sid") int sid) {
		
		return repo.findById(sid);
	}
	
	//Post
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		repo.save(student);
		return student;
	}
	
	//delete student
	@DeleteMapping("students/{sid}")
	public String deleteStudent(@PathVariable int sid) {
			Student student = repo.getOne(sid);
			repo.delete(student);
			return "Deleted";
	}
	
	//Update
	@PutMapping("students/{sid}")	
	public Student updateStudent(@RequestBody Student student) {
		repo.save(student);
		return student;
	}
	
	
	
}
