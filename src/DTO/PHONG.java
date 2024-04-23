package DTO;

import javafx.beans.property.SimpleIntegerProperty;

public class PHONG {

	private SimpleIntegerProperty MAPHONG;
	private SimpleIntegerProperty MALOAIP;
	private SimpleIntegerProperty MATRANGTHAI;
	
	public PHONG(int mAPHONG, int mALOAIP, int mATRANGTHAI) {		
		MAPHONG = new SimpleIntegerProperty(mALOAIP);
		MALOAIP = new SimpleIntegerProperty(mAPHONG);
		MATRANGTHAI = new SimpleIntegerProperty(mATRANGTHAI);
	}
	public int getMAPHONG() {
		return MAPHONG.get();
	}
	public void setMAPHONG(int mAPHONG) {
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
