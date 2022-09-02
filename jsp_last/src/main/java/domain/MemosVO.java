package domain;

public class MemosVO {
	private int mno;
	private int uno;
	private int cno;
	private String types;
	private String color;
	private String content;
	private String created;
	private String modified;
	
	// channel
	private String nick_name;
	
	public MemosVO() {}

	// my_memo_reg
	public MemosVO(int uno, String types, String color) {
		this.uno = uno;
		this.types = types;
		this.color = color;
	}
	
	// my_image_reg
	public MemosVO(int uno, String types, String color, String content) {
		this(uno, types, color);
		this.content = content;
	}
	
	// ch_memo_reg
	public MemosVO(int uno, int cno, String types, String color) {
		this(uno, types, color);
		this.cno = cno;
	}
	
	// ch_image_reg
	public MemosVO(int uno, int cno, String types, String color, String content) {
		this(uno, cno, types, color);
		this.content = content;
	}

	// update
	public MemosVO(int mno, String content) {
		this.mno = mno;
		this.content = content;
	}
	
	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}



	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
	
	
}
