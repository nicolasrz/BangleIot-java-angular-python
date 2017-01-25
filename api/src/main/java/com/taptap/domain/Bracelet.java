package com.taptap.domain;

import javax.persistence.*;

/**
 * Created by Nicolas on 05/01/2017.
 */
@Entity
public class Bracelet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String deviceIdentifier;

    @OneToOne
    @JoinColumn(name="associated_bracelet")
    private Bracelet bracelet;
    
    


	public Bracelet() {
		super();
	}

	public Bracelet(String deviceIdentifier) {
		super();
		this.deviceIdentifier = deviceIdentifier;
	}

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

	public Bracelet getBracelet() {
		return bracelet;
	}

	public void setBracelet(Bracelet bracelet) {
		this.bracelet = bracelet;
	}

	
    
    
}
