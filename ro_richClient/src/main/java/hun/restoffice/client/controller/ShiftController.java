/**
 * 
 */
package hun.restoffice.client.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hun.restoffice.client.converter.Converter;
import hun.restoffice.client.model.EmployeeShiftModel;
import hun.restoffice.client.model.JobPosition;
import hun.restoffice.client.service.RemoteServiceFactory;
import hun.restoffice.remoteClient.exception.FacadeException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 * Controller class for ShiftView
 * 
 * @author kalmankostenszky
 */
public class ShiftController implements WizardElement {

	private static final Logger LOG = Logger.getLogger(ShiftController.class);

	@FXML
	private TableView<EmployeeShiftModel> employees;

	@FXML
	private TableColumn<EmployeeShiftModel, String> nameCol;

	@FXML
	private TableColumn<EmployeeShiftModel, String> defaultPositionCol;

	@FXML
	private TableColumn<EmployeeShiftModel, String> defaultStartCol;

	@FXML
	private TextField actualStartField;

	@FXML
	private TextField actualEndField;

	@FXML
	private ComboBox<JobPosition> actualPosCombo;

	private ObservableList<EmployeeShiftModel> model;

	/**
	 * @param employees
	 *            model to set
	 * 
	 */
	public ShiftController(ObservableList<EmployeeShiftModel> employees) {
		model = employees;
	}

	/**
	 * called by FXML loader
	 */
	@FXML
	private void initialize() {

		employees.setItems(model);

		nameCol.setCellValueFactory(new PropertyValueFactory<EmployeeShiftModel, String>("name"));
		defaultPositionCol.setCellValueFactory(new PropertyValueFactory<EmployeeShiftModel, String>("defaultPosition"));
		defaultStartCol.setCellValueFactory(new PropertyValueFactory<EmployeeShiftModel, String>("defaultStart"));

		actualPosCombo.setItems(FXCollections.observableArrayList(JobPosition.values()));

		employees.getSelectionModel().selectedItemProperty().addListener((event, oldValue, newValue) -> {
			actualStartField.textProperty().unbindBidirectional(oldValue == null ? "" : oldValue.actualStartProperty());
			Bindings.bindBidirectional(actualStartField.textProperty(), newValue.actualStartProperty(), new StringConverter<LocalTime>() {

				

				@Override
				public String toString(LocalTime object) {
					
					return object.format(DateTimeFormatter.ofPattern("HH:mm"));
				}

				@Override
				public LocalTime fromString(String string) {

					return LocalTime.parse(string, DateTimeFormatter.ofPattern("HH:mm"));
				}
			});
			actualEndField.textProperty().unbindBidirectional(oldValue == null ? "" : oldValue.actualEndProperty());
			Bindings.bindBidirectional(actualEndField.textProperty(), newValue.actualEndProperty(), new StringConverter<LocalTime>() {

				LocalDateTime ldt;

				@Override
				public String toString(LocalTime object) {
					
					return object.format(DateTimeFormatter.ofPattern("HH:mm"));
				}

				@Override
				public LocalTime fromString(String string) {

					return LocalTime.parse(string, DateTimeFormatter.ofPattern("HH:mm"));
				}
			});
			if (oldValue != null)
				oldValue.actualPositionProperty().unbind();
			actualPosCombo.setValue(newValue.actualPositionProperty().get());
			newValue.actualPositionProperty().bind(actualPosCombo.valueProperty());

		});
		onLoaded();
	}

	/**
	 * called when view initiallized correctly
	 */
	private void onLoaded() {
		if (model == null)
			return;

		employees.setItems(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onNext()
	 */
	@Override
	public boolean onNext() {
		StringBuilder sb = new StringBuilder();
		boolean rtrn = true;
		for (EmployeeShiftModel es : model) {
			LOG.debug("end is: "+es.actualEndProperty());
			LOG.debug("start is: "+es.actualStartProperty());
			LOG.debug(es.isShiftLengthOk());
			
			if (!es.isShiftLengthOk()) {
				sb.append("Munkaidő nem megfelelő: " + es.nameProperty().get() + "\n");
				rtrn = false;
			}
		}
		if (!rtrn) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(sb.toString());
			alert.showAndWait();
		}
		return rtrn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onPrevious()
	 */
	@Override
	public boolean onPrevious() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onCancel()
	 */
	@Override
	public void onCancel() {
		employees.getItems().clear();
		model = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onSend()
	 */
	@Override
	public void onSend() {
		onNext();
		try {
			RemoteServiceFactory.lookupShift().batchShiftClose(Converter.fromEmployeeShiftModel(model));
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
