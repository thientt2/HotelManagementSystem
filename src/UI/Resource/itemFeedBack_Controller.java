package UI.Resource;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class itemFeedBack_Controller implements Initializable {
    
	@FXML
    private Label content;

    @FXML
    private Label dayBefore_txt;

    @FXML
    private Label gmail;

    @FXML
    private Label name;

    @FXML
    private Label room_txt;

    @FXML
    private ImageView star;

    @FXML
    private ImageView starNot;

    @FXML
    private Label type_txt;
    
    @FXML
    private HBox star_hbox;
    
    @FXML
    private ImageView image;
    
    public void setData(Object[] item) {
    	Image newImage1 = new Image("/Images/star.png");
    	star.setImage(newImage1);
		Image newImage2 = new Image("/Images/borderStar.png");
		starNot.setImage(newImage2);
    	star_hbox.getChildren().clear();
    	int rating = Integer.parseInt(item[0].toString());
        for (int i = 0; i < 5; i++) {
            ImageView star = new ImageView();
            star.setFitWidth(20);
            star.setFitHeight(20);
            if (i < rating) {
                star.setImage(newImage1);
            } else {
                star.setImage(newImage2);
            }
            star_hbox.getChildren().add(star);
        }
//    	if(Integer.parseInt(item[0].toString()) == 1)
//    	{
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(starNot);
//        	star_Hbox.getChildren().add(starNot);
//        	star_Hbox.getChildren().add(starNot);
//        	star_Hbox.getChildren().add(starNot);
//    	}
//    	if(Integer.parseInt(item[0].toString()) == 2)
//    	{
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(starNot);
//        	star_Hbox.getChildren().add(starNot);
//        	star_Hbox.getChildren().add(starNot);
//    	}
//    	if(Integer.parseInt(item[0].toString()) == 3)
//    	{
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(starNot);
//        	star_Hbox.getChildren().add(starNot);
//    	}
//    	if(Integer.parseInt(item[0].toString()) == 4)
//    	{
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(starNot);
//    	}
//    	if(Integer.parseInt(item[0].toString()) == 5)
//    	{
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//        	star_Hbox.getChildren().add(star);
//    	}
    	
    	gmail.setText("(" + String.valueOf(item[1]) + ")");
    	content.setText(String.valueOf(item[2]));
    	name.setText(String.valueOf(item[4]));
    	room_txt.setText(String.valueOf(item[5]));
    	
    	type_txt.setText(String.valueOf(item[6]));
    	if (type_txt.getText().equals("Standard"))
    	{
    		Image newImage = new Image("/Images/Rooms/Standard.jpg");
    		image.setImage(newImage);
    	}
    	if (type_txt.getText().equals("Deluxe"))
    	{
    		Image newImage = new Image("/Images/Rooms/Deluxe.jpg");
    		image.setImage(newImage);
    	}
    	if (type_txt.getText().equals("Premium"))
    	{
    		Image newImage = new Image("/Images/Rooms/Premium.jpg");
    		image.setImage(newImage);
    	}
    	if (type_txt.getText().equals("La Opera"))
    	{
    		Image newImage = new Image("/Images/Rooms/LaOpera.jpg");
    		image.setImage(newImage);
    	}

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime dayFb = LocalDateTime.parse(item[3].toString(), inputFormatter);
		String day = dayFb.format(outputFormatter);	
		dayBefore_txt.setText(day);
	}

    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
}
