package DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DICHVU {
		private SimpleStringProperty MADV;
		private SimpleStringProperty TENDV;
		private SimpleIntegerProperty LOAIDV;
		private SimpleDoubleProperty GIA;
		
		public DICHVU(String mADV, String tENDV, Integer lOAIDV, Double gIA) {
			MADV = new SimpleStringProperty(mADV);
			TENDV = new SimpleStringProperty(tENDV);
			LOAIDV = new SimpleIntegerProperty(lOAIDV);
			GIA = new SimpleDoubleProperty(gIA);
		}
		
		public String getMADV() {
			return MADV.get();
		}
		
		public void setMADV(String mADV) {
			MADV.set(mADV);
		}
		
		public String getTENDV() {
			return TENDV.get();
		}
		
		public void setTENDV(String tENLOAI) {
			TENDV.set(tENLOAI);
		}
		
		public Integer getLOAIDV() {
			return LOAIDV.get();
		}
		
		public void setLOAIDV(Integer lOAIDV) {
			LOAIDV.set(lOAIDV);
		}
		
		public Double getGIA() {
			return GIA.get();
		}
		
		public void setGIA(Double gIA) {
			GIA.set(gIA);
		}
		
}