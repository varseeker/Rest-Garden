package com.enigma.restgarden.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tx_users_graves")
public class Transaction {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String userName;

    @ManyToOne
    @JoinColumn(name = "grave_id")
    private Grave grave;
    private String graveName;
    private Integer gravePrice;
    private String graveAddress;
    private String image;

    private Integer totalSlot;
    private Timestamp date;
    private Integer totalPrice;
    private String description;
    private String status;
    private String type;

    public Transaction() {
    }

    public Transaction(User user, Grave grave, Integer totalSlot, String description) {
        this.user = user;
        this.userName = user.getName();
        this.grave = grave;
        this.graveName = grave.getName();
        this.graveAddress = grave.getAddress();
        this.image = grave.getImage();
        this.totalSlot = totalSlot;
        this.gravePrice = grave.getPrice();
        this.date = new Timestamp(System.currentTimeMillis());
        this.description = description;
    }

    public Integer getTotalSlot() {
        return totalSlot;
    }

    public void setTotalSlot(Integer totalSlot) {
        this.totalSlot = totalSlot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    public Grave getGrave() {
        return grave;
    }

    public void setGrave(Grave grave) {
        this.grave = grave;
    }

    public String getGraveName() {
        return graveName;
    }

    public void setGraveName(String graveName) {
        this.graveName = graveName;
    }

    public Integer getGravePrice() {
        return gravePrice;
    }

    public void setGravePrice(Integer gravePrice) {
        this.gravePrice = gravePrice;
    }

    public Timestamp getDate() {
        return date;
    }

    public void getDate(Timestamp date) {
        this.date = date;
    }

    public String getGraveAddress() {
        return graveAddress;
    }

    public void setGraveAddress(String graveAddress) {
        this.graveAddress = graveAddress;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
