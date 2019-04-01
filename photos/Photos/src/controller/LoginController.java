package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Photos;

public class LoginController {
	@FXML
	private TextField username;
	@FXML
	private Button loginbutton;
	private static ObservableList<String> users = FXCollections.observableArrayList();
	public void startList() throws FileNotFoundException {
		File file=new File("Userlist.txt");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String name=sc.nextLine();
			users.add(name);
		}
	}
	public void login()  {
		String input=username.getText().trim();
		if(input.equals("admin")) {
			Photos.changePane(0);
		}
		if(users.contains(input)) {
			Photos.changePane(1);
			
		}
	}
}
