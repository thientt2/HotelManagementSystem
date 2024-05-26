package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class BAOCAO {
		private SimpleIntegerProperty MALOAIP;
		private SimpleIntegerProperty NGAY;
		private SimpleIntegerProperty THANG;
		private SimpleIntegerProperty NAM;
		private SimpleLongProperty GIATRI;
		private SimpleIntegerProperty SOLUOTTHUE;
		private SimpleIntegerProperty TILE;
		
		public BAOCAO(Integer mALOAP, Integer nGAY, Integer tHANG, Integer nAM, Long gIATRI, Integer sOLUOTTHUE, Integer tILE) {
			MALOAIP = new SimpleIntegerProperty(mALOAP);
			NGAY = new SimpleIntegerProperty(nGAY);
			THANG = new SimpleIntegerProperty(tHANG);
			NAM = new SimpleIntegerProperty(nAM);
			GIATRI = new SimpleLongProperty(gIATRI);
			SOLUOTTHUE = new SimpleIntegerProperty(sOLUOTTHUE);
			TILE = new SimpleIntegerProperty(tILE);
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
		
		public Long getGIATRI() {
			return GIATRI.get();
		}
		
		public void setGIATRI(Long gIATRI) {
			GIATRI.set(gIATRI);
		}
		
		public Integer getSOLUOTTHUE() {
			return SOLUOTTHUE.get();
		}
		
		public void setSOLUOTTHUE(Integer sOLUOTTHUE) {
			SOLUOTTHUE.set(sOLUOTTHUE);
		}
		
		public Integer getTILE() {
			return TILE.get();
		}
		
		public void setTILE(Integer tILE) {
			TILE.set(tILE);
		}
}