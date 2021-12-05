package com.enigma.restgarden.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mst_grave")
public class Grave {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "uuid")
    private String id;
    private String name;
    private Integer availableSlots;
    private Integer price;
    private String address;
    private String phoneNumber;
    private String type;
    private String description;

    @OneToMany(mappedBy = "grave", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Corpse> corpses = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name = "user_id", nullable = false)
    private List<User> user;

    public Grave() {
    }

    public Grave(String id, String name, Integer availableSlots, Integer price, String address, String phoneNumber, String type, String description, List<Corpse> corpses, List<User> user) {
        this.id = id;
        this.name = name;
        this.availableSlots = availableSlots;
        this.price = price;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.description = description;
        this.corpses = corpses;
        this.user = user;
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

    public Integer getAvailableSlots() {
        return availableSlots-user.size();
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Corpse> getCorpses() {
        return corpses;
    }

    public void setCorpses(List<Corpse> corpses) {
        this.corpses = corpses;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
