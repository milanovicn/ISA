package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchMedicine;
import com.example.ISABackend.enums.MedicinePrescription;
import com.example.ISABackend.enums.MedicineType;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.repository.MedicineRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine getById(Long id) {
        return  medicineRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public ArrayList<Medicine> search(SearchMedicine searchParameters) {
        ArrayList<Medicine> ret = new ArrayList<Medicine>();

        // getting all pharmacies
        for (Medicine m : medicineRepository.findAll()) {
            ret.add(m);
        }

        for (Medicine p : medicineRepository.findAll()) {

            // if medicine contraindications doesn't meet search condition
            if (!searchParameters.getContraindications().equals("all")) {
                if (!p.getContraindications().toLowerCase().contains(searchParameters.getContraindications().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if medicine name doesn't meet search condition
            if (!searchParameters.getName().equals("all")) {
                if (!p.getName().toLowerCase().contains(searchParameters.getName().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if pharmacy description doesn't meet search condition
            if (!searchParameters.getType().equals("all")) {
                if (!p.getType().toString().toLowerCase().contains(searchParameters.getType().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if pharmacy city doesn't meet search condition
            if ( searchParameters.getCode() != 0){
                String pCodeParsed =  Integer.toString(p.getCode());
                String searchParametersCodeParsed =  Integer.toString(searchParameters.getCode());
                if (!pCodeParsed.toLowerCase().contains(searchParametersCodeParsed.toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }




        }

        System.out.println("RET : " + ret);

        return ret;
    }

    @Override
    public ArrayList<Medicine> sort(ArrayList<Medicine> sortMedicines, String sortType) {
        if(sortType.equals("CODE")){
            sortMedicines.sort(Comparator.comparingInt(Medicine :: getCode));
            return sortMedicines;
        } else if(sortType.equals("TYPE")){
            Comparator<Medicine> compareByType = (Medicine m1, Medicine m2) ->
                    m1.getType().toString().compareTo( m2.getType().toString() );
            Collections.sort(sortMedicines, compareByType);
            return sortMedicines;
        } else if(sortType.equals("NAME")){
            Comparator<Medicine> compareByName = (Medicine m1, Medicine m2)->
                    m1.getName().compareTo( m2.getName() );
            Collections.sort(sortMedicines, compareByName);
            return sortMedicines;
        }

        return null;
    }

    @Override
    public Medicine download(Long medicine_id) {
        Medicine med = this.getById(medicine_id);

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(med.getName() +med.getCode()+ ".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String p = "";
        if (med.getPrescription() == MedicinePrescription.OVER_THE_COUNTER){
            p="over the counter";
        } else {
            p="PRESCRIPTION DRUG";
            p = p.toLowerCase();
        }

        String t = "";
        if (med.getType() == MedicineType.DIETARY_SUPPLEMENT){
            t="DIETARY SUPPLEMENT";
            t = t.toLowerCase();
        } else {
            t = med.getType().toString().toLowerCase();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 16, BaseColor.BLACK);
        Paragraph newLine = new Paragraph("   ",headerFont);
        Paragraph header = new Paragraph("======= "+ med.getName() +" =======",headerFont);
        Paragraph chunk3 = new Paragraph("Manufacturer: " + med.getManufacturer() + Chunk.NEWLINE, font);
        Paragraph chunk = new Paragraph("Medicine name: " + med.getName() + Chunk.NEWLINE, font);
        Paragraph chunk1 = new Paragraph("Medicine code: " + med.getCode(), font );
        Paragraph chunk7 = new Paragraph( "Contraindications: " + med.getContraindications() + Chunk.NEWLINE, font);
        Paragraph chunk2 = new Paragraph("Ingredients: " + med.getIngredients() + Chunk.NEWLINE, font);
        Paragraph chunk4 = new Paragraph("Type: " + t + Chunk.NEWLINE, font);
        Paragraph chunk6 = new Paragraph("Form: " + med.getForm().toString().toLowerCase() + Chunk.NEWLINE, font);
        Paragraph chunk5 = new Paragraph("Prescription: " + p + Chunk.NEWLINE, font);

        try {

            document.add(newLine);
            document.add(header);
            document.add(newLine);
            document.add(newLine);
            document.add(chunk3);
            document.add(newLine);
            document.add(chunk);
            document.add(newLine);
            document.add(chunk1);
            document.add(newLine);
            document.add(chunk2);
            document.add(newLine);
            document.add(chunk4);
            document.add(newLine);
            document.add(chunk6);
            document.add(newLine);
            document.add(chunk5);
            document.add(newLine);
            document.add(chunk7);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
        return null;
    }


}
