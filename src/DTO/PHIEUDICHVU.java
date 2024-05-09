package DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PHIEUDICHVU {
		private SimpleStringProperty MAPNP;
		private SimpleStringProperty MADV;
		private SimpleIntegerProperty SOLUONG;
		private SimpleDoubleProperty TONGTIEN;
		
		public PHIEUDICHVU(String mAPNP, String mADV, Integer sOLUONG, Double tONGTIEN) {
			MAPNP = new SimpleStringProperty(mAPNP);
			MADV = new SimpleStringProperty(mADV);
			SOLUONG = new SimpleIntegerProperty(sOLUONG);
			TONGTIEN = new SimpleDoubleProperty(tONGTIEN);
		}
		
		public String getMAPNP() {
			return MAPNP.get();
		}
		
		public void setMAPNP(String mAPNP) {
			MAPNP.set(mAPNP);
		}
		
		public String getMADV() {
			return MADV.get();
		}
		
		public void setMADV(String mADV) {
			MADV.set(mADV);
		}
		
		public Integer getSOLUONG() {
			return SOLUONG.get();
		}
		
		public void setSOLUONG(Integer sOLUONG) {
			SOLUONG.set(sOLUONG);
		}
		
		public Double getTONGTIEN() {
			return TONGTIEN.get();
		}
		
		public void setTONGTIEN(Double tONGTIEN) {
			TONGTIEN.set(tONGTIEN);
		}
		
}