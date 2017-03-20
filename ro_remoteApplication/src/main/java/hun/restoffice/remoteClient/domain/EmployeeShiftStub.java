package hun.restoffice.remoteClient.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonGetter;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * DTO for EmployeeShift
 *
 * @author kalmankostenszky
 */
public class EmployeeShiftStub implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final int defaultPosition;
	private final Calendar atcualStart;
	private final Calendar actualEnd;
	private final int actualPosition;
	
	private int shiftId;
	private final int rowId;
	

	public EmployeeShiftStub(Date actualStart, Integer rowId, Date actualEnd, int actualPosition, String employeeName, int defaultPosition) {

		this.name = employeeName;
		this.defaultPosition = defaultPosition;
		this.actualPosition = actualPosition;
		if (actualStart != null)
		(this.atcualStart = Calendar.getInstance()).setTime(actualStart);
		else
			this.atcualStart = null;
		if (actualEnd != null)
		(this.actualEnd = Calendar.getInstance()).setTime(actualEnd);
		else
			this.actualEnd = null;
		
		this.rowId = rowId;
		this.shiftId = -1;
	}

	/**
	 * @param name
	 * @param shiftId
	 * @param actualStart
	 * @param actualEnd
	 * @param actualPosition
	 */
	public EmployeeShiftStub(String name, int shiftId, int rowId, Date actualStart, Date actualEnd, int actualPosition) {
		this(actualStart, rowId, actualEnd, actualPosition, name, -1);
		this.shiftId = shiftId;
	}

	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the defaultPosition
	 */
	@JsonIgnore
	public int getDefaultPosition() {
		return defaultPosition;
	}
	/**
	 * 
	 * @return the actualStart
	 */
	public Calendar getActualStart() {
		return atcualStart;
	}

	/**
	 * 
	 * @return the actualEnd
	 */
	public Calendar getActualEnd() {
		return actualEnd;
	}
	
	/**
	 * @return the shiftid
	 */
	@JsonIgnore
	public int getShiftid() {
		return shiftId;
	}

	/**
	 * 
	 * @return the actualPosition
	 */
	@JsonIgnore
	public int getActualPosition() {
		return actualPosition;
	}

	/**
	 * @return the rowId
	 */
	@JsonIgnore
	public int getRowId() {
		return rowId;
	}

	@JsonGetter("defaultPosition")
	public String getTheDefaultPosition(){
		switch (defaultPosition){
			case 0: return "waiter";
			case 1: return "bartender";
			case 2: return "chef";				
			case -1: default: return "unknown";
		}
	}
	@JsonGetter("acutalPosition")
	public String getTheActualPosition(){
		switch (actualPosition){
			case 0: return "waiter";
			case 1: return "bartender";
			case 2: return "chef";				
			case -1: default: return "unknown";
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("EmployeeShiftStub [name=%s, defaultPosition=%s, acutalStart=%s, actualEnd=%s, actualPosition=%s]", name, defaultPosition,
				atcualStart, actualEnd, actualPosition);
	}
	

	
}
