package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.UsersVO;
import handler.JsonHandler;
import service.MypageSeriveImpl;
import service.MypageService;

@WebServlet("/mypage/*")
public class MypageCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MypageCtrl.class);
	private RequestDispatcher rdp;
	private String destPage;
	private MypageService msv;
	
    public MypageCtrl() {
    	msv = new MypageSeriveImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> mypageCtrl >>> path : " + path);
		
		switch (path) {
		case "":
			destPage = "/mypages/index.jsp";
			break;
		case "login":
			try {
				HttpSession ses = req.getSession();
				UsersVO ssuvo = (UsersVO)ses.getAttribute("ses");
				UsersVO uvo = msv.login(new UsersVO(ssuvo.getEmail(), req.getParameter("l_pwd")));
				if (uvo != null) {
					req.setAttribute("mod_chk", 1);
					destPage = "/mypages/modify.jsp";
				} else {
					req.setAttribute("mod_chk", 0);
					destPage = "/mypages/index.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
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
