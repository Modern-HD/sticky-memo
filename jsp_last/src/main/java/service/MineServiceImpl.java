package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemosVO;
import domain.RoomInfoDTO;
import repository.MemosDAO;
import repository.MemosDAOImpl;

public class MineServiceImpl implements MineService {
	private static Logger log = LoggerFactory.getLogger(MineServiceImpl.class);
	private MemosDAO mdao;
	
	public MineServiceImpl() {
		mdao = MemosDAOImpl.getInstance();
	}
	
	@Override
	public int RegisterMine(MemosVO mvo) {
		return mdao.insertMine(mvo);
	}

	@Override
	public int RegisterMineImg(MemosVO mvo) {
		return mdao.insertMineImg(mvo);
	}

	@Override
	public List<MemosVO> getListMine(int uno) {
		return mdao.selectListMine(uno);
	}

	@Override
	public MemosVO detail(int mno) {
		return mdao.selectOne(mno);
	}

	@Override
	public RoomInfoDTO getMyInfo(int uno) {
		return mdao.selectMyInfo(uno);
	}

	@Override
	public int modify(MemosVO mvo) {
		return mdao.update(mvo);
	}

	@Override
	public int remove(int mno) {
		return mdao.delete(mno);
	}
}
