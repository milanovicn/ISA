package com.example.ISABackend.model;


import com.example.ISABackend.enums.MedicineForm;
import com.example.ISABackend.enums.MedicinePrescription;
import com.example.ISABackend.enums.MedicineType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "contraindications", nullable = false)
    private String contraindications;

    @Column(name = "ingredients", nullable = false)
    private String ingredients;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "type", nullable = false)
    private MedicineType type;

    @Column(name = "form", nullable = false)
    private MedicineForm form;

    @Column(name = "prescription", nullable = false)
    private MedicinePrescription prescription;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Medicine> replacement = new HashSet<Medicine>();

    public Medicine(String name, int code, MedicineType type, String contraindications, Set<Medicine> replacement) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.contraindications = contraindications;
        this.replacement = replacement;
    }

    public Medicine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MedicineType getType() {
        return type;
    }

    public void setType(MedicineType type) {
        this.type = type;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public Set<Medicine> getReplacement() {
        return replacement;
    }

    public void setReplacement(Set<Medicine> replacement) {
        this.replacement = replacement;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public MedicineForm getForm() {
        return form;
    }

    public void setForm(MedicineForm form) {
        this.form = form;
    }

    public MedicinePrescription getPrescription() {
        return prescription;
    }

    public void setPrescription(MedicinePrescription prescription) {
        this.prescription = prescription;
    }
}
