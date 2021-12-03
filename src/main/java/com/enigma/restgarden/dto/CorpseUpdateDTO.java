package com.enigma.restgarden.dto;

public class CorpseUpdateDTO {
    private String graveId;
    private String corpseId;

    public CorpseUpdateDTO() {
    }

    public String getGraveId() {
        return graveId;
    }

    public void setGraveId(String graveId) {
        this.graveId = graveId;
    }

    public String getCorpseId() {
        return corpseId;
    }

    public void setCorpseId(String corpseId) {
        this.corpseId = corpseId;
    }
}
