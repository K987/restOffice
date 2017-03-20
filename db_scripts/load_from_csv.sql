COPY cost_centers(cost_center_name,cost_center_default)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/cost_centers.csv' DELIMITER ',' CSV HEADER;

COPY shifts(shift_start_d,shift_start_t,shift_duration)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/shifts.csv' DELIMITER ',' CSV HEADER;

COPY employees(employee_name,employee_default_position,employee_active,employee_default_hourly_wage)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/employees.csv' DELIMITER ',' CSV HEADER;

COPY employee_shift(employee_shift_employee_id,employee_shift_shift_id,employee_shift_actual_start,employee_shift_actual_end,employee_shift_actual_position)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/employee_shift.csv' DELIMITER ',' CSV HEADER;

COPY daily_incomes(daily_income_employee_shift,daily_pos_sum,daily_income_card,daily_income_cash)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/daily_incomes.csv' DELIMITER ',' CSV HEADER;

COPY exp_types(exp_type_name,exp_type_prod_related)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/exp_types.csv' DELIMITER ',' CSV HEADER;

COPY users(user_id,user_name,user_role,user_pwd)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/users.csv' DELIMITER ',' CSV HEADER;

COPY partners(partner_name,partner_account,partner_contact_name,partner_contact_email,partner_contact_phone,partner_technical)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/partners.csv' DELIMITER ',' CSV HEADER;

COPY expenses(expense_doc_id,expense_doc_type,expense_issuer,expense_payment_method,expense_gross_amount,expense_description,expense_recieved_date,expense_expiry_date,expense_payed_date,expense_acc_per_start,expense_acc_per_end,expense_costcenter,expense_type)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/expenses.csv' DELIMITER ',' CSV HEADER;

COPY inc_types(inc_type_name,inc_type_prod_related)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/inc_types.csv' DELIMITER ',' CSV HEADER;

COPY registers(register_id,register_type)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/registers.csv' DELIMITER ',' CSV HEADER;

COPY register_closes(register_close_register_id,register_close_no,register_close_date,register_close_amt)
FROM '/Volumes/MacHDD1/oejee2016autumn/projects/restoffice/db_scripts/register_closes.csv' DELIMITER ',' CSV HEADER;
