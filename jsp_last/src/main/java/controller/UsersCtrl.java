package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.UsersVO;
import handler.JsonHandler;
import service.UsersService;
import service.UsersServiceImpl;

@WebServlet("/user/*")
public class UsersCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(UsersCtrl.class);
	private UsersService usv;
	private int isUp;
	
    public UsersCtrl() {
    	usv = new UsersServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/user/") + 6);
		PrintWriter out = res.getWriter();
		log.info(">>> paths : " + path);
		
		String pathVar = "";
		if(path.contains("/")) {
			pathVar = path.substring(path.lastIndexOf("/")+1);
			path = path.substring(0, path.lastIndexOf("/"));
		}
		

		
		switch (path) {
		case "login":
			try {
				JSONObject result = new JSONObject();
				JsonHandler jsh = new JsonHandler();
				JSONObject jsonObj = jsh.jsonReadParse(req);
				UsersVO uvo = usv.login(new UsersVO(jsonObj.get("email").toString(),
											jsonObj.get("pwd").toString()));
				if (uvo != null) {
					log.info(">>> 로그인 성공");
					HttpSession ses = req.getSession();
					ses.setAttribute("ses", uvo);
					ses.setMaxInactiveInterval(60 * 30);
					result.put("result", 1);
					result.put("msg", uvo.getNick_name() + " 님 환영합니다.");
				} else {
					log.info(">>> 로그인 실패");
					result.put("result", 0);
					result.put("msg", "이메일 또는 비밀번호가 다릅니다.");
				}
				out.write(result.toJSONString());
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject result = new JSONObject();
				result.put("result", 0);
				result.put("msg", "오류 발생");
				out.write(result.toJSONString());
			}
			break;
		case "logout":
			try {
				HttpSession ses = req.getSession();
				ses.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "signup":
			JSONObject result = new JSONObject();
			try {
				JsonHandler jsh = new JsonHandler();
				JSONObject jsonObj = jsh.jsonReadParse(req);
				if (usv.chkEmail(jsonObj.get("email").toString()) > 0) {
					log.info(">>> 이메일 중복");
					result.put("result", 0);
					result.put("msg", "이미 사용 중인 이메일 입니다.");
				} else if(usv.chkNick(jsonObj.get("nick_name").toString()) > 0) {
					log.info(">> 닉네임 중복");
					result.put("result", 0);
					result.put("msg", "이미 사용 중인 닉네임 입니다.");
				} else {
					usv.register(new UsersVO(jsonObj.get("email").toString(),
											jsonObj.get("nick_name").toString(),
											jsonObj.get("pwd").toString()));
					result.put("result", 1);
					result.put("msg", "회원가입 성공.");
				}
				out.print(result.toJSONString());
			} catch (Exception e) {
				e.printStackTrace();
				result.put("result", 0);
				result.put("msg", "오류 발생");
				out.write(result.toJSONString());
			}
			break;
		case "mod_nick":
			try {
				JsonHandler jsh = new JsonHandler();
				JSONObject jsonObj = jsh.jsonReadParse(req);
				if(usv.chkNick(jsonObj.get("nick_name").toString()) > 0) {
					log.info(">> 닉네임 중복");
					out.print(0);
				} else {
					isUp = usv.modNick(new UsersVO(Integer.parseInt(jsonObj.get("uno").toString()),
															jsonObj.get("nick_name").toString()));
					if(isUp > 0) {
						HttpSession ses = req.getSession();
						ses.invalidate();
					}
					out.print(isUp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
			break;
		case "mod_pwd":
			try {
				JsonHandler jsh = new JsonHandler();
				JSONObject jsonObj = jsh.jsonReadParse(req);
				int uno = Integer.parseInt(jsonObj.get("uno").toString());
				String oldPwd = usv.chkPwd(uno);
				if(oldPwd.equals(jsonObj.get("old_pwd").toString())) {
					isUp = usv.modPwd(new UsersVO(jsonObj.get("new_pwd").toString(),
													uno));
					if(isUp > 0) {
						HttpSession ses = req.getSession();
						ses.invalidate();
					}
					out.print(isUp);
				} else {
					out.print(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
			break;
		default:
			break;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
