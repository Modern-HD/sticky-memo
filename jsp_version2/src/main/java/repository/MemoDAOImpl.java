package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemoVO;
import domain.UserVO;
import orm.DataBaseBuilder;

public class MemoDAOImpl implements MemoDAO {
	private static Logger log = LoggerFactory.getLogger(MemoDAOImpl.class);
	private SqlSession sql;
	private final String NS = "MemoMapper.";
	
	public MemoDAOImpl() {
		new DataBaseBuilder();
		sql = DataBaseBuilder.getFactory().openSession();
	}
	
	@Override
	public List<MemoVO> selectList(String uid) {
		return sql.selectList(NS + "list", uid);
	}

	@Override
	public int insert(MemoVO mvo) {
		int isUp = sql.insert(NS + "make", mvo);
		if(isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int delete(int mno) {
		int isUp = sql.insert(NS + "remove", mno);
		if(isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public UserVO selectOne(UserVO uvo) {
		return sql.selectOne(NS + "login", uvo);
	}

	@Override
	public int update(MemoVO mvo) {
		int isUp = sql.update(NS + "modify", mvo);
		if(isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int insertUser(UserVO uvo) {
		int isUp = sql.insert(NS + "register", uvo);
		if(isUp > 0) {
			sql.commit();
		}
		return isUp;
	}
	
}