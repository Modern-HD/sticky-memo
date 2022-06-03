package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class MemoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Service svc;
	
	public MemoController() {
		svc = new Service();
	}
	
	public Service getSvc() {
		return svc;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();
		System.out.println("rui: " + uri);
		String content = req.getContextPath();
		System.out.println("content: " + content);
		String path = uri.substring(content.length());
		System.out.println("path: " + path);
		String destPage = "";
		
		if (path.equals("/insert.sm")) {
			svc.register(req.getParameter("color"));
			destPage = "/index.jsp";
		} else if (path.equals("/modify.sm")) {
			svc.edit(Integer.parseInt(req.getParameter("id")), req.getParameter("content"));
			destPage = "/index.jsp";
		} else if (path.equals("/remove.sm")) {
			svc.remove(Integer.parseInt(req.getParameter("id")));
			destPage = "/index.jsp";
		}
		
		RequestDispatcher rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service(req, resp);	
	}
	
	
}
