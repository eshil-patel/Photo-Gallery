package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class AdminController {
	@FXML
	private Button listusers;
	@FXML
	private Button createuser;
	@FXML
	private Button deleteuser;
	@FXML
	private ListView<String> userlist;
	@FXML
	private Button logout;
}
