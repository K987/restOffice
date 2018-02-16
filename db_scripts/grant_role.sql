GRANT SELECT, INSERT, UPDATE ON
cost_center,
cost_type,
daily_close,
daily_close_detail,
deposit_box,
deposit_box_x_expense,
deposit_box_x_income,
document_type,
employee,
employee_shift,
employee_shift_income,
expense,
expense_detail,
income,
income_detail,
income_form_type,
income_type,
job_type,
param_allowed_payment_x_deposit,
param_income_form_x_deposit_box,
partner,
partner_contact,
payment_detail,
payment_method,
register,
register_close,
register_type,
ro_user,
transition
TO ro_crud_role;

GRANT USAGE, SELECT, UPDATE ON
cost_center_cost_center_id_seq,
cost_type_cost_type_id_seq,
daily_close_daily_close_id_seq,
daily_close_detail_daily_close_detail_id_seq,
deposit_box_deposit_box_id_seq,
deposit_box_x_expense_deposit_box_x_expense_id_seq,
deposit_box_x_income_deposit_box_x_income_id_seq,
document_type_document_type_id_seq,
employee_employee_id_seq,
employee_shift_employee_shift_id_seq,
employee_shift_income_employee_shift_income_id_seq,
expense_detail_expense_detail_id_seq,
expense_expense_id_seq,
income_detail_income_detail_id_seq,
income_form_type_income_form_type_id_seq,
income_income_id_seq,
income_type_income_type_id_seq,
job_type_job_type_id_seq,
param_allowed_payment_x_depos_allowed_payment_x_deposit_box_seq,
param_income_form_x_deposit_bo_income_form_x_deposit_box_id_seq,
partner_contact_partner_contact_id_seq,
partner_partner_id_seq,
payment_detail_payment_detail_id_seq,
payment_method_payment_method_id_seq,
register_close_register_close_id_seq,
register_register_id_seq,
register_type_register_type_id_seq,
ro_user_user_id_seq,
transition_transition_id_seq
to ro_crud_role;