package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LOAIDICHVU {
		private SimpleIntegerProperty MALOAIDV;
		private SimpleStringProperty TENLOAIDV;
		
		public LOAIDICHVU(int mALOAIDV, String tENLOAIDV) {
			MALOAIDV = new SimpleIntegerProperty(mALOAIDV);
			TENLOAIDV = new SimpleStringProperty(tENLOAIDV);
		}
		
		public Integer getMALOAIDV() {
			return MALOAIDV.get();
		}
		
		public void setMALOAIDV(Integer mALOAIDV) {
			MALOAIDV.set(mALOAIDV);;
		}
		
		public String getTENLOAIDV() {
			return TENLOAIDV.get();
		}
		
		public void setTENLOAIDV(String tENLOAIDV) {
			TENLOAIDV.set(tENLOAIDV);
		}
		
}