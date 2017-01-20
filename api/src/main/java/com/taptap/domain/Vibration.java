package com.taptap.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Nicolas on 20/01/2017.
 */
@Entity
@Table(name="vibration")
public class Vibration {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private Boolean state;
	
	@ManyToOne
	private Bracelet bracelet;

	public Vibration(long id, Boolean state, Bracelet bracelet) {
		super();
		this.id = id;
		this.state = state;
		this.bracelet = bracelet;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Bracelet getBracelet() {
		return bracelet;
	}

	public void setBracelet(Bracelet bracelet) {
		this.bracelet = bracelet;
	}
	
	
	
	
	
	
}
