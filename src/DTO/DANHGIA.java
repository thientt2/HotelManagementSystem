package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DANHGIA {
		private SimpleStringProperty MADG;
		private SimpleIntegerProperty SAO;
		private SimpleStringProperty EMAIL;
		private SimpleStringProperty NOIDUNG;
		private SimpleStringProperty NGAYTAOFEEDBACK;
		
		public DANHGIA(String mADG, Integer sAO, String eMAIL, String nOIDUNG, String nGAYTAOFEEDBACK) {
			MADG = new SimpleStringProperty(mADG);
			SAO = new SimpleIntegerProperty(sAO);
			EMAIL = new SimpleStringProperty(eMAIL);
			NOIDUNG = new SimpleStringProperty(nOIDUNG);
			NGAYTAOFEEDBACK = new SimpleStringProperty(nGAYTAOFEEDBACK);
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
		
		public String getEMAIL() {
			return EMAIL.get();
		}
		
		public void setEMAIL(String eMAIL) {
			EMAIL.set(eMAIL);
		}
		
		public String getNOIDUNG() {
			return NOIDUNG.get();
		}
		
		public void setNOIDUNG(String nOIDUNG) {
			NOIDUNG.set(nOIDUNG);
		}
		
		public String getNGAYTAOFEEDBACK() {
			return NGAYTAOFEEDBACK.get();
		}
		
		public void setNGAYTAOFEEDBACK(String nGAYTAOFEEDBACK) {
			NGAYTAOFEEDBACK.set(nGAYTAOFEEDBACK);
		}
		
}