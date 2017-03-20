/**
 * 
 */
package hun.restoffice.client.model;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

/**
 * Model for register closing
 *
 * @author kalmankostenszky
 */
public class RegisterCloseModel {

	private static final Logger LOG = Logger.getLogger(RegisterCloseModel.class);

	private Calendar date;

	private ObservableList<RegisterModel> model;

	private DoubleBinding card;
	private DoubleBinding cash;
	private DoubleBinding sum;

	/**
	 * 
	 * @param registerCloses models to set
	 */
	public RegisterCloseModel(List<RegisterModel> registerCloses) {

		Callback<RegisterModel, Observable[]> extractor = new Callback<RegisterModel, Observable[]>() {

			@Override
			public Observable[] call(RegisterModel p) {
				Observable[] rtrn = new Observable[] { p.amountProperty() };
				return rtrn;
			}
		};

		model = FXCollections.observableArrayList(extractor);

		model.setAll(registerCloses);

		sum = Bindings.createDoubleBinding(new Callable<Double>() {

			@Override
			public Double call() throws Exception {
				double rtrn = 0;
				for (RegisterModel rm : model) {
					if (rm.getType().equals(RegisterType.CASH)) {
						rtrn += rm.amountProperty().get();
					}
				}
				return rtrn;
			}
		}, model);

		card = Bindings.createDoubleBinding(new Callable<Double>() {

			@Override
			public Double call() throws Exception {
				double rtrn = 0;
				for (RegisterModel rm : model) {
					if (rm.getType().equals(RegisterType.CARD)) {
						rtrn += rm.amountProperty().get();
					}
				}
				return rtrn;
			}
		}, model);

		cash = sum.subtract(card);
		
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return the models
	 */
	public ObservableList<RegisterModel> getRegModels() {
		return model;
	}

	/**
	 * @return the card
	 */
	public DoubleBinding getCard() {
		return card;
	}

	/**
	 * @return the cash
	 */
	public DoubleBinding getCash() {
		return cash;
	}

	/**
	 * @return the sum
	 */
	public DoubleBinding getSum() {
		return sum;
	}

}
