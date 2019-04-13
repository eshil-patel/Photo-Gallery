package controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Album;
import model.Photo;
import model.Tag;
import model.User;
import model.UserList;

public class OpenAlbumController implements Initializable{
	public static UserList ULL;
	public static Album album; 
	public static SwitchPage switchpage;
	public static User user;
	private int currentImg;
	@FXML
	private Button back;
	@FXML
	private Button next;
	@FXML
	private Button prev;
	@FXML
	private Button captionButton;
	@FXML
	private Button addTagButton;
	@FXML
	private Button removeTagButton;
	@FXML
	private Button copyAlbumButton;
	@FXML
	private Button moveAlbumButton;
	@FXML
	private GridPane grid;
	@FXML
	private ImageView dispImg;
	@FXML
	private Label captionLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label tagsLabel;
	@FXML
	private TextField captionText;
	@FXML
	private TextField addTagText1;
	@FXML
	private TextField addTagText2;
	@FXML
	private TextField copyAlbumText;
	@FXML
	private TextField moveAlbumText;
	
	public static void initializeAlbum(UserList UL,Album a,User u) {
		ULL=UL;
		album=a;
		user=u;
		switchpage=new SwitchPage();

	}
	public void loadImages() throws FileNotFoundException{
		System.out.println(album.getPhotos().get(1).getPath());
		int j = 0;
		for (Photo i: album.getPhotos()){
			System.out.println(i.getPath());
			FileInputStream inputstream = new FileInputStream(i.getPath());
			Image img = new Image(inputstream);
			double dh = 90;
			double rat = img.getWidth()/img.getHeight();
			
			ImageView imgView = new ImageView(img);
			imgView.setFitHeight(dh);
			imgView.setFitWidth(dh*rat);
			System.out.println(j/2 + " " + j % 2);
			System.out.println(dh + " " + dh*rat);
			grid.add(imgView,j%2, j/2);
			j++;
		}
		displayImg();
	}
	public void nextImg(ActionEvent event) throws FileNotFoundException {
		currentImg++;
		if (currentImg >= album.getPhotos().size()){
			showAlert("Reached end of the album!");
			currentImg--;
			return;
		}
		displayImg();
	}
	public void prevImg(ActionEvent event) throws FileNotFoundException {
		currentImg--;
		if (currentImg < 0){
			showAlert("Reached beginning of the album!");
			currentImg++;
			return;
		}
		displayImg();
	}
	//UPDATES IMAGE VIEW AND ALSO SAVES DATA!!
	public void displayImg() throws FileNotFoundException{
		Photo i = album.getPhotos().get(currentImg);
		FileInputStream inputstream = new FileInputStream(i.getPath());
		Image img = new Image(inputstream);
		dispImg.setImage(img);
		captionLabel.setText(i.getCaption());
		dateLabel.setText(i.getDate().toString());
		tagsLabel.setText(i.getTags().toString());
		DataSaver.save(ULL);
		
	}
	public void back(ActionEvent event) throws Exception {
		NonAdminController.initializePage(ULL, user);
		switchpage.showScreen("/view/NonAdminPage.fxml", event);
	}
	public void caption(ActionEvent event){
		album.getPhotos().get(currentImg).setCaption(captionText.getText());
		Photo i = album.getPhotos().get(currentImg);
		captionText.setText("");
		try {
			displayImg();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addTag(ActionEvent event){
		Tag t = new Tag(addTagText1.getText(),addTagText2.getText());
		if (album.getPhotos().get(currentImg).hasTag(t)){
			showAlert("Already has those tags");
			return;
		}
		album.getPhotos().get(currentImg).addTags(t);
		addTagText1.setText("");
		addTagText2.setText("");
		try {
			displayImg();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeTag(ActionEvent event){
		Tag t = new Tag(addTagText1.getText(),addTagText2.getText());
		if (!album.getPhotos().get(currentImg).hasTag(t)){
			showAlert("Tags not found");
			return;
		}
		album.getPhotos().get(currentImg).removeTags(t);
		addTagText1.setText("");
		addTagText2.setText("");
		try {
			displayImg();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void copyAlbum(ActionEvent event){
		
	}
	public void moveAlbum(ActionEvent event){
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			currentImg = 0;
			loadImages();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showAlert(String text) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText(text);
		alert.showAndWait();
		return;
	}
}
