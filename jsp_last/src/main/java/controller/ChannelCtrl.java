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

import domain.ChannelVO;
import domain.MemosVO;
import domain.RoomInfoDTO;
import domain.UsersVO;
import handler.ImageHandler;
import handler.JsonHandler;
import service.ChannelService;
import service.ChannelServiceImpl;

@WebServlet("/ch/*")
public class ChannelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(MineCtrl.class);
    private ChannelService csv;
    private int isUp;
	
    public ChannelCtrl() {
        csv = new ChannelServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/ch/") + 4);
		PrintWriter out = res.getWriter();
		log.info(">>> chCtrl >>> paths : " + path);
		
		String pathVar = "";
		if(path.contains("/")) {
			pathVar = path.substring(path.lastIndexOf("/")+1);
			path = path.substring(0, path.lastIndexOf("/"));
		}
		
		switch (path) {
		case "reg_ch":
			try {
				JsonHandler jsh = new JsonHandler();
				JSONObject jsonObj = jsh.jsonReadParse(req);
				String ch_name = jsonObj.get("ch_name").toString();
				isUp = csv.register(ch_name);
				if(isUp > 0) {
					ChannelVO cvo = csv.detailName(ch_name);
					HttpSession ses = req.getSession();
					UsersVO uvo = (UsersVO)ses.getAttribute("ses");
					isUp = csv.RegisterCh(new MemosVO(uvo.getUno(),
												cvo.getCno(),
												"post",
												jsonObj.get("color").toString()));
					out.print(isUp);
				} else {
					out.print(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
			break;
		case "reg_memo":
			try {
				JsonHandler jsh = new JsonHandler();
				JSONObject jsonObj = jsh.jsonReadParse(req);
				HttpSession ses = req.getSession();
				UsersVO uvo = (UsersVO)ses.getAttribute("ses");
				isUp = csv.RegisterCh(new MemosVO(uvo.getUno(), 
													Integer.parseInt(jsonObj.get("cno").toString()), 
													"post",
													jsonObj.get("color").toString()));
				out.print(isUp);
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
			break;
		case "reg_image":
			try {
				HttpSession ses = req.getSession();
				UsersVO uvo = (UsersVO)ses.getAttribute("ses");
				ImageHandler igh = new ImageHandler();
				String fileName = "";
				int cno = 0;
				String savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				
				List<FileItem> itemList = igh.FileUploader(fileDir).parseRequest(req);
				for (FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "cno":
						cno = Integer.parseInt(item.getString("utf-8"));
						break;
					case "image_file":
						fileName = igh.ImageWriter(item, fileDir, req, uvo.getUno());
						break;
					}
				}
				isUp = csv.RegisterChImg(new MemosVO(uvo.getUno(), cno, "image", "img", fileName));
				out.print(isUp);
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
			break;
		case "list":
			try {
				List<MemosVO> list = csv.getListCh(Integer.parseInt(pathVar));
				JSONArray jsonObjList = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("mno", list.get(i).getMno());
					jsonObj.put("uno", list.get(i).getUno());
					jsonObj.put("nick_name", list.get(i).getNick_name());
					jsonObj.put("cno", list.get(i).getCno());
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
		case "info":
			try {
				RoomInfoDTO idto = csv.getChInfo(Integer.parseInt(pathVar));
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("cno", idto.getCno());
				jsonObj.put("ch_name", idto.getCh_name());
				jsonObj.put("created", idto.getCreated());
				jsonObj.put("memo_count", idto.getMemo_count());
				jsonObj.put("head_count", idto.getHead_count());
				jsonObj.put("last_used", idto.getLast_used());
				String jsonData = jsonObj.toJSONString();
				out.print(jsonData);
			} catch (Exception e) {
				e.printStackTrace();
				out.print(-1);
			}
			break;
		case "ch_list":
			try {
				List<RoomInfoDTO> list = csv.getList();
				JSONArray jsonObjList = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("ch_name", list.get(i).getCh_name());
					jsonObj.put("memo_count", list.get(i).getMemo_count());
					
					jsonObjList.add(jsonObj);
				}
				String jsonData = jsonObjList.toJSONString();
				out.print(jsonData);
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
