package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HOADONDICHVU {
	private SimpleStringProperty MAHD;
	private SimpleStringProperty MAPNP;
	private SimpleStringProperty NVNHAP;
	private SimpleStringProperty NGAYTAO;
	private SimpleIntegerProperty GIADICHVU;
	private SimpleIntegerProperty PHUTHU;
	private SimpleIntegerProperty TRANGTHAI;
	private SimpleIntegerProperty TONGTIEN;
	
	public HOADONDICHVU(String mAHD, String mAPNP, String nVNHAP, String nGAYTAO, Integer gIADICHVU, Integer pHUTHU, Integer tRANGTHAI, Integer tONGTIEN) {
		MAHD = new SimpleStringProperty(mAHD);
		MAPNP = new SimpleStringProperty(mAPNP);
		NVNHAP = new SimpleStringProperty(nVNHAP);
		NGAYTAO = new SimpleStringProperty(nGAYTAO);
		GIADICHVU = new SimpleIntegerProperty(gIADICHVU);
		PHUTHU = new SimpleIntegerProperty(pHUTHU);
		TRANGTHAI = new SimpleIntegerProperty(tRANGTHAI);
		TONGTIEN = new SimpleIntegerProperty(tONGTIEN);
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
	
	public Integer getGIADICHVU() {
		return GIADICHVU.get();
	}
	
	public void setGIADICHVU(Integer gIADICHVU) {
		this.GIADICHVU.set(gIADICHVU);
	}
	
	public Integer getPHUTHU() {
		return PHUTHU.get();
	}
	
	public void setPHUTHU(Integer pHUTHU) {
		this.PHUTHU.set(pHUTHU);
	}
	
	public Integer getTRANGTHAI() {
		return TRANGTHAI.get();
	}
	
	public void setTRANGTHAI(Integer tRANGTHAI) {
		this.TRANGTHAI.set(tRANGTHAI);
	}
	
	public Integer getTONGTIEN() {
		return TONGTIEN.get();
	}
	
	public void setTONGTIEN(Integer tONGTIEN) {
		this.TONGTIEN.set(tONGTIEN);
	}
}
