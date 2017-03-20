package hun.restoffice.persistence.entity.employee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the shifts database table.
 * 
 * @author kalmankostenszky
 * 
 * @TODO maybe set to composite key start date and start time
 */
@Entity
@Table(name = "shifts")
@NamedQueries( value = { 
		@NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s"),
		@NamedQuery(name = Shift.GET_SCHEDULE, query = "SELECT DISTINCT s FROM Shift s LEFT JOIN FETCH s.employeeShifts es JOIN FETCH es.employee WHERE s.startDate BETWEEN :"+Shift.FROM_DATE+" AND :"+Shift.TO_DATE),
		@NamedQuery(name = Shift.GET_BY_ID, query = "SELECT s FROM Shift s WHERE s.id =:"+Shift.ID)
})
public class Shift implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_SCHEDULE = "Shift.getSchedule";
	public static final String GET_BY_ID = "Shift.getById";

	public static final String TO_DATE = "toDate";
	public static final String FROM_DATE = "fromDate";
	public static final String ID = "id";




	// fields
	@Id
	@SequenceGenerator(name = "SHIFTS_SHIFTID_GENERATOR", sequenceName = "SHIFTS_SHIFT_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHIFTS_SHIFTID_GENERATOR")
	@Column(name = "shift_id")
	private Integer id;

	@Column(name = "shift_duration", nullable = false)
	private BigDecimal duration;

	@Temporal(TemporalType.DATE)
	@Column(name = "shift_start_d", nullable = false)
	private Date startDate;

	@Temporal(TemporalType.TIME)
	@Column(name = "shift_start_t", nullable = false)
	private Date startTime;

	// bi-directional many-to-one association to EmployeeShift
	@OneToMany(mappedBy = "shift", fetch = FetchType.LAZY, targetEntity = EmployeeShift.class)
	private Set<EmployeeShift> employeeShifts;

	// constructors
	public Shift() {
	}

	// public methods

	public EmployeeShift addEmployeeShift(EmployeeShift employeeShift) {
		getEmployeeShifts().add(employeeShift);
		employeeShift.setShift(this);

		return employeeShift;
	}

	public EmployeeShift removeEmployeeShift(EmployeeShift employeeShift) {
		getEmployeeShifts().remove(employeeShift);
		employeeShift.setShift(null);

		return employeeShift;
	}

	// getters setters
	public Integer getId() {
		return this.id;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal shiftDuration) {
		this.duration = shiftDuration;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date shiftStartD) {
		this.startDate = shiftStartD;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date shiftStartT) {
		this.startTime = shiftStartT;
	}

	public Set<EmployeeShift> getEmployeeShifts() {
		return this.employeeShifts;
	}

	public void setEmployeeShifts(Set<EmployeeShift> employeeShifts) {
		this.employeeShifts = employeeShifts;
	}

}