package DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class THAMSO {
		private SimpleStringProperty TENTHAMSO;
		private SimpleDoubleProperty GIATRI;
		private SimpleStringProperty MOTA;
		private SimpleStringProperty NGAYSUA;
		
		public THAMSO(String tENTHAMSO, Double gIATRI, String mOTA, String nGAYSUA) {
			TENTHAMSO = new SimpleStringProperty(tENTHAMSO);
			GIATRI = new SimpleDoubleProperty(gIATRI);
			MOTA = new SimpleStringProperty(mOTA);
			NGAYSUA = new SimpleStringProperty(nGAYSUA);
		}
		
		public String getTENTHAMSO() {
			return TENTHAMSO.get();
		}
		
		public void setTENTHAMSO(String tENTHAMSO) {
			TENTHAMSO.set(tENTHAMSO);
		}
		
		public Double getGIATRI() {
			return GIATRI.get();
		}
		
		public void setGIATRI(Double gIATRI) {
			GIATRI.set(gIATRI);
		}
		
		public String getMOTA() {
			return MOTA.get();
		}
		
		public void setMOTA(String mOTA) {
			MOTA.set(mOTA);
		}
		
		public String getNGAYSUA() {
			return NGAYSUA.get();
		}
		
		public void setNGAYSUA(String nGAYSUA) {
			NGAYSUA.set(nGAYSUA);
		}
}