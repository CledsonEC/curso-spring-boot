package br.com.devdojo.awesome.endpoint;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.awesome.error.CustomErrorType;
import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.util.DateUtil;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

	@Autowired
	private DateUtil dateUtil;
	
	public StudentEndpoint(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(Student.studentList,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path ="/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") int id){
		Student student = new Student();
		student.setId(id);
		int index = Student.studentList.indexOf(student);
		
		if(index == -1)
			return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);		
		return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Student student){
		Student.studentList.add(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	
}
