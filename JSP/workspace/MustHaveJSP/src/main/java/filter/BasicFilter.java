package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(filterName = "BasicFilter", urlPatterns = "/15FilterListener/AnnoFilter.jsp")
public class BasicFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String searchField = request.getParameter("searchField"); 
		String searchWord = request.getParameter("searchWord");
		System.out.println(searchField);
		System.out.println(searchWord);
		chain.doFilter(request, response);
	}

}
