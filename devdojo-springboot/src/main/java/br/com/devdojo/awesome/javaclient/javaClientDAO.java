package br.com.devdojo.awesome.javaclient;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.devdojo.awesome.handler.RestResponseExceptionHandler;
import br.com.devdojo.awesome.model.PageableResponse;
import br.com.devdojo.awesome.model.Student;

public class javaClientDAO {
	private RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8081/v1/protected/students")
			.basicAuthentication("cledson", "devdojo")
			.errorHandler(new RestResponseExceptionHandler())
			.build();

	private RestTemplate restTemplateAdmin = new RestTemplateBuilder()
			.rootUri("http://localhost:8081/v1/admin/students")
			.basicAuthentication("cledson", "devdojo")
			.errorHandler(new RestResponseExceptionHandler())
			.build();

	public Student findById(long id) {
		Student student = restTemplate.getForObject("/{id}", Student.class, id);
		// ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}",
		// Student.class,1);
		return student;
	}

    public List<Student> listAll() {
        ResponseEntity<PageableResponse<Student>> exchange = restTemplate.exchange("/", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>() {
                });
        return exchange.getBody().getContent();
    }

	public Student save(Student student) {
		ResponseEntity<Student> exchangePost = restTemplateAdmin.exchange("/", HttpMethod.POST,
				new HttpEntity<>(student, createJsonHeader()), Student.class);
		return exchangePost.getBody();
	}

	public void update(Student student) {
		restTemplateAdmin.put("/", student);
	}

	public void delete(long id) {
		restTemplateAdmin.delete("/{id}", id);
	}

	private HttpHeaders createJsonHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
