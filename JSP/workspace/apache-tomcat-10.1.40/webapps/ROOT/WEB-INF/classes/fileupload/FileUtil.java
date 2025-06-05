package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import utils.JSFunction;

public class FileUtil {
	public static String uploadFile(HttpServletRequest req, String sDirectory) throws IOException, ServletException {
		Part part = req.getPart("ofile");
		String partHeader = part.getHeader("content-disposition");
		String [] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"", "");
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory + File.separator + originalFileName);
		}
		return originalFileName;
	}
	public static String renameFile(String sDirectory, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		return newFileName;
		
	}
	// 파일을 Uploads폴더 저장하고 파일 이름을 List형식으로 돌려주는 메서드 
	public static ArrayList<String> multipleFile(HttpServletRequest req, String sDirectory) throws IOException, ServletException{
		ArrayList<String> listFileName = new ArrayList<>();
		// 여러개의 요청 데이터를 parts변수에 저장
		Collection<Part> parts = req.getParts();
		for(Part part : parts) {
			// 데이터가 ofile이 아니라면 다음 반복을 실행
			if(!part.getName().equals("ofile")) {
				continue;
			}
			// ofile이라면 파일을 꺼내어 저장하는 처리 실행
			String partHeader = part.getHeader("content-disposition");
			String[] phArr = partHeader.split("filename=");
			String originalFileName = phArr[1].trim().replace("\"", "");
			if(!originalFileName.isEmpty()) {
				// 실제 파일을 저장하는 처리
				part.write(sDirectory+File.separator+originalFileName);
			}
			// 파일이름을 List에 저장
			listFileName.add(originalFileName);
		}
		return listFileName;
	}
	public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String sfileName, String ofileName) {
		// 데이터가 저장되는 폴더
		String sDirectory = req.getServletContext().getRealPath("/Uploads");
		
		try{
			// 파일을 객체로 만들어서 저장 : 실제 저장된 파일의 경로와 이름을 설정
			File file = new File(sDirectory, sfileName);
			InputStream inStream = new FileInputStream(file);
			
			String client = req.getHeader("User-Agent");
			// 인터넷 익스플로러 인지 확인하고 다르면 UTF-8로 설정
			if(client.indexOf("WOW64") == -1){
				ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
			// 인터넷 익스플로러 라면 KSC5601로 설정 파일이름이 깨지지 않도록 설정
			}else{
				ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
			}
			// 응답 초기화
			resp.reset();
			// 응답에 파일을 돌려주도록 ContentType설정
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\""+ofileName + "\"");
			resp.setHeader("Content-Length", "" + file.length() );
			
			// 화면에 출력하는 내용도 초기화
			//out.clear();
			
			// response에 실제 파일을 설정
			OutputStream outStream = resp.getOutputStream();
			byte[] b = new byte[(int)file.length()];
			int readBuffer = 0;
			while((readBuffer = inStream.read(b)) > 0){
				outStream.write(b,0,readBuffer);
			}
			inStream.close();
			outStream.close();
		}catch(FileNotFoundException e){
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("예외가 발생하였습니다.");
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(HttpServletRequest req, String directory, String fileName) {
		// 실제 파일이 저장된 폴더를 설정 /Uploads 폴더
		String sDirectory = req.getServletContext().getRealPath(directory);
		// 저장된 파일을 file 변수에 저장 
		File file = new File(sDirectory + File.separator + fileName);
		// 파일이 존재하는지 확인하는 if문
		if(file.exists()) {
			// 파일 삭제 메서드
			file.delete();
		}
	}
}










