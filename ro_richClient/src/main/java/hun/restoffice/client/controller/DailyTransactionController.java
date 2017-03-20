/**
 * 
 */
package hun.restoffice.client.controller;

import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hun.restoffice.client.converter.Converter;
import hun.restoffice.client.model.DailyTransactionModel;
import hun.restoffice.client.service.RemoteServiceFactory;
import hun.restoffice.remoteClient.domain.DailyTransactionStub;
import hun.restoffice.remoteClient.exception.FacadeException;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.CurrencyStringConverter;

/**
 * Controller class of DailyTransactionView
 *
 * @author kalmankostenszky
 */
public class DailyTransactionController implements WizardElement {

	private static final Logger LOG = Logger.getLogger(DailyTransactionController.class);
	@FXML
	private TableView<DailyTransactionModel> transactions;

	@FXML
	private TableColumn<DailyTransactionModel, String> nameCol;

	@FXML
	private TableColumn<DailyTransactionModel, String> actualPosCol;

	@FXML
	private TextField amtPOSField;

	@FXML
	private TextField amtCashField;

	@FXML
	private TextField amtCardField;

	private ObservableList<DailyTransactionModel> model;

	/**
	 * 
	 * @param transactions
	 *            model of the controller
	 */
	public DailyTransactionController(ObservableList<DailyTransactionModel> transactions) {
		model = transactions;
	}

	/**
	 * called by FXML loader
	 */
	@FXML
	private void initialize() {
		transactions.setItems(model);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		actualPosCol.setCellValueFactory(new PropertyValueFactory<>("employeeActPos"));

		transactions.getSelectionModel().selectedItemProperty().addListener((event, oldValue, newValue) -> {

			Bindings.unbindBidirectional(amtPOSField.textProperty(), (oldValue == null ? "" : oldValue.posAmtProperty()));
			Bindings.bindBidirectional(amtPOSField.textProperty(), newValue.posAmtProperty(), new CurrencyStringConverter());

			Bindings.unbindBidirectional(amtCashField.textProperty(), (oldValue == null ? "" : oldValue.cashAmtProperty()));
			Bindings.bindBidirectional(amtCashField.textProperty(), newValue.cashAmtProperty(), new CurrencyStringConverter());

			Bindings.unbindBidirectional(amtCardField.textProperty(), (oldValue == null ? "" : oldValue.cardAmtProperty()));
			Bindings.bindBidirectional(amtCardField.textProperty(), newValue.cardAmtProperty(), new CurrencyStringConverter());

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onNext()
	 */
	@Override
	public boolean onNext() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onPrevious()
	 */
	@Override
	public boolean onPrevious() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Innen nem lehet visszalépni");
		alert.showAndWait();
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onCancel()
	 */
	@Override
	public void onCancel() {
		transactions.getItems().clear();
		model = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onSend()
	 */
	@Override
	public void onSend() {
		try {
			List<DailyTransactionStub> tmp = Converter.fromDailyTransactionModel(model);
			for (DailyTransactionStub dailyTransactionStub : tmp) {
				LOG.debug(dailyTransactionStub);
			}
			RemoteServiceFactory.lookupDailyTransaction().batchTransactionClose(tmp);
		} catch (FacadeException e) {
			LOG.error(e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Hiba történt a végrehajtás közben");
			alert.setContentText(e.getLocalizedMessage());
			alert.showAndWait();
		} catch (NamingException e) {
			LOG.error(e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Szolgáltatás nem elérhető");
			alert.showAndWait();
		}

	}

}
