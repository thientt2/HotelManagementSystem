package DTO;

import javafx.beans.property.SimpleIntegerProperty;

public class TRUYCAP{
	
	private SimpleIntegerProperty MALOAINV;
	private SimpleIntegerProperty MACONGVIEC;
	
	public TRUYCAP(Integer mALOAINV, Integer mACONGVIEC) {
		MALOAINV = new SimpleIntegerProperty(mALOAINV);
		MACONGVIEC = new SimpleIntegerProperty(mACONGVIEC);
	}
	
	public Integer getMALOAINV() {
		return MALOAINV.get();
	}
	
	public void setMALOAINV(Integer mALOAINV) {
		this.MALOAINV.set(mALOAINV);
	}
	
	public Integer getMACONGVIEC() {
		return MACONGVIEC.get();
	}
	
	public void setMACONGVIEC(Integer mACONGVIEC) {
		this.MACONGVIEC.set(mACONGVIEC);
	}

}