package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Teacher;
import com.uniovi.services.TeachersService;

@Controller
public class TeacherController {

	@Autowired // inyectar el servicio
	private TeachersService teachersService;

	@RequestMapping("/teacher/list")
	public String getList() {
		//model.addAttribute("teacherList", teachersService.getTeachers());
		return teachersService.getTeachers().toString();
		//return "teacher/list";
	}
	
	
	@RequestMapping(value = "/teacher/add")
	public String getTeacher() {
		return "teacher/add";
	}

	@RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
	public String setTeacher(@ModelAttribute Teacher teacher) {
		teachersService.addTeacher(teacher);
		return "redirect:/teacher/list";
	}

	@RequestMapping(value = "/teacher/edit/{dni}")
	public String getEdit(Model model, @PathVariable String dni) {
		model.addAttribute("teacher", teachersService.getTeacher(dni));
		return "teacher/edit";
	}

	

	@RequestMapping(value = "/teacher/edit/{dni}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable String dni, @ModelAttribute Teacher teacher) {
		teacher.setDni(dni);
		teachersService.addTeacher(teacher);
		return "redirect:/teacher/details/" + dni;
	}
	
	
	
	

	@RequestMapping("/teacher/details/{dni}")
	public String detailsTeacher(Model model, @PathVariable String dni) {
		model.addAttribute("teacher", teachersService.getTeacher(dni));
		return "teacher/details";
	}
	

	@RequestMapping("/teacher/delete/{dni}")
	public String deleteTeacher(@PathVariable String dni) {
		teachersService.deleteTeacher(dni);
		return "redirect:/teacher/list";
	}
	
	
	
	
}
