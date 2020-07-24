package com.vepilef.springboothibernate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vepilef.springboothibernate.dao.StudentDAO;
import com.vepilef.springboothibernate.model.Student;



@Controller
public class PessoaEnrollmentController {
	
	@Autowired
	private StudentDAO studentDao;
	
	@RequestMapping(value="/enroll",method=RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		Pessoa pessoa = new Pessoa();
		model.addAttribute("pessoa",pessoa);
		return "enroll";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveRegistration(@Valid Student student,BindingResult result,ModelMap model,RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			System.out.println("has errors");
			return "enroll";
		}
	
		pessoaDao.save(pessoa);
		
		return "redirect:/viewstudents";
	}
	
	
	@RequestMapping(value="/viewpessoa")
	public ModelAndView getAll() {
		
		List<Pessoa> list=pessoaDao.findAll();
		return new ModelAndView("viewpessoa","list",list);
	}
	
	
	@RequestMapping(value="/editpessoa/{id}")
	public String edit (@PathVariable int id,ModelMap model) {
		
		Pessoa student=PessoaDao.findOne(id);
		model.addAttribute("pessoa",student);
		return "editpessoa";
	}
	
	@RequestMapping(value="/editsave",method=RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("student") Student p) {
		
		Pessoa pessoa=studentDao.findOne(p.getId());
		
		pessoa.setName(p.getFirstName());
		pessoa.setLastName(p.getLastName());
		pessoa.setCountry(p.getCountry());
		pessoa.setEmail(p.getEmail());
		pessoa.setSection(p.getSection());
		pessoa.setSexo(p.getSex());
		
		studentDao.save(student);
		return new ModelAndView("redirect:/viewstudents");
	}
	
	@RequestMapping(value="/deletestudent/{id}",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		Student student=studentDao.findOne(id);
		studentDao.delete(student);
		return new ModelAndView("redirect:/viewstudents");
	}
	
	

	@ModelAttribute("sections")
	public List<String> intializeSections(){
		List<String> sections = new ArrayList<String>();
		sections.add("Graduate");
		sections.add("Post Graduate");
		sections.add("Reasearch");
		return sections;
	}
	
	
	/*
	 * Method used to populate the country list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("countries")
	public List<String> initializeCountries() {

		List<String> countries = new ArrayList<String>();
		countries.add("INDIA");
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		return countries;
	}

	
	
	

}
