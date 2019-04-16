package controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
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
	private int startImg;
	private int endImg;
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
	private Button addPhotosButton;
	@FXML
	private Button removePhotosButton;
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
	@FXML
	private ScrollPane scrollpane;
	@FXML
	private TableView<Tag> tagList;
	@FXML 
	private TableColumn<Tag,String> nameCol;
	@FXML
	private TableColumn<Tag,String> valueCol;
	public static void initializeAlbum(UserList UL,Album a,User u) {
		ULL=UL;
		album=a;
		user=u;
		switchpage=new SwitchPage();
	}
	public void loadTags(){
		if (album.getNumPhotos()!=0 && currentImg < album.getNumPhotos()){
			ObservableList<Tag> data = FXCollections.observableArrayList(album.getPhotos().get(currentImg).getTags());
			nameCol.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));
			valueCol.setCellValueFactory(new PropertyValueFactory<Tag, String>("value"));
			tagList.setItems(data);
		}
	}
	//UPDATES THE GRID IMAGES
	public void loadImages() throws FileNotFoundException{
	System.out.println(currentImg);
	System.out.println(album.toString());
		int j = 0;
		ArrayList <Photo> temp = new ArrayList<Photo>();
		temp=album.getPhotos();
		grid.getChildren().removeAll(grid.getChildren());
		for (Photo i: temp){
			System.out.println(i.getPath());
			FileInputStream inputstream = new FileInputStream(i.getPath());
			Image img = new Image(inputstream);
			double dh = 90;
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
		if (currentImg != -1){
			displayImg();
		}
	}
	public void nextImg(ActionEvent event) throws FileNotFoundException {
		currentImg++;
		if (currentImg >= album.getPhotos().size()){
			showAlert("Reached end of the album!");
			currentImg--;
			return;
		}
		loadImages();
		displayImg();
	}
	public void prevImg(ActionEvent event) throws FileNotFoundException {
		currentImg--;
		if (currentImg < 0){
			showAlert("Reached beginning of the album!");
			currentImg++;
			return;
		}
		loadImages();
		displayImg();
	}
	//UPDATES IMAGE VIEW AND ALSO SAVES DATA!!
	public void displayImg() throws FileNotFoundException{
		System.out.println(currentImg);
		System.out.println(album.toString());
		System.out.println(album.getNumPhotos());
		dispImg.setImage(null);
		if (album.getNumPhotos() != 0 && currentImg < album.getNumPhotos()){
			loadTags();
			Photo i = album.getPhotos().get(currentImg);
			FileInputStream inputstream = new FileInputStream(i.getPath());
			Image img = new Image(inputstream);
			dispImg.setImage(img);
			captionLabel.setText(i.getCaption());
			dateLabel.setText(i.getDate().toString());
		}
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
		if (currentImg == -1){
			showAlert("No image available");
			return;
		}
		String albumName = copyAlbumText.getText();
		if (!user.hasAlbum(albumName)){
			showAlert("No Such Album!");
			return;
		}else{ 
			Photo ii = album.getPhotos().get(currentImg);
			Photo i = new Photo(ii.getPath(), ii.getDate());
			Album j = user.getAlbum(albumName);
			j.addPhoto(i);
			copyAlbumText.setText("");
			try {
				displayImg();
				loadImages();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void moveAlbum(ActionEvent event){
		if (currentImg == -1){
			showAlert("No image available");
			return;
		}
		String albumName = moveAlbumText.getText();
		if (!user.hasAlbum(albumName)){
			showAlert("No Such Album!");
			return;
		}else{ 
			Photo i = album.getPhotos().get(currentImg);
			album.removePhoto(currentImg);
			Album j = user.getAlbum(albumName);
			j.addPhoto(i);
			moveAlbumText.setText("");
			if (currentImg == album.getNumPhotos()){
				currentImg--;
			}
			try {
				displayImg();
				loadImages();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void addPhotos(ActionEvent event) throws FileNotFoundException{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		File file = fileChooser.showOpenDialog(window);
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		if (file == null){
			showAlert("No file entered!");
			return;
		}
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());
		String fp = file.getAbsolutePath();
		System.out.println(fp);
		Photo n = new Photo(fp, file.lastModified());
		album.addPhoto(n);
		loadImages();
		displayImg();
	}
	public void removePhotos(ActionEvent event) throws FileNotFoundException{
		if (album.getNumPhotos() == 0){
			showAlert("No photos to remove!");
			return;
		}
		album.removePhoto(currentImg);
		if (currentImg != 0){
			currentImg--;
		}
		loadImages();
		displayImg();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			currentImg = -1;
			if (album.getNumPhotos() > 0){
				currentImg = 0;
			}
			startImg = 0;
			endImg = 5;
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
