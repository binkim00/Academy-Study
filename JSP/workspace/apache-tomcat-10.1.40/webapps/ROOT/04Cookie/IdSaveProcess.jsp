<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="utils.CookieManager, utils.JSFunction" %>
<%
	// 로그인 버튼 실행시 실행되는 코드
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
	String save_check = request.getParameter("save_check");
	
	//id가 must, pw가 1234이면 로그인 처리를 실행
	if(user_id.equals("must") && "1234".equals(user_pw)){
		// save_check가 null이 아니고 내용이 Y라면 쿠키에 저장
		if(save_check != null && save_check.equals("Y")){
			CookieManager.makeCookie(response,"loginId", user_id, 60*60*24);
		}else{
			// 체크를 하지 않았다면 쿠키를 제거 합니다.
			CookieManager.deleteCookie(response, "loginId");
		}
		JSFunction.alertLocation("로그인에 성공했습니다.", "IdSaveMain.jsp", out);
	}else{
		JSFunction.alertBack("로그인에 실패했습니다.", out);
	}
%>



