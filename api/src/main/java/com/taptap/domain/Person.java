package com.taptap.domain;

import javax.persistence.*;

/**
 * Created by Nicolas on 05/01/2017.
 */
@Entity
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;
    private String password;
    private String firstname;
    private String lastname;

    @OneToOne
    private Bracelet bracelet;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Bracelet getBracelet() {
		return bracelet;
	}

	public void setBracelet(Bracelet bracelet) {
		this.bracelet = bracelet;
	}
    

    



}