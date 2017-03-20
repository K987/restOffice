/**
 * 
 */
package hun.restoffice.client.controller;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hun.restoffice.client.converter.Converter;
import hun.restoffice.client.model.RegisterCloseModel;
import hun.restoffice.client.model.RegisterModel;
import hun.restoffice.client.service.RemoteServiceFactory;
import hun.restoffice.remoteClient.exception.FacadeException;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.CurrencyStringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * Controller class for reigster view
 *
 * @author kalmankostenszky
 */
public class RegisterCloseController implements WizardElement {

	private static final Logger LOG = Logger.getLogger(RegisterCloseController.class);

	private static final String SUM_COL = "Z összeg";
	private static final String CLOSE_NO_COL = "Z szám";

	@FXML
	private TableView<RegisterModel> registerTable;

	@FXML
	private TableColumn<RegisterModel, String> idCol;

	@FXML
	private TableColumn<RegisterModel, String> typeCol;

	@FXML
	private TableColumn<RegisterModel, Number> closeNoCol;

	@FXML
	private TableColumn<RegisterModel, Number> amtCol;

	@FXML
	private TableColumn<RegisterModel, Boolean> usedCol;

	@FXML
	private Label cashLbl;

	@FXML
	private Label cardLbl;

	@FXML
	private Label sumLabel;

	private RegisterCloseModel model;

	private LocalDate closingDate;

	/**
	 * @param date
	 *            date on registers to close
	 */
	public RegisterCloseController(LocalDate date) {
		this.closingDate = date;
	}

	/**
	 * called by FXML loader
	 */
	@FXML
	private void initialize() {

		closeNoCol.setText(CLOSE_NO_COL);
		amtCol.setText(SUM_COL);

		idCol.setCellValueFactory(new PropertyValueFactory<RegisterModel, String>("id"));
		typeCol.setCellValueFactory(new PropertyValueFactory<RegisterModel, String>("type"));
		closeNoCol.setCellValueFactory(new PropertyValueFactory<RegisterModel, Number>("closeNo"));
		amtCol.setCellValueFactory(new PropertyValueFactory<RegisterModel, Number>("amount"));
		usedCol.setCellValueFactory(new PropertyValueFactory<RegisterModel, Boolean>("used"));

		final NumberFormat nf = NumberFormat.getInstance();
		nf.setParseIntegerOnly(true);

		closeNoCol.setCellFactory(tableColumn -> {
			final DynamicEditableTextTableCell<RegisterModel, Number> cell = new DynamicEditableTextTableCell<>(//
					new NumberStringConverter(nf));

			cell.recordProperty().addListener((ChangeListener<RegisterModel>) (ov, vOld, vNew) -> {
				cell.editableProperty().unbind();
				if (vNew != null)
					cell.editableProperty().bind(vNew.usedProperty());
			});
			return cell;
		});

		amtCol.setCellFactory(tableColumn -> {
			final DynamicEditableTextTableCell<RegisterModel, Number> cell = new DynamicEditableTextTableCell<>(//
					new CurrencyStringConverter());

			cell.recordProperty().addListener((ChangeListener<RegisterModel>) (ov, vOld, vNew) -> {
				cell.editableProperty().unbind();
				if (vNew != null)
					cell.editableProperty().bind(vNew.usedProperty());
			});
			return cell;
		});
		usedCol.setCellFactory(CheckBoxTableCell.forTableColumn(usedCol));
		onLoaded();
	}

	/**
	 * called when view initialized Loads registercloses if not yet loaded
	 */
	private void onLoaded() {
		if (model != null || closingDate == null)
			return;

		Calendar cal;
		(cal = Calendar.getInstance()).setTime(Date.from(closingDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		try {
			model = Converter.toRegisterCloseModel(RemoteServiceFactory.lookupRegister().getRegistersToClose(cal));
		} catch (FacadeException e) {
			LOG.error(e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Hiba történt a pénztárgépek ellenőrzése közben");
			alert.showAndWait();
		} catch (NamingException e) {
			LOG.error(e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("A szolgáltatás nem elérhető");
			alert.showAndWait();
		}

		registerTable.setItems(model.getRegModels());
		cardLbl.textProperty().bind(Bindings.format("%.1f Ft", model.getCard()));
		cashLbl.textProperty().bind(Bindings.format("%.1f Ft", model.getCash()));
		sumLabel.textProperty().bind(Bindings.format("%.1f Ft", model.getSum()));
	}

	/**
	 * Eventhandler called when number in cell changed
	 * 
	 * @param event
	 */
	@FXML
	public void handleNumberChange(CellEditEvent<RegisterModel, Number> event) {
		RegisterModel toChange = event.getTableView().getItems().get(event.getTablePosition().getRow());
		switch (event.getTableColumn().getText()) {
			case CLOSE_NO_COL: {
				toChange.closeNoProperty().setValue(event.getNewValue());
				break;
			}
			case SUM_COL: {
				toChange.amountProperty().setValue(event.getNewValue());
				break;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.main.WizardElement#onNext()
	 */
	@Override
	public boolean onNext() {
		boolean rtrn = true;
		StringBuilder sb = new StringBuilder();
		for (RegisterModel model : model.getRegModels()) {
			if (model.usedProperty().get()) {
				if (model.amountProperty().get() < 0) {
					sb.append(model.idProperty().get() + " számu gép záró összege negatív.\n");
					rtrn = false;
				}
				if (model.closeNoProperty().get() <= 0) {
					sb.append(model.idProperty().get() + " számu gép záró száma nem megfelelő.\n");
					rtrn = false;
				}
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
	 * @see hun.restoffice.client.main.WizardElement#onPrevious()
	 */
	@Override
	public boolean onPrevious() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.main.WizardElement#onCancel()
	 */
	@Override
	public void onCancel() {
		registerTable.getItems().clear();
		model = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.main.WizardElement#onSend()
	 */
	@Override
	public void onSend() {
		try {
			List<RegisterModel> tmp = model.getRegModels().stream().filter(rm -> rm.usedProperty().get()).collect(Collectors.toList());
			for (RegisterModel registerModel : tmp) {
				LOG.debug(registerModel.toString());
			}
			RemoteServiceFactory.lookupRegister().batchRegisterClose(
					Converter.fromRegisterModel(tmp));
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
