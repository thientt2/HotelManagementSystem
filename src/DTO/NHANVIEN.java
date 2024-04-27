package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class NHANVIEN {
	public SimpleStringProperty MANV;
	public SimpleStringProperty TENDANGNHAP; 
	public SimpleStringProperty TENNV;
	public SimpleStringProperty MATKHAU;
	public SimpleStringProperty EMAIL;
	public SimpleIntegerProperty MALOAINV;
	public SimpleStringProperty CCCD;
	public SimpleStringProperty NGAYSINH;
	public SimpleStringProperty GIOITINH;
	public SimpleStringProperty DIACHI;
	public SimpleStringProperty SDT;
	public SimpleStringProperty NGAYVAOLAM;
	
	
	public NHANVIEN(String mANV,String tENDANGNHAP, String tENNV, String mATKHAU,
			String eMAIL, Integer mALOAINV, String cCCD,
			String nGAYSINH, String gIOITINH, String dIACHI,
			String sDT, String nGAYVAOLAM) {
		MANV = new SimpleStringProperty(mANV);
		TENDANGNHAP = new SimpleStringProperty(tENDANGNHAP);
		TENNV = new SimpleStringProperty(tENNV);
		MATKHAU = new SimpleStringProperty(mATKHAU);
		EMAIL = new SimpleStringProperty(eMAIL);
		MALOAINV = new SimpleIntegerProperty(mALOAINV);;
		CCCD = new SimpleStringProperty(cCCD);
		NGAYSINH = new SimpleStringProperty(nGAYSINH);
		GIOITINH = new SimpleStringProperty(gIOITINH);
		DIACHI = new SimpleStringProperty(dIACHI);
		SDT = new SimpleStringProperty(sDT);
		NGAYVAOLAM = new SimpleStringProperty(nGAYVAOLAM);
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
