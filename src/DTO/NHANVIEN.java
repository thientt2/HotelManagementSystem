package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class NHANVIEN {
	private SimpleStringProperty MANV;
	private SimpleStringProperty TENNV;
	private SimpleStringProperty EMAIL;
	private SimpleIntegerProperty MALOAINV; 
	private SimpleStringProperty CCCD;
	private SimpleStringProperty NGAYSINH;	
	private SimpleStringProperty GIOITINH;
	private SimpleStringProperty DIACHI;
	private SimpleStringProperty SDT;
	private SimpleStringProperty NGAYVAOLAM;
	private SimpleStringProperty TENDANGNHAP;
	private SimpleStringProperty MATKHAU;
	private SimpleIntegerProperty TINHTRANG;
	private byte[] PHOTO;
	
	
	public NHANVIEN(String mANV, String tENNV, String eMAIL, Integer mALOAINV, String cCCD, String nGAYSINH, String gIOITINH, String dIACHI, String sDT, String nGAYVAOLAM, String tENDANGNHAP, String mATKHAU, Integer tINHTRANG, byte[] pHOTO) {
		MANV = new SimpleStringProperty(mANV);
		TENNV = new SimpleStringProperty(tENNV);
		EMAIL = new SimpleStringProperty(eMAIL);
		MALOAINV = new SimpleIntegerProperty(mALOAINV);
		CCCD = new SimpleStringProperty(cCCD);
		NGAYSINH = new SimpleStringProperty(nGAYSINH);
		GIOITINH = new SimpleStringProperty(gIOITINH);
		DIACHI = new SimpleStringProperty(dIACHI);
		SDT = new SimpleStringProperty(sDT);
		NGAYVAOLAM = new SimpleStringProperty(nGAYVAOLAM);
		TENDANGNHAP = new SimpleStringProperty(tENDANGNHAP);
		MATKHAU = new SimpleStringProperty(mATKHAU);
		TINHTRANG = new SimpleIntegerProperty(tINHTRANG);
		this.PHOTO = pHOTO;
	}
	
	public byte[] getPHOTO() { 
        return PHOTO;
    }
    
    public void setPHOTO(byte[] pHOTO) { 
        this.PHOTO = pHOTO;
    }
	
	public Integer getTINHTRANG() {
		return TINHTRANG.get();
	}
	
	public void setTINHTRANG(Integer tINHTRANG) {
		TINHTRANG.set(tINHTRANG);
	}
	
	public String getMANV() {
		return MANV.get();
	}
	
	public void setMANV(String mANV) {
		MANV.set(mANV);
	}
	
	public String getTENDANGNHAP() {
		return TENDANGNHAP.get();
	}
	
	public void setTENDANGNHAP(String tENDANGNHAP) {
		TENDANGNHAP.set(tENDANGNHAP);
	}
	
	public String getTENNV() {
		return TENNV.get();
	}
	
	public void setTENNV(String tENNV) {
		TENNV.set(tENNV);
	}
	
	public String getMATKHAU() {
		return MATKHAU.get();
	}
	
	public void setMATKHAU(String mATKHAU) {
		MATKHAU.set(mATKHAU);
	}
	
	public String getEMAIL() {
		return EMAIL.get();
	}
	
	public void setEMAIL(String eMAIL) {
		EMAIL.set(eMAIL);
	}
	
	public Integer getMALOAINV() {
		return MALOAINV.get();
	}
	
	public void setMALOAINV(Integer mALOAINV) {
		MALOAINV.set(mALOAINV);
	}
	
	public String getCCCD() {
		return CCCD.get();
	}
	
	public void setCCCD(String cCCD) {
		CCCD.set(cCCD);
	}
	
	public String getNGAYSINH() {
		return NGAYSINH.get();
	}
	
	public void setNGAYSINH(String nGAYSINH) {
		NGAYSINH.set(nGAYSINH);
	}
	
	public String getGIOITINH() {
		return GIOITINH.get();
	}
	
	public void setGIOITINH(String gIOITINH) {
		GIOITINH.set(gIOITINH);
	}
	
	public String getDIACHI() {
		return DIACHI.get();
	}
	
	public void setDIACHI(String dIACHI) {
		DIACHI.set(dIACHI);
	}
	
	public String getSDT() {
		return SDT.get();
	}
	
	public void setSDT(String sDT) {
		SDT.set(sDT);
	}
	
	public String getNGAYVAOLAM() {
		return NGAYVAOLAM.get();
	}
	
	public void setNGAYVAOLAM(String nGAYVAOLAM) {
		NGAYVAOLAM.set(nGAYVAOLAM);
	}
	

}
