GRANT SELECT, INSERT, UPDATE, DELETE ON
cost_category,
cost_center_category,
daily_close,
daily_close_detail,
deposit_box,
employee,
employee_shift,
employee_shift_income,
expense,
expense_detail,
income_category,
income_form_category,
job_category,
partner,
partner_contact,
register,
register_category,
register_close,
ro_user,
transition,
income,
income_detail
TO ro_crud_role;

GRANT USAGE, SELECT, UPDATE ON
cost_category_cost_category_id_seq,
cost_center_category_cost_center_category_id_seq,
daily_close_daily_close_id_seq,
daily_close_detail_daily_close_detail_id_seq,
daily_close_x_income_daily_close_x_income_id_seq,
deposit_box_deposit_box_id_seq,
employee_employee_id_seq,
employee_shift_employee_shift_id_seq,
employee_shift_income_employee_shift_income_id_seq,
expense_detail_expense_detail_id_seq,
expense_expense_id_seq,
income_category_income_category_id_seq,
income_detail_income_detail_id_seq,
income_form_category_income_form_category_id_seq,
income_income_id_seq,
job_category_job_category_id_seq,
partner_contact_partner_contact_id_seq,
partner_partner_id_seq,
register_category_register_category_id_seq,
register_close_register_close_id_seq,
register_register_id_seq,
ro_user_user_id_seq,
transition_transition_id_seq
to ro_crud_role;
