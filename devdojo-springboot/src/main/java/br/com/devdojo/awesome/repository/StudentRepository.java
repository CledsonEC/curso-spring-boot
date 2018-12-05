package br.com.devdojo.awesome.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.devdojo.awesome.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	List<Student> findByNameIgnoreCaseContaining(String name);
}
