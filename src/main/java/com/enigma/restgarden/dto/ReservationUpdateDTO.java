package com.enigma.restgarden.dto;

public class ReservationUpdateDTO {
    private String reservationId;
    private Integer userBalance;

    public ReservationUpdateDTO() {
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Integer userBalance) {
        this.userBalance = userBalance;
    }
}
