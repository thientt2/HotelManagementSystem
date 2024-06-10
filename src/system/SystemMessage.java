package system;

public class SystemMessage {
	public static String ERROR_MESSAGE = "";
	
	private static String MANV;
	private static String Type;
	
	public static void setMANV(String mANV) {
		MANV = mANV;
	}
	public static String getMANV() {
		return MANV;
	}
	
	public static void setType(String type) {
		Type = type;
	}
	public static String getType() {
		return Type;
	}
}
