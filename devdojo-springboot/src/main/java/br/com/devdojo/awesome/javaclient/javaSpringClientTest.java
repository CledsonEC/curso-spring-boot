package br.com.devdojo.awesome.javaclient;

import java.util.List;

import br.com.devdojo.awesome.model.Student;

public class javaSpringClientTest {

	public static void main(String[] args) {
		
		Student studentPost = new Student();
		studentPost.setName("Blah");
		studentPost.setEmail("b@b.com");
		javaClientDAO dao = new javaClientDAO();
		//System.out.println(dao.findById(1));
		List<Student> students = dao.listAll();
		System.out.println(dao.listAll());
		//System.out.println(dao.save(studentPost));
		//studentPost.setId(29L);
		//dao.update(studentPost);
		//dao.delete(29L);
		
	}
	

}
