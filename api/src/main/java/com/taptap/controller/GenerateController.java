package com.taptap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taptap.domain.BraceletRepository;
import com.taptap.domain.*;
import com.taptap.domain.PersonRepository;
import com.taptap.domain.VibrationRepository;

@RestController
public class GenerateController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private BraceletRepository braceletRepository;
	
	@Autowired
	private VibrationRepository vibrationRepository;
	
	@RequestMapping("/api/generate")
	public String generate(){
		
		Bracelet braceletNicolas = new Bracelet("bracelet_nicolas");
		Bracelet braceletCharly = new Bracelet("bracelet_charly");
		Bracelet braceletPedro = new Bracelet("bracelet_pedro");
		
		braceletNicolas = braceletRepository.save(braceletNicolas);
		braceletCharly = braceletRepository.save(braceletCharly);
		braceletPedro = braceletRepository.save(braceletPedro);
		
		Person nicolas = new Person(1, "nruiz@mail.fr", "nicolas", "Nicolas", "Ruiz", braceletNicolas );
		Person charly = new Person(2, "charly@mail.fr", "charly", "Charly", "Parpet", braceletCharly);
		Person pedro = new Person(3, "pedro@mail.fr", "pedro", "Pedro", "Cristino", braceletPedro );
		
		braceletNicolas.setBracelet(braceletPedro);
		braceletNicolas.setBracelet(braceletCharly);
		
		braceletCharly.setBracelet(braceletPedro);
		braceletCharly.setBracelet(braceletNicolas);
		
		braceletPedro.setBracelet(braceletCharly);
		braceletPedro.setBracelet(braceletNicolas);
		
		
		
		nicolas = personRepository.save(nicolas);
		charly = personRepository.save(charly);
		pedro = personRepository.save(pedro);
		
		return "Value generated ok";
		
		
	}
	

}
