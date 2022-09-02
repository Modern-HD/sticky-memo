package handler;

import java.io.File;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.coobird.thumbnailator.Thumbnails;

public class ImageHandler {
	private static Logger log = LoggerFactory.getLogger(ImageHandler.class);
	
	public ServletFileUpload FileUploader(File fileDir) {
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setRepository(fileDir);
		fileItemFactory.setSizeThreshold(3*1024*1024);
		
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		return fileUpload;
	}
	
	public String getFileName(FileItem item) {
		String fileName = item.getName()
				.substring(item.getName().lastIndexOf(File.separator)+1);

		log.info(fileName.toLowerCase());		
		
		if (Pattern.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*", fileName)) {
			fileName = "picture" + fileName.substring(fileName.lastIndexOf("."));
		}
		return fileName;
	}
	
	public String ImageWriter(FileItem item, File fileDir, HttpServletRequest req, int uno) {
		String fileName = null;
		if(item.getSize() > 0) {
			String extension = item.getName().substring(item.getName().lastIndexOf("."));
			
			fileName = uno + "_" + "picture" + "_" + System.currentTimeMillis() + extension;
			File UploadFilePath = new File(fileDir + File.separator + fileName);
			
			try {
				item.write(UploadFilePath);
				
				Thumbnails.of(UploadFilePath)
						.size(270, 240)
						.toFile(new File(fileDir + File.separator + "th_" + fileName));
			} catch (Exception e) {
				log.info(">>> File Writer on disk Fail");
				e.printStackTrace();
			}
			log.info(fileName);
		}
		return fileName;
	}
	
	public int ImageDeleter(String imageFileName, String savePath) {
		try {
			File fileDir = new File(savePath);
			
			File removeFile = new File(fileDir + File.separator + imageFileName);
			File removeThumbFile = new File(fileDir + File.separator + "th_" + imageFileName);
			
			boolean isDel = true;
			if(removeFile.exists() || removeThumbFile.exists()) {
				isDel = removeFile.delete();
				if(isDel) {
					isDel = removeThumbFile.delete();
				}
			}
			log.info(">>> FileHandler Remove is {}", isDel ? "OK" : "Fail");
			return isDel ? 1 : 0;
		} catch (Exception e) {
			log.info(">>> FileHandler > Remove > Error");
			e.printStackTrace();
		}
		return 0;
	}
}
