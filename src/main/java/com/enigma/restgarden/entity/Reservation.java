//package com.enigma.restgarden.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.sql.Timestamp;

//@Entity
//@Table(name = "tx_reservation")
//public class Reservation {
//    @Id
//    @GeneratedValue(generator = "uuid_generator")
//    @GenericGenerator(name = "uuid_generator", strategy = "uuid")
//    private String id;
//
//    @ManyToOne
//    @JoinColumn(name = "grave_id")
//    private Grave grave;
//    private String graveName;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//    private String userName;
//
//    private Integer totalSlot;
//    private String status;
//    private Timestamp reserveDate;
//    private Integer price;
//    private Integer currentPayment;
//    private String description;
//
//    public Reservation() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @JsonIgnore
//    public Grave getGrave() {
//        return grave;
//    }
//
//    public void setGrave(Grave grave) {
//        this.grave = grave;
//    }
//
//    public String getGraveName() {
//        return graveName;
//    }
//
//    public void setGraveName(String graveName) {
//        this.graveName = graveName;
//    }
//
//    @JsonIgnore
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public Integer getTotalSlot() {
//        return totalSlot;
//    }
//
//    public void setTotalSlot(Integer totalSlot) {
//        this.totalSlot = totalSlot;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public Timestamp getReserveDate() {
//        return reserveDate;
//    }
//
//    public void setReserveDate(Timestamp reserveDate) {
//        this.reserveDate = reserveDate;
//    }
//
//
//    public Integer getPrice() {
//        return price;
//    }
//
//    public void setPrice(Integer price) {
//        this.price = price;
//    }
//
//    public Integer getCurrentPayment() {
//        return currentPayment;
//    }
//
//    public void setCurrentPayment(Integer currentPayment) {
//        this.currentPayment = currentPayment;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}
