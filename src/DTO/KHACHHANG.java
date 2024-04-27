package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class KHACHHANG {
	
	private SimpleStringProperty MAKH;
	private SimpleStringProperty TENKH;
	private SimpleStringProperty CCCD;
	private SimpleStringProperty GIOITINH;
	private SimpleStringProperty NGAYSINH;
	private SimpleStringProperty EMAIL;
	private SimpleIntegerProperty LOAIKH;
	private SimpleStringProperty DIACHI;
	private SimpleStringProperty SDT;
	private SimpleStringProperty QUOCTICH;
	private SimpleIntegerProperty TINHTRANG;
	
	public KHACHHANG(String mAKH, String tENKH, String cCCD, String gIOITINH, String nGAYSINH, String eMAIL, Integer lOAIKH, String dIACHI, String sDT, String qUOCTICH, Integer tINHTRANG) {
		MAKH = new SimpleStringProperty(mAKH);
		TENKH = new SimpleStringProperty(tENKH);
		CCCD = new SimpleStringProperty(cCCD);
		GIOITINH = new SimpleStringProperty(gIOITINH);
		NGAYSINH = new SimpleStringProperty(nGAYSINH);
		EMAIL = new SimpleStringProperty(eMAIL);
		LOAIKH = new SimpleIntegerProperty(lOAIKH);
		DIACHI = new SimpleStringProperty(dIACHI);
		SDT = new SimpleStringProperty(sDT);
		QUOCTICH = new SimpleStringProperty(qUOCTICH);
		TINHTRANG = new SimpleIntegerProperty(tINHTRANG);
	}
	
	public String getMAKH() {
		return MAKH.get();
	}
	
	public void setMAKH(String mAKH) {
		this.MAKH.set(mAKH);
	}
	
	public String getTENKH() {
		return TENKH.get();
	}
	
	public void setTENKH(String tENKH) {
		this.TENKH.set(tENKH);
	}
	
	public String getCCCD() {
		return CCCD.get();
	}
	
	public void setCCCD(String cCCD) {
		this.CCCD.set(cCCD);
	}
	
	public String getGIOITINH() {
		return GIOITINH.get();
	}
	
	public void setGIOITINH(String gIOITINH) {
		this.GIOITINH.set(gIOITINH);
	}
	
	public String getNGAYSINH() {
		return NGAYSINH.get();
	}
	
	public void setNGAYSINH(String nGAYSINH) {
		this.NGAYSINH.set(nGAYSINH);
	}
	
	public String getEMAIL() {
		return EMAIL.get();
	}
	
	public void setEMAIL(String eMAIL) {
		this.EMAIL.set(eMAIL);
	}
	
	public Integer getLOAIKH() {
		return LOAIKH.get();
	}
	
	public void setLOAIKH(Integer lOAIKH) {
		this.LOAIKH.set(lOAIKH);
	}
	
	public String getDIACHI() {
		return DIACHI.get();
	}
	
	public void setDIACHI(String dIACHI) {
		this.DIACHI.set(dIACHI);
	}
	
	public String getSDT() {
		return SDT.get();
	}
	
	public void setSDT(String sDT) {
		this.SDT.set(sDT);
	}
	
	public String getQUOCTICH() {
		return QUOCTICH.get();
	}
	
	public void setQUOCTICH(String qUOCTICH) {
		this.QUOCTICH.set(qUOCTICH);
	}
	
	public Integer getTINHTRANG() {
		return TINHTRANG.get();
	}
	
	public void setTINHTRANG(Integer tINHTRANG) {
		this.TINHTRANG.set(tINHTRANG);
	}
	
}
