package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import controller.LoginController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import controller.AdminController;
import controller.NonAdminController;


public class Photos extends Application {

	static List<AnchorPane> windows = new ArrayList<AnchorPane>();
	static int index=2;
	static AnchorPane root;
	/* (non-Javadoc)
	 * starts up the application, initializing the login page for user to use 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/login.fxml"));
			root = (AnchorPane)loader.load();
			//windows.add((AnchorPane)FXMLLoader.load(getClass().getResource("/view/AdminPage.fxml")));
			//windows.add((AnchorPane)FXMLLoader.load(getClass().getResource("/view/NonAdminPage.fxml")));
			//windows.add((AnchorPane)FXMLLoader.load(getClass().getResource("/view/login.fxml")));
			//windows.add((AnchorPane)FXMLLoader.load(getClass().getResource("/view/openAlbum.fxml")));
			//windows.add((AnchorPane)FXMLLoader.load(getClass().getResource("/view/searchPhotos.fxml")));
			//root.getChildren().add(windows.get(2));
			LoginController example = loader.getController();
			example.start();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Photo Library App"); 
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**starts the application
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	

}
