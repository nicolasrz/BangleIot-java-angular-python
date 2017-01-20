package com.taptap.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taptap.domain.Bracelet;
import com.taptap.domain.BraceletRepository;
import com.taptap.domain.Person;
import com.taptap.domain.PersonRepository;

@RestController

public class MainController {

	
	@Autowired
	private BraceletRepository braceletRepository;

	@Autowired PersonRepository personRepository;
	
	@RequestMapping("/api")
	public String welcome(){
		String s = "api <br>";
		s += "/api/person?email={email}&password={password}";
		s += "<br>";
		s += "/api/fullinfo?idperson={idperson}";
		return s;
	}
	
	@RequestMapping(value="/api/person")
	public Person person(@RequestParam(value="email") String email, 
			@RequestParam(value="password") String password){
		Person person = personRepository.findByEmailAndPassword(email, password);
		return person;
		
	}
	
   
    @RequestMapping("/api/fullinfo")
    public HashMap<String, Object> personbracelet(@RequestParam(value="idperson") int id){
    	HashMap<String, Object> fullinfo = new HashMap<String, Object>();
    	Person person = personRepository.findById(id);
    	
    	fullinfo.put("person", person);
    	
    	if(person.getBracelet() != null){
    		fullinfo.put("bracelet", person.getBracelet());
    		if(!CollectionUtils.isEmpty(person.getBracelet().getBracelets())){
    			for(Bracelet braceletassocie : person.getBracelet().getBracelets()){
        			fullinfo.put("bracelet_associe", braceletassocie);
    			}	
    		}
    	}
    	
    	
    	
    	return fullinfo;
    }
}