package service;

import java.util.List;

import domain.MemosVO;
import domain.RoomInfoDTO;

public interface MineService {
	public int RegisterMine(MemosVO mvo);
	public int RegisterMineImg(MemosVO mvo);
	public List<MemosVO> getListMine(int uno);
	public MemosVO detail(int mno);
	public RoomInfoDTO getMyInfo(int cno);
	public int modify(MemosVO mvo);
	public int remove(int mno);
}
