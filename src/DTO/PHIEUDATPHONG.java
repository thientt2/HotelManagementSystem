package DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class PHIEUDATPHONG{
	
	private SimpleStringProperty MAPDP;
	private SimpleStringProperty MAKH;
	private SimpleStringProperty TGDAT;
	private SimpleStringProperty NGAYNHAN;
	private SimpleStringProperty NGAYTRA;
	private SimpleStringProperty HINHTHUC;
	
	public PHIEUDATPHONG(String mAPDP, String mAKH, String tGDAT, String nGAYNHAN, String nGAYTRA, String hINHTHUC) {
		MAPDP = new SimpleStringProperty(mAPDP);
		MAKH = new SimpleStringProperty(mAKH);
		TGDAT = new SimpleStringProperty(tGDAT);
		NGAYNHAN = new SimpleStringProperty(nGAYNHAN);
		NGAYTRA = new SimpleStringProperty(nGAYTRA);
		HINHTHUC = new SimpleStringProperty(hINHTHUC);
	}
	
	public String getMAPDP() {
		return MAPDP.get();
	}
	
	public void setMAPDP(String mAPDP) {
		this.MAPDP.set(mAPDP);
	}
	
	public String getMAKH() {
		return MAKH.get();
	}
	
	public void setMAKH(String mAKH) {
		this.MAKH.set(mAKH);
	}
	
	public String getTGDAT() {
		return TGDAT.get();
	}
	
	public void setTGDAT(String tGDAT) {
		this.TGDAT.set(tGDAT);
	}
	
	public String getNGAYNHAN() {
		return NGAYNHAN.get();
	}
	
	public void setNGAYNHAN(String nGAYNHAN) {
		this.NGAYNHAN.set(nGAYNHAN);
	}
	
	public String getNGAYTRA() {
		return NGAYTRA.get();
	}
	
	public void setNGAYTRA(String nGAYTRA) {
		this.NGAYTRA.set(nGAYTRA);
	}
	
	public String getHINHTHUC() {
		return HINHTHUC.get();
	}
	
	public void setHINHTHUC(String hINHTHUC) {
		this.HINHTHUC.set(hINHTHUC);
	}
	

}