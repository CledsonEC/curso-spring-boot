package br.com.devdojo.awesome.javaclient;

import java.util.List;

import javax.sound.midi.Soundbank;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.devdojo.awesome.model.Student;

public class javaSpringClientTest {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("http://localhost:8081/v1/protected/students")
				.basicAuthentication("cledson", "devdojo")
				.build();
		
		Student student = restTemplate.getForObject("/{id}", Student.class,1);
		ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class,1);
		System.out.println(student);
		System.out.println(forEntity);
		System.out.println(forEntity.getBody());
		
		Student[] students = restTemplate.getForObject("/", Student[].class,1);
		System.out.println(students.toString());
		
		
		ResponseEntity<List<Student>> exchange = restTemplate.exchange("/", HttpMethod.GET,null,
				new ParameterizedTypeReference<List<Student>>(){ 
		});
		
		System.out.println(exchange.getBody());
	}
}
