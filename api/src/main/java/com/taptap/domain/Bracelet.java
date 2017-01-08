package com.taptap.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Nicolas on 05/01/2017.
 */
@Entity
public class Bracelet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String device_identifier;

    @ManyToMany
    private Collection<Bracelet> bracelets ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDevice_identifier() {
        return device_identifier;
    }

    public void setDevice_identifier(String device_identifier) {
        this.device_identifier = device_identifier;
    }
}
