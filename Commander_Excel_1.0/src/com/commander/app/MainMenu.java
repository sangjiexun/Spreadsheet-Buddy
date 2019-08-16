package com.commander.app;

import java.io.IOException;

import org.controlsfx.dialog.ExceptionDialog;

import com.commander.app.model.Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author H.G. Dulaney IV
 */

public class MainMenu extends Application {

	private AnchorPane rootPane;
	private Stage primaryStage;
	private static MainMenu mm;


	public MainMenu() {

		mm = this;

	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Welcome to Super Commander for MS Excel");

		initRootLayerShow();

	}

	public void initRootLayerShow() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/RootRoot.fxml"));
			rootPane = loader.load();

			MenuController controller = loader.getController();
			controller.setMainmenu(this);

			Scene scene = new Scene(rootPane);

			this.primaryStage.setScene(scene);
			getPrimaryStage().show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showProject() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("/com/commander/app/view/UserView.fxml"));
		AnchorPane anchorpane = (AnchorPane) loader.load();

		ProjectController controller = loader.getController();
		controller.setMainmenu(this);

		BorderPane borderpane = (BorderPane) getNestedPane(rootPane.getScene());
		borderpane.setCenter(anchorpane);

	}

	public Stage getPrimaryStage() {

		return primaryStage;
	}

	public Node getSubPaneTwo(Scene scene) {

		Node bp = scene.lookup("#startBlank");
		return bp;

	}

	public Node getNestedPane(Scene scene) {
		Node bp = scene.lookup("#borderpane");
		return bp;
	}

	public static MainMenu getMainMenu() {

		return MainMenu.mm;
	}

	@Override
	public void init() {

	}

	@Override
	public void stop() {

	}

}
