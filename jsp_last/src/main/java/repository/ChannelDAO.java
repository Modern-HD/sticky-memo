package repository;

import java.util.List;

import domain.ChannelVO;
import domain.RoomInfoDTO;

public interface ChannelDAO {
	public int insert(String ch_name);
	public List<RoomInfoDTO> selectList();
	public ChannelVO selectName(String ch_name);
	public ChannelVO selectCno(int cno);
}
