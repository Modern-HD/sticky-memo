package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemosVO;
import domain.RoomInfoDTO;
import domain.UsersVO;
import handler.ImageHandler;
import handler.JsonHandler;
import service.MineService;
import service.MineServiceImpl;

@WebServlet("/mine/*")
public class MineCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(MineCtrl.class);
	private MineService msv;
    private int isUp;
    
    public MineCtrl() {
    	msv = new MineServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/mine/") + 6);
		PrintWriter out = res.getWriter();
		log.info(">>> paths : " + path);
		
		String pathVar = "";
		if(path.contains("/")) {
			pathVar = path.substring(path.lastIndexOf("/")+1);
			path = path.substring(0, path.lastIndexOf("/"));
		}
		
		switch (path) {
		case "list":
			try {
				List<MemosVO> list = msv.getListMine(Integer.parseInt(pathVar));
				JSONArray jsonObjList = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("mno", list.get(i).getMno());
					jsonObj.put("uno", list.get(i).getUno());
					jsonObj.put("types", list.get(i).getTypes());
					jsonObj.put("color", list.get(i).getColor());
					jsonObj.put("content", list.get(i).getContent());
					jsonObj.put("created", list.get(i).getCreated());
					jsonObj.put("modified", list.get(i).getModified());
					
					jsonObjList.add(jsonObj);
				}
				String jsonData = jsonObjList.toJSONString();
				out.print(jsonData);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "register":
			try {
				HttpSession ses = req.getSession();
				UsersVO uvo = (UsersVO)ses.getAttribute("ses");
				if (pathVar.equals("image")) {
					ImageHandler igh = new ImageHandler();
					String fileName = "";
					String savePath = getServletContext().getRealPath("/_fileUpload");
					File fileDir = new File(savePath);
					
					List<FileItem> itemList = igh.FileUploader(fileDir).parseRequest(req);
					for (FileItem item : itemList) {
						switch (item.getFieldName()) {
						case "image_file":
							fileName = igh.ImageWriter(item, fileDir, req, uvo.getUno());
							break;
						}
					}
					isUp = msv.RegisterMineImg(new MemosVO(uvo.getUno(), "image", "img", fileName));
				} else {
					isUp = msv.RegisterMine(new MemosVO(uvo.getUno(), "post", pathVar));
				}
				out.print(isUp);
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
			break;
		case "modify":
			try {
				JsonHandler jsh = new JsonHandler();
				JSONObject jsonObj = jsh.jsonReadParse(req);
				isUp = msv.modify(new MemosVO(Integer.parseInt(jsonObj.get("mno").toString())
											, jsonObj.get("content").toString()));
				out.print(isUp);
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
			break;
		case "modify_img":
			try {
				HttpSession ses = req.getSession();
				UsersVO uvo = (UsersVO)ses.getAttribute("ses");
				ImageHandler igh = new ImageHandler();
				String fileName = "";
				int mno = 0;
				String savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				FileItem imageFile = null;
				List<FileItem> itemList = igh.FileUploader(fileDir).parseRequest(req);
				for (FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "mno":
						mno = Integer.parseInt(item.getString("utf-8"));
						break;
					case "image_file":
						imageFile = item;
						break;
					}
				}
				MemosVO mvo = msv.detail(mno);
				igh.ImageDeleter(mvo.getContent(), savePath);
				isUp = msv.modify(new MemosVO(mno, igh.ImageWriter(imageFile, fileDir, req, uvo.getUno())));
				out.print(isUp);
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
			break;
		case "remove":
			try {
				JsonHandler jsh = new JsonHandler();
				JSONObject jsonObj = jsh.jsonReadParse(req);
				int mno = Integer.parseInt(jsonObj.get("mno").toString());
				MemosVO mvo = msv.detail(mno);
				if(mvo.getTypes().equals("image")) {
					String savePath = getServletContext().getRealPath("/_fileUpload");
					ImageHandler igh = new ImageHandler();
					igh.ImageDeleter(mvo.getContent(), savePath);
				}
				isUp = msv.remove(mno);
				out.print(isUp);
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
		default:
			break;
		case "info":
			try {
				RoomInfoDTO idto = msv.getMyInfo(Integer.parseInt(pathVar));
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("memo_count", idto.getMemo_count());
				jsonObj.put("last_used", idto.getLast_used());
				String jsonData = jsonObj.toJSONString();
				out.print(jsonData);
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
