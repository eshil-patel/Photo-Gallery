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
import javafx.event.EventHandler;
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
import javafx.scene.control.ListView;
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
import javafx.scene.input.MouseEvent;
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
	@FXML
	private ListView<String> tagNames;
	@FXML
	private TextField addPresetTagText;
	@FXML
	private Button addPresetTagButton;
	@FXML
	private Button deletePresetTagButton;
	/**
	 * Callback function for the case when the user clicks on an object on the list of preset tag names. 
	 * @param arg0
	 */
	@FXML public void fillTagName(MouseEvent arg0){
		addTagText1.setText(tagNames.getSelectionModel().getSelectedItem());
	}
	
	
	public static void initializeAlbum(UserList UL,Album a,User u) {
		ULL=UL;
		album=a;
		user=u;
		switchpage=new SwitchPage();
	}
	
	//
	/**
	 * UPDATES THE GRID IMAGES. Sets the scroll view. 
	 * @throws FileNotFoundException
	 */
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

	//
	/**
	 * UPDATES IMAGE VIEW AND ALSO SAVES DATA!! Is responsible for the constant saving done in this page. It also displays the image in the ImageView and updates the values for the caption, date and tags. 
	 * @throws FileNotFoundException
	 */
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
	/**
	 * Callback function of the next button. Scrolls through the list of images in the album. 
	 * @param event
	 * @throws FileNotFoundException
	 */
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
	/**
	 * Callback function of the previous button. Scrolls through the list of images in the album. 
	 * @param event
	 * @throws FileNotFoundException
	 */
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
	/**
	 * Callback function for the back button. Goes to the previous page, the NonAdminController. 
	 * @param event
	 * @throws Exception
	 */
	public void back(ActionEvent event) throws Exception {
		NonAdminController.initializePage(ULL, user);
		switchpage.showScreen("/view/NonAdminPage.fxml", event);
	}
	/**
	 * Updates the caption of the image. Also calls on displayImg to update the state. 
	 * @param event
	 */
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
	/**
	 * Adds a tag to the Photo object and also updates the list accordingly.
	 * @param event
	 */
	public void addTag(ActionEvent event){
		if (addTagText1.getText().length()==0 || addTagText2.getText().length()==0 ){
			showAlert("Tag values must be non-null!");
		}
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
	/**
	 * 
	 * @param event
	 */
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
	/**
	 * @param event
	 */
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
			Photo i = album.getPhotos().get(currentImg);
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
	/**
	 * @param event
	 */
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
	/**
	 * @param event
	 * @throws FileNotFoundException
	 */
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
	/**
	 * @param event
	 * @throws FileNotFoundException
	 */
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
	
	/**
	 * 
	 */
	public void loadTags(){
		if (album.getNumPhotos()!=0 && currentImg < album.getNumPhotos()){
			ObservableList<Tag> data = FXCollections.observableArrayList(album.getPhotos().get(currentImg).getTags());
			nameCol.setCellValueFactory(new PropertyValueFactory<Tag, String>("name"));
			valueCol.setCellValueFactory(new PropertyValueFactory<Tag, String>("value"));
			tagList.setItems(data);
		}
	}
	/**
	 * 
	 */
	public void loadPresetTags(){
		ObservableList<String> list = FXCollections.observableArrayList(album.getPresetTagNames());
		tagNames.setItems(list);
	}
	/**
	 * @param event
	 */
	public void addPresetTag(ActionEvent event){
		if(addPresetTagText.getText().isEmpty()){
			showAlert("Tag name must be nonempty!");
			return;
		}
		if (album.getPresetTagNames().contains(addPresetTagText.getText())){
			showAlert("Add unique tag name!");
			return;
		}
		album.addPresetTagName(addPresetTagText.getText());
		loadPresetTags();
		addPresetTagText.setText("");
		try {
			displayImg();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param event
	 */
	public void deletePresetTag(ActionEvent event){
		if (!tagNames.getSelectionModel().getSelectedItem().isEmpty()){
			album.deletePresetTagName(tagNames.getSelectionModel().getSelectedItem());
			loadPresetTags();
			try {
				displayImg();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			currentImg = -1;
			if (album.getNumPhotos() > 0){
				currentImg = 0;
			}
			loadPresetTags();
			loadImages();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param text
	 */
	public void showAlert(String text) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText(text);
		alert.showAndWait();
		return;
	}
}
