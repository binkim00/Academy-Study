<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.OutputStream"%>
<%@page import="utils.JSFunction"%>
<%@page import="java.io.FileNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 데이터가 저장되는 폴더
String saveDirectory = application.getRealPath("/Uploads");
// 저장되는 파일 이름 : 실제 파일 이름이 날짜로 저장됨
String saveFilename = request.getParameter("sName");
// 원본 파일 이름 : 데이터베이스에 저장되어있는 원본 파일 이름
String originalFilename = request.getParameter("oName");

try{
	// 파일을 객체로 만들어서 저장 : 실제 저장된 파일의 경로와 이름을 설정
	File file = new File(saveDirectory, saveFilename);
	InputStream inStream = new FileInputStream(file);
	
	String client = request.getHeader("User-Agent");
	// 인터넷 익스플로러 인지 확인하고 다르면 UTF-8로 설정
	if(client.indexOf("WOW64") == -1){
		originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
	// 인터넷 익스플로러 라면 KSC5601로 설정 파일이름이 깨지지 않도록 설정
	}else{
		originalFilename = new String(originalFilename.getBytes("KSC5601"), "ISO-8859-1");
	}
	// 응답 초기화
	response.reset();
	// 응답에 파일을 돌려주도록 ContentType설정
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename=\""+originalFilename + "\"");
	response.setHeader("Content-Length", "" + file.length() );
	// 화면에 출력하는 내용도 초기화
	out.clear();
	
	// response에 실제 파일을 설정
	OutputStream outStream = response.getOutputStream();
	byte[] b = new byte[(int)file.length()];
	int readBuffer = 0;
	while((readBuffer = inStream.read(b)) > 0){
		outStream.write(b,0,readBuffer);
	}
	inStream.close();
	outStream.close();
}catch(FileNotFoundException e){
	JSFunction.alertBack("파일을 찾을 수 없습니다.", out);
}catch(Exception e){
	JSFunction.alertBack("예외가 발생하였습니다.", out);
}

%>










