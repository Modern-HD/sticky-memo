package domain;


public class UsersVO {
	private int uno;
	private String email;
	private String nick_name;
	private String pwd;
	private String reg_at;
	private String mod_at;
	
	public UsersVO() {}
	
	
	//register
	public UsersVO(String email, String nick_name, String pwd) {
		this.email = email;
		this.nick_name = nick_name;
		this.pwd = pwd;
	}

	//login
	public UsersVO(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
	}
	
	//mod_nick
	public UsersVO(int uno, String nick_name) {
		this.uno = uno;
		this.nick_name = nick_name;
	}

	//mod_pwd
	public UsersVO(String pwd, int uno) {
		this.uno = uno;
		this.pwd = pwd;
	}


	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getReg_at() {
		return reg_at;
	}

	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}

	public String getMod_at() {
		return mod_at;
	}

	public void setMod_at(String mod_at) {
		this.mod_at = mod_at;
	}

	
	
}
