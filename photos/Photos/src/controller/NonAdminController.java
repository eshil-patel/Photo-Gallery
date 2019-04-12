package controller;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	private Button openalbum;
	@FXML
	private Button searchphotos;
	@FXML
	private TableView<Album> table;
	@FXML 
	private TableColumn<Album,String> name;
	@FXML
	private TableColumn<Album,Integer> numphotos;
	@FXML 
	private TableColumn<Album,String> dates;
	@FXML
	private TextField createalbum;
	@FXML
	private TextField renamealbum;
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
		ObservableList<Album> data = FXCollections.observableArrayList(user.getAlbums());
		name.setCellValueFactory(new PropertyValueFactory<Album, String>("name"));
		numphotos.setCellValueFactory(new PropertyValueFactory<Album, Integer>("numPhotos"));
		dates.setCellValueFactory(new PropertyValueFactory<Album, String>("dateRange"));
		table.setItems(data);
	}
	public void createAlbum() {
		String name = createalbum.getText();
		createalbum.setText("");
		if(name.equals("")) {
			String text ="Please enter a name for your album, then press create album";
			showAlert(text);
			return;
		}
		if(user.hasAlbum(name)) {
			String text="This is already the name of an existing album. Please choose a unique name.";
			showAlert(text);
			return;
		}
		Album newalbum = new Album(name);
		user.addAlbum(newalbum);
		DataSaver.save(userlist);
		setTable();
	}
	private void setTable(){
		ObservableList<Album> data = FXCollections.observableArrayList(user.getAlbums());
		table.setItems(data);
	}
	public void deleteAlbum() {
		Album todelete = table.getSelectionModel().getSelectedItem();
		if(todelete==null) {
			String text="No album was selected. Please select an album before pressing delete album";
			showAlert(text);
		}
		user.removeAlbum(todelete);
		DataSaver.save(userlist);
		setTable();
	}
	public void renameAlbum() {
		Album torename=table.getSelectionModel().getSelectedItem();
		String newname = renamealbum.getText();
		if(newname.equals("") || torename==null) {
			String text="Please select an album, and type a new name before pressing rename album";
			showAlert(text);
			return;
		}
		renamealbum.setText("");
		user.renameAlbum(torename, newname);  
		DataSaver.save(userlist);
		setTable();
		table.refresh();
	}
	public void openAlbum(ActionEvent event) throws Exception {
		Album album=table.getSelectionModel().getSelectedItem();
		if(album==null) {
			String text="No album was selected. Please select an album before pressing open album";
			showAlert(text);
			return;
		}
		OpenAlbumController.initializeAlbum(userlist, album,user);
		switchpage.showScreen("/view/openAlbum.fxml", event);
	}
	public void searchPhotos(ActionEvent event) throws Exception {
		SearchPhotosController.initializeAlbums(userlist, user.getAlbums(),user);
		switchpage.showScreen("/view/searchPhotos.fxml", event);
	}
	public void showAlert(String text) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText(text);
		alert.showAndWait();
		return;
	}
}
