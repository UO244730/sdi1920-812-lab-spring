package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;
import com.uniovi.repositories.TeachersRepository;

@Service
public class TeachersService {

	@Autowired
	private TeachersRepository teachersRepository;

	//private List<Teacher> teachersList = new LinkedList<Teacher>();
	
	/*@PostConstruct
	public void init() {
		teachersList.add(new Teacher("7867234F","Iván","Suárez", "Física"));
		teachersList.add(new Teacher("3453543F","Iván","Castiñeiras", "Historia"));
	}*/
	
	
	public List<Teacher> getTeachers() {
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachersRepository.findAll().forEach(teachers::add);
		return teachers;
		//return teachersList;
	}

	public Teacher getTeacher(String dni) {
		return teachersRepository.findById(dni).get();
		//return teachersList.stream().filter(teacher -> teacher.getDni().equals(dni)).findFirst().get();
		
	}

	public void addTeacher(Teacher teacher) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		teachersRepository.save(teacher);
		//teachersList.add(teacher);
	}

	public void deleteTeacher(String dni) {
		teachersRepository.deleteById(dni);
		//teachersList.removeIf(teacher -> teacher.getDni().equals(dni));
	}
}