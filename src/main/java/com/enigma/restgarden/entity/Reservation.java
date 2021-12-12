package com.enigma.restgarden.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tx_reservation")
public class Reservation {
    @Id
    @GeneratedValue(generator = "uuid_generator")
    @GenericGenerator(name = "uuid_generator", strategy = "uuid")
    private String id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "grave_id")
    private Grave grave;
    private String graveName;
    private String graveAddress;
    private String image;
    private Integer gravePrice;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;
    private String userName;

    private Integer totalSlot;
    private String status;
    private Timestamp expiredDate;
    private Double totalPayment;
    private String description;

    public Reservation() {
    }

    public Reservation(Grave grave, User user, Integer totalSlot, String status, String description) {
        this.grave = grave;
        this.graveName = grave.getName();
        this.gravePrice = grave.getPrice();
        this.graveAddress = grave.getAddress();
        this.image = grave.getImage();
        this.user = user;
        this.userName = user.getName();
        this.expiredDate = new Timestamp(System.currentTimeMillis() + (1000*60*7));
        this.totalSlot = totalSlot;
        this.status = status;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getTotalSlot() {
        return totalSlot;
    }

    public void setTotalSlot(Integer totalSlot) {
        this.totalSlot = totalSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGravePrice() {
        return gravePrice;
    }

    public void setGravePrice(Integer gravePrice) {
        this.gravePrice = gravePrice;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getGraveAddress() {
        return graveAddress;
    }

    public void setGraveAddress(String graveAddress) {
        this.graveAddress = graveAddress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
