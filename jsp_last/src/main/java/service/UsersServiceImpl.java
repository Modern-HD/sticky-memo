package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.UsersVO;
import repository.UsersDAO;
import repository.UsersDAOImpl;

public class UsersServiceImpl implements UsersService {
	private static Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);
	private UsersDAO udao;
	
	public UsersServiceImpl() {
		udao = UsersDAOImpl.getInstance();
	}
	
	@Override
	public int register(UsersVO uvo) {
		return udao.insert(uvo);
	}

	@Override
	public List<UsersVO> getList() {
		return udao.selectList();
	}

	@Override
	public UsersVO login(UsersVO uvo) {
		return udao.selectLogin(uvo);
	}

	@Override
	public UsersVO detail(int uno) {
		return udao.selectOne(uno);
	}

	@Override
	public int chkEmail(String email) {
		return udao.selectEmail(email);
	}

	@Override
	public int chkNick(String nick_name) {
		return udao.selectNick(nick_name);
	}

	@Override
	public int modNick(UsersVO uvo) {
		return udao.updateNick(uvo);
	}

	@Override
	public int modPwd(UsersVO uvo) {
		return udao.updatePwd(uvo);
	}

	@Override
	public int remove(int uno) {
		return udao.delete(uno);
	}

	@Override
	public String chkPwd(int uno) {
		return udao.selectPwd(uno);
	}
}
