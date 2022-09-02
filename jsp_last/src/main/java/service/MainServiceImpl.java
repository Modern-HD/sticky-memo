package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ChannelVO;
import repository.ChannelDAO;
import repository.ChannelDAOImpl;

public class MainServiceImpl implements MainService{
	private static Logger log = LoggerFactory.getLogger(MainServiceImpl.class);
	private ChannelDAO cdao;
	
	public MainServiceImpl() {
		cdao = ChannelDAOImpl.getInstance();
	}
	
	@Override
	public ChannelVO detailName(String ch_name) {
		return cdao.selectName(ch_name);
	}
}
