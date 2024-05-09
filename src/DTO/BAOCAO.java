package DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BAOCAO {
		private SimpleIntegerProperty MALOAIP;
		private SimpleIntegerProperty NGAY;
		private SimpleIntegerProperty THANG;
		private SimpleIntegerProperty NAM;
		private SimpleDoubleProperty GIATRI;
		private SimpleIntegerProperty SOLUOTTHUE;
		private SimpleDoubleProperty TILE;
		
		public BAOCAO(Integer mALOAP, Integer nGAY, Integer tHANG, Integer nAM, Double gIATRI, Integer sOLUOTTHUE, Double tILE) {
			MALOAIP = new SimpleIntegerProperty(mALOAP);
			NGAY = new SimpleIntegerProperty(nGAY);
			THANG = new SimpleIntegerProperty(tHANG);
			NAM = new SimpleIntegerProperty(nAM);
			GIATRI = new SimpleDoubleProperty(gIATRI);
			SOLUOTTHUE = new SimpleIntegerProperty(sOLUOTTHUE);
			TILE = new SimpleDoubleProperty(tILE);
		}
		
		public Integer getMALOAIP() {
			return MALOAIP.get();
		}
		
		public void setMALOAIP(Integer mALOAP) {
			MALOAIP.set(mALOAP);
		}
		
		public Integer getNGAY() {
			return NGAY.get();
		}
		
		public void setNGAY(Integer nGAY) {
			NGAY.set(nGAY);
		}
		
		public Integer getTHANG() {
			return THANG.get();
		}
		
		public void setTHANG(Integer tHANG) {
			THANG.set(tHANG);
		}
		
		public Integer getNAM() {
			return NAM.get();
		}
		
		public void setNAM(Integer nAM) {
			NAM.set(nAM);
		}
		
		public Double getGIATRI() {
			return GIATRI.get();
		}
		
		public void setGIATRI(Double gIATRI) {
			GIATRI.set(gIATRI);
		}
		
		public Integer getSOLUOTTHUE() {
			return SOLUOTTHUE.get();
		}
		
		public void setSOLUOTTHUE(Integer sOLUOTTHUE) {
			SOLUOTTHUE.set(sOLUOTTHUE);
		}
		
		public Double getTILE() {
			return TILE.get();
		}
		
		public void setTILE(Double tILE) {
			TILE.set(tILE);
		}
}