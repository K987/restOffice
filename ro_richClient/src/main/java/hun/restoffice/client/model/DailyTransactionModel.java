/**
 * 
 */
package hun.restoffice.client.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableStringValue;

/**
 *  Model class for daily transactions
 *
 * @author kalmankostenszky
 */
public class DailyTransactionModel {
	
	private DoubleProperty posAmt;
	private DoubleProperty cardAmt;
	private DoubleProperty cashAmt;
	
	private EmployeeShiftModel employeeModel;

	/**
	 * Creates a daily transaction to the related employee
	 * @param esm
	 */
	public DailyTransactionModel(EmployeeShiftModel esm){
		employeeModel = esm;
		posAmt = new SimpleDoubleProperty(0);
		cardAmt = new SimpleDoubleProperty(0);
		cashAmt = new SimpleDoubleProperty(0);	
	}

	/**
	 * @return the posAmt
	 */
	public DoubleProperty posAmtProperty() {
		return posAmt;
	}

	/**
	 * @return the cardAmt
	 */
	public DoubleProperty cardAmtProperty() {
		return cardAmt;
	}

	/**
	 * @return the cashAmt
	 */
	public DoubleProperty cashAmtProperty() {
		return cashAmt;
	}

	/**
	 * @return the model
	 */
	public EmployeeShiftModel employeeModelProperty() {
		return employeeModel;
	}
	
	/**
	 * 
	 * @return the name
	 */
	public ObservableStringValue employeeNameProperty(){
		return employeeModel.nameProperty();
	}
	
	/**
	 * 
	 * @return the actualPosition
	 */
	public SimpleObjectProperty<JobPosition> employeeActPosProperty(){
		return employeeModel.actualPositionProperty();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("DailyTransactionModel [posAmt=%s, cardAmt=%s, cashAmt=%s, employeeModel=%s]", posAmt, cardAmt, cashAmt, employeeModel);
	}
	
	
	
	

}
