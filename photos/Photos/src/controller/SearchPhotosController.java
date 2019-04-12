package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Album;
import model.User;
import model.UserList;

public class SearchPhotosController {
	@FXML
	private Button back;
	public static ArrayList<Album> albums;
	public static UserList userlist;
	public static SwitchPage switchpage;
	public static User user;
	public static void initializeAlbums(UserList UL,ArrayList<Album> a, User u) {
		userlist=UL;
		albums=a;
		user=u;
		switchpage=new SwitchPage();
	}
	public void back(ActionEvent event) throws Exception {
		NonAdminController.initializePage(userlist, user);
		switchpage.showScreen("/view/NonAdminPage.fxml", event);
	}
}
