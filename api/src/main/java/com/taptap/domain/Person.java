package com.taptap.domain;

import javax.persistence.*;

import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Nicolas on 05/01/2017.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Entity
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

    @OneToOne
    private Bracelet bracelet;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bracelet getBracelet() {
        return bracelet;
    }

    public void setBracelet(Bracelet bracelet) {
        this.bracelet = bracelet;
    }



}