package repository;

import java.util.List;

import domain.MemosVO;
import domain.RoomInfoDTO;

public interface MemosDAO {
	public int insertMine(MemosVO mvo);
	public int insertMineImg(MemosVO mvo);
	public int insertCh(MemosVO mvo);
	public int insertChImg(MemosVO mvo);
	public List<MemosVO> selectListMine(int uno);
	public List<MemosVO> selectListCh(int cno);
	public MemosVO selectOne(int mno);
	public RoomInfoDTO selectMyInfo(int uno);
	public RoomInfoDTO selectChInfo(int cno);
	public int update(MemosVO mvo);
	public int delete(int mno);
}
