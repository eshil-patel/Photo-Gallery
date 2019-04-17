package controller;
import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdminController implements Initializable {
	@FXML
	private Button listusers;
	@FXML
	private Button createuser;
	@FXML
	private Button deleteuser;
	@FXML
	private ListView<User> userlist;
	@FXML
	private Button logout;
	@FXML
	private TextField newUser;
	public static UserList ULL;
	public static SwitchPage switchpage;
	
	/**
	 * A method dedicated to creating a UserList object
	 * @param UL A UserList object consisting of all the users -> primary object that permeates through all other controllers and is also the object being serialized. Consists of all application information.
	 * @return
	 * @see UserList
	 */
	public static void initializeUserList(UserList UL){
		ULL=UL;
		switchpage=new SwitchPage();
	}
	/**
	 * Logs out of the admin page and diverts to the login page. 
	 * @param event Event passed by the scene with the caller
	 * @throws Exception
	 */
	public void logout(ActionEvent event) throws Exception{
		switchpage.showScreen("/view/login.fxml", event);
	}
	/**
	 * Method that creates a user specified by the user and the name they provide. 
	 * @param 
	 * @return 
	 */
	public void createUser() {
		String name = newUser.getText();
		if (name.length() == 0){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Name must be non-empty");
			alert.showAndWait();
			return;
		}
		User user = new User(name);
		ULL.addUser(user);
		listUsers();
		DataSaver.save(ULL);
	}
	/**
	 * Method that deletes a User object from UserList
	 * @param
	 */
	public void deleteUser() {
		User m = (userlist.getSelectionModel().getSelectedItem());
		ULL.deleteUser(m);
		listUsers();
		DataSaver.save(ULL);
	}
	/**
	 * Method that updates the observable list in the admin page.
	 * 
	 */
	public void listUsers() {
		userlist.setItems(FXCollections.observableArrayList(ULL.getList()));
	}
	/**
	 * Method that is called when the scene switches to the admin page
	 * @param arg0 Default parameters not used in this situation
	 * @param arg1 Another default parameter not used
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listUsers();
	}
	
	
	
}
