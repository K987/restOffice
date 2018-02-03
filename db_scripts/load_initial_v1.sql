--WARNINING: Foreign key refer to sequential IDs, think twice before you remove a row or modify order
--id starts 1

--uses seq_id
INSERT INTO expense_types (expense_t_name,expense_t_production) VALUES ('áru', true);
INSERT INTO expense_types (expense_t_name,expense_t_production) VALUES ('bérköltség', true);
INSERT INTO expense_types (expense_t_name,expense_t_production) VALUES ('beruházás', false);
INSERT INTO expense_types (expense_t_name,expense_t_production) VALUES ('tulajdonosi kifizetés', false);
INSERT INTO expense_types (expense_t_name,expense_t_production) VALUES ('szolgáltatás', false);
INSERT INTO expense_types (expense_t_name,expense_t_production) VALUES ('eszköz', true);

--uses seq_id
INSERT INTO income_types (income_t_name, income_t_production) VALUES ('étel-ital', true);
INSERT INTO income_types (income_t_name, income_t_production) VALUES ('szolgáltatás', true);
INSERT INTO income_types (income_t_name, income_t_production) VALUES ('visszáru', false);
INSERT INTO income_types (income_t_name, income_t_production) VALUES ('tulajdonosi befizetés', false);
INSERT INTO income_types (income_t_name, income_t_production) VALUES ('helyszín bérlet', false);

--uses seq_id
INSERT INTO doc_types (doc_type_name, doc_type_final, doc_type_internal) VALUES ('végszámla', true, false);
INSERT INTO doc_types (doc_type_name, doc_type_final, doc_type_internal) VALUES ('rész számla', false, false);
INSERT INTO doc_types (doc_type_name, doc_type_final, doc_type_internal) VALUES ('pénztár bevételi igazolsás', true, false);
INSERT INTO doc_types (doc_type_name, doc_type_final, doc_type_internal) VALUES ('pénztár kifizetési igazolsás', true, true);

--uses seq_id
INSERT INTO partners (partner_name, partner_bank_account, partner_address, partner_contact, parnter_technical)
VALUES ('Konyhai Alapanyag Bt.', 12345, '1111 Kis utca 2', 'hozd@kaja.hu', false);
INSERT INTO partners (partner_name, partner_bank_account, partner_address, partner_contact, parnter_technical)
VALUES ('Ital Szállító Kft.', 54321, '3240 Nagy utca 12', 'hozd@ital.hu', false);
INSERT INTO partners (partner_name, partner_bank_account, partner_address, partner_contact, parnter_technical)
VALUES ('Sajtos Bt.', 43243, '3232 Lajos utca 2', 'hozd@sajt.hu', false);
INSERT INTO partners (partner_name, partner_bank_account, partner_address, partner_contact, parnter_technical)
VALUES ('Boros Kft.', 46542, '3240 Bor utca 12', 'bor@ital.hu', false);
INSERT INTO partners (partner_name, partner_bank_account, partner_address, partner_contact, parnter_technical)
VALUES ('Kis Béla zenész', null, null ,null, false);
INSERT INTO partners (partner_name, partner_bank_account, partner_address, partner_contact, parnter_technical)
VALUES ('napi forgalmi bevétel', null, null, null, true);
INSERT INTO partners (partner_name, partner_bank_account, partner_address, partner_contact, parnter_technical)
VALUES ('bér kifizetés', null, null, null, true);
INSERT INTO partners (partner_name, partner_bank_account, partner_address, partner_contact, parnter_technical)
VALUES ('Rendezvényt tartok Kft', 23142, '2353 Ferenc utca 4', 'rendezveny@rendezveny.hu', true);
INSERT INTO partners (partner_name, partner_bank_account, partner_address, partner_contact, parnter_technical)
VALUES ('Konyvelo bt', 21213, '2353 Lala utca 4', 'konyv@elek.hu', true);

--uses seq_id
INSERT INTO payment_methods (payment_m_name, payment_m_postponed) VALUES ('kp azonnali', false);
INSERT INTO payment_methods (payment_m_name, payment_m_postponed) VALUES ('kp halasztott', false);
INSERT INTO payment_methods (payment_m_name, payment_m_postponed) VALUES ('kp átutalás', false);

--uses seq_id
INSERT INTO cost_centers (costcenter_name, constcenter_production) VALUES ('konyha', true);
INSERT INTO cost_centers (costcenter_name, constcenter_production) VALUES ('étterem', true);
INSERT INTO cost_centers (costcenter_name, constcenter_production) VALUES ('iroda', false);

INSERT INTO users (user_ID, user_PW, user_name, user_role) VALUES ('ügyvez', 'pwd123', 'Kis János', 'ügyvezető');
INSERT INTO users (user_ID, user_PW, user_name, user_role) VALUES ('manyika', 'pwd123', 'Manyi Kriszti', 'gazdaságis');

INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('15311002739',1,'2015-08-01',null,22700,27,1,2,1,'jég',2,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064897',1,'2015-08-01',null,12000,27,1,3,2,'beugrós szakács',8,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064898',1,'2015-08-01',null,25000,27,1,3,5,'dj',6,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064899',1,'2015-08-01',null,20000,27,1,3,2,'fizetés',8,'2016-07-01',null,4,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('IH4EA5177711',1,'2015-08-02',null,8700,27,1,2,1,'pékárú',1,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('0513/00064',1,'2015-08-02',null,1995,27,1,1,1,'paradicsomlé',2,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('IH4SA1908520',1,'2015-08-02',null,139300,27,1,2,1,'kedveskrém',4,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('IH4EA0744727',1,'2015-08-02',null,250190,27,1,3,5,'takarítás',9,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('15311002751',1,'2015-08-02',null,30300,27,1,2,1,'jég',2,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('B12EA8674074',1,'2015-08-02',null,1575,27,1,2,1,'cukor',1,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('15271003416',1,'2015-08-02',null,13500,27,1,2,1,'jég',2,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064900',1,'2015-08-02',null,12500,27,1,1,2,'fizetés',8,'2016-07-01',null,4,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064701',1,'2015-08-02',null,7000,27,1,3,5,'telefon számla kompenzáció 08. ',8,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064702',1,'2015-08-02',null,6000,27,1,2,2,'fizetés',8,'2016-07-01',null,4,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('201502219',1,'2015-08-03','2015-08-21',23000,27,2,2,1,'kávé',4,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('HC4EA1292140',1,'2015-08-03','2015-08-21',13630,18,2,1,1,'sajt',3,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('IH4EA5796748',1,'2015-08-03',null,5825,27,1,1,1,'kenyér',1,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('A12300193/0292/0004',1,'2015-08-03',null,1320,27,1,2,1,'fűszer',1,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('2015/03219',1,'2015-08-03',null,20066,27,1,2,1,'yuzu',4,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('200-08433/2015',1,'2015-08-03',null,38500,27,1,2,1,'üditő',2,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('NQ9LA1373632',1,'2015-08-03',null,137795,27,1,3,5,'könyvelés',9,'2016-07-01',null,2,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('15481002580',1,'2015-08-03',null,19500,27,1,2,1,'jég',2,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064704',1,'2015-08-03',null,5000,27,1,1,2,'fizetés',8,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064705',1,'2015-08-03',null,20000,27,1,3,5,'zene',6,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('15461002905',1,'2015-08-03',null,19500,27,1,2,1,'jég',2,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA07072/2015',1,'2015-08-03',null,63380,27,1,2,1,'gyümölcs',3,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA07070/2015',1,'2015-08-03',null,15055,27,1,1,1,'zöldség',1,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA07071/2015',1,'2015-08-03',null,2540,27,1,1,1,'zöldség',1,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064707',1,'2015-08-03',null,5000,27,1,2,2,'fizetés',8,'2016-07-01',null,4,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064706',1,'2015-08-03',null,20000,27,1,2,2,'fizetés',8,'2016-07-01',null,4,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('CE064708',1,'2015-08-03',null,9000,27,1,2,2,'fizetés bár',8,'2016-07-01',null,4,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('201530080',1,'2015-08-21','2015-08-29',290152,27,3,2,1,'bor',4,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('201527505',1,'2015-07-31','2015-08-08',140331,27,3,2,1,'bor',4,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('201530558',1,'2015-08-25','2015-09-02',23406,27,3,2,1,'bor',4,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('201530562',1,'2015-08-26','2015-09-03',112807,27,3,2,1,'bor',4,null,null,1,'manyika','2015-08-11');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('201526177',1,'2015-07-20','2015-07-29',317666,27,3,2,1,'bor',4,null,543254235243,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA06582/2015',1,'2015-07-22','2015-07-30',47821,27,3,1,1,'gyümölcs',1,null,43214312432,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA06581/2015',1,'2015-07-22','2015-07-30',23774,27,3,1,1,'zöldség',1,null,54325423,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('5897154',1,'2015-07-23','2015-07-28',128872,27,3,2,1,'szesz',2,null,3421431243,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA06620/2015',1,'2015-07-23','2015-07-31',14445,27,3,1,1,'zöldség',1,null,432143124312,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA06621/2015',1,'2015-07-23','2015-07-31',54831,27,3,2,1,'gyümölcs',1,null,431243124,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('201526577',1,'2015-07-23','2015-07-31',67177,27,3,2,1,'bor',2,null,43214312,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA06620/2015',1,'2015-07-23','2015-07-31',3000,27,3,1,1,'zöldség',1,null,431243124,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('201526700',1,'2015-07-24','2015-08-01',367322,27,3,2,1,'bor',1,null,413243125,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA06670/2015',1,'2015-07-24','2015-08-01',10017,27,3,1,1,'zöldség',1,null,43214123,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA06671/2015',1,'2015-07-24','2015-08-01',70150,27,3,2,1,'gyümölcs',1,null,431243124,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('5897491',1,'2015-07-24','2015-07-29',501355,27,3,2,1,'szesz',2,null,431243124,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('201526755',1,'2015-07-24','2015-08-01',119354,27,3,2,1,'bor',4,null,43124124321,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('5897850',1,'2015-07-25','2015-07-30',28263,27,3,2,1,'szesz',2,null,43124124312,1,'ügyvez','2015-08-30');
INSERT INTO expenses (expense_doc_ID, expense_doc_part, expense_due_date,expense_expiry_date,expense_gross_amount,expense_vat, expense_payment_method, expense_costcenter, expense_type, expense_description, expense_beneficiary, expense_accounting_period, expense_proof_of_payment,expense_doc_type,expense_last_modified_by, expense_last_modified_dt) VALUES ('SZA06721/2015',1,'2015-07-25','2015-08-02',49472,27,3,1,1,'zöldség',1,null,43124321,1,'ügyvez','2015-08-30');

INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('443124',1,'2015-08-13','2015-08-30',5432210,27,2,2,5,'esküvő',7,'2015-07-01',null,1,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42224_965-207_967-213_310-97',1,'2015-08-08',null,434000,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42224_965-207_967-213_310-98',1,'2015-08-08',null,11000,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42225_965-208_967-214_310-',1,'2015-08-09',null,712572,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42225_965-208_967-214_310-2',1,'2015-08-09',null,536028,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42225_965-208_967-214_310-3',1,'2015-08-09',null,0,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42225_965-208_967-214_310-4',1,'2015-08-09',null,0,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42226_965-209_967-215_310-981',1,'2015-08-10',null,957433,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42226_965-209_967-215_310-982',1,'2015-08-10',null,143117,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42226_965-209_967-215_310-984',1,'2015-08-10',null,4500,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42227_965-210_967-216_310-996',1,'2015-08-11',null,940861,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42227_965-210_967-216_310-995',1,'2015-08-11',null,406439,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42227_965-210_967-216_310-66',1,'2015-08-11',null,10500,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42227_965-210_967-216_310-9954',1,'2015-08-11',null,0,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42228_965-211_967-_310-100',1,'2015-08-12',null,764646,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42228_965-211_967-_310-10032',1,'2015-08-12',null,324054,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42228_965-211_967-_310-100320',1,'2015-08-12',null,109500,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42228_965-211_967-_310-100321',1,'2015-08-12',null,2500,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');
INSERT INTO incomes (income_doc_ID, income_doc_part, income_due_date, income_expiry_date, income_gross_amount, income_vat, income_payment_method, income_costcenter, income_type, income_description, income_liable, income_accounting_period, income_proof_of_payment, income_doc_type, income_last_modified_by, income_last_modified_dt) VALUES ('42229_965-212_967-_310-101',1,'2015-08-13',null,1564551,27,1,2,1,'üzem',6,null,null,3,'manyika','2016-08-23');