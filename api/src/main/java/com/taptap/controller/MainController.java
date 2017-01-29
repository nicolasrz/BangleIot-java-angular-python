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
		String s = "<strong>TAPTAPAPI</strong> <br><br>";
		
		s += "<br> <strong># Persons </strong><br>";
		s +="<strong>Get person by his email and password</strong><br>";
		s += "<a href='http://localhost:8080/api/person?email={email}&password={password}'>http://localhost:8080/api/person?email={email}&password={password}</a>";
		s += "<br>";
		s += "<br>";
		s +="<strong>Get person, his bracelet and his associated bracelet</strong><br>";
		s += "<a href='http://localhost:8080/api/fullinfo?idperson={idperson}'>http://localhost:8080/api/fullinfo?idperson={idperson}</a>";
		s += "<br>";
		s += "<br>";
		
		s += "<br> <strong># Bracelet</strong><br>";
		s +="<strong>Get associated bracelet by his own bracelet id</strong><br>";
		s += "<a href='http://localhost:8080/api/bracelet/associated/id?idbracelet={idOwnBracelet}'>http://localhost:8080/api/bracelet/associated/id?idbracelet{idOwnBracelet}</a>";
		s += "<br>";
		s += "<br>";
		s +="<strong>Get his bracelet by his own bracelet identifier</strong><br>";
		s += "<a href='http://localhost:8080/api/bracelet/deviceidentifier?deviceidentifier={ownDeviceIdentifier}'>http://localhost:8080/api/bracelet/deviceidentifier?deviceidentifier{ownDeviceIdentifier}</a>";
		s += "<br>";
		s += "<br>";
		s +="<strong>Get associated bracelet by his own bracelet identifier</strong><br>";
		s += "<a href='http://localhost:8080/api/bracelet/associated/devideidentifier?deviceidentifier={ownDeviceIdentifier}'>api/bracelet/associated/devideidentifier?deviceidentifier{ownDeviceIdentifier}</a>";
		s += "<br>";
		s += "<br>";
		
		s += "<br> <strong># Vibrations</strong><br>";
		s +="<strong>Get all vibrations of a bracelet by its id</strong><br>";
		s += "<a href='http://localhost:8080/api/vibration/all?idbracelet={idbracelet}'>http://localhost:8080/api/vibration/all?idbracelet={idbracelet}</a>";
		s += "<br>";
		s += "<br>";
		s +="<strong>Get all actived vibrations of a bracelet by its id</strong><br>";
		s += "<a href='http://localhost:8080/api/vibration/true?idbracelet={idbracelet}'>http://localhost:8080/api/vibration/true?idbracelet={idbracelet}</a>";
		s += "<br>";
		s += "<br>";
		s +="<strong>Add vibration</strong><br>";
		s += "<a href='http://localhost:8080/api/vibration/post?idbracelet={idbracelet}'>http://localhost:8080/api/vibration/post?idbracelet={idbracelet}</a>";
		s += "<br>";
		s += "<br>";
		s +="<strong>Put vibration (change state only)</strong><br>";				
		s += "<a href='http://localhost:8080/api/vibration/put?idvibration={idvibration}&state={true or false}'>http://localhost:8080/api/vibration/put?idvibration={idvibration}&state={true or false}</a>";
		s += "<br>";
		s += "<br>";
		
		s += "<br> # <strong>Generation</strong><br>";
		s +="<strong>Generate person and bracelet if base is empty</strong><br>";
		s += "<a href='http://localhost:8080/api/generate'>http://localhost:8080/api/generate</a>";
		s += "<br>";
		s += "<br>";
		
		s += "<br> # <strong>Full information</strong><br>";
		s +="<strong>Generate all information about one user</strong><br>";
		s += "<a href='http://localhost:8080/api/fullinfo?idperson={idperson}'>http://localhost:8080/api/fullinfo?idperson={idperson}</a>";
		s += "<br>";
		s += "<br>";
		return s;
	}
	
	@RequestMapping(value="/api/person")
	public Person person(@RequestParam(value="email") String email, 
			@RequestParam(value="password") String password){
		Person person = personRepository.findByEmailAndPassword(email, password);
		
		return person;
		
	}
	
	@RequestMapping(value="/api/bracelet/associated/id")
	public Bracelet getAssociatedBraceletById(@RequestParam(value="idbracelet") long idbracelet){
		Bracelet bracelet = braceletRepository.findById(idbracelet);
		Bracelet braceletAssociated = bracelet.getBracelet();

		return braceletAssociated;
	}
	
	@RequestMapping(value="/api/bracelet/associated/deviceidentifier")
	public Bracelet getAssociatedBraceletByHisDeviceIdentifier(@RequestParam(value="deviceidentifier") String deviceidentifier){
		Bracelet bracelet = braceletRepository.findByDeviceIdentifier(deviceidentifier);
		
		Bracelet braceletAssociated = bracelet.getBracelet();

		return braceletAssociated;
	}
	
	@RequestMapping(value="/api/bracelet/deviceidentifier")
	public Bracelet getBraceletByDeviceIdentifier(@RequestParam(value="deviceidentifier") String deviceidentifier){
		Bracelet bracelet = braceletRepository.findByDeviceIdentifier(deviceidentifier);
		return bracelet;
	}

	
	@RequestMapping(value="/api/vibration/all")
	public List<Vibration> allVibrations(@RequestParam(value="idbracelet") long idbracelet){
		Bracelet bracelet = braceletRepository.findById(idbracelet);
		List<Vibration> vibrations = vibrationRepository.findByBracelet(bracelet);
		return vibrations;
	}
	@RequestMapping(value="/api/vibration/true")
	public List<Vibration> vibrationsTrue(@RequestParam(value="idbracelet") long idbracelet){
		Bracelet bracelet = braceletRepository.findById(idbracelet);
		List<Vibration> vibrations = vibrationRepository.findByBraceletAndState(bracelet, true);
		return vibrations;
	}
	
	@RequestMapping(value="/api/vibration/put")
	public String putVibration(@RequestParam(value="idvibration") long idvibration,
			@RequestParam(value="state") boolean state){		
		
		Vibration vib = vibrationRepository.findById(idvibration);
		vib.setState(state);
		vib = vibrationRepository.save(vib);
		return "vibration putted";
	}
	
	@RequestMapping(value="/api/vibration/post")
	public HashMap<String, String> postVibration(@RequestParam(value="idbracelet") long idbracelet){		
		HashMap<String, String> map = new HashMap<>();
		map.put("success", "vibration added");
		Bracelet bracelet = braceletRepository.findById(idbracelet);
		Vibration vib = new Vibration();
		vib.setState(true);
		vib.setBracelet(bracelet);
		vib = vibrationRepository.save(vib);
		
		return map;
	}
   
    @RequestMapping("/api/fullinfo")
    public HashMap<String, Object> personbracelet(@RequestParam(value="idperson") long id){
    	HashMap<String, Object> fullinfo = new HashMap<String, Object>();
    	Person person = personRepository.findById(id);
    	
    	fullinfo.put("person", person);
    	
    	if(person.getBracelet() != null){
    		fullinfo.put("bracelet", person.getBracelet());
    		List<Vibration> vibrations = vibrationRepository.findByBraceletAndState(person.getBracelet(), true);
    		if(vibrations != null){
    			fullinfo.put("vibrations", vibrations.size());
    		}
    		if(person.getBracelet().getBracelet() != null){
    			fullinfo.put("bracelet_associated", person.getBracelet().getBracelet());
    		}
    	}
    	
    	
    	
    	return fullinfo;
    }
}