package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DANHGIA {
		private SimpleStringProperty MADG;
		private SimpleIntegerProperty SAO;
		private SimpleStringProperty MAKH;
		private SimpleStringProperty NOIDUNG;
		
		public DANHGIA(String mADG, Integer sAO, String mAKH, String nOIDUNG) {
			MADG = new SimpleStringProperty(mADG);
			SAO = new SimpleIntegerProperty(sAO);
			MAKH = new SimpleStringProperty(mAKH);
			NOIDUNG = new SimpleStringProperty(nOIDUNG);
		}
		
		public String getMADG() {
			return MADG.get();
		}
		
		public void setMADG(String mADG) {
			MADG.set(mADG);
		}
		
		public Integer getSAO() {
			return SAO.get();
		}
		
		public void setSAO(Integer sAO) {
			SAO.set(sAO);
		}
		
		public String getMAKH() {
			return MAKH.get();
		}
		
		public void setMAKH(String mAKH) {
			MAKH.set(mAKH);
		}
		
		public String getNOIDUNG() {
			return NOIDUNG.get();
		}
		
		public void setNOIDUNG(String nOIDUNG) {
			NOIDUNG.set(nOIDUNG);
		}
		
}