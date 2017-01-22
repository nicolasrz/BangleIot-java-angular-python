package com.taptap.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taptap.domain.*;


@RestController

public class MainController {
	
	@Autowired VibrationRepository vibrationRepository;

	@Autowired PersonRepository personRepository;
	
	@Autowired BraceletRepository braceletRepository;
	
	@RequestMapping("/api")
	public String welcome(){
		String s = "api <br>";
		s += "/api/person?email={email}&password={password}";
		s += "<br>";
		s += "/api/fullinfo?idperson={idperson}";
		s += "<br>";
		s += "/api/generate";
		s += "<br>";
		s += "/api/vibration?idbracelet={idbracelet}";
		return s;
	}
	
	@RequestMapping(value="/api/person")
	public Person person(@RequestParam(value="email") String email, 
			@RequestParam(value="password") String password){
		Person person = personRepository.findByEmailAndPassword(email, password);
		
		return person;
		
	}
	
	@RequestMapping(value="/api/vibration")
	public List<Vibration> vibrations(@RequestParam(value="idbracelet") long idbracelet){
		Bracelet bracelet = braceletRepository.findById(idbracelet);
		List<Vibration> vibrations = vibrationRepository.findByBracelet(bracelet);
		return vibrations;
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