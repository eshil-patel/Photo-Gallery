package controller;
import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;


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
	//private static ObservableList<User> users = FXCollections.observableArrayList();
	public static UserList UL;
	public static void start() throws FileNotFoundException{
		try {
			String pa = System.getProperty("user.dir")+File.separator+"src"+File.separator+"model" + File.separator + "UserData.dat";
			System.out.println(pa);
			FileInputStream FIS = new FileInputStream(pa);
			System.out.println("FIS CREATED" + FIS.available());
			if (FIS.available() == 0){
				UL = new UserList();
				return;
			}
			System.out.println("FIS NOT NULL");
			ObjectInputStream in = new ObjectInputStream(FIS);
			System.out.println("after the output stream read");
	        UL = (UserList) in.readObject(); 
	        System.out.println(UL.toString());
	        System.out.println("The deserialzation of the empty list worked");
	        in.close();
		}
		catch(Exception e) {
//			e.printStackTrace();
			System.out.println("There was an error deserializing the data");
		}
	}
	public void startList() throws FileNotFoundException {
		File file=new File("Userlist.txt");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String name=sc.nextLine();
			//users.add(name);
		}
	}
	public void login()  {
		String input=username.getText().trim();
		if(input.equals("admin")) {
			AdminController.initializeUserList(UL);
			Photos.changePane(0);
			// and you got to set all of the stuff for this page as well? so access the fields in nonadmin controller, and do your thing?
		}
		if(input.equals("stock")) {
			Photos.changePane(1);
			// same idea, have to set the values of the next page
			// and then have to load images via filepaths
		}
		// will need to change the argument for this to match the fact this is an arraylist of users 
		
	}
}
