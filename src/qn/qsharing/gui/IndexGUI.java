package qn.qsharing.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import qn.qsharing.Main;

public class IndexGUI extends Application {

	public void launchGUI(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
		@SuppressWarnings("unused")
		Scene scene = new Scene(root);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle(Main.NAME);
		primaryStage.show();
	}

}
