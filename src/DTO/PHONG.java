package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PHONG {

	private SimpleStringProperty MAPHONG;
	private SimpleIntegerProperty MALOAIP;
	private SimpleIntegerProperty MATRANGTHAI;
	
	
	public PHONG(String mAPHONG, Integer mALOAIP, Integer mATRANGTHAI) {		
		MAPHONG = new SimpleStringProperty(mAPHONG);
		MALOAIP = new SimpleIntegerProperty(mALOAIP);
		MATRANGTHAI = new SimpleIntegerProperty(mATRANGTHAI);
	}
	
	public String getMAPHONG() {
		return MAPHONG.get();
	}
	
	public void setMAPHONG(String mAPHONG) {
		MAPHONG.set(mAPHONG);
	}
	
	public Integer getMALOAIP() {
		return MALOAIP.get();
	}
	
	public void setMALOAIP(Integer mALOAIP) {
		MALOAIP.set(mALOAIP);
	}
	
	public Integer getMATRANGTHAI() {
		return MATRANGTHAI.get();
	}
	
	public void setMATRANGTHAI(Integer mATRANGTHAI) {
		MATRANGTHAI.set(mATRANGTHAI);
	}
	
	
}
