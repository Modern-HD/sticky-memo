package domain;


public class ChannelVO {
	// Channel table
	private int cno;
	private String ch_name;
	private String created;

	public ChannelVO() {}
	
	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCh_name() {
		return ch_name;
	}

	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
	
}
