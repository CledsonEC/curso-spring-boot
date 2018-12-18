package br.com.devdojo.awesome.endpoint;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.awesome.error.ResourceNotFoundException;
import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.repository.StudentRepository;

@RestController
@RequestMapping("v1")
public class StudentEndpoint {

	private final StudentRepository studentDAO;

	@Autowired
	public StudentEndpoint(StudentRepository studentDAOl) {
		this.studentDAO = studentDAOl;
	}

	@GetMapping(path = "protected/students")
	public ResponseEntity<?> listAll(Pageable pageable) {
		return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(path = "protected/students/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") long id,
				Authentication authentication) {
		verifyIfStudentExists(id);
		System.out.println(authentication);
		Optional<Student> student = studentDAO.findById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping(path = "protected/students/findByName/{name}")
	public ResponseEntity<?> findStudentByName(@PathVariable String name) {
		return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
	}

	@PostMapping (path = "admin/students")
	@Transactional //Caso tenha alguma excessão não será feiro o commit
	public ResponseEntity<?> save(@Valid @RequestBody Student student) {
		return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "admin/students/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable long id) {
		verifyIfStudentExists(id);
		studentDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping (path = "admin/students")
	public ResponseEntity<?> update(@RequestBody Student student) {
		verifyIfStudentExists(student.getId());
		studentDAO.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void verifyIfStudentExists(Long id) {
		if (!studentDAO.findById(id).isPresent())
			throw new ResourceNotFoundException("Student not found for ID: "+ id);
	}
}
