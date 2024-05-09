package DTO;

import javafx.beans.property.SimpleStringProperty;

public class PHIEUNHANPHONG{
	
	private SimpleStringProperty MAPNP;
	private SimpleStringProperty MAPDP;
	private SimpleStringProperty MAPHONG;
	private SimpleStringProperty TGNHAN;
	private SimpleStringProperty TGTRA;
	
	public PHIEUNHANPHONG(String mAPNP, String mAPDP, String mAPHONG, String tGNHAN, String tGTRA) {
		MAPNP = new SimpleStringProperty(mAPNP);
		MAPDP = new SimpleStringProperty(mAPDP);
		MAPHONG = new SimpleStringProperty(mAPHONG);
		TGNHAN = new SimpleStringProperty(tGNHAN);
		TGTRA = new SimpleStringProperty(tGTRA);
	}
	
	public String getMAPNP() {
		return MAPNP.get();
	}
	
	public void setMAPNP(String mAPNP) {
		this.MAPNP.set(mAPNP);
	}
	
	public String getMAPDP() {
		return MAPDP.get();
	}
	
	public void setMAPDP(String mAPDP) {
		this.MAPDP.set(mAPDP);
	}
	
	public String getMAPHONG() {
		return MAPHONG.get();
	}
	
	public void setMAPHONG(String mAPHONG) {
		this.MAPHONG.set(mAPHONG);
	}
	
	public String getTGNHAN() {
		return TGNHAN.get();
	}
	
	public void setTGNHAN(String tGNHAN) {
		this.TGNHAN.set(tGNHAN);
	}
	
	public String getTGTRA() {
		return TGTRA.get();
	}
	
	public void setTGTRA(String tGTRA) {
		this.TGTRA.set(tGTRA);
	}
	
}