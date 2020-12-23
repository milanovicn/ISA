package com.example.ISABackend.dto;

public class SearchMedicine {
    private String name;
    private String type;
    private String contraindications;
    private int code;

    public SearchMedicine(String name, String type, String contraindications, int code) {
        this.name = name;
        this.type = type;
        this.contraindications = contraindications;
        this.code = code;
    }

    public SearchMedicine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
