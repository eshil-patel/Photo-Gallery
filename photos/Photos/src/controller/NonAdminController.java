package controller;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class NonAdminController implements Initializable{
	@FXML 
	private Button createAlbum;
	@FXML
	private Button deleteAlbum;
	@FXML
	private Button renameAlbum;
	@FXML
	private Button logout;
	@FXML
	private TableView<Album> table;
	@FXML 
	private TableColumn<Album,String> name;
	@FXML
	private TableColumn<Album,Integer> numphotos;
	@FXML 
	private TableColumn<Album,String> dates;
	public static UserList userlist;
	public static User user;
	public static SwitchPage switchpage;
	public static void initializePage(UserList UL, User u) {
		userlist=UL;
		user=u;
		switchpage=new SwitchPage();
	}
	public void logout(ActionEvent event) throws Exception {
		switchpage.showScreen("/view/login.fxml", event);
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		// will have to check if the user doesn't have photos at all!
		// the inside tells how you are parsing the album argument, and which row gets what!
		ObservableList<Album> data = FXCollections.observableArrayList(user.getAlbums());
		name.setCellValueFactory(new PropertyValueFactory<Album, String>("name"));
		numphotos.setCellValueFactory(new PropertyValueFactory<Album, Integer>("numPhotos"));
		dates.setCellValueFactory(new PropertyValueFactory<Album, String>("dateRange"));
		table.setItems(data);
	}
	
	
}
