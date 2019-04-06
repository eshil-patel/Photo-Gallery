package controller;
import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.fxml.FXML;
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
	private ListView<String> userlist;
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
		User user = new User(name);
		ULL.addUser(user);
		try
        {    
            System.out.println("Entered the try condition");
            String pa = System.getProperty("user.dir")+File.separator+"src"+File.separator+"model";
			System.out.println("ADMIN CONT " + pa);
            FileOutputStream file = new FileOutputStream(pa+File.separator+"UserData.dat"); 
            System.out.println("the filepath was something that was recognized");
            ObjectOutputStream out = new ObjectOutputStream(file);  
            // Method for serialization of object 
            out.writeObject(ULL); 
            out.close(); 
            file.close(); 
            System.out.println("Object has been serialized"); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
	}
	public void deleteUser() {
		
	}
	public void logout() {
		Photos.changePane(2);
	}
	public void listUsers() {
		
	}
	
	
}
