package service;

import java.util.List;

import domain.MemoVO;
import repository.MemoDAO;

public class Service {
	
	private MemoDAO dao;
	
	public Service() {
		dao = new MemoDAO();
	}
	
	public List<MemoVO> read() {
		List<MemoVO> list = dao.select();
		return list;
	}

	public void register(String color) {
		dao.insert(color);
	}

	public void edit(int id, String content) {
		dao.update(id, content);
	}

	public void remove(int id) {
		dao.delete(id);
	}
}
