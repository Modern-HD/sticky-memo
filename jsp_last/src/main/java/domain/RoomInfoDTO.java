package domain;

public class RoomInfoDTO {
	private int cno;
	private String ch_name;
	private String created;
	private int memo_count;
	private int head_count;
	private String last_used;
	
	public RoomInfoDTO() {}

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

	public int getMemo_count() {
		return memo_count;
	}

	public void setMemo_count(int memo_count) {
		this.memo_count = memo_count;
	}

	public int getHead_count() {
		return head_count;
	}

	public void setHead_count(int head_count) {
		this.head_count = head_count;
	}

	public String getLast_used() {
		return last_used;
	}

	public void setLast_used(String last_used) {
		this.last_used = last_used;
	}
	
	
}
