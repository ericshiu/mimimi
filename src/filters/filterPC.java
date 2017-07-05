package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postpartum_care.model.PostpartumCareVO;


public class filterPC implements Filter {

	private FilterConfig config;
  
	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req =(HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		HttpSession session=req.getSession();
		String userType = (String) session.getAttribute("userType"); 

		if (userType==null){
			
			session.setAttribute("pageReq",req.getServletPath());	
			res.sendRedirect(req.getContextPath()+"/front_end/LoginFir.jsp");
			
			return;
		
		}else if(userType.equals("Mem") || userType.equals("Firm")){
			
			res.sendRedirect(req.getContextPath()+"/front_end/frontINDEX.jsp");
			
		}else {
			
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}
