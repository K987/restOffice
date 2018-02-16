CREATE TABLE PARTNER
(
  PARTNER_ID SERIAL NOT NULL,
  LEGAL_NAME VARCHAR(100) NOT NULL,
  SHORT_NAME VARCHAR(50),
  BANK_ACCOUNT_NO VARCHAR(30),
  LEGAL_ADDRESS VARCHAR(200),
  TAX_ID_NO VARCHAR(50),
  VALD_FROM_DTTM DATE NOT NULL,
  VALID_TO_DTTM DATE NOT NULL,
  MODIFICAITON_STATUS_CD BOOL NOT NULL,
  PRIMARY KEY (PARTNER_ID),
  UNIQUE (LEGAL_NAME)
);

CREATE TABLE PARTNER_CONTACT
(
  PARTNER_CONTACT_ID SERIAL NOT NULL,
  CONTACT_PERSON_NAME VARCHAR(50) NOT NULL,
  PHONE_NO VARCHAR(50),
  EMAIL_ADDR VARCHAR(253),
  CELL_PHONE_NO VARCHAR(50),
  PARTNER_ID INTEGER NOT NULL,
  PRIMARY KEY (PARTNER_CONTACT_ID),
  FOREIGN KEY (PARTNER_ID) REFERENCES PARTNER(PARTNER_ID)
);

CREATE TABLE DOCUMENT_TYPE
(
  DOCUMENT_TYPE_ID SERIAL NOT NULL,
  NAME VARCHAR(50) NOT NULL,
  DOCUMENT_TYPE_TYPE_CD INTEGER NOT NULL,
  DESCRIPTION_TXT VARCHAR(500),
  VALID_FROM_DTTM DATE NOT NULL,
  VALID_TO_DTTM DATE NOT NULL,
  MODIFICATION_STATUS_CD BOOL NOT NULL,
  PRIMARY KEY (DOCUMENT_TYPE_ID),
  UNIQUE (NAME)
);

CREATE TABLE PAYMENT_METHOD
(
  PAYMENT_METHOD_ID SERIAL NOT NULL,
  NAME VARCHAR(50) NOT NULL,
  PAYMENT_METHOD_TYPE_CD INTEGER NOT NULL,
  DESCRIPTION_TXT VARCHAR(500),
  VALID_FROM_DTTM DATE NOT NULL,
  VALID_TO_DTTM DATE NOT NULL,
  MODIFICATION_STATUS_CD BOOL NOT NULL,
  PRIMARY KEY (PAYMENT_METHOD_ID),
  UNIQUE (NAME)
);

CREATE TABLE COST_CENTER
(
  COST_CENTER_ID SERIAL NOT NULL,
  NAME VARCHAR(50) NOT NULL,
  DESCRIPTION_TXT VARCHAR(500),
  PARENT_COST_CENTER_ID INTEGER,
  VALID_FROM_DTTM DATE NOT NULL,
  VALID_TO_DTTM DATE NOT NULL,
  MODIFICAITON_STATUS_CD BOOL NOT NULL,
  PRIMARY KEY (COST_CENTER_ID),
  UNIQUE (NAME)
);

CREATE TABLE COST_TYPE
(
  COST_TYPE_ID SERIAL NOT NULL,
  NAME VARCHAR(50) NOT NULL,
  DESCRIPTION_TXT VARCHAR(500),
  VALID_FROM_DTTM DATE NOT NULL,
  VALID_TO_DTTM DATE NOT NULL,
  MODIFICATION_STATUS_CD BOOL NOT NULL,
  PRIMARY KEY (COST_TYPE_ID),
  UNIQUE (NAME)
);

CREATE TABLE INCOME_TYPE
(
  INCOME_TYPE_ID SERIAL NOT NULL,
  NAME VARCHAR(50) NOT NULL,
  VALID_FROM_DTTM DATE NOT NULL,
  VALID_TO_DTTM DATE NOT NULL,
  DESCRIPTION_TXT VARCHAR(500),
  MODIFICATION_STATUS_CD BOOL NOT NULL,
  PRIMARY KEY (INCOME_TYPE_ID),
  UNIQUE (NAME)
);

CREATE TABLE DEPOSIT_BOX
(
  DEPOSIT_BOX_ID SERIAL NOT NULL,
  DEPOSIT_BOX_TYPE_CD INTEGER NOT NULL,
  NAME VARCHAR(50) NOT NULL,
  DESCRIPTION_TXT VARCHAR(500) NOT NULL,
  PRIMARY KEY (DEPOSIT_BOX_ID),
  UNIQUE (NAME)
);

CREATE TABLE PARAM_ALLOWED_PAYMENT_X_DEPOSIT
(
  ALLOWED_PAYMENT_X_DEPOSIT_BOX_ID SERIAL NOT NULL,
  VALID_FROM_DTTM DATE NOT NULL,
  VALID_TO_DTTM DATE NOT NULL,
  DEFAULT_DEPOSIT_FLG BOOL NOT NULL,
  DEPOSIT_BOX_ID INTEGER NOT NULL,
  PAYMENT_METHOD_ID INTEGER NOT NULL,
  MODIFICATION_STATUS_CD BOOL NOT NULL,
  PRIMARY KEY (ALLOWED_PAYMENT_X_DEPOSIT_BOX_ID)
);

CREATE TABLE RO_USER
(
  USER_ID SERIAL NOT NULL,
  PRIMARY KEY (USER_ID)
);

CREATE TABLE TRANSITION
(
  TRANSITION_ID SERIAL NOT NULL,
  TRANSITION_DTTM DATE NOT NULL,
  TRANSITION_AMT NUMERIC(12,2) NOT NULL,
  FROM_DEPOSIT_BOX_ID INTEGER NOT NULL,
  TO_DEPOSIT_BOX_ID INTEGER NOT NULL,
  USER_ID INTEGER NOT NULL,
  PRIMARY KEY (TRANSITION_ID),
  FOREIGN KEY (FROM_DEPOSIT_BOX_ID) REFERENCES DEPOSIT_BOX(DEPOSIT_BOX_ID),
  FOREIGN KEY (TO_DEPOSIT_BOX_ID) REFERENCES DEPOSIT_BOX(DEPOSIT_BOX_ID),
  FOREIGN KEY (USER_ID) REFERENCES RO_USER(USER_ID)
);

CREATE TABLE PAYMENT_DETAIL
(
  PAYMENT_DETAIL_ID SERIAL NOT NULL,
  ISSUE_DT DATE NOT NULL,
  DUE_DT DATE NOT NULL,
  EXPIRY_DT DATE,
  PAID_DT DATE,
  DOCUMENT_TYPE_ID INTEGER NOT NULL,
  PAYMENT_METHOD_ID INTEGER NOT NULL,
  PRIMARY KEY (PAYMENT_DETAIL_ID),
  FOREIGN KEY (DOCUMENT_TYPE_ID) REFERENCES DOCUMENT_TYPE(DOCUMENT_TYPE_ID),
  FOREIGN KEY (PAYMENT_METHOD_ID) REFERENCES PAYMENT_METHOD(PAYMENT_METHOD_ID)
);

CREATE TABLE EXPENSE
(
  EXPENSE_ID SERIAL NOT NULL,
  DOCUMENT_NO VARCHAR(50) NOT NULL,
  PARTNER_ID INTEGER NOT NULL,
  PAYMENT_DETAIL_ID INTEGER NOT NULL,
  CREATE_DTTM DATE NOT NULL,
  DELETE_DTTM DATE,
  CREATE_USER_ID INTEGER NOT NULL,
  DELETE_USER_ID INTEGER,
  PRIMARY KEY (EXPENSE_ID),
  FOREIGN KEY (PARTNER_ID) REFERENCES PARTNER(PARTNER_ID),
  FOREIGN KEY (PAYMENT_DETAIL_ID) REFERENCES PAYMENT_DETAIL(PAYMENT_DETAIL_ID),
  FOREIGN KEY (CREATE_USER_ID) REFERENCES RO_USER(USER_ID),
  FOREIGN KEY (DELETE_USER_ID) REFERENCES RO_USER(USER_ID),
  UNIQUE (DOCUMENT_NO)
);

CREATE TABLE EXPENSE_DETAIL
(
  EXPENSE_DETAIL_ID SERIAL NOT NULL,
  EXPENSE_NET_AMT NUMERIC(12,2),
  EXPENSE_GROSS_AMT NUMERIC(12,2) NOT NULL,
  EXPENSE_VAT_PCT NUMERIC(2,2),
  DESCRIPTION_TXT VARCHAR(500) NOT NULL,
  COST_TYPE_ID INTEGER NOT NULL,
  COST_CENTER_ID INTEGER NOT NULL,
  EXPENSE_ID INTEGER NOT NULL,
  PRIMARY KEY (EXPENSE_DETAIL_ID),
  FOREIGN KEY (COST_TYPE_ID) REFERENCES COST_TYPE(COST_TYPE_ID),
  FOREIGN KEY (COST_CENTER_ID) REFERENCES COST_CENTER(COST_CENTER_ID),
  FOREIGN KEY (EXPENSE_ID) REFERENCES EXPENSE(EXPENSE_ID)
);

CREATE TABLE INCOME
(
  INCOME_ID SERIAL NOT NULL,
  DOCUMENT_NO VARCHAR(50) NOT NULL,
  PAYMENT_DETAIL_ID INTEGER NOT NULL,
  PARTNER_ID INTEGER NOT NULL,
  CREATE_DTTM DATE NOT NULL,
  DELETED_DTTM DATE,
  CREATE_USER_ID INTEGER NOT NULL,
  DELETE_USER_ID INTEGER,
  PRIMARY KEY (INCOME_ID),
  FOREIGN KEY (CREATE_USER_ID) REFERENCES RO_USER(USER_ID),
  FOREIGN KEY (DELETE_USER_ID) REFERENCES RO_USER(USER_ID),
  FOREIGN KEY (PAYMENT_DETAIL_ID) REFERENCES PAYMENT_DETAIL(PAYMENT_DETAIL_ID),
  FOREIGN KEY (PARTNER_ID) REFERENCES PARTNER(PARTNER_ID),
  UNIQUE (DOCUMENT_NO)
);

CREATE TABLE INCOME_DETAIL
(
  INCOME_DETAIL_ID SERIAL NOT NULL,
  INCOME_NET_AMT NUMERIC(12,2),
  INCOME_GROSS_AMT NUMERIC(12,2) NOT NULL,
  INCOME_VAT_PCT NUMERIC(2,2),
  DESCRIPTION_TXT VARCHAR(500) NOT NULL,
  INCOME_ID INTEGER NOT NULL,
  INCOME_TYPE_ID INTEGER NOT NULL,
  PRIMARY KEY (INCOME_DETAIL_ID),
  FOREIGN KEY (INCOME_ID) REFERENCES INCOME(INCOME_ID),
  FOREIGN KEY (INCOME_TYPE_ID) REFERENCES INCOME_TYPE(INCOME_TYPE_ID)
);

CREATE TABLE DEPOSIT_BOX_X_INCOME
(
  DEPOSIT_BOX_X_INCOME_ID SERIAL NOT NULL,
  REGISTERED_DTTM DATE NOT NULL,
  DEPOSIT_BOX_ID INTEGER NOT NULL,
  INCOME_ID INTEGER NOT NULL,
  PRIMARY KEY (DEPOSIT_BOX_X_INCOME_ID),
  FOREIGN KEY (DEPOSIT_BOX_ID) REFERENCES DEPOSIT_BOX(DEPOSIT_BOX_ID),
  FOREIGN KEY (INCOME_ID) REFERENCES INCOME(INCOME_ID)
);

CREATE TABLE DEPOSIT_BOX_X_EXPENSE
(
  DEPOSIT_BOX_X_EXPENSE_ID SERIAL NOT NULL,
  REGISTERED_DTTM DATE NOT NULL,
  DEPOSIT_BOX_ID INTEGER NOT NULL,
  EXPENSE_ID INTEGER NOT NULL,
  PRIMARY KEY (DEPOSIT_BOX_X_EXPENSE_ID),
  FOREIGN KEY (DEPOSIT_BOX_ID) REFERENCES DEPOSIT_BOX(DEPOSIT_BOX_ID),
  FOREIGN KEY (EXPENSE_ID) REFERENCES EXPENSE(EXPENSE_ID)
);
