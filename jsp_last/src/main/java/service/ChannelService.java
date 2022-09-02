package service;

import java.util.List;

import domain.ChannelVO;
import domain.MemosVO;
import domain.RoomInfoDTO;

public interface ChannelService {
	public int register(String ch_name);
	public int RegisterCh(MemosVO mvo);
	public int RegisterChImg(MemosVO mvo);
	public List<MemosVO> getListCh(int cno);
	public List<RoomInfoDTO> getList();
	public ChannelVO detailName(String ch_name);
	public ChannelVO detailCno(int cno);
	public RoomInfoDTO getChInfo(int cno);
	
}
