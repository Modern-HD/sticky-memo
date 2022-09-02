package handler;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonHandler {
	private static Logger log = LoggerFactory.getLogger(JsonHandler.class);
	
	public JSONObject jsonReadParse(HttpServletRequest req) {
		JSONObject jsonObj = null;
		try {
			StringBuffer sb = new StringBuffer();
			String line = null;
			BufferedReader br = req.getReader();
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			log.info(">>> sb : {}", sb.toString());
			
			JSONParser parser = new JSONParser();
			
			jsonObj = (JSONObject) parser.parse(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
}
