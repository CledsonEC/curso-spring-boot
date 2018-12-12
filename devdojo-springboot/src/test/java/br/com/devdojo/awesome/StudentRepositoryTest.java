package br.com.devdojo.awesome;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.repository.StudentRepository;

@RunWith(SpringRunner.class)

//Notação utilizada para executar o test comm banco de dados em memória
@DataJpaTest

//Notação utilizada para executar o test com o banco de dados mysql
//@AutoConfigureTestDatabase(replace = Replace.NONE)

public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studentRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createShouldPersistDate() {
		Student student = new Student("Cledson","cpacheco@cledson.com");
		this.studentRepository.save(student);
		Assertions.assertThat(student.getId()).isNotNull();
		Assertions.assertThat(student.getName()).isEqualTo("Cledson");
		Assertions.assertThat(student.getEmail()).isEqualTo("cpacheco@cledson.com");
	}
	
	@Test
	public void deleteShouldRemoveDate() {
		Student student = new Student("Cledson","cpacheco@cledson.com");
		this.studentRepository.save(student);
		this.studentRepository.delete(student);
		assertThat(studentRepository.findById(student.getId())).isEmpty();
	}
	
	@Test
	public void updateShouldChangePersistDate() {
		Student student = new Student("Cledson","cpacheco@cledson.com");
		this.studentRepository.save(student);
		student.setName("Cledson2111");
		student.setEmail("cledson.pacheco@cp.com");
		this.studentRepository.save(student);
		student = this.studentRepository.findById(student.getId()).get();
		Assertions.assertThat(student.getId()).isNotNull();
		Assertions.assertThat(student.getName()).isEqualTo("Cledson2111");
		Assertions.assertThat(student.getEmail()).isEqualTo("cledson.pacheco@cp.com");
	}
	
	@Test
	public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
		Student student = new Student("Cledson","cpacheco@cledson.com");
		Student student2 = new Student("cledson2","cpacheco2@cledson.com");
		this.studentRepository.save(student);
		this.studentRepository.save(student2);
		List<Student> studentList = this.studentRepository.findByNameIgnoreCaseContaining("cledson");
		assertThat(studentList.size()).isEqualTo(2);
	}
	
	@Test
	public void createWhenNameIsNullShouldThrowVaiolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("O campo nome do estudante é obrigatório");
		this.studentRepository.save(new Student());
	}
	
	@Test
	public void createWhenEmailIsNullShouldThrowVaiolationException() {
		thrown.expect(ConstraintViolationException.class);
		this.studentRepository.save(new Student("Cledson",""));
	}
	
	@Test
	public void createWhenEmailIsNotValidIsNullShouldThrowVaiolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Digite um email válido");
		this.studentRepository.save(new Student("Cledson","blah"));
	}
	
}
