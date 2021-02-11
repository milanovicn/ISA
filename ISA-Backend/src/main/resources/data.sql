--PHARMACIES
insert into pharmacy.pharmacy(id,address,city,description,name,rate) values (1,'Roze Luksemburg 5','Pozarevac','Pozarevacka apoteka broj 1','Apoteka Milanovic',5)
insert into pharmacy.pharmacy(id,address,city,description,name,rate) values (2,'Oslobodjenja 24','Kucevo','Kucevljanska apoteka broj 1','Apoteka Kljucevic',5)
insert into pharmacy.pharmacy(id,address,city,description,name,rate) values (3,'Njegoseva 16','Banja Koviljaca','Banjokoviljcanska apoteka broj 1','Apoteka Mitrovic',5)
insert into pharmacy.pharmacy(id,address,city,description,name,rate) values (4,'Alekse Santica 2','Loznica','Loznicka apoteka broj 1','Apoteka Petrovic',5)
insert into pharmacy.pharmacy(id,address,city,description,name,rate) values (5,'Knez Mihajlova 7','Beograd','Beogradska apoteka broj 1','Apoteka Stefanovic',5)
insert into pharmacy.pharmacy(id,address,city,description,name,rate) values (6,'Drinska 9','Smederevo','Smederevska apoteka broj 1','Apoteka Zugic',5)


-- PATIENTS
insert into pharmacy.user(id, first_name, last_name, email, phone_number, password, address, city, country,  user_role, prvi_put_logovan) values (1, 'Mika', 'Mikic', 'patient1@pharmacy.com', 0691116111, '123', 'Pacijentica 11/11', 'Novi Sad', 'Srbija', 2,0)
insert into pharmacy.user(id, first_name, last_name, email, phone_number, password, address, city, country,  user_role,prvi_put_logovan) values (2, 'Zika', 'Zikic', 'patient2@pharmacy.com', 0692226222, '123', 'Pacijentica 22/22', 'Beograd', 'Srbija', 2,0)
insert into pharmacy.user(id, first_name, last_name, email, phone_number, password, address, city, country,  user_role,prvi_put_logovan) values (3, 'Petar', 'Petric', 'patient3@pharmacy.com', 0692226223, '123', 'Pacijentica 22/22', 'Lazarevac', 'Srbija', 2,0)
insert into pharmacy.user(id, first_name, last_name, email, phone_number, password, address, city, country,  user_role,prvi_put_logovan) values (4, 'Milos', 'Misic', 'patient4@pharmacy.com', 0692226224, '123', 'Pacijentica 22/22', 'Pirot', 'Srbija', 2,0)
insert into pharmacy.user(id, first_name, last_name, email, phone_number, password, address, city, country,  user_role,prvi_put_logovan) values (5, 'Stefan', 'Stefanovic', 'patient5@pharmacy.com', 0692226225, '123', 'Pacijentica 22/22', 'Loznica', 'Srbija', 2,0)
insert into pharmacy.user(id, first_name, last_name, email, phone_number, password, address, city, country,  user_role,prvi_put_logovan) values (6, 'Igor', 'Ilic', 'patient6@pharmacy.com', 0692226226, '123', 'Pacijentica 22/22', 'Sabac', 'Srbija', 2,0)




-- PHARMACISTS
insert into  pharmacy.pharmacist(id, email, first_name, last_name, password, phone_number, user_role, pharmacy_id, address, city, country, rate, prvi_put_logovan) values (1, 'pharmacist1@pharmacy.com', 'Sanja', 'Sanjic', '123', 0651234567, 3, 1, 'Ulica Farmaceuta 1', 'Novi Sad', 'Srbija',4, 1)
insert into pharmacy.pharmacist(id, email, first_name, last_name, password, phone_number, user_role, pharmacy_id, address, city, country, rate, prvi_put_logovan) values (2, 'pharmacist2@pharmacy.com', 'Alvaro', 'Sanchez', '123', 065763421, 3, 1, 'Ulica Farmaceuta 2', 'Novi Sad', 'Srbija',5, 1)
insert into pharmacy.pharmacist(id, email, first_name, last_name, password, phone_number, user_role, pharmacy_id, address, city, country, rate, prvi_put_logovan) values (3, 'pharmacist3@pharmacy.com', 'Stefan', 'Stefanovic', '123', 0657654321, 3, 2, 'Ulica Farmaceuta 3', 'Novi Sad', 'Srbija',3, 1)
insert into pharmacy.pharmacist(id, email, first_name, last_name, password, phone_number, user_role, pharmacy_id, address, city, country, rate, prvi_put_logovan) values (4, 'pharmacist4@pharmacy.com', 'Igor', 'Loncarevic', '123', 0657654331, 3, 3, 'Ulica Farmaceuta 4', 'Novi Sad', 'Srbija',3, 1)
insert into pharmacy.pharmacist(id, email, first_name, last_name, password, phone_number, user_role, pharmacy_id, address, city, country, rate, prvi_put_logovan) values (5, 'pharmacist5@pharmacy.com', 'Nora', 'Gajic', '123', 0657654311, 3, 4, 'Ulica Farmaceuta 5', 'Novi Sad', 'Srbija',3, 1)
insert into pharmacy.pharmacist(id, email, first_name, last_name, password, phone_number, user_role, pharmacy_id, address, city, country, rate, prvi_put_logovan) values (6, 'pharmacist6@pharmacy.com', 'Leonid', 'Mitrovic', '123', 0657654781, 3, 5, 'Ulica Farmaceuta 6', 'Novi Sad', 'Srbija',3, 1)



INSERT INTO pharmacy.pharmacy_pharmacist(pharmacy_id, pharmacist_id) VALUES (1,1)
INSERT INTO pharmacy.pharmacy_pharmacist(pharmacy_id, pharmacist_id) VALUES (1,2)
INSERT INTO pharmacy.pharmacy_pharmacist(pharmacy_id, pharmacist_id) VALUES (2,3)
INSERT INTO pharmacy.pharmacy_pharmacist(pharmacy_id, pharmacist_id) VALUES (3,4)
INSERT INTO pharmacy.pharmacy_pharmacist(pharmacy_id, pharmacist_id) VALUES (1,5)
INSERT INTO pharmacy.pharmacy_pharmacist(pharmacy_id, pharmacist_id) VALUES (1,6)

-- DERMATOLOGISTS
insert into  pharmacy.dermatologist(id,email, first_name, last_name, password, phone_number, user_role, address, city, country, rate, prvi_put_logovan) values (1, 'dermatologist1@pharmacy.com', 'Pera', 'Peric', '123', 066123123, 4, 'Ulica Dermatologa 1', 'Novi Sad', 'Srbija',2, 1)
insert into  pharmacy.dermatologist(id, email, first_name, last_name, password, phone_number, user_role, address, city, country, rate, prvi_put_logovan) values (2, 'dermatologist2@pharmacy.com', 'Ivan', 'Radic', '123', 06666666, 4, 'Ulica Dermatologa 2', 'Novi Sad', 'Srbija',5, 1)
insert into pharmacy.dermatologist(id, email, first_name, last_name, password, phone_number, user_role, address, city, country, rate, prvi_put_logovan) values (3, 'dermatologist3@pharmacy.com', 'Marija', 'Pavic', '123', 066321329, 4, 'Ulica Dermatologa 3', 'Novi Sad', 'Srbija',4, 1)
insert into pharmacy.dermatologist(id, email, first_name, last_name, password, phone_number, user_role, address, city, country, rate, prvi_put_logovan) values (4, 'dermatologist4@pharmacy.com', 'Kristina', 'Djukic', '123', 066321320, 4, 'Ulica Dermatologa 4', 'Novi Sad', 'Srbija',4, 1)
insert into pharmacy.dermatologist(id, email, first_name, last_name, password, phone_number, user_role, address, city, country, rate, prvi_put_logovan) values (5, 'dermatologist5@pharmacy.com', 'Tomica', 'Tanjic', '123', 066321324, 4, 'Ulica Dermatologa 5', 'Novi Sad', 'Srbija',4, 1)
insert into pharmacy.dermatologist(id, email, first_name, last_name, password, phone_number, user_role, address, city, country, rate, prvi_put_logovan) values (6, 'dermatologist6@pharmacy.com', 'Milos', 'Rajkovic', '123', 066321323, 4, 'Ulica Dermatologa 6', 'Novi Sad', 'Srbija',4, 1)



INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('1', '1', '1', '0')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('2', '1', '1', '1')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('3', '1', '1', '2')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('4', '1', '1', '3')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('5', '1', '1', '4')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('6', '1', '2', '2')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('7', '1', '2', '3')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('8', '1', '2', '4')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('9', '2', '1', '0')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('10', '2', '1', '1')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('11', '3', '2', '0')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('12', '3', '3', '0')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('13', '4', '4', '0')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('14', '5', '5', '0')
INSERT INTO pharmacy.dermatologist_schedule(id, dermatologist_id, pharmacy_id, work_day) VALUES ('15', '6', '6', '0')



-- PHARMACY ADMIN
INSERT INTO pharmacy.pharmacy_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,pharmacy_id,prvi_put_logovan) values (1,'Alekse Santica 1','Novi Sad','Srbija','pharmacyadmin1@pharmacy.com','Marko','Markovic','123',061123123,1,1,1)
INSERT INTO pharmacy.pharmacy_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,pharmacy_id,prvi_put_logovan) values (2,'Alekse Santica 2','Novi Sad','Srbija','pharmacyadmin2@pharmacy.com','Marija','Maksic','123',061123123,1,2,1)
INSERT INTO pharmacy.pharmacy_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,pharmacy_id,prvi_put_logovan) values (3,'Alekse Santica 3','Novi Sad','Srbija','pharmacyadmin3@pharmacy.com','Lazar','Sajic','123',061123123,1,3,1)
INSERT INTO pharmacy.pharmacy_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,pharmacy_id,prvi_put_logovan) values (4,'Alekse Santica 4','Novi Sad','Srbija','pharmacyadmin4@pharmacy.com','Igor','Savic','123',061123123,1,4,1)
INSERT INTO pharmacy.pharmacy_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,pharmacy_id,prvi_put_logovan) values (5,'Alekse Santica 5','Novi Sad','Srbija','pharmacyadmin5@pharmacy.com','Jovana','Peric','123',061123123,1,5,1)
INSERT INTO pharmacy.pharmacy_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,pharmacy_id,prvi_put_logovan) values (6,'Alekse Santica 6','Novi Sad','Srbija','pharmacyadmin6@pharmacy.com','Pera','Peric','123',061123123,1,6,1)




-- MEDICINES AND REPLACEMENTS
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (1, 0110, 'Tahikardija, mucnina, hipotenzija i poremecaj cirkulacije', 'Analgin 500mg tableta', 3, 0, 1, 'Galenika Beograd', 'Sastojci 1')
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (2, 0120, 'Aktivni gastrični ili duodenalni ulkus, krvarenje ili perforacija', 'Rapten-K 50mg obložena tableta', 3, 1, 1, 'Galenika Beograd', 'Sastojci 2')
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (1,2)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (2,1)


insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (3, 0210, 'Preosetljivost na paracetamol ili na bilo koju od pomoćnih supstanci ', 'Febricet 500mg tableta', 6, 0, 1, 'Galenika Beograd', 'Sastojci 3')
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (4, 0220, 'Preosetljivost na acetilsalicilnu kiselinu ili druge saliciliate ili bilo koju od pomoćnih supstanci ovog leka', 'Aspirin 500 500mg tableta', 6, 0,0 , 'Galenika Beograd', 'Sastojci 3')
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (3,3)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (3,4)



insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (5, 0310, 'Preosetljivost na aktivnu supstancu, na bilo koju od pomoćnih supstanci koje ulaze u sastav leka, na hidroksizin ili bilo koji derivat piperazina.', 'Xyzal 5mg film tableta', 1, 1, 0, 'Galenika Beograd', 'Sastojci 4')
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (6, 0320, 'Alergijski rinitis, hronična idiopatska urtikarija', 'Cetirizin 10mg film tableta', 1, 0, 1, 'Galenika Beograd', 'Sastojci 5')
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (7, 0330, 'Alergijski rinitis, hronična idiopatska urtikarija', 'ALERGOSAN 10mg film tableta', 1, 0, 0, 'Galenika Beograd', 'Sastojci 6')
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (5,6)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (6,5)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (6,7)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (7,6)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (5,7)
insert into  pharmacy.medicine_replacement(medicine_id,replacement_id) values (7,5)


insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (8, 0410, 'Nema', 'Alpha D3 0.5mcg kapsula, meka', 5, 1, 0, 'Galenika Beograd', 'Sastojci 8')
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (9, 0420, 'Nema', 'B-Complex obložena tableta', 5, 1, 0, 'Galenika Beograd', 'Sastojci 9')
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (10, 0430, 'Nema', 'Ca-C 1000 Calvive šumeća tableta', 5, 1, 0, 'Galenika Beograd', 'Sastojci 10')
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (11, 0440, 'Nema', 'Kalcijum karbonat Alkaloid 1g tableta', 5, 1, 0, 'Galenika Beograd', 'Sastojci 11')
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (12, 0450, 'Nema', 'Kalii chloridi 1g prašak za oralni rastvor', 5, 3, 0, 'Galenika Beograd', 'Sastojci 12')
insert into pharmacy.medicine(id, code, contraindications, name, type, form, prescription, manufacturer, ingredients) values (13, 0460, 'Nema', 'Elevit pronatal film tableta', 5, 1, 0, 'Galenika Beograd', 'Sastojci 13')

--ALLERGIES
insert into pharmacy.user_allergies(user_id, allergies_id) values (1,1)
insert into pharmacy.user_allergies(user_id, allergies_id) values (1,7)
insert into pharmacy.user_allergies(user_id, allergies_id) values (1,5)
insert into pharmacy.user_allergies(user_id, allergies_id) values (2,5)
insert into pharmacy.user_allergies(user_id, allergies_id) values (2,3)
insert into pharmacy.user_allergies(user_id, allergies_id) values (3,1)
insert into pharmacy.user_allergies(user_id, allergies_id) values (3,8)
insert into pharmacy.user_allergies(user_id, allergies_id) values (4,2)
insert into pharmacy.user_allergies(user_id, allergies_id) values (4,5)
insert into pharmacy.user_allergies(user_id, allergies_id) values (5,9)
insert into pharmacy.user_allergies(user_id, allergies_id) values (5,2)
insert into pharmacy.user_allergies(user_id, allergies_id) values (6,1)
insert into pharmacy.user_allergies(user_id, allergies_id) values (6,2)



-- SYSTEM ADMIN
insert into pharmacy.system_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,prvi_put_logovan) values (1,'Ulica Sys Admina 1','Novi Sad','Srbija','sysadmin1@pharmacy.com','Mina','Minic','123',061123122,0,1)
insert into pharmacy.system_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,prvi_put_logovan) values (2,'Ulica Sys Admina 2','Novi Sad','Srbija','sysadmin2@pharmacy.com','Nena','Gajic','123',061123121,0,1)
insert into pharmacy.system_admin(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,prvi_put_logovan) values (3,'Ulica Sys Admina 3','Novi Sad','Srbija','sysadmin3@pharmacy.com','Dalija','Pavic','123',06112318,0,1)



-- SUPPLIER
insert into pharmacy.supplier(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,prvi_put_logovan) values (1,'Ulica Dobavljaca 1','Novi Sad','Srbija','supplier1@pharmacy.com','Pika','Pikic','123',061123123,5,1)
insert into pharmacy.supplier(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,prvi_put_logovan) values (2,'Ulica Dobavljaca 2','Novi Sad','Srbija','supplier2@pharmacy.com','Kika','Kikic','123',061123123,5,1)
insert into pharmacy.supplier(id,address,city,country,email,first_name,last_name,password,phone_number,user_role,prvi_put_logovan) values (3,'Ulica Dobavljaca 3','Novi Sad','Srbija','supplier3@pharmacy.com','Tika','Tikic','123',061123123,5,1)



-- MEDICINE STOCKS IN PHARMACY
INSERT INTO pharmacy.pharmacy_stock(id, in_stock, medicine_id, medicine_name, pharmacy_id, reserved) VALUES ('1', '15', '1','Analgin 500mg tableta', '1', '0')
INSERT INTO pharmacy.pharmacy_stock(id, in_stock, medicine_id, medicine_name, pharmacy_id, reserved) VALUES ('2', '60', '2', 'Rapten-K 50mg obložena tableta', '1', '0')
INSERT INTO pharmacy.pharmacy_stock(id, in_stock, medicine_id, medicine_name, pharmacy_id, reserved) VALUES ('3', '40', '3', 'Febricet 500mg tableta', '1', '0')
INSERT INTO pharmacy.pharmacy_stock(id, in_stock, medicine_id, medicine_name, pharmacy_id, reserved) VALUES ('4', '2', '4', 'Aspirin 500 500mg tableta', '1', '0')
INSERT INTO pharmacy.pharmacy_stock(id, in_stock, medicine_id, medicine_name, pharmacy_id, reserved) VALUES ('5', '20', '5', 'Xyzal 5mg film tableta', '1', '0')
INSERT INTO pharmacy.pharmacy_stock(id, in_stock, medicine_id, medicine_name, pharmacy_id, reserved) VALUES ('6', '1', '6', 'Cetirizin 10mg film tableta', '1', '0')
INSERT INTO pharmacy.pharmacy_stock(id, in_stock, medicine_id, medicine_name, pharmacy_id, reserved) VALUES ('7', '4', '7', 'ALERGOSAN 10mg film tableta', '1', '0')

-- ORDERS
INSERT INTO pharmacy.orders(id, deadline, order_status, pharmacy_admin_id, pharmacy_id, pharmacy_name) VALUES ('1', '2021-02-02', '0', '1', '1', 'Apoteka Milanovic')
INSERT INTO pharmacy.orders(id, deadline, order_status, pharmacy_admin_id, pharmacy_id, pharmacy_name) VALUES ('2', '2021-04-05', '0', '1', '1', 'Apoteka Milanovic')

-- ORDER ITEMS
INSERT INTO pharmacy.order_item(id, medicine_id, medicine_name, order_id, quantity) VALUES ('1', '1', 'Analgin 500mg tableta', '1', '200')
INSERT INTO pharmacy.order_item(id, medicine_id, medicine_name, order_id, quantity) VALUES ('2', '2', 'Rapten-K 50mg obložena tableta', '1', '100')
INSERT INTO pharmacy.order_item(id, medicine_id, medicine_name, order_id, quantity) VALUES ('3', '3', 'Febricet 500mg tableta', '1', '150')
INSERT INTO pharmacy.order_item(id, medicine_id, medicine_name, order_id, quantity) VALUES ('4', '4', 'Aspirin 500 500mg tableta', '2', '300')
INSERT INTO pharmacy.order_item(id, medicine_id, medicine_name, order_id, quantity) VALUES ('5', '5', 'Xyzal 5mg film tableta', '2', '50')


-- ORDER OFFER

INSERT INTO pharmacy.order_offer (id, delivery_date, order_id, price, status, supplier_id) VALUES ('1', '2021-01-05', '1', '555', '0', '1')
INSERT INTO pharmacy.order_offer (id, delivery_date, order_id, price, status, supplier_id) VALUES ('2', '2021-02-22', '1', '222', '0', '2')
INSERT INTO pharmacy.order_offer (id, delivery_date, order_id, price, status, supplier_id) VALUES ('3', '2021-03-02', '1', '333', '0', '3')



-- ACTIONS
insert into pharmacy.actions(id, pharmacy_id, date_from, date_to, description) values ('1', '1', '2021-02-01', '2021-02-28', 'February action- Free eye exam in our pharmacy!')
insert into pharmacy.actions(id, pharmacy_id, date_from, date_to, description) values ('2', '1', '2021-03-01', '2021-03-31', 'March action- Free blood sugar measuring!')
insert into pharmacy.actions(id, pharmacy_id, date_from, date_to, description) values ('3', '1', '2021-03-01', '2021-03-31', 'March action- Free blood sugar measuring!')
insert into pharmacy.actions(id, pharmacy_id, date_from, date_to, description) values ('4', '2', '2021-03-01', '2021-03-31', 'March action- Free blood sugar measuring!')
insert into pharmacy.actions(id, pharmacy_id, date_from, date_to, description) values ('5', '3', '2021-03-01', '2021-03-31', 'March action- Free blood sugar measuring!')
insert into pharmacy.actions(id, pharmacy_id, date_from, date_to, description) values ('6', '4', '2021-03-01', '2021-03-31', 'March action- Free blood sugar measuring!')
insert into pharmacy.actions(id, pharmacy_id, date_from, date_to, description) values ('7', '5', '2021-03-01', '2021-03-31', 'March action- Free blood sugar measuring!')
insert into pharmacy.actions(id, pharmacy_id, date_from, date_to, description) values ('8', '6', '2021-03-01', '2021-03-31', 'March action- Free blood sugar measuring!')
insert into pharmacy.actions(id, pharmacy_id, date_from, date_to, description) values ('9', '1', '2021-04-01', '2021-04-31', 'April action- 2+1 gratis on eyeliners !')




