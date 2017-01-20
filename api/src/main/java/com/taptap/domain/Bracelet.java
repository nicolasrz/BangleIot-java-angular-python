package com.taptap.domain;

import javax.persistence.*;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

/**
 * Created by Nicolas on 05/01/2017.
 */
@Entity
public class Bracelet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String deviceIdentifier;

    @ManyToMany
    private Collection<Bracelet> bracelets ;


	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


	public String getDeviceIdentifier() {
		return deviceIdentifier;
	}

	public void setDeviceIdentifier(String deviceIdentifier) {
		this.deviceIdentifier = deviceIdentifier;
	}

	public Collection<Bracelet> getBracelets() {
		return bracelets;
	}

	public void setBracelets(Collection<Bracelet> bracelets) {
		this.bracelets = bracelets;
	}
    
    
}
