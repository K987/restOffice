/**
 * 
 */
package hun.restoffice.client.model;

import java.util.Calendar;

import org.apache.log4j.Logger;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;

/**
 * Register model class 
 *
 * @author kalmankostenszky
 */
public class RegisterModel {

	private static final Logger LOG = Logger.getLogger(RegisterModel.class);

	private StringProperty id;
	private StringProperty typeName;

	private Calendar date;
	private RegisterType type;
	private IntegerProperty closeNo;
	private DoubleProperty amount;

	private BooleanProperty used;

	public RegisterModel() {

	}

	/**
	 * 
	 * @param id
	 * @param type
	 * @param date
	 * @param closeNo
	 * @param amount
	 */
	public RegisterModel(String id, int type, Calendar date, int closeNo, double amount) {

		this.id = new ReadOnlyStringWrapper(id);
		if (type == 0) {
			this.typeName = new ReadOnlyStringWrapper(RegisterType.CASH.toString());
			this.type = RegisterType.CASH;
		} else {
			this.typeName = new ReadOnlyStringWrapper(RegisterType.CARD.toString());
			this.type = RegisterType.CARD;
		}

		this.date = date;
		this.closeNo = new SimpleIntegerProperty(closeNo);
		this.amount = new SimpleDoubleProperty(amount);
		this.used = new SimpleBooleanProperty(false);

		used.addListener((ChangeListener<Boolean>) (paramObservableValue, paramT1, newValue) -> {
			if (newValue != null && !newValue) {
				this.amount.set(0);
			}
		});
	}

	/**
	 * @return the id
	 */
	public StringProperty idProperty() {
		return id;
	}

	/**
	 * @return the type
	 */
	public StringProperty typeProperty() {
		return typeName;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @return the closeNo
	 */
	public IntegerProperty closeNoProperty() {
		return closeNo;
	}

	/**
	 * @return the amount
	 */
	public DoubleProperty amountProperty() {
		return amount;
	}

	/**
	 * @return the used
	 */
	public BooleanProperty usedProperty() {
		return used;
	}

	/**
	 * @return the type
	 */
	public RegisterType getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("RegisterModel [id=%s, type=%s, date=%s, closeNo=%s, amount=%s, isUsed=%s]", id.get(), typeName.get(),
				date.get(Calendar.HOUR_OF_DAY), closeNo.get(), amount.get(), used.get());
	}

}
