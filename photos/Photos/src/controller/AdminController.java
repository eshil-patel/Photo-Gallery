package controller;
import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdminController {
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
	public static void initializeUserList(UserList UL){
		ULL=UL;
	}
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
	public void deleteUser() {
		User m = (userlist.getSelectionModel().getSelectedItem());
		ULL.deleteUser(m);
		listUsers();
		DataSaver.save(ULL);
	}
	public void logout() {
		Photos.changePane(2);
	}
	public void listUsers() {
		userlist.setItems(FXCollections.observableArrayList(ULL.getList()));
	}
	
	
	
}
