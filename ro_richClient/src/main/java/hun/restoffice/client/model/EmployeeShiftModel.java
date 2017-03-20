package hun.restoffice.client.model;

import java.time.LocalTime;
import java.util.Calendar;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;

/*
 * Model class for EmployeeShift 
 */
public class EmployeeShiftModel {

	private final ObservableStringValue name;
	private final ObservableValue<JobPosition> defaultPosition;
	private final ObservableValue<LocalTime> defaultStart;
	private SimpleObjectProperty<JobPosition> actualPosition;
	private SimpleObjectProperty<LocalTime> actualStart;
	private SimpleObjectProperty<LocalTime> actualEnd;
	
	private final int shiftId;
	private final int rowId;

	/**
	 * @param id
	 * @param start
	 * @param name
	 * @param defaultPosition
	 * @param actualPosition
	 * @param actualStart
	 * @param actualEnd
	 */
	public EmployeeShiftModel(int id, int rowId, Calendar start, String name, int defaultPosition, int actualPosition, Calendar actualStart, Calendar actualEnd) {
		this.name = new ReadOnlyStringWrapper(name);
		this.defaultPosition = new ReadOnlyObjectWrapper<>(JobPosition.values()[defaultPosition]);
		this.actualPosition = new SimpleObjectProperty<>();

		if (actualPosition == -1)
			this.actualPosition.set(this.defaultPosition.getValue());
		else
			this.actualPosition.set(JobPosition.values()[actualPosition]);

		this.defaultStart = new ReadOnlyObjectWrapper<>(LocalTime.of(start.get(Calendar.HOUR_OF_DAY), start.get(Calendar.MINUTE)));
		this.actualStart = new SimpleObjectProperty<>();
		this.actualEnd = new SimpleObjectProperty<>();

		if (actualStart == null || actualStart.getTime() == null)
			this.actualStart.set(defaultStart.getValue());
		else
			this.actualStart.set(LocalTime.of(actualStart.get(Calendar.HOUR_OF_DAY), actualStart.get(Calendar.MINUTE)));

		if (actualEnd == null || actualEnd.getTime() == null)
			this.actualEnd.set(this.actualStart.getValue());
		else
			this.actualEnd.set(LocalTime.of(actualEnd.get(Calendar.HOUR_OF_DAY), actualEnd.get(Calendar.MINUTE)));
		
		this.shiftId = id;
		this.rowId = rowId;
	}
	
	public boolean isShiftLengthOk(){
		LocalTime start = this.actualStart.get();
		LocalTime end = this.actualEnd.get();
		
		return start.isBefore(end);
	}

	/**
	 * @return the name
	 */
	public ObservableStringValue nameProperty() {
		return name;
	}

	/**
	 * @return the defaultPosition
	 */
	public ObservableValue<JobPosition> defaultPositionProperty() {
		return defaultPosition;
	}

	/**
	 * @return the defaultStart
	 */
	public ObservableValue<LocalTime> defaultStartProperty() {
		return defaultStart;
	}

	/**
	 * @return the actualPosition
	 */
	public SimpleObjectProperty<JobPosition> actualPositionProperty() {
		return actualPosition;
	}

	/**
	 * @return the actaulStart
	 */
	public SimpleObjectProperty<LocalTime> actualStartProperty() {
		return actualStart;
	}

	/**
	 * @return the actualEnd
	 */
	public SimpleObjectProperty<LocalTime> actualEndProperty() {
		return actualEnd;
	}

	
	/**
	 * @return the shiftId
	 */
	public int getShiftId() {
		return shiftId;
	}
	
	

	/**
	 * @return the rowId
	 */
	public int getRowId() {
		return rowId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("EmployeeShiftModel [name=%s, defaultPosition=%s, actualPosition=%s, actualStart=%s, actualEnd=%s]", name.get(),
				defaultPosition.getValue(), actualPosition.get(), actualStart.get(), actualEnd.get());
	}
}
