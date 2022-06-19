package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemoVO;
import domain.UserVO;
import repository.MemoDAO;
import repository.MemoDAOImpl;

public class MemoServiceImpl implements MemoService {
	private static Logger log = LoggerFactory.getLogger(MemoServiceImpl.class);
	private MemoDAO mdao;
	
	public MemoServiceImpl() {
		mdao = new MemoDAOImpl();
	}
	
	@Override
	public List<MemoVO> read(String uid) {
		return mdao.selectList(uid);
	}

	@Override
	public int make(MemoVO mvo) {
		return mdao.insert(mvo);
	}

	@Override
	public int remove(int mno) {
		return mdao.delete(mno);
	}

	@Override
	public UserVO login(UserVO uvo) {
		return mdao.selectOne(uvo);
	}

	@Override
	public int modify(MemoVO mvo) {
		return mdao.update(mvo);
	}

	@Override
	public int register(UserVO uvo) {
		return mdao.insertUser(uvo);
	}

}
