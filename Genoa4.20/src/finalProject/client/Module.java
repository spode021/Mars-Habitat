package finalProject.client;

enum ModType {
	AIRLOCK, MED, CONTROL, POWER, FOOD, DORM, CANTEEN, SAN, GYM, PLAIN, UNKNOWN
}

public class Module {
	// basic constructor
	public Module() {
	}

	// constructor that takes in all module details
	public Module(Integer code, Double x, Double y, String status,
			Integer orientation) {
		codeNum = code;
		xCoor = x;
		yCoor = y;
		this.status = status;
		this.orientation = orientation;
		modType = getType(code);
	}

	// constructor from a string
	public Module(String modString) {
		String remove = "[ ,\":]+";
		String[] data = modString.split(remove);
		int code = Integer.parseInt(data[1]);
		codeNum = code;
		xCoor = Double.parseDouble(data[7]);
		yCoor = Double.parseDouble(data[9]);
		this.status = data[3];
		this.orientation = Integer.parseInt(data[5]);
		modType = getType(code);
	}

	// get method for codeNum
	public Integer getCode() {
		return codeNum;
	}

	// get method for type
	public ModType getType() {
		return modType;
	}

	// get method for xCoor
	public Double getX() {
		return xCoor;
	}

	// get method for yCoor
	public Double getY() {
		return yCoor;
	}

	// get method for status
	public String getStatus() {
		return status;
	}

	// get method for orientation
	public Integer getOrientation() {
		return orientation;
	}

	// setter for codeNum
	public void setCode(Integer code) {
		codeNum = code;
	}

	// setter for xCoor
	public void setX(Double X) {
		xCoor = X;
	}

	// setter for yCoor
	public void setY(Double Y) {
		yCoor = Y;
	}

	// setter for status
	public void setStatus(String stat) {
		status = stat;
	}

	// setter for orientation
	public void setOrientation(Integer orient) {
		orientation = orient;
	}

	// makes a mod class a string
	public String toString() {
		return "code:" + this.getCode() + ", status:\"," + this.getStatus()
				+ "\", turns:" + this.getOrientation() + ",X:" + this.getX()
				+ ", Y:" + this.getY();
	}

	// set mod type
	public ModType getType(Integer code) {
		ModType type;
		if (code >= 1 && code <= 40) {
			type = ModType.PLAIN;
		} else if (code >= 61 && code <= 80) {
			type = ModType.DORM;
		} else if (code >= 91 && code <= 100) {
			type = ModType.SAN;
		} else if (code >= 111 && code <= 120) {
			type = ModType.FOOD;
		} else if (code >= 131 && code <= 134) {
			type = ModType.GYM;
		} else if (code >= 141 && code <= 144) {
			type = ModType.CANTEEN;
		} else if (code >= 151 && code <= 154) {
			type = ModType.POWER;
		} else if (code >= 161 && code <= 164) {
			type = ModType.CONTROL;
		} else if (code >= 171 && code <= 174) {
			type = ModType.AIRLOCK;
		} else if (code >= 181 && code <= 184) {
			type = ModType.MED;
		} else {
			type = ModType.UNKNOWN;
		}
		return type;
	}

	// Private data variables
	private ModType modType;
	private Integer codeNum;
	private Double xCoor;
	private Double yCoor;
	private String status;
	private Integer orientation;
}
