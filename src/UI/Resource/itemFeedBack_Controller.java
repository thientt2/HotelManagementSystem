package UI.Resource;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class itemFeedBack_Controller implements Initializable {
    
    @FXML
    private Label dynamicLabel;

    public void initialize(URL arg0, ResourceBundle arg1) {
        // Bind the label's preferred width to the text's width
        dynamicLabel.prefWidthProperty().bind(dynamicLabel.widthProperty());
    }
}
