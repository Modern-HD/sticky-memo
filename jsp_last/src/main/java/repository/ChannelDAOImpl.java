package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ChannelVO;
import domain.RoomInfoDTO;
import orm.DataBaseBuilder;

public class ChannelDAOImpl implements ChannelDAO {
	private static Logger log = LoggerFactory.getLogger(ChannelDAOImpl.class);
	private SqlSession sql;
	private final String NS = "ChannelMapper.";
	private static ChannelDAO cdao = null;
	
	public ChannelDAOImpl() {
		new DataBaseBuilder();
		sql = DataBaseBuilder.getFactory().openSession();
	}
	
	public static ChannelDAO getInstance() {
		if (cdao == null) {
			cdao = new ChannelDAOImpl();
		}
		return cdao;
	}
	
	@Override
	public int insert(String ch_name) {
		int isUp = sql.insert(NS + "register", ch_name);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public List<RoomInfoDTO> selectList() {
		return sql.selectList(NS + "list");
	}

	@Override
	public ChannelVO selectName(String ch_name) {
		return sql.selectOne(NS + "name_detail", ch_name);
	}

	@Override
	public ChannelVO selectCno(int cno) {
		return sql.selectOne(NS + "cno_detail", cno);
	}
}
