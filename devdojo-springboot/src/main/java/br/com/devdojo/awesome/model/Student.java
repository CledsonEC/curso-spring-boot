package br.com.devdojo.awesome.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
	
	private int id;
	private String name;
	
	public static List<Student> studentList;
	
	static {
		studentRepository();
	}
	
	public Student(int id, String name){
		this(name);
		this.id = id;
	}
	
	public Student(String name){
		this.name = name;
	}
	
	public Student(){
		
	}
	
	private static void studentRepository() {
		studentList = new ArrayList<>(Arrays.asList( new Student(1, "Deku"), new Student(2,"Blah")));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result;
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		
		result = this.getId() == ((Student)obj).getId();
		return result;
	}
	
}
