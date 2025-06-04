package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDAO;
import membership.MemberDTO;
import utils.JSFunction;

@WebFilter(filterName = "WriteFilter", 
urlPatterns = {"/09PagingBoard/Write.jsp"})
public class WriteFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		String backUrl = req.getRequestURI();
		
		if (session.getAttribute("UserId") == null) {
			JSFunction.alertLocation(resp, 
					"[Filter]로그인 먼저 하시오.", 
					"../15FilterListener/LoginFilter.jsp?backUrl="+backUrl);
			return;
		}else {
			chain.doFilter(request, response);
		}
		
				
		
		
		
	}

}
