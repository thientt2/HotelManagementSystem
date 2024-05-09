package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TRANGTHAIPHONG {
	private SimpleIntegerProperty MATRANGTHAI;
	private SimpleStringProperty  TENTRANGTHAI;
	
	public int getMATRANGTHAI() {
		return MATRANGTHAI.get();
	}
	
	public void setMATRANGTHAI(int mATRANGTHAI) {
		MATRANGTHAI.set(mATRANGTHAI);
	}
	
	public String getTENTRANGTHAI() {
		return TENTRANGTHAI.get();
	}
	
	public void setTENTRANGTHAI(String tENTRANGTHAI) {
		TENTRANGTHAI.set(tENTRANGTHAI);
	}
	
	public TRANGTHAIPHONG(int mATRANGTHAI, String tENTRANGTHAI) {
		MATRANGTHAI = new SimpleIntegerProperty(mATRANGTHAI);
		TENTRANGTHAI = new SimpleStringProperty(tENTRANGTHAI);
	}

	

}
