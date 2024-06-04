package UI.BookRoom;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import BLL.NHANVIEN_BLL;
import DTO.HOADONPHONG;
import UI.Resource.itemBillBookRoom_Controller;
import UI.Resource.itemBookRoomDetail_Controller;
import application.AlertMessage;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;


public class billBookRoom_Controller implements Initializable {

	@FXML
    private Label billId_txt;

    @FXML
    private Button cancel_btn;

    @FXML
    private Label checkinDate_txt;

    @FXML
    private Label checkoutDate_txt;

    @FXML
    private VBox detail_vbox;

    @FXML
    private Label midPrice_txt;

    @FXML
    private Button print_btn;

    @FXML
    private Label staffName_txt;

    @FXML
    private Label surcharge_txt;

    @FXML
    private Label totalPrice_txt;
    
    public void setData(String checkin,String checkout,HOADONPHONG hoadonphong, ObservableList<Object[]> list) {
    	checkinDate_txt.setText(checkin);
		checkoutDate_txt.setText(checkout);
		midPrice_txt.setText(hoadonphong.getTONGTIEN().toString());
		surcharge_txt.setText(hoadonphong.getGIAMGIA().toString());
		int total = (int) (hoadonphong.getTONGTIEN() - hoadonphong.getGIAMGIA());
		totalPrice_txt.setText(String.valueOf(total));
		billId_txt.setText(hoadonphong.getMAHDP().toString());
		staffName_txt.setText(NHANVIEN_BLL.getStaffById(hoadonphong.getNVNHAP()).getTENNV());
		
		for(Object[] item : list) {
			try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemBillBookRoom.fxml"));
	            Parent roomData = loader.load();
	            itemBillBookRoom_Controller controller = loader.getController();	            
	            controller.setData(item);	                     
	            detail_vbox.getChildren().add(roomData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
    }
    
    public void print() {
        WritableImage snapshot = new WritableImage(496, 530);
        detail_vbox.getScene().getRoot().snapshot(new SnapshotParameters(), snapshot);

        // Chuyển đổi ảnh thành byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Xác định đường dẫn tới desktop
        String userHome = System.getProperty("user.home");
        String desktopPath = userHome + File.separator + "Desktop";
        String dest = desktopPath + File.separator + billId_txt.getText() + ".pdf";

        // Tạo thư mục nếu cần thiết
        File directory = new File(desktopPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Tạo PDF và chèn ảnh vào
        try {
            PdfWriter writer = new PdfWriter(dest);
            com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            Document document = new Document(pdf);
            
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            ImageData imageData = ImageDataFactory.create(byteArray);
            Image pdfImg = new Image(imageData);
            
            document.add(pdfImg);
            document.close();
            writer.close();
            
            System.out.println("PDF created successfully.");
            System.out.println("PDF location: " + dest);
            AlertMessage alert = new AlertMessage();
            alert.successMessage("Thanh toán đặt phòng thành công!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cancel() {
    	Stage stage = (Stage) cancel_btn.getScene().getWindow();
    	stage.close();
    }

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
