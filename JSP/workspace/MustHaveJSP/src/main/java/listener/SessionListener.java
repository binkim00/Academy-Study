package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

	int sessionCnt;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		sessionCnt++;
		System.out.println("[리스너]세션 생성: "+se.getSession().getId());
		System.out.println("[리스너]세션 카운트: "+ this.sessionCnt);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		sessionCnt--;
		System.out.println("[리스너]세션 생성: "+se.getSession().getId());
		System.out.println("[리스너]세션 카운트: "+ this.sessionCnt);
	}
	
}
