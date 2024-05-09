package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PHONG {

	private SimpleStringProperty MAPHONG;
	private SimpleIntegerProperty MALOAIP;
	private SimpleIntegerProperty MATRANGTHAI;
	
	
	public PHONG(String mAPHONG, int mALOAIP, int mATRANGTHAI) {		
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
	
	public int getMALOAIP() {
		return MALOAIP.get();
	}
	
	public void setMALOAIP(int mALOAIP) {
		MALOAIP.set(mALOAIP);
	}
	
	public int getMATRANGTHAI() {
		return MATRANGTHAI.get();
	}
	
	public void setMATRANGTHAI(int mATRANGTHAI) {
		MATRANGTHAI.set(mATRANGTHAI);
	}
	
	
}
