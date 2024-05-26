package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CHITIETHOADON {
		private SimpleStringProperty MAHD;
		private SimpleStringProperty MADV;
		private SimpleIntegerProperty SOLUONG;
		private SimpleIntegerProperty TONGTIEN;
		
		public CHITIETHOADON(String mAHD, String mADV, Integer sOLUONG, Integer tONGTIEN) {
			MAHD = new SimpleStringProperty(mAHD);
			MADV = new SimpleStringProperty(mADV);
			SOLUONG = new SimpleIntegerProperty(sOLUONG);
			TONGTIEN = new SimpleIntegerProperty(tONGTIEN);
		}
		
		public String getMAHD() {
			return MAHD.get();
		}
		
		public void setMAHD(String mAHD) {
			MAHD.set(mAHD);
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
		
		public Integer getTONGTIEN() {
			return TONGTIEN.get();
		}
		
		public void setTONGTIEN(Integer tONGTIEN) {
			TONGTIEN.set(tONGTIEN);
		}
		
}