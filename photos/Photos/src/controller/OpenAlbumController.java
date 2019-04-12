package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Album;
import model.User;
import model.UserList;

public class OpenAlbumController {
	public static UserList userlist;
	public static Album album; 
	public static SwitchPage switchpage;
	public static User user;
	@FXML
	private Button back;
	public static void initializeAlbum(UserList UL,Album a,User u) {
		userlist=UL;
		album=a;
		user=u;
		switchpage=new SwitchPage();
	}
	public void back(ActionEvent event) throws Exception {
		NonAdminController.initializePage(userlist, user);
		switchpage.showScreen("/view/NonAdminPage.fxml", event);
	}
}
