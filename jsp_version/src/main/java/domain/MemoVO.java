package domain;

public class MemoVO {
	private int id;
	private String color;
	private String content;
	
	public MemoVO() {}
	
	public MemoVO(int id, String color, String content) {
		this.id = id;
		this.color = color;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}
