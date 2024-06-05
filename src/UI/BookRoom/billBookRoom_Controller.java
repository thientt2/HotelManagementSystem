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
		
		//midPrice_txt.setText(hoadonphong.getTONGTIEN().toString());
		Double gia1 = Double.parseDouble(hoadonphong.getTONGTIEN().toString());
    	String formattedPrice1 = String.format("%.0f", gia1);
    	StringBuilder sb1 = new StringBuilder(formattedPrice1);
    	int length1 = sb1.length();
    	for (int i = length1 - 3; i > 0; i -= 3) {
    	    sb1.insert(i, ".");
    	}
    	sb1.append(" VND");
    	String finalPrice1 = sb1.toString();
    	midPrice_txt.setText(finalPrice1);
		
		//surcharge_txt.setText(hoadonphong.getGIAMGIA().toString());
		Double gia2 = Double.parseDouble(hoadonphong.getGIAMGIA().toString());
    	String formattedPrice2 = String.format("%.0f", gia2);
    	StringBuilder sb2 = new StringBuilder(formattedPrice2);
    	int length2 = sb2.length();
    	for (int i = length2 - 3; i > 0; i -= 3) {
    	    sb2.insert(i, ".");
    	}
    	sb2.append(" VND");
    	String finalPrice2 = sb2.toString();
    	surcharge_txt.setText(finalPrice2);
		
		int total = (int) (hoadonphong.getTONGTIEN() - hoadonphong.getGIAMGIA());
		
		//totalPrice_txt.setText(String.valueOf(total));
		Double gia3 = Double.parseDouble(String.valueOf(total));
    	String formattedPrice3 = String.format("%.0f", gia3);
    	StringBuilder sb3 = new StringBuilder(formattedPrice3);
    	int length3 = sb3.length();
    	for (int i = length3 - 3; i > 0; i -= 3) {
    	    sb3.insert(i, ".");
    	}
    	sb3.append(" VND");
    	String finalPrice3 = sb3.toString();
    	totalPrice_txt.setText(finalPrice3);
		
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
        
        Stage stage = (Stage) print_btn.getScene().getWindow();
    	stage.close();
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
