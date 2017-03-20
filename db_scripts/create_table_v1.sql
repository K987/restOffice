--create tables and configure
CREATE TABLE exp_types(
  exp_type_ID SERIAL PRIMARY KEY,
  exp_type_name VARCHAR(100) NOT NULL UNIQUE,
  exp_type_prod_related BOOLEAN NOT NULL
  );
ALTER TABLE exp_types OWNER TO postgres;
--CREATE UNIQUE INDEX UI_EXPENSE_NAME ON expense_types USING btree (expense_t_name);

CREATE TABLE inc_types(
  inc_type_ID SERIAL PRIMARY KEY,
  inc_type_name VARCHAR(100) NOT NULL UNIQUE,
  inc_type_prod_related BOOLEAN NOT NULL
  );
ALTER TABLE inc_types OWNER TO postgres;
--CREATE UNIQUE INDEX UI_INCOME_NAME ON income_types USING btree (income_t_name);

/*
CREATE TABLE doc_types(
  doc_type_ID SERIAL PRIMARY KEY,
  doc_type_name VARCHAR(100) NOT NULL UNIQUE,
  doc_type_internal BOOLEAN NOT NULL,
  doc_type_final BOOLEAN NOT NULL
  );
ALTER TABLE doc_types OWNER TO postgres;
--CREATE UNIQUE INDEX UI_DOC_NAME ON doc_types USING btree (doc_type_name);
*/

CREATE TABLE partners(
  partner_ID SERIAL PRIMARY KEY,
  partner_account VARCHAR(100) UNIQUE,
  partner_name VARCHAR(100) NOT NULL,
  partner_contact_name VARCHAR(100),
  partner_contact_email VARCHAR(100),
  partner_contact_phone VARCHAR(100),
  partner_technical BOOLEAN NOT NULL
  );
ALTER TABLE partners OWNER TO postgres;

/*
CREATE TABLE payment_methods(
  payment_m_ID SERIAL PRIMARY KEY,
  payment_m_name VARCHAR(100) NOT NULL UNIQUE,
  payment_m_postponed BOOLEAN NOT NULL
  );
ALTER TABLE payment_methods OWNER TO postgres;
--CREATE UNIQUE INDEX UI_PAYMENT_METHOD_NAME ON payment_methods btree (payment_m_name);
*/

CREATE TABLE cost_centers(
  cost_center_ID SERIAL PRIMARY KEY,
  cost_center_name VARCHAR(100) NOT NULL UNIQUE,
  cost_center_default BOOLEAN NOT NULL
  );
ALTER TABLE cost_centers OWNER TO postgres;
--CREATE UNIQUE INDEX UI_CC_NAME ON cost_centers USING btree (costcenter_name);

CREATE TABLE users(
  user_ID VARCHAR(50) PRIMARY KEY,
  user_PWD VARCHAR(50) NOT NULL,
  user_name VARCHAR(100),
  user_role VARCHAR(50)
  );
ALTER TABLE users OWNER TO postgres;

CREATE TABLE expenses(
  expense_doc_ID VARCHAR(100),
  expense_doc_type INTEGER NOT NULL,
  expense_issuer INTEGER NOT NULL,
  expense_payment_method INTEGER NOT NULL,
  expense_gross_amount NUMERIC NOT NULL,
  expense_description VARCHAR(200),
  expense_recieved_date DATE NOT NULL,
  expense_expiry_date DATE,
  expense_payed_date DATE,
  expense_acc_per_start DATE,
  expense_acc_per_end DATE,
  expense_costcenter INTEGER NOT NULL,
  expense_type INTEGER NOT NULL,
  expense_last_modified_by VARCHAR(50),
  expense_last_modified_dt TIMESTAMP,
  CONSTRAINT PK_EXP PRIMARY KEY (expense_doc_ID),
  CONSTRAINT FK_ECC FOREIGN KEY (expense_costcenter) REFERENCES
  cost_centers (cost_center_ID),
  CONSTRAINT FK_ETY FOREIGN KEY (expense_type) REFERENCES
  exp_types (exp_type_ID),
  CONSTRAINT FK_EIS FOREIGN KEY (expense_issuer) REFERENCES
  partners (partner_ID),
  CONSTRAINT FK_EXP_MODIFIER FOREIGN KEY (expense_last_modified_by) REFERENCES
  users (user_ID)
);
ALTER TABLE expenses OWNER TO postgres;


CREATE TABLE incomes(
  income_doc_ID VARCHAR(100),
  income_doc_type INTEGER NOT NULL,
  income_liable INTEGER NOT NULL,
  income_payment_method INTEGER NOT NULL,
  income_gross_amount NUMERIC NOT NULL,
  income_description VARCHAR(200),
  income_issue_date DATE NOT NULL,
  income_expiry_date DATE,
  income_payed_date DATE,
  income_acc_per_start DATE,
  income_acc_per_end DATE,
  income_type INTEGER NOT NULL,
  income_last_modified_by VARCHAR(50),
  income_last_modified_dt TIMESTAMP,
  CONSTRAINT PK_INC PRIMARY KEY (income_doc_ID),
  CONSTRAINT FK_ITY FOREIGN KEY (income_type) REFERENCES
  inc_types (inc_type_ID),
  CONSTRAINT FK_ILI FOREIGN KEY (income_liable) REFERENCES
  partners (partner_ID),
  CONSTRAINT FK_IMB FOREIGN KEY (income_last_modified_by) REFERENCES
  users (user_ID)
  );
ALTER TABLE incomes OWNER TO postgres;

CREATE TABLE employees(
  employee_ID SERIAL PRIMARY KEY,
  employee_name VARCHAR(100) NOT NULL,
  employee_default_position INTEGER NOT NULL,
  employee_active BOOLEAN NOT NULL,
  employee_default_hourly_wage NUMERIC
  );
ALTER TABLE employees OWNER TO postgres;

CREATE TABLE shifts(
  shift_ID SERIAL PRIMARY KEY,
  shift_start_d DATE NOT NULL,
  shift_start_t TIME NOT NULL,
  shift_duration NUMERIC NOT NULL
  );
ALTER TABLE shifts OWNER TO postgres;

CREATE TABLE employee_shift(
  employee_shift_id SERIAL UNIQUE NOT NULL,
  employee_shift_employee_id INTEGER NOT NULL,
  employee_shift_shift_id INTEGER NOT NULL,
  employee_shift_actual_start TIME,
  employee_shift_actual_end TIME,
  employee_shift_actual_position INTEGER,
  CONSTRAINT PK_ES PRIMARY KEY (employee_shift_shift_id, employee_shift_employee_id),
  CONSTRAINT FK_EEI FOREIGN KEY (employee_shift_employee_id) REFERENCES
  employees (employee_ID),
  CONSTRAINT FK_ESI FOREIGN KEY (employee_shift_shift_id) REFERENCES
  shifts (shift_ID)
  );
ALTER TABLE employee_shift OWNER TO postgres;

CREATE TABLE daily_incomes(
  daily_income_ID SERIAL PRIMARY KEY,
  daily_income_employee_shift INTEGER NOT NULL UNIQUE,
  daily_pos_sum NUMERIC NOT NULL,
  daily_income_card NUMERIC,
  daily_income_cash NUMERIC,
  CONSTRAINT FK_DRS FOREIGN KEY (daily_income_employee_shift) REFERENCES
  employee_shift (employee_shift_ID)
  );
ALTER TABLE daily_incomes OWNER TO postgres;

CREATE TABLE registers(
  register_ID VARCHAR(50) PRIMARY KEY,
  register_type INTEGER NOT NULL
  );
ALTER TABLE registers OWNER TO postgres;

CREATE TABLE register_closes(
  register_close_register_ID VARCHAR(50) NOT NULL,
  register_close_No INTEGER NOT NULL,
  register_close_date DATE NOT NULL,
  register_close_amt NUMERIC,
  CONSTRAINT PK_RC PRIMARY KEY (register_close_register_ID, register_close_No),
  CONSTRAINT FK_RID FOREIGN KEY (register_close_register_ID) REFERENCES
  registers (register_ID)
  );
ALTER TABLE register_closes OWNER TO postgres;
