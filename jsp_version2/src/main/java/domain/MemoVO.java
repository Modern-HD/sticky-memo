package domain;

public class MemoVO {
	private int mno;
	private String uid;
	private String type;
	private String color;
	private String content;
	private String creadted;
	private String modified;
	
	public MemoVO() {}
	
	// register
	public MemoVO(String uid, String color) {
		this.uid = uid;
		this.color = color;
	}

	// Modify
	public MemoVO(int mno, String content) {
		this.mno = mno;
		this.content = content;
	}
	
	// List
	public MemoVO(int mno, String uid, String type, String color, String content) {
		this.mno = mno;
		this.uid = uid;
		this.type = type;
		this.color = color;
		this.content = content;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getCreadted() {
		return creadted;
	}

	public void setCreadted(String creadted) {
		this.creadted = creadted;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	
}
