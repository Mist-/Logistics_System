package data.vo;

import java.util.Vector;

public class BriefOrderVO {
	public Vector<Vector<String>> info;
	public Vector<String> header;
	public BriefOrderVO(Vector<Vector<String>> info) {
		this.info = info;
		header = new Vector<String>();
		header.add("∂©µ•∫≈");
		header.add("÷ÿ¡ø£®kg£©");
	}
	
	public static int getColumnNum(){
		return 2;
	}
}
