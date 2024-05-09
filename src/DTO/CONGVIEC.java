package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CONGVIEC{
	
	private SimpleIntegerProperty MACONGVIEC;
	private SimpleStringProperty TENCV;
	private SimpleStringProperty MANHINH;
	
	public CONGVIEC(Integer mACONGVIEC, String tENCV, String mANHINH) {
		MACONGVIEC = new SimpleIntegerProperty(mACONGVIEC);
		TENCV = new SimpleStringProperty(tENCV);
		MANHINH = new SimpleStringProperty(mANHINH);
	}
	
	public Integer getMACONGVIEC() {
		return MACONGVIEC.get();
	}
	
	public void setMACONGVIEC(Integer mACONGVIEC) {
		this.MACONGVIEC.set(mACONGVIEC);
	}
	
	public String getTENCV() {
		return TENCV.get();
	}
	
	public void setTENCV(String tENCV) {
		this.TENCV.set(tENCV);
	}
	
	public String getMANHINH() {
		return MANHINH.get();
	}
	
	public void setMANHINH(String mANHINH) {
		this.MANHINH.set(mANHINH);
	}

}