package hun.restoffice.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import hun.restoffice.client.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of rich client
 * 
 *
 * @author kalmankostenszky
 */
public class AppEntry extends Application {

	private static final Logger LOG = Logger.getLogger(AppEntry.class);

	public static final String[] URI_END = { //
			"view/DateView.fxml", //
			"view/RegisterCloseView.fxml", //
			"view/ShiftView.fxml", //
			"view/DailyTransactionView.fxml" //
	};

	private Stage stage;
	private Parent root;

	/**
	 * Main method launches gui
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		this.stage.setTitle("Napi zárás");

		initLayout();
	}

	/**
	 * init main scene
	 * 
	 */
	private void initLayout() {
		LOG.info("initlayout() invoked");

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppEntry.class.getResource("view/MainView.fxml"));
			initMaincontroller(loader);

			root = loader.load();
			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			LOG.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * init main controller of Mainview
	 * 
	 * @param loader
	 */
	private void initMaincontroller(FXMLLoader loader) {
		List<URL> tmp = new ArrayList<>();
		for (String uriEnd : URI_END) {
			tmp.add(AppEntry.class.getResource(uriEnd));
		}
		loader.setControllerFactory(arg0 -> new MainController(tmp));
	}

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}
}
