package br.com.devdojo.awesome.endpoint;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.util.DateUtil;

@RestController
@RequestMapping("student")
public class StudentEndpoint {

	@Autowired
	private DateUtil dateUtil;
	
	@RequestMapping(method = RequestMethod.GET, path = "/list")
	public List<Student>listAll(){
		
		System.out.println("------"+dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		
		
		return Arrays.asList(new Student( "Deku"), new Student("Blah"));
	}
}
