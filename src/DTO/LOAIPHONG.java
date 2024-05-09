package DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LOAIPHONG {
		private SimpleIntegerProperty MALOAI;
		private SimpleStringProperty TENLOAI;
		private SimpleStringProperty LOAIGIUONG;
		private SimpleDoubleProperty GIA;
		private SimpleIntegerProperty NGUOITOIDA;
		private SimpleDoubleProperty DIENTICH;
		
		public LOAIPHONG(int mALOAI, String tENLOAI, String lOAIGIUONG, Double gIA, Integer nGUOITOIDA, Double dIENTICH) {
			MALOAI = new SimpleIntegerProperty(mALOAI);
			TENLOAI = new SimpleStringProperty(tENLOAI);
			LOAIGIUONG = new SimpleStringProperty(lOAIGIUONG);
			DIENTICH = new SimpleDoubleProperty(dIENTICH);
			GIA = new SimpleDoubleProperty(gIA);
			NGUOITOIDA = new SimpleIntegerProperty(nGUOITOIDA);
		}
		
		public Integer getMALOAI() {
			return MALOAI.get();
		}
		
		public void setMALOAI(Integer mALOAI) {
			MALOAI.set(mALOAI);
		}
		
		public String getTENLOAI() {
			return TENLOAI.get();
		}
		
		
		public void setTENLOAI(String tENLOAI) {
			TENLOAI.set(tENLOAI);
		}
		
		public String getLOAIGIUONG() {
			return LOAIGIUONG.get();
		}
		
		public void setLOAIGIUONG(String lOAIGIUONG) {
			LOAIGIUONG.set(lOAIGIUONG);
		}		
		
		public Double getGIA() {
			return GIA.get();
		}
		
		public void setGIA(Double gIA) {
			GIA.set(gIA);
		}
		
		public Integer getNGUOITOIDA() {
			return NGUOITOIDA.get();
		}
		
		public void setNGUOITOIDA(Integer nGUOITOIDA) {
			NGUOITOIDA.set(nGUOITOIDA);
		}
		
		public Double getDIENTICH() {
			return DIENTICH.get();
		}
		
		public void setDIENTICH(Double dIENTICH) {
			DIENTICH.set(dIENTICH);
		}
		
}
