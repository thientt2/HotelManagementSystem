package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CHITIETPDP{
	
	private SimpleStringProperty MAPDP;
	private SimpleIntegerProperty MALOAIP;
	private SimpleIntegerProperty SOLUONG;
	
	public CHITIETPDP(String mAPDP, Integer mALOAIP, Integer sOLUONG) {
		MAPDP = new SimpleStringProperty(mAPDP);
		MALOAIP = new SimpleIntegerProperty(mALOAIP);
		SOLUONG = new SimpleIntegerProperty(sOLUONG);
	}
	
	public String getMAPDP() {
		return MAPDP.get();
	}
	
	public void setMAPDP(String mAPDP) {
		this.MAPDP.set(mAPDP);
	}
	
	public Integer getMALOAIP() {
		return MALOAIP.get();
	}
	
	public void setMALOAIP(Integer mALOAIP) {
		this.MALOAIP.set(mALOAIP);
	}
	
	public Integer getSOLUONG() {
		return SOLUONG.get();
	}
	
	public void setSOLUONG(Integer sOLUONG) {
		this.SOLUONG.set(sOLUONG);
	}

}