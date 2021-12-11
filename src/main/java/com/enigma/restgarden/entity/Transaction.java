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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;
    private String userName;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "grave_id")
    private Grave grave;
    private String graveName;
    private Integer gravePrice;
    private String graveAddress;
    private String image;

    private Integer totalSlot;
    private Timestamp expiredDate;
    private String description;

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
        this.expiredDate = new Timestamp(System.currentTimeMillis() + (1000*60*2));
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

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void getExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getGraveAddress() {
        return graveAddress;
    }

    public void setGraveAddress(String graveAddress) {
        this.graveAddress = graveAddress;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
