package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemoVO;
import domain.UserVO;
import service.MemoService;
import service.MemoServiceImpl;

@WebServlet("/memo/*")
public class MemoCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemoCtrl.class);
	private RequestDispatcher rdp;
	private MemoService msv;
	private int isUp;
	private String destPage;
    
	public MemoCtrl() {
		msv = new MemoServiceImpl();
	}
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		HttpSession ses = req.getSession();
		log.info(">>> path: " + path);
		
		switch (path) {
		case "login":
			UserVO uvo = msv.login(new UserVO(req.getParameter("uid"), req.getParameter("pwd")));
			if (uvo != null) {
				log.info(">>> login > {} : Success");
				
				ses.setAttribute("ses", uvo);
				req.setAttribute("msg_login", 1);
				ses.setMaxInactiveInterval(60 * 30);
				destPage = "/memo/list";
			} else {
				log.info(">>> Login > {} > Fail");
				req.setAttribute("msg_login", 0);
				destPage = "/memo/list";
			}
			break;
		case "logout":
			ses.invalidate();
			destPage = "/memo/list";
			break;
		case "sign_up":
			if (req.getParameter("pwd").equals(req.getParameter("pwdchk"))) {
				isUp = msv.register(new UserVO(req.getParameter("uid"), req.getParameter("pwd")));
				log.info(">>> sign_up > {}", isUp > 0 ? "Success" : "Fail");
			} else {
				log.info(">>> sign_up > {} > Fail: 비밀번호 확인 실패");
				req.setAttribute("sign_up", 0);
			}
			destPage = "/memo/list";
			break;
		case "modify":
			isUp = msv.modify(new MemoVO(Integer.parseInt(req.getParameter("mno")), req.getParameter("content")));
			log.info(">>> update > {}", isUp > 0 ? "Success" : "Fail");
			destPage = "/memo/list";
			break;
		case "make":
			UserVO who = (UserVO)ses.getAttribute("ses");
			isUp = msv.make(new MemoVO(who.getUid(), req.getParameter("color")));
			log.info(">>> make > {}", isUp > 0 ? "Success" : "Fail");
			destPage = "/memo/list";
			break;
		case "remove":
			isUp = msv.remove(Integer.parseInt(req.getParameter("mno")));
			log.info(">>> remove > {}", isUp > 0 ? "Success" : "Fail");
			destPage = "/memo/list";
			break;
		case "list":
			destPage = "/index.jsp";
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
