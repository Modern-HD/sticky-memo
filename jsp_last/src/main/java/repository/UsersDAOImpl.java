package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.UsersVO;
import orm.DataBaseBuilder;

public class UsersDAOImpl implements UsersDAO {
	private static Logger log = LoggerFactory.getLogger(UsersDAOImpl.class);
	private SqlSession sql;
	private final String NS = "UsersMapper.";
	private static UsersDAO udao = null;
	
	public UsersDAOImpl() {
		new DataBaseBuilder();
		sql = DataBaseBuilder.getFactory().openSession();
	}
	
	public static UsersDAO getInstance() {
		if (udao == null) {
			udao = new UsersDAOImpl();
		}
		return udao;
	}
	
	@Override
	public int insert(UsersVO uvo) {
		int isUp = sql.insert(NS + "reg", uvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public List<UsersVO> selectList() {
		return sql.selectList(NS + "list");
	}

	@Override
	public UsersVO selectLogin(UsersVO uvo) {
		return sql.selectOne(NS + "login", uvo);
	}

	@Override
	public UsersVO selectOne(int uno) {
		return sql.selectOne(NS + "detail", uno);
	}

	@Override
	public int selectEmail(String email) {
		return sql.selectOne(NS + "email_chk", email);
	}

	@Override
	public int selectNick(String nick_name) {
		return sql.selectOne(NS + "nick_chk", nick_name);
	}

	@Override
	public String selectPwd(int uno) {
		return sql.selectOne(NS + "pwd_chk", uno);
	}
	
	@Override
	public int updateNick(UsersVO uvo) {
		int isUp = sql.update(NS + "mod_nick", uvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int updatePwd(UsersVO uvo) {
		int isUp = sql.update(NS + "mod_pwd", uvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int delete(int uno) {
		int isUp = sql.delete(NS + "remove", uno);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

}
