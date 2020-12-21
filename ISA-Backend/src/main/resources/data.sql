--PHARMACIES
insert into pharmacy.pharmacy(id,address,city,description,name,rate) values (1,'Roze Luksemburg 5','Pozarevac','Pozarevacka apoteka broj 1','Apoteka Milanovic',5)
insert into pharmacy.pharmacy(id,address,city,description,name,rate) values (2,'Oslobodjenja 24','Kucevo','Kucevljanska apoteka broj 1','Apoteka Kljucevic',5)
insert into pharmacy.pharmacy(id,address,city,description,name,rate) values (3,'Njegoseva 16','Banja Koviljaca','Banjokoviljcanska apoteka broj 1','Apoteka Mitrovic',5)

-- PATIENTS
insert into user(id, first_name, last_name, email, phone_number, password, address, city, country,  user_role, prvi_put_logovan) values (1, 'Mika', 'Mikic', 'patient1@pharmacy.com', 0691116111, '123', 'Pacijentica 11/11', 'Novi Sad', 'Srbija', 2,0)
insert into user(id, first_name, last_name, email, phone_number, password, address, city, country,  user_role,prvi_put_logovan) values (2, 'Zika', 'Zikic', 'patient2@pharmacy.com', 0692226222, '123', 'Pacijentica 22/22', 'Novi Sad', 'Srbija', 2,0)

-- PHARMACISTS
insert into  pharmacy.pharmacist(id, email, first_name, last_name, password, phone_number, user_role, pharmacy_id, address, city, country ) values (1, 'pharmacist1@pharmacy.com', 'Sanja', 'Sanjic', '123', 0651234567, 3, 1, 'Ulica Farmaceuta 1', 'Novi Sad', 'Srbija')
insert into pharmacy.pharmacist(id, email, first_name, last_name, password, phone_number, user_role, pharmacy_id, address, city, country ) values (2, 'pharmacist2@pharmacy.com', 'Tanja', 'Tanjic', '123', 0657654321, 3, 1, 'Ulica Farmaceuta 2', 'Novi Sad', 'Srbija')

-- DERMATOLOGISTS
insert into  pharmacy.dermatologist(id, email, first_name, last_name, password, phone_number, user_role, address, city, country ) values (1, 'dermatologist1@pharmacy.com', 'Pera', 'Peric', '123', 066123123, 4, 'Ulica Dermatologa 1', 'Novi Sad', 'Srbija')
insert into pharmacy.dermatologist(id, email, first_name, last_name, password, phone_number, user_role, address, city, country) values (2, 'dermatologist2@pharmacy.com', 'Tanja', 'Tanjic', '123', 066321321, 4, 'Ulica Dermatologa 2', 'Novi Sad', 'Srbija')
insert into  pharmacy.pharmacy_dermatologist(pharmacy_id,dermatologist_id) values (1,1)
insert into  pharmacy.pharmacy_dermatologist(pharmacy_id,dermatologist_id) values (2,2)

-- PHARMACY ADMIN
INSERT INTO pharmacy.pharmacy_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,pharmacy_id,prvi_put_logovan) values (1,'Alekse Santica 48','Novi Sad','Srbija','pharmacyadmin1@pharmacy.com','Pera','Peric','123',061123123,1,1,1)

-- MEDICINES AND REPLACEMENTS
insert into pharmacy.medicine(id, code, contraindications, name, type) values (1, 0110, 'Tahikardija, mucnina, hipotenzija i poremecaj cirkulacije', 'Analgin 500mg tableta', 3)
insert into pharmacy.medicine(id, code, contraindications, name, type) values (2, 0120, 'Aktivni gastrični ili duodenalni ulkus, krvarenje ili perforacija', 'Rapten-K 50mg obložena tableta', 3)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (1,2)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (2,1)

insert into pharmacy.medicine(id, code, contraindications, name, type) values (3, 0210, 'Preosetljivost na paracetamol ili na bilo koju od pomoćnih supstanci ', 'Febricet 500mg tableta', 6)
insert into pharmacy.medicine(id, code, contraindications, name, type) values (4, 0220, 'Preosetljivost na acetilsalicilnu kiselinu ili druge saliciliate ili bilo koju od pomoćnih supstanci ovog
leka', 'Aspirin 500 500mg tableta', 6)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (3,4)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (4,3)

insert into pharmacy.medicine(id, code, contraindications, name, type) values (5, 0310, 'Preosetljivost na aktivnu supstancu, na bilo koju od pomoćnih supstanci koje ulaze u sastav leka, na
hidroksizin ili bilo koji derivat piperazina.', 'Xyzal 5mg film tableta', 1)
insert into pharmacy.medicine(id, code, contraindications, name, type) values (6, 0320, 'Alergijski rinitis, hronična idiopatska urtikarija', 'Cetirizin 10mg film tableta', 1)
insert into pharmacy.medicine(id, code, contraindications, name, type) values (7, 0330, 'Alergijski rinitis, hronična idiopatska urtikarija', 'ALERGOSAN 10mg film tableta', 1)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (5,6)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (6,5)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (6,7)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (7,6)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (5,7)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (7,5)

insert into pharmacy.medicine(id, code, contraindications, name, type) values (8, 0410, 'Nema', 'Alpha D3 0.5mcg kapsula, meka', 5)
insert into pharmacy.medicine(id, code, contraindications, name, type) values (9, 0420, 'Nema', 'B-Complex obložena tableta', 5)
insert into pharmacy.medicine(id, code, contraindications, name, type) values (10, 0430, 'Nema', 'Ca-C 1000 Calvive šumeća tableta', 5)
insert into pharmacy.medicine(id, code, contraindications, name, type) values (11, 0440, 'Nema', 'Kalcijum karbonat Alkaloid 1g tableta', 5)
insert into pharmacy.medicine(id, code, contraindications, name, type) values (12, 0450, 'Nema', 'Kalii chloridi 1g prašak za oralni rastvor', 5)
insert into pharmacy.medicine(id, code, contraindications, name, type) values (13, 0460, 'Nema', 'Elevit pronatal film tableta', 5)

--ALLERGIES
insert into pharmacy.user_allergies(user_id, allergies_id) values (1,1)