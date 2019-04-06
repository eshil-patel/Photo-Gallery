package controller;
import model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NonAdminController {
	@FXML 
	private Button createAlbum;
	@FXML
	private Button deleteAlbum;
	@FXML
	private Button renameAlbum;
	@FXML
	private Button logout;
	public static UserList userlist;
	public static User user;
	public static void initialize(UserList UL, User u) {
		userlist=UL;
		user=u;
	}
}
