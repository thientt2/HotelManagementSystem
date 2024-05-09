package DTO;

import javafx.beans.property.SimpleStringProperty;

public class CHITIETPNP{
	
	private SimpleStringProperty MAPNP;
	private SimpleStringProperty MAKHKHAC;
	
	public CHITIETPNP(String mAPNP, String mAKHKHAC) {
		MAPNP = new SimpleStringProperty(mAPNP);
		MAKHKHAC = new SimpleStringProperty(mAKHKHAC);
	}
	
	public String getMAPNP() {
		return MAPNP.get();
	}
	
	public void setMAPNP(String mAPNP) {
		this.MAPNP.set(mAPNP);
	}
	
	public String getMAKHKHAC() {
		return MAKHKHAC.get();
	}
	
	public void setMAKHKHAC(String mAKHKHAC) {
		this.MAKHKHAC.set(mAKHKHAC);
	}

}