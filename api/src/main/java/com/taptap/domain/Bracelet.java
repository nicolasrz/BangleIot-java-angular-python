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

    public String getDevice_identifier() {
        return deviceIdentifier;
    }

    public void setDevice_identifier(String deviceIdentifier) {
        this.deviceIdentifier = deviceIdentifier;
    }
}
