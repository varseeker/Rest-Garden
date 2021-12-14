package com.enigma.restgarden.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "mst_corpse")
public class Corpse {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "uuid")
    private String id;
    private String name;
    private String parentName;
    private String location;
    private Date birthDate;
    private Timestamp date = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "grave_id", nullable = true)
    private Grave grave;

    public Corpse() {
    }

    public Corpse(String name, String parentName, String location, Grave grave, Date birthDate) {
        this.name = name;
        this.parentName = parentName;
        this.location = location;
        this.grave = grave;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Grave getGrave() {
        return this.grave;
    }

    public void setGrave(Grave grave) {
        this.grave = grave;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
