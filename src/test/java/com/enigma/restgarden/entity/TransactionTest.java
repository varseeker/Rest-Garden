package com.enigma.restgarden.entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    public void able_ToCreateTransaction() {
        User user = new User();
        Grave grave = new Grave();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(user, grave, 3, 50000, date, "apa");
        assertNotNull(transaction);
    }

    @Test
    public void getTotalSlot_returnThreeWhenGiven_3In_totalSLot() {
        User user = new User();
        Grave grave = new Grave();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(user, grave, 3, 50000, date, "apa");
        assertEquals(transaction.getTotalSlot(), 3);
    }

    @Test
    public void getTotalSlot_returnFalseWhenGiven_different_totalSLot() {
        User user = new User();
        Grave grave = new Grave();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(user, grave, 2, 50000, date, "apa");
        assertNotEquals(transaction.getTotalSlot(), 3);
    }

    @Test
    public void getUserBalance_returnTenThousandWhenGiven_10000_userBalance() {
        User user = new User();
        Grave grave = new Grave();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(user, grave, 3, 10000, date, "apa");
        assertEquals(transaction.getUserBalance(), 10000);
    }

    @Test
    public void getDate_returnCurrentDateWhenGiven_correct_Date() {
        User user = new User();
        Grave grave = new Grave();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Timestamp dateExpected = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(user, grave, 3, 50000, date, "apa");
        assertEquals(transaction.getDate(), dateExpected);
    }

    @Test
    public void getDescription_returnGRAVE_whenDescriptionGiven() {
        User user = new User();
        Grave grave = new Grave();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(user, grave, 3, 10000, date, "GRAVE");
        assertEquals(transaction.getDescription(), "GRAVE");
    }

    @Test
    public void setTotalSlot_AbleToChangeValueTotalSlot_fromThree_ToFive() {
        User user = new User();
        Grave grave = new Grave();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(user, grave, 3, 10000, date, "GRAVE");
        transaction.setTotalSlot(5);
        assertEquals(transaction.getTotalSlot(), 5);
    }

    @Test
    public void setUserBalance_AbleToChangeValueUserBalance_fromTenThousand_ToFiveThousand() {
        User user = new User();
        Grave grave = new Grave();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(user, grave, 3, 10000, date, "GRAVE");
        transaction.setUserBalance(50000);
        assertEquals(transaction.getUserBalance(), 50000);
    }
}