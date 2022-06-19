package repository;

import java.util.List;

import domain.MemoVO;
import domain.UserVO;

public interface MemoDAO {

	List<MemoVO> selectList(String uid);

	int insert(MemoVO mvo);

	int delete(int mno);

	UserVO selectOne(UserVO uvo);

	int update(MemoVO mvo);

	int insertUser(UserVO uvo);
	
}
