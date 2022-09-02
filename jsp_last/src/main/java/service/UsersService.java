package service;

import java.util.List;

import domain.UsersVO;

public interface UsersService {
	public int register(UsersVO uvo);
	public List<UsersVO> getList();
	public UsersVO login(UsersVO uvo);
	public UsersVO detail(int uno);
	public int chkEmail(String email);
	public int chkNick(String nick_name);
	public String chkPwd(int uno);
	public int modNick(UsersVO uvo);
	public int modPwd(UsersVO uvo);
	public int remove(int uno);
}
