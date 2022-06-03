package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.MemoVO;
import orm.DatabaseConnector;

public class MemoDAO {
	private Connection conn;
	private PreparedStatement pst;
	String query = "";
	
	public MemoDAO() {
		DatabaseConnector dbc = DatabaseConnector.getInstance();
		conn = dbc.getConnection();
	}
	
	public List<MemoVO> select() {
		List<MemoVO> list = new ArrayList<>();
		query = "select id, color, content from memos order by id desc";
		try {
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				list.add(new MemoVO(rs.getInt("id"), rs.getString("color"), rs.getString("content")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insert(String color) {
		query = "insert into memos(types, color, content, created, modified) values('post', ?, '', now(), now())";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, color);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(int id, String content) {
		query = "update memos set content=?, modified=now() where id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, content);
			pst.setInt(2, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		query = "delete from memos where id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
