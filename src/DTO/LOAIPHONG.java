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
		
		public int getMALOAI() {
			return MALOAI.get();
		}
		public void setMALOAI(int mALOAI) {
			MALOAI.set(mALOAI);;
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
		public double getGIA() {
			return GIA.get();
		}
		public void setGIA(double gIA) {
			GIA.set(gIA);
		}
		public int getNGUOITOIDA() {
			return NGUOITOIDA.get();
		}
		public void setNGUOITOIDA(int nGUOITOIDA) {
			NGUOITOIDA.set(nGUOITOIDA);;
		}
		public double getDIENTICH() {
			return DIENTICH.get();
		}
		public void setDIENTICH(double dIENTICH) {
			DIENTICH.set(dIENTICH);
		}
		public LOAIPHONG(int mALOAI, String tENLOAI, String lOAIGIUONG,double gIA, int nGUOITOIDA, double dIENTICH) {
			MALOAI = new SimpleIntegerProperty(mALOAI);
			TENLOAI = new SimpleStringProperty(tENLOAI);
			LOAIGIUONG = new SimpleStringProperty(lOAIGIUONG);
			DIENTICH = new SimpleDoubleProperty(dIENTICH);
			GIA = new SimpleDoubleProperty(gIA);
			NGUOITOIDA = new SimpleIntegerProperty(nGUOITOIDA);
		}
		

}
