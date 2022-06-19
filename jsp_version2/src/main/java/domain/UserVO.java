package domain;

public class UserVO {
	private String uid;
	private String pwd;
	private String reg;
	
	public UserVO() {}
	
	public UserVO(String uid, String pwd) {
		this.uid = uid;
		this.pwd = pwd;
	}

	public UserVO(String uid, String pwd, String reg) {
		this(uid, pwd);
		this.reg = reg;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}
	
}

