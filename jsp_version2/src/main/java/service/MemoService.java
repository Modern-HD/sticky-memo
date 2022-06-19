package service;

import java.util.List;

import domain.MemoVO;
import domain.UserVO;

public interface MemoService {
	
	public List<MemoVO> read(String uid);

	public int make(MemoVO mvo);

	public int remove(int mno);

	public UserVO login(UserVO uvo);

	public int modify(MemoVO mvo);

	public int register(UserVO uvo);
}
