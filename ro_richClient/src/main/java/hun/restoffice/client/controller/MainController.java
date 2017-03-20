/**
 * 
 */
package hun.restoffice.client.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hun.restoffice.client.model.DailyTransactionModel;
import hun.restoffice.client.model.EmployeeShiftModel;
import hun.restoffice.client.model.JobPosition;
import hun.restoffice.client.service.RemoteServiceFactory;
import hun.restoffice.remoteClient.exception.FacadeException;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Controller class of MainView
 *
 * @author kalmankostenszky
 */
public class MainController implements WizardElement {

	private static final Logger LOG = Logger.getLogger(MainController.class);

	private List<WizardStep> steps;
	private int selectedItem = -1;

	private WizardElement selectedController;

	@FXML
	private Button cancel;

	@FXML
	private Button previous;

	@FXML
	private Button next;

	@FXML
	private Button send;

	@FXML
	private VBox advanceIndicator;

	@FXML
	private BorderPane pane;

	private SimpleObjectProperty<LocalDate> closingDate;
	private SimpleListProperty<EmployeeShiftModel> employees;

	private boolean canSend;

	/**
	 * 
	 * @param urls
	 *            urls of sub elements
	 */
	public MainController(List<URL> urls) {
		steps = new ArrayList<>();
		closingDate = new SimpleObjectProperty<>();
		employees = new SimpleListProperty<>();

		for (URL location : urls) {
			steps.add(new WizardStep(location, null));
		}
		if (!steps.isEmpty())
			selectedItem = 0;
	}

	/**
	 * called by FXML loader
	 */
	@FXML
	public void initialize() {
		setPane(selectedItem);
	}

	/**
	 * Sets the new wizard pane active
	 * 
	 * @param nextElement
	 *            number of next pane
	 */
	private void setPane(int nextElement) {
		if (selectedItem < 0 || selectedItem >= steps.size()) {
			LOG.error("No wizard elements set");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Nap zárás nem elérhető");
			alert.showAndWait();
			System.exit(-1);
		}

		WizardStep tmp = steps.get(nextElement);

		if (tmp.loader == null) {
			LOG.debug("loader is null");
			try {
				tmp.loader = createStepLoader(tmp.path);
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		Node node = tmp.loader.getRoot();
		BorderPane.setAlignment(node, Pos.CENTER);
		BorderPane.setMargin(node, new Insets(5, 5, 5, 5));
		node.setStyle("-fx-border-color: lightgray;");
		pane.setCenter(node);
		selectedController = tmp.loader.getController();
	}

	/**
	 * Creates FXML loader from url with the tight controller
	 * 
	 * @param path
	 *            URL of view
	 * @return
	 * @throws IOException
	 */
	private FXMLLoader createStepLoader(URL path) throws IOException {
		FXMLLoader rtrn = new FXMLLoader(path);
		switch (path.toString().substring(path.toString().lastIndexOf('/') + 1)) {

			case ("DateView.fxml"): {
				DateController controller = new DateController();
				rtrn.setController(controller);
				rtrn.load();
				closingDate.bind(controller.datePickerProperty().valueProperty());
				employees.bind(controller.employeesProperty().itemsProperty());
				break;
			}
			case ("RegisterCloseView.fxml"): {
				rtrn.setController(new RegisterCloseController(closingDate.getValue()));
				rtrn.load();
				break;
			}
			case ("ShiftView.fxml"):
				rtrn.setController(new ShiftController(employees.getValue()));
				rtrn.load();
				break;
			case ("DailyTransactionView.fxml"):
				ObservableList<DailyTransactionModel> tmp = FXCollections.observableArrayList();
				for (EmployeeShiftModel esm : employees) {
					if (esm.actualPositionProperty().get().equals(JobPosition.BARTENDER) || esm.actualPositionProperty().get().equals(JobPosition.WAITER))
						tmp.add(new DailyTransactionModel(esm));
				}
				rtrn.setController(new DailyTransactionController(tmp));
				rtrn.load();
				canSend = true;
				break;
		}
		LOG.debug(rtrn.getRoot());
		return rtrn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onCancel()
	 */
	@Override
	@FXML
	public void onCancel() {
		LOG.debug("cancel pressed");
		for (int i = steps.size() - 1; i >= 0; i--) {
			if (steps.get(i).loader != null) {
				((WizardElement) steps.get(i).loader.getController()).onCancel();
				steps.get(i).loader = null;
			}
		}
		selectedItem = 0;
		setPane(selectedItem);
		setAdvanceIndicator(selectedItem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onPrevious()
	 */
	@Override
	@FXML
	public boolean onPrevious() {
		LOG.debug("previous pressed");
		boolean rtrn = selectedController.onPrevious();
		// boolean rtrn = true;
		if (rtrn && (selectedItem > 1)) {
			setPane(--selectedItem);
			setAdvanceIndicator(selectedItem);
		}
		return rtrn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onNext()
	 */
	@Override
	@FXML
	public boolean onNext() {
		LOG.debug("next pressed");
		// boolean rtrn = true;
		boolean rtrn = selectedController.onNext();
		if (rtrn && (selectedItem < steps.size() - 1)) {
			setPane(++selectedItem);
			setAdvanceIndicator(selectedItem);
		}
		return rtrn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.client.controller.WizardElement#onSend()
	 */
	@Override
	@FXML
	public void onSend() {
		LOG.debug("send pressed");
		if (!canSend)
			return;

		for (WizardStep step : steps) {
			if (step.loader != null) {
				((WizardElement) step.loader.getController()).onSend();
			}
		}
		Date toClose = Date.from(closingDate.get().atStartOfDay(ZoneId.systemDefault()).toInstant());
		try {
			RemoteServiceFactory.lookupDailyTransaction().closeDay(toClose);
		} catch (FacadeException e) {
			LOG.error(e);
		} catch (NamingException e) {
			LOG.error(e);
		}
	}

	/**
	 * Set the view advance indicator
	 * 
	 * @param nextItem
	 */
	private void setAdvanceIndicator(int nextItem) {
		ObservableList<Node> labels = advanceIndicator.getChildren();
		for (int i = 0; i < labels.size(); i++) {
			if (i == nextItem) {
				((Label) labels.get(i)).setStyle("-fx-background-color: greenyellow;");
			} else if (i < nextItem) {
				((Label) labels.get(i)).setStyle("-fx-background-color: limegreen;");
			} else {
				((Label) labels.get(i)).setStyle("-fx-background-color: salmon;");
			}
		}
	}

	/**
	 * @return the closingDate
	 */
	public SimpleObjectProperty<LocalDate> getClosingDate() {
		return closingDate;
	}

	private class WizardStep {

		private URL path;
		private FXMLLoader loader;

		/**
		 * @param path
		 * @param object
		 */
		public WizardStep(URL path, FXMLLoader loader) {
			this.path = path;
			this.loader = loader;
		}

	}

	/**
	 * @param employeeModels
	 */
	public void setEmployees(ObservableList<EmployeeShiftModel> employeeModels) {
		// TODO Auto-generated method stub

	}

}
