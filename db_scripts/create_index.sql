CREATE UNIQUE INDEX IF NOT EXISTS I_PARTNER_LEGAL_NAME ON partner(legal_name);

CREATE INDEX IF NOT EXISTS I_PARTNER_CONTACT_CONTACT_NAME ON partner_contact(PARTNER_ID,CONTACT_PERSON_NAME);

CREATE UNIQUE INDEX IF NOT EXISTS UI_DEPOSIT_BOX_NAME ON deposit_box(name);

CREATE UNIQUE INDEX IF NOT EXISTS UI_EXPENSE_DOCUMENT_NO ON expense(partner_id, document_no);

CREATE UNIQUE INDEX IF NOT EXISTS UI_INCOME_DOCUMENT_NO ON income(partner_id, document_no);

CREATE INDEX IF NOT EXISTS I_EXPENSE_DETAIL_EXPENSE_ID ON expense_detail(expense_id);

CREATE INDEX IF NOT EXISTS I_INCOME_DETAIL_INCOME_ID ON income_detail(income_id);

CREATE INDEX IF NOT EXISTS I_REGISTER_REGISTER_NO ON register(register_no);

CREATE INDEX IF NOT EXISTS I_DAILY_CLOSE_CLOSE_DAY_DT ON daily_close(close_day_dt);

CREATE UNIQUE INDEX IF NOT EXISTS UI_DAILY_CLOSE_DETAIL_DAILY_CLOSE_ID ON DAILY_CLOSE_DETAIL(DAILY_CLOSE_ID, INCOME_FORM_CATEGORY_ID);

CREATE UNIQUE INDEX IF NOT EXISTS UI_REGISTER_CLOSE_CLOSE_NO ON REGISTER_CLOSE(CLOSE_NO, REGISTER_ID);

CREATE INDEX IF NOT EXISTS I_REGISTER_CLOSE_CLOSE_DT ON REGISTER_CLOSE(CLOSE_DT);

CREATE UNIQUE INDEX IF NOT EXISTS UI_EMPLOYEE_SHIFT_EMP_DAY_JOB ON EMPLOYEE_SHIFT(EMPLOYEE_ID, DAILY_CLOSE_ID, ACTUAL_JOB_CATEGORY_ID);

CREATE UNIQUE INDEX IF NOT EXISTS UI_EMPLOYEE_SHIFT_INCOME_SHIFT_FORM ON EMPLOYEE_SHIFT_INCOME(EMPLOYEE_SHIFT_ID, INCOME_FORM_ID);
