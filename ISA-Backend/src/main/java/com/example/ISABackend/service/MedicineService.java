package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchMedicine;
import com.example.ISABackend.model.Medicine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface MedicineService {

    public Medicine getById(Long id);
    public List<Medicine> getAll();
    public ArrayList<Medicine> search(SearchMedicine searchParameters);
    public ArrayList<Medicine> sort(ArrayList<Medicine> sortMedicines, String sortType);
    public Medicine download(Long medicine_id);
    public Medicine addNew(Medicine newMedicine);
    public Medicine getByCode(int code);
    public Medicine getByName(String name);
    public ArrayList<Long> addReplacements(ArrayList<Long> replacementsId, Long medicineId);

}
