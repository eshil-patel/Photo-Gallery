package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import model.Album;
import model.Photo;
import model.User;
import model.UserList;

public class SearchPhotosController {
	@FXML
	private Button back;
	@FXML
	private TextField startDate;
	@FXML 
	private TextField endDate;
	@FXML
	private TextField tag1;
	@FXML
	private TextField tag2;
	@FXML
	private TextField val1;
	@FXML
	private TextField val2;
	@FXML
	private RadioButton and;
	@FXML
	private RadioButton or;
	@FXML
	private Button searchbytag;
	@FXML
	private Button searchbydate;
	@FXML
	private TextField albumname;
	@FXML
	private Button createalbum;
	@FXML
	private GridPane grid;
	@FXML
	private ScrollPane scrollpane;
	public static ArrayList<Album> albums;
	public static UserList userlist;
	public static SwitchPage switchpage;
	public static User user;
	public static ArrayList<Photo> newAlbum;
	public static void initializeAlbums(UserList UL,ArrayList<Album> a, User u) {
		userlist=UL;
		albums=a;
		user=u;
		switchpage=new SwitchPage();
	}
	/**Callback to the back button. Goes to previous page 
	 * @param event
	 * @throws Exception
	 */
	public void back(ActionEvent event) throws Exception {
		NonAdminController.initializePage(userlist, user);
		switchpage.showScreen("/view/NonAdminPage.fxml", event);
	}
	/**Callback to the search by date button. Searches your photos by the date range inputted
	 * @throws ParseException
	 * @throws FileNotFoundException
	 */
	public void searchByDate() throws ParseException, FileNotFoundException {
		System.out.println("Got into the method im testing");
		String start=startDate.getText();
		String end=endDate.getText();
		startDate.setText("");
		endDate.setText("");
		String[] checkstart=start.split("/");
		String[] checkend=end.split("/");
		// check if the numbers are ok later
		if((!areDates(checkstart))||(!areDates(checkend))) {
			showAlert("These are not valid dates");
			return;
		}
		ArrayList<Photo> photosBySearch=user.getPhotosByDate(start, end);
		newAlbum=photosBySearch;
		setGridpane(photosBySearch);
	}
	/**Private helper used to check if the dates are valid dates
	 * @param input
	 * @return
	 */
	private boolean areDates(String[] input) {
		if(input.length!=3) {
			return false;
		}
		if(input[0].length()!=2 || input[1].length()!=2 || input[2].length()!=4) {
			return false;
		}
		if((!input[0].matches("[0-9]+"))||(!input[1].matches("[0-9]+"))||(!input[2].matches("[0-9]+"))) {
			return false;
		}
		return true;
	}
	/**Shows alert with message if there is a user error
	 * @param text
	 */
	public void showAlert(String text) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText(text);
		alert.showAndWait();
		return;
	}
	/**Callback to the search by tag button. Searches the photos by the tag value(s) inputted
	 * @throws FileNotFoundException
	 */
	public void searchByTag() throws FileNotFoundException {
		String t1=tag1.getText().trim();
		String v1=val1.getText().trim();
		String t2=tag2.getText().trim();
		String v2=val2.getText().trim();
		tag1.setText("");
		tag2.setText("");
		val1.setText("");
		val2.setText("");
		ArrayList<Photo> searchResults = null;
		if(t1.equals("") || v1.equals("")) {
			showAlert("Please make sure to enter in all relevant fields for tag 1 and value 1");
			return;
		}
		else if(t2.equals("") && t2.equals("")) {
			searchResults=user.getPhotosByTag(t1, v1);
			}
		else if(!(t1.equals(""))||!(v1.equals(""))||!(t2.equals(""))||!(v2.equals(""))) {
			boolean isOr=or.isSelected();
			boolean isAnd=and.isSelected();
			if(isOr && isAnd) { //both selected
				showAlert("Please select only one of or/and");
				return;
			}
			if(!(isOr) && !(isAnd) && !(t2.equals("")) && !(v2.equals(""))) {
				System.out.println(t2);
				showAlert("Please select or/and as the search method");
				return;
			}
			if(isOr) {
				searchResults=user.getPhotosByTag(t1, v1, t2, v2, "or");
			}
			if(isAnd) {
				searchResults=user.getPhotosByTag(t1, v1, t2, v2, "and");
			}
			newAlbum=searchResults;
		}
		else {
			showAlert("Please make sure to enter in all relevant fields for tag 2 and value 2");
			return;
		}
		newAlbum=searchResults;
		setGridpane(searchResults);
	}
	
	/**Sets the gridpane of the photos that were returned by the search
	 * @param searchResults
	 * @throws FileNotFoundException
	 */
	public void setGridpane(ArrayList<Photo> searchResults) throws FileNotFoundException {
		grid.getChildren().removeAll(grid.getChildren());
		System.out.println(searchResults.size());
		int j=0;
		for (Photo i: searchResults){
			FileInputStream inputstream = new FileInputStream(i.getPath());
			Image img = new Image(inputstream);
			double dh = 60;
			double dw = 150;
			double rat = img.getWidth()/img.getHeight();
			
			ImageView imgView = new ImageView(img);
			if (dw/dh < rat){
				imgView.setFitWidth(dw);
				imgView.setFitHeight(dw/rat);
			}else{
				imgView.setFitHeight(dh);
				imgView.setFitWidth(dh*rat);
			}
			System.out.println(j/2 + " " + j % 2);
			System.out.println(imgView.getFitHeight() + " " + imgView.getFitWidth());
			grid.add(imgView,j%2, j/2);
			j++;
		}
		for (Node i: grid.getChildren()){
			GridPane.setHalignment(i, HPos.CENTER);
			GridPane.setValignment(i, VPos.CENTER);
		}
		scrollpane.setContent(grid);
	}
	/**Callback for the create album button. Creates a new album from the search results 
	 * 
	 */
	public void createAlbum() {
		System.out.println("got in here and now im in buisness");
		String albumName=albumname.getText();
		Album album = new Album(albumName);
		if(user.getAlbums().contains(album)) {
			showAlert("Duplicate name");
			return;
		}
		for(Photo p:newAlbum) {
			album.addPhoto(p);
		}
		if(album.getPhotos()==null) {
			showAlert("This is a blank album");
			return;
		}
		user.addAlbum(album);
		DataSaver.save(userlist);
	}
}
