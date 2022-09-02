package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ChannelVO;
import domain.MemosVO;
import domain.RoomInfoDTO;
import repository.ChannelDAO;
import repository.ChannelDAOImpl;
import repository.MemosDAO;
import repository.MemosDAOImpl;

public class ChannelServiceImpl implements ChannelService {
	private static Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);
	private ChannelDAO cdao;
	private MemosDAO mdao;
	
	public ChannelServiceImpl() {
		cdao = ChannelDAOImpl.getInstance();
		mdao = MemosDAOImpl.getInstance();
	}
	
	@Override
	public int register(String ch_name) {
		return cdao.insert(ch_name);
	}

	@Override
	public List<RoomInfoDTO> getList() {
		return cdao.selectList();
	}

	@Override
	public ChannelVO detailName(String ch_name) {
		return cdao.selectName(ch_name);
	}

	@Override
	public ChannelVO detailCno(int cno) {
		return cdao.selectCno(cno);
	}

	@Override
	public int RegisterCh(MemosVO mvo) {
		return mdao.insertCh(mvo);
	}

	@Override
	public int RegisterChImg(MemosVO mvo) {
		return mdao.insertChImg(mvo);
	}

	@Override
	public List<MemosVO> getListCh(int cno) {
		return mdao.selectListCh(cno);
	}

	@Override
	public RoomInfoDTO getChInfo(int cno) {
		return mdao.selectChInfo(cno);
	}
	
	
}
