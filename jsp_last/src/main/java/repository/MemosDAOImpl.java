package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemosVO;
import domain.RoomInfoDTO;
import orm.DataBaseBuilder;

public class MemosDAOImpl implements MemosDAO {
	private static Logger log = LoggerFactory.getLogger(MemosDAOImpl.class);
	private SqlSession sql;
	private final String NS = "MemosMapper.";
	private static MemosDAO mdao = null;
	
	public MemosDAOImpl() {
		new DataBaseBuilder();
		sql = DataBaseBuilder.getFactory().openSession();
	}
	
	public static MemosDAO getInstance() {
		if (mdao == null) {
			mdao = new MemosDAOImpl();
		}
		return mdao;
	}
	
	@Override
	public int insertMine(MemosVO mvo) {
		int isUp = sql.insert(NS + "my_reg_post", mvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int insertCh(MemosVO mvo) {
		int isUp = sql.insert(NS + "ch_reg_post", mvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int insertMineImg(MemosVO mvo) {
		int isUp = sql.insert(NS + "my_reg_image", mvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int insertChImg(MemosVO mvo) {
		int isUp = sql.insert(NS + "ch_reg_image", mvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}
	
	@Override
	public List<MemosVO> selectListMine(int uno) {
		return sql.selectList(NS + "my_list", uno);
	}

	@Override
	public List<MemosVO> selectListCh(int cno) {
		return sql.selectList(NS + "ch_list", cno);
	}

	@Override
	public MemosVO selectOne(int mno) {
		return sql.selectOne(NS + "detail", mno);
	}

	@Override
	public int update(MemosVO mvo) {
		int isUp = sql.insert(NS + "mod", mvo);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public int delete(int mno) {
		int isUp = sql.insert(NS + "del", mno);
		if (isUp > 0) {
			sql.commit();
		}
		return isUp;
	}

	@Override
	public RoomInfoDTO selectMyInfo(int uno) {
		return sql.selectOne(NS + "room_uno_detail", uno);
	}
	
	@Override
	public RoomInfoDTO selectChInfo(int cno) {
		return sql.selectOne(NS + "room_cno_detail", cno);
	}


}
