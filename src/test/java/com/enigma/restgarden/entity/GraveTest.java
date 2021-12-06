package com.enigma.restgarden.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraveTest {

    @Test
    public void able_ToCreateGrave_withGiven_correctInformation(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertNotNull(grave);
    }

    @Test
    public void should_ReturnIdIs_asd_withGiven_correctInformationOfId(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertEquals(grave.getId(), "asd");
    }

    @Test
    public void getName_ReturnNameIsCipete_withGiven_correctInformationOfName(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertEquals(grave.getName(), "Cipete");
    }

    @Test
    public void setName_ChangeNameToRagunan_withGiven_NewValueOfName(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setName("Ragunan");
        assertEquals(grave.getName(), "Ragunan");
    }

    @Test
    public void getAvailableSlot_ReturnSlot13_withGiven_correctInformationOf_AvailableSlsot(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertEquals(grave.getAvailableSlots(), 13);
    }

    @Test
    public void setAvailableSLot_ChangeSlotTo5_withGiven_NewValueOf_AvailableSLot(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setAvailableSlots(5);
        assertEquals(grave.getAvailableSlots(), 5);
    }

    @Test
    public void getPrice_ReturnPrice_ThreeBillions_withGiven_correctInformationOfPrice(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertEquals(grave.getPrice(), 30000000);
    }

    @Test
    public void setPrice_ChangePriceTo_FiveTeenBillions_withGiven_NewValueOfPrice(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setPrice(15000000);
        assertEquals(grave.getPrice(), 15000000);
    }

    @Test
    public void getAddress_ReturnAddress_withGiven_correctInformationOfAddress(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertEquals(grave.getAddress(), "Cipete utara");
    }

    @Test
    public void setAddress_ChangeAddressTo_RagunanSelatan_withGiven_NewValueOfAddress(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setAddress("Ragunan Selatan");
        assertEquals(grave.getAddress(), "Ragunan Selatan");
    }

    @Test
    public void getPhoneNumber_ReturnPhoneNumber_withGiven_correctInformationOfPhoneNumber(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertEquals(grave.getPhoneNumber(), "0811132554");
    }

    @Test
    public void setPhoneNumber_ChangePhoneNumberTo_081234567_withGiven_NewValueOfPhoneNumber(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setPhoneNumber("081234567");
        assertEquals(grave.getPhoneNumber(), "081234567");
    }

    @Test
    public void getType_ReturnTypeOfGrave_withGiven_correctInformationOfType(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertEquals(grave.getType(), "TPS");
    }

    @Test
    public void setType_ChangeTypeTo_TPU_withGiven_NewValueOfType(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setType("TPU");
        assertEquals(grave.getType(), "TPU");
    }

    @Test
    public void getDescription_ReturnDescriptionOfGrave_withGiven_correctInformationOfDescription(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertEquals(grave.getDescription(), "gatau");
    }

    @Test
    public void setDesciption_ChangeDescriptionTo_PemakamanUmum_withGiven_NewValueOfDescription(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setDescription("Pemakaman Umum");
        assertEquals(grave.getDescription(), "Pemakaman Umum");
    }

    @Test
    public void getUser_ReturnListOfUser(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertNotNull(grave.getUser());
    }

    @Test
    public void getCorpse_ReturnListOfCorpse(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        assertNotNull(grave.getCorpses());
    }

    @Test
    public void setCorpseList_ReturnListOfCorpse(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setCorpses(corpseList);
        assertEquals(grave.getCorpses(), corpseList);
    }

    @Test
    public void setUserList_ReturnListOfUser(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setUser(userList);
        assertEquals(grave.getUser(), userList);
    }

    @Test
    public void setId_ReturnGraveId(){
        List<Corpse> corpseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Grave grave = new Grave("asd", "Cipete", 13, 30000000, "Cipete utara", "0811132554", "TPS", "gatau", corpseList, userList);
        grave.setId("ADW");
        assertEquals(grave.getId(), "ADW");
    }
}