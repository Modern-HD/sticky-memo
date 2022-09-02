package repository;

import java.util.List;

import domain.UsersVO;

public interface UsersDAO {
	public int insert(UsersVO uvo);
	public List<UsersVO> selectList();
	public UsersVO selectLogin(UsersVO uvo);
	public UsersVO selectOne(int uno);
	public int selectEmail(String email);
	public int selectNick(String nick_name);
	public String selectPwd(int uno);
	public int updateNick(UsersVO uvo);
	public int updatePwd(UsersVO uvo);
	public int delete(int uno);
}
