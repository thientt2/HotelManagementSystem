package DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LOAIPHONG {
		private SimpleIntegerProperty MALOAI;
		private SimpleStringProperty TENLOAI;
		private SimpleDoubleProperty GIA;
		private SimpleIntegerProperty NGUOITOIDA;
		
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
		public LOAIPHONG(int mALOAI, String tENLOAI, double gIA, int nGUOITOIDA) {
			MALOAI = new SimpleIntegerProperty(mALOAI);
			TENLOAI = new SimpleStringProperty(tENLOAI);
			GIA = new SimpleDoubleProperty(gIA);
			NGUOITOIDA = new SimpleIntegerProperty(nGUOITOIDA);
		}
		

}
