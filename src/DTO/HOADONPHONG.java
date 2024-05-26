package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HOADONPHONG {
	private SimpleStringProperty MAHDP;
	private SimpleStringProperty MAPDP;
	private SimpleStringProperty NVNHAP;
	private SimpleStringProperty NGAYTAO;
	private SimpleIntegerProperty TRANGTHAI;
	private SimpleIntegerProperty GIAMGIA;
	private SimpleIntegerProperty TONGTIEN;
	
	public HOADONPHONG(String mAHDP, String mAPDP, String nVNHAP, String nGAYTAO, Integer tRANGTHAI, Integer gIAMGIA, Integer tONGTIEN) {
		MAHDP = new SimpleStringProperty(mAHDP);
		MAPDP = new SimpleStringProperty(mAPDP);
		NVNHAP = new SimpleStringProperty(nVNHAP);
		NGAYTAO = new SimpleStringProperty(nGAYTAO);
		TRANGTHAI = new SimpleIntegerProperty(tRANGTHAI);
		GIAMGIA = new SimpleIntegerProperty(gIAMGIA);
		TONGTIEN = new SimpleIntegerProperty(tONGTIEN);
	}
	
	public String getMAHDP() {
		return MAHDP.get();
	}
	
	public void setMAHDP(String mAHDP) {
		this.MAHDP.set(mAHDP);
	}
	
	public String getMAPDP() {
		return MAPDP.get();
	}
	
	public void setMAPDP(String mAPDP) {
		this.MAPDP.set(mAPDP);
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
	
	public Integer getTRANGTHAI() {
		return TRANGTHAI.get();
	}
	
	public void setTRANGTHAI(Integer tRANGTHAI) {
		this.TRANGTHAI.set(tRANGTHAI);
	}
	
	public Integer getGIAMGIA() {
		return GIAMGIA.get();
	}
	
	public void setGIAMGIA(Integer gIAMGIA) {
		this.GIAMGIA.set(gIAMGIA);
	}
	
	public Integer getTONGTIEN() {
		return TONGTIEN.get();
	}
	
	public void setTONGTIEN(Integer tONGTIEN) {
		this.TONGTIEN.set(tONGTIEN);
	}
}
