package com.taptap.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taptap.domain.Person;
import com.taptap.domain.PersonRepository;

@RestController

public class MainController {

	@Autowired PersonRepository personRepository;
	
	@RequestMapping("/api")
	public String welcome(){
		String s = "api <br>";
		s += "/api/person?email={email}&password={password}";
		s += "<br>";
		s += "/api/fullinfo?idperson={idperson}";
		s += "<br>";
		s += "/api/generate";
		return s;
	}
	
	@RequestMapping(value="/api/person")
	public Person person(@RequestParam(value="email") String email, 
			@RequestParam(value="password") String password){
		Person person = personRepository.findByEmailAndPassword(email, password);
		
		return person;
		
	}
	
   
    @RequestMapping("/api/fullinfo")
    public HashMap<String, Object> personbracelet(@RequestParam(value="idperson") long id){
    	HashMap<String, Object> fullinfo = new HashMap<String, Object>();
    	Person person = personRepository.findById(id);
    	
    	fullinfo.put("person", person);
    	
    	if(person.getBracelet() != null){
    		fullinfo.put("bracelet", person.getBracelet());
    		if(person.getBracelet().getBracelet() != null){
    			fullinfo.put("bracelet_associated", person.getBracelet().getBracelet());
    		}
    	}
    	
    	
    	
    	return fullinfo;
    }
}