package hun.restoffice.persistence.entity.employee;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the employee_shift database table.
 * 
 * @author kalmankostenszky
 */
@Entity
@Table(name = "employee_shift")
@IdClass(EmployeeShiftId.class)
@NamedQueries(value = { @NamedQuery(name = "EmployeeShift.findAll", query = "SELECT es FROM EmployeeShift es"),
		@NamedQuery(name = EmployeeShift.GET_ENTITY_AFTER, query = "SELECT es FROM EmployeeShift es  WHERE employee=:" + EmployeeShift.EMPLOYEE
				+ " AND shift.startDate >:" + EmployeeShift.FROM_DATE),
		@NamedQuery(name = EmployeeShift.FIND_BY_NAME_AND_SHIFT, query = "SELECT es FROM EmployeeShift es WHERE LOWER(employee.name)=:"+EmployeeShift.EMPLOYEE_NAME//
		+" AND shift.id=:"+EmployeeShift.SHIFT_ID),
		@NamedQuery(name = EmployeeShift.GET_BY_ROWID, query = "SELECT es FROM EmployeeShift es WHERE rowId =:"+EmployeeShift.ROWID),
})
public class EmployeeShift implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_ENTITY_AFTER = "EmployeeShift.getEntites";
	public static final String FIND_BY_NAME_AND_SHIFT = "EmployeeShift.findById";
	public static final String GET_BY_ROWID = "EmployeeShift.getByRowId";

	public static final String EMPLOYEE = "employee";
	public static final String FROM_DATE = "fromDate";
	public static final String EMPLOYEE_NAME = "employeeName";
	public static final String SHIFT_ID = "shiftId";
	public static final String ROWID = "rowId";




	// bi-directional many-to-one association to Shift
	@Id
	@ManyToOne
	@JoinColumn(name = "employee_shift_employee_id", referencedColumnName = "employee_id")
	private Employee employee;

	// bi-directional many-to-one association to Shift
	@Id
	@ManyToOne
	@JoinColumn(name = "employee_shift_shift_id", referencedColumnName = "shift_id")
	private Shift shift;

	// TODO: set nullable true in DB
	@Column(name = "employee_shift_actual_start")
	@Temporal(TemporalType.TIME)
	private Date actualStart;

	// TODO: set nullable true in DB
	@Column(name = "employee_shift_actual_end")
	@Temporal(TemporalType.TIME)
	private Date actualEnd;

	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "employee_shift_actual_position")
	private JobPosition actualPosition;
	

	@SequenceGenerator(name = "employee_shift_employee_shift_id_generator", sequenceName = "employee_shift_employee_shift_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_shift_employee_shift_id_generator")
	@Column(name = "employee_shift_id", updatable = false)
	private Integer rowId;

	@Transient
	private int shiftId;
	
	@Transient
	private String employeeName;
	
	public EmployeeShift(){
		
	}
	/**
	 * @param name
	 * @param shiftid
	 * @param rowId 
	 * @param start
	 * @param end
	 * @param position
	 */
	public EmployeeShift(String name, int shiftid, Date start, Date end, int position) {
		this.employeeName = name;
		this.shiftId = shiftid;
		this.actualStart = start;
		this.actualEnd = end;
		this.actualPosition = JobPosition.values()[position];
	}
	
	

	/**
	 * Use only for Shift lookup
	 * @return the shiftId
	 */
	public int getShiftId() {
		return shiftId;
	}



	/**
	 * Use only for Employee lookup
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}



	public Date getActualEnd() {
		return this.actualEnd;
	}

	public void setActualEnd(Date employeeShiftActualEnd) {
		this.actualEnd = employeeShiftActualEnd;
	}

	public JobPosition getActualPosition() {
		return this.actualPosition;
	}

	public void setActualPosition(JobPosition employeeShiftActualPosition) {
		this.actualPosition = employeeShiftActualPosition;
	}

	public Date getActualStart() {
		return this.actualStart;
	}

	public void setActualStart(Date employeeShiftActualStart) {
		this.actualStart = employeeShiftActualStart;
	}

	public Integer getRowId() {
		return this.rowId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
		employee.addEmployeeShift(this);
	}

	public Shift getShift() {
		return this.shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
		shift.addEmployeeShift(this);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("EmployeeShift [employee=%s, shift=%s, actualStart=%s, actualEnd=%s, actualPosition=%s, rowId=%s]",
				employee.getEmployeeName(), shift.getId(), actualStart, actualEnd, actualPosition, rowId);
	}
	
	

}