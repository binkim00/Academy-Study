package utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {
	
	public static void makeCookie(HttpServletResponse response, String cName, String cValue, int cTime) {
		//쿠키를 생성하는 메서드
		Cookie cookie = new Cookie(cName, cValue);
		cookie.setPath("/");
		cookie.setMaxAge(cTime);
		response.addCookie(cookie);
	}
	
	public static String readCookie(HttpServletRequest request, String cName) {
		//쿠키안에있는 특정 쿠키를 읽고 value를 돌려주는 메서드
		String cookieValue = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie c : cookies) {
				String cookieName = c.getName();
				if(cookieName.equals(cName)) {
					cookieValue = c.getValue();
				}
			}
		}
		return cookieValue;
	}
	
	public static void deleteCookie(HttpServletResponse response, String cName) {
		// 쿠키를 삭제하는 메서드
		// 쿠키를 삭제하는 방법 : 쿠키의 유효시간을 0으로 설정하는 것으로 삭제
		makeCookie(response, cName, "", 0);
	}
}












