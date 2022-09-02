package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.UsersVO;
import repository.UsersDAO;
import repository.UsersDAOImpl;

public class MypageSeriveImpl implements MypageService {
	private static Logger log = LoggerFactory.getLogger(MypageSeriveImpl.class);
	private UsersDAO udao;
	
	public MypageSeriveImpl() {
		udao = UsersDAOImpl.getInstance();
	}
	
	@Override
	public UsersVO login(UsersVO uvo) {
		return udao.selectLogin(uvo);
	}
	
}
