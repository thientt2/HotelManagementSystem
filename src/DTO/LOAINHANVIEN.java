package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LOAINHANVIEN {
	
	private SimpleIntegerProperty MALOAINV;
	private SimpleStringProperty TENLOAI;
	
	public LOAINHANVIEN(Integer mALOAINV, String tENLOAI) {
		MALOAINV = new SimpleIntegerProperty(mALOAINV);
		TENLOAI = new SimpleStringProperty(tENLOAI);
	}
	
	public Integer getMALOAINV() {
		return MALOAINV.get();
	}
	
	public void setMALOAINV(Integer mALOAINV) {
		MALOAINV.set(mALOAINV);
	}
	
	public String getTENLOAI() {
		return TENLOAI.get();
	}	
	
	public void setTENLOAI(String tENLOAI) {
		TENLOAI.set(tENLOAI);
	}
	
	
}
