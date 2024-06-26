package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TRANGTHAIPHONG {
	private SimpleIntegerProperty MATRANGTHAI;
	private SimpleStringProperty  TENTRANGTHAI;
	
	public Integer getMATRANGTHAI() {
		return MATRANGTHAI.get();
	}
	
	public void setMATRANGTHAI(Integer mATRANGTHAI) {
		MATRANGTHAI.set(mATRANGTHAI);
	}
	
	public String getTENTRANGTHAI() {
		return TENTRANGTHAI.get();
	}
	
	public void setTENTRANGTHAI(String tENTRANGTHAI) {
		TENTRANGTHAI.set(tENTRANGTHAI);
	}
	
	public TRANGTHAIPHONG(Integer mATRANGTHAI, String tENTRANGTHAI) {
		MATRANGTHAI = new SimpleIntegerProperty(mATRANGTHAI);
		TENTRANGTHAI = new SimpleStringProperty(tENTRANGTHAI);
	}

	

}
