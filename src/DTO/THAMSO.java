package DTO;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class THAMSO {
		private SimpleStringProperty TENTHAMSO;
		private SimpleFloatProperty GIATRI;
		private SimpleStringProperty MOTA;
		private SimpleStringProperty NGAYSUA;
		
		public THAMSO(String tENTHAMSO, Float gIATRI, String mOTA, String nGAYSUA) {
			TENTHAMSO = new SimpleStringProperty(tENTHAMSO);
			GIATRI = new SimpleFloatProperty(gIATRI);
			MOTA = new SimpleStringProperty(mOTA);
			NGAYSUA = new SimpleStringProperty(nGAYSUA);
		}
		
		public String getTENTHAMSO() {
			return TENTHAMSO.get();
		}
		
		public void setTENTHAMSO(String tENTHAMSO) {
			TENTHAMSO.set(tENTHAMSO);
		}
		
		public Float getGIATRI() {
			return GIATRI.get();
		}
		
		public void setGIATRI(Float gIATRI) {
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