package DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HOADONDICHVU {
	private SimpleStringProperty MAHD;
	private SimpleStringProperty MAPNP;
	private SimpleStringProperty NVNHAP;
	private SimpleStringProperty NGAYTAO;
	private SimpleDoubleProperty GIADICHVU;
	private SimpleDoubleProperty PHUTHU;
	private SimpleIntegerProperty TRANGTHAI;
	private SimpleDoubleProperty TONGTIEN;
	
	public HOADONDICHVU(String mAHD, String mAPNP, String nVNHAP, String nGAYTAO, double gIADICHVU, double pHUTHU, int tRANGTHAI, double tONGTIEN) {
		MAHD = new SimpleStringProperty(mAHD);
		MAPNP = new SimpleStringProperty(mAPNP);
		NVNHAP = new SimpleStringProperty(nVNHAP);
		NGAYTAO = new SimpleStringProperty(nGAYTAO);
		GIADICHVU = new SimpleDoubleProperty(gIADICHVU);
		PHUTHU = new SimpleDoubleProperty(pHUTHU);
		TRANGTHAI = new SimpleIntegerProperty(tRANGTHAI);
		TONGTIEN = new SimpleDoubleProperty(tONGTIEN);
	}
	
	public String getMAHD() {
		return MAHD.get();
	}
	
	public void setMAHD(String mAHD) {
		this.MAHD.set(mAHD);
	}
	
	public String getMAPNP() {
		return MAPNP.get();
	}
	
	public void setMAPNP(String mAPNP) {
		this.MAPNP.set(mAPNP);
	}
	
	public String getNVNHAP() {
		return NVNHAP.get();
	}
	
	public void setNVNHAP(String nVNHAP) {
		this.NVNHAP.set(nVNHAP);
	}
	
	public String getNGAYTAO() {
		return NGAYTAO.get();
	}
	
	public void setNGAYTAO(String nGAYTAO) {
		this.NGAYTAO.set(nGAYTAO);
	}
	
	public Double getGIADICHVU() {
		return GIADICHVU.get();
	}
	
	public void setGIADICHVU(Double gIADICHVU) {
		this.GIADICHVU.set(gIADICHVU);
	}
	
	public Double getPHUTHU() {
		return PHUTHU.get();
	}
	
	public void setPHUTHU(Double pHUTHU) {
		this.PHUTHU.set(pHUTHU);
	}
	
	public Integer getTRANGTHAI() {
		return TRANGTHAI.get();
	}
	
	public void setTRANGTHAI(Integer tRANGTHAI) {
		this.TRANGTHAI.set(tRANGTHAI);
	}
	
	public Double getTONGTIEN() {
		return TONGTIEN.get();
	}
	
	public void setTONGTIEN(Double tONGTIEN) {
		this.TONGTIEN.set(tONGTIEN);
	}
}
