/**
 * 
 */
package hun.restoffice.client.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hun.restoffice.client.converter.Converter;
import hun.restoffice.client.model.EmployeeShiftModel;
import hun.restoffice.client.service.RemoteServiceFactory;
import hun.restoffice.remoteClient.exception.FacadeException;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * Controller class of DateView
 *
 * @author kalmankostenszky
 */
public class DateController  implements WizardElement {

	private static final Logger LOG = Logger.getLogger(DateController.class);

	@FXML
	private DatePicker datePicker;

	@FXML
	private ListView<EmployeeShiftModel> employees;
	
	
	public DateController() {
		employees = new ListView<>();
	}

	/**
	 * called by FXMLLoader
	 */
	@FXML
	private void initialize() {
		employees.setCellFactory(param -> {
			ListCell<EmployeeShiftModel> cell = new ListCell<EmployeeShiftModel>() {
				@Override
				protected void updateItem(EmployeeShiftModel item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null)
						setText("név: " + item.nameProperty().get()//
								+ "\n\t kezdés: " + item.actualStartProperty().get().format(DateTimeFormatter.ofPattern("HH:mm"))//
								+ "\n\t munkakör: " + item.defaultPositionProperty().getValue()//
						);
				}
			};
			return cell;
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.main.WizardElement#onNext()
	 */
	@Override
	public boolean onNext() {
		LOG.debug("onNext() invoked");
		if (employees.getItems().isEmpty()){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Nincs kiválasztva mnkanap, vagy a kiválaszott munkanapra még nincs beosztás");
			alert.showAndWait();
			return false;
		}
			
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.main.WizardElement#onPrevious()
	 */
	@Override
	public boolean onPrevious() {
		LOG.debug("onPrevious() invoked");
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.main.WizardElement#onCancel()
	 */
	@Override
	public void onCancel() {
		LOG.debug("onCancel() invoked");
		datePicker.setValue(LocalDate.now());
		employees.getItems().clear();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.main.WizardElement#onSend()
	 */
	@Override
	public void onSend() {
		LOG.debug("onSend() invoked");

	}

	/**
	 * Evenet handler for date selection
	 * populates employees list
	 * 
	 * @param event
	 */
	@FXML
	private void onDateSelected(Event event) {
		LOG.debug("date selected :" + datePicker.getValue());
		try {
			Calendar cal;
			(cal = Calendar.getInstance()).setTime(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			ObservableList<EmployeeShiftModel> employeeModels = Converter.toEmployeeShiftModel(RemoteServiceFactory.lookupShift().getCalendarschedule(cal));
			employees.setItems(employeeModels);
			if (employees.getItems().isEmpty()){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("A kiválazott napra még nincs beosztás. Válasszon másik napot.");
				alert.showAndWait();
			}
		} catch (FacadeException e) {
			LOG.error(e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Hiba történt az alkalmazottak ellenőrzése közben");
			alert.showAndWait();
		} catch (NamingException e) {
			LOG.error(e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("A szolgáltatás nem elérhető");
			alert.showAndWait();
		}
	}

	/**
	 * @return the datePicker
	 */
	public DatePicker datePickerProperty() {
		return datePicker;
	}

	/**
	 * @return the employees
	 */
	public ListView<EmployeeShiftModel> employeesProperty() {
		return employees;
	}
	
	

}
