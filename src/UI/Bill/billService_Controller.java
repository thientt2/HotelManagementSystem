package UI.Bill;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import BLL.CHITIETHOADON_BLL;
import BLL.HOADONDICHVU_BLL;
import BLL.LOAIPHONG_BLL;
import BLL.NHANVIEN_BLL;
import BLL.PHONG_BLL;
import DTO.HOADONDICHVU;
import DTO.LOAIPHONG;
import DTO.NHANVIEN;
import UI.Resource.itemBillService_Controller;
import application.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import system.SystemMessage;

public class billService_Controller implements Initializable {
	@FXML
    private Label billId_txt;

    @FXML
    private Button cancel_btn;

    @FXML
    private Label checkin_txt;

    @FXML
    private Label checkout_txt;

    @FXML
    private VBox detailSevice_vbox;

    @FXML
    private Button printBill_btn;

    @FXML
    private Label roomPrice_txt;

    @FXML
    private Label roomType_txt;

    @FXML
    private Label servicePrice_txt;

    @FXML
    private Label staffName_txt;

    @FXML
    private Label surcharge_txt;
    
    @FXML
    private AnchorPane shadow_anchor;
    

    @FXML
    private AnchorPane mainBill_anchor;
    
    @FXML
    private Circle top_circle;

    @FXML
    private Label totalPrice_txt;    
    
    public Circle getCircle() {
		return top_circle;
	}
    
    private String roomNumber;

    public void setData(Object[] item) {
	   
    	String billId = item[0].toString();
    	roomNumber = item[1].toString();
    	LOAIPHONG roomType = LOAIPHONG_BLL.getRoomTypeByRoomNumber(roomNumber);
    	HOADONDICHVU billService = HOADONDICHVU_BLL.getBillServiceByBillId(billId);
    	ObservableList<Object[]> listData = CHITIETHOADON_BLL.getListDetailService(billId);
    	String checkin = item[4].toString();
    	String checkout = item[5].toString();
    	
    	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDateTime checkinDate = LocalDateTime.parse(checkin,inputFormatter);
        LocalDateTime checkoutDate = LocalDateTime.parse(checkout, inputFormatter);
        
        String outputCheckIn = checkinDate.format(outputFormatter);
        String outputCheckOut = checkoutDate.format(outputFormatter);
        
        checkin_txt.setText(outputCheckIn);
        checkout_txt.setText(outputCheckOut);
     
        roomType_txt.setText(roomType.getTENLOAI());
//        roomPrice_txt.setText(roomType.getGIA().toString());
		double roomPrice = (double) roomType.getGIA();
		String formattedRoomPrice = String.format("%.0f",roomPrice);
		StringBuilder sbRoomPrice = new StringBuilder(formattedRoomPrice);
		int lngRoomPrice = sbRoomPrice.length();
		for (int i = lngRoomPrice - 3; i > 0; i -= 3) {
			sbRoomPrice.insert(i, ".");
		}
		sbRoomPrice.append(" VND");
		String finalRoomPrice = sbRoomPrice.toString();
		roomPrice_txt.setText(finalRoomPrice);

    	NHANVIEN staff = NHANVIEN_BLL.getStaffById(SystemMessage.getMANV());
    	
    	billId_txt.setText(billId);
    	staffName_txt.setText(staff.getTENNV());
    	
//    	servicePrice_txt.setText(billService.getGIADICHVU().toString());
    	
    	int servicePrice = (int) billService.getGIADICHVU();
    	String midPriceString = String.valueOf(servicePrice);
    	StringBuilder sbMidPrice = new StringBuilder(midPriceString);
    	int lngMidPrice = sbMidPrice.length();
    	for (int i = lngMidPrice - 3; i > 0; i -= 3) {
    		sbMidPrice.insert(i, ".");
    	}
    	sbMidPrice.append(" VND");
    	String finalMidPrice = sbMidPrice.toString();
    	servicePrice_txt.setText(finalMidPrice);
		

		int surcharge = (int) billService.getPHUTHU();
    	String formattedSurcharge = String.valueOf(surcharge);
    	StringBuilder sbSurcharge = new StringBuilder(formattedSurcharge);
    	int lngSurcharge = sbSurcharge.length();
    	for (int i = lngSurcharge - 3; i > 0; i -= 3) {
    		sbSurcharge.insert(i, ".");
    	}
    	sbSurcharge.append(" VND");
    	String finalSurcharge = sbSurcharge.toString();
    	surcharge_txt.setText(finalSurcharge);
		
		int total =  (int) billService.getTONGTIEN();	
		
    	String formattedTotal = String.valueOf(total);
    	StringBuilder sbTotal = new StringBuilder(formattedTotal);
    	int lngTotal = sbTotal.length();
    	for (int i = lngTotal - 3; i > 0; i -= 3) {
    		sbTotal.insert(i, ".");
    	}
    	sbTotal.append(" VND");
    	String finalTotal = sbTotal.toString();
    	totalPrice_txt.setText(finalTotal);    	
    	
    	
    	

    	
    	listData.forEach(itemService -> {
    		try {
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Resource/itemBillService.fxml"));
                Parent itemBillService = loader.load();                
                itemBillService_Controller controller = loader.getController();
                Object[] data = new Object[4];
                data[0] = itemService[1];
                data[1] = itemService[2];
                data[2] = itemService[3];
                data[3] = itemService[4];                
                controller.setData(data);                
                detailSevice_vbox.getChildren().add(itemBillService);
        	} catch (IOException e) {
	            e.printStackTrace();
	        }
    	});
    	
    	

    }
    
    public void printBill() {
    	printBill_btn.setVisible(false);
    	cancel_btn.setVisible(false);
    	mainBill_anchor.setStyle("-fx-background-color: transparent;");
    	shadow_anchor.setEffect(null);
    	
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("billId", billId_txt.getText());
		data.put("staffId", SystemMessage.getMANV());
		data.put("statusBill", 1);
		
    	WritableImage snapshot = new WritableImage(500, 683);
    	detailSevice_vbox.getScene().getRoot().snapshot(new SnapshotParameters(), snapshot);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String userHome = System.getProperty("user.home");
        String desktopPath = userHome + File.separator + "Desktop";
        String dest = desktopPath + File.separator + billId_txt.getText() + ".pdf";

        File directory = new File(desktopPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

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
    		HOADONDICHVU_BLL.updateBillServiceAfterPrint(data);
    		//PHONG_BLL.changeEmptyRoomStatus(roomNumber);
    		AlertMessage alert = new AlertMessage();
    		alert.successMessage("In hóa đơn và thanh toán dịch vụ thành công");
    		close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
    
    public void close() {
		Stage stage = (Stage) cancel_btn.getScene().getWindow();
		stage.close();		
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		printBill_btn.setVisible(true);
        cancel_btn.setVisible(true);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setWidth(21.0);
        dropShadow.setHeight(21.0);
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setSpread(0.0);
        shadow_anchor.setEffect(dropShadow);
	}
}
