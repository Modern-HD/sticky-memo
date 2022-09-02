package controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ChannelVO;
import service.MainService;
import service.MainServiceImpl;

@WebServlet("/main/*")
public class MainCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MainCtrl.class);
	private RequestDispatcher rdp;
	private String destPage;
	private MainService msv;
	
    public MainCtrl() {
    	msv = new MainServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		path =  URLDecoder.decode(URLDecoder.decode(path, "UTF-8"),"UTF-8");
		log.info(">>> maCtrl >>> path : " + path);
		
		switch (path) {
		case "main":
		case "me":
		case "":
		case "나의 메모":
		case "나의메모":
			destPage = "/myroom/index.jsp";
			break;
		default:
			ChannelVO cvo = msv.detailName(path);
			if(cvo != null) {
				req.setAttribute("cvo", cvo);
			}
			req.setAttribute("ch_name", path);
			destPage = "/channel/index.jsp";
			break;
		}
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
