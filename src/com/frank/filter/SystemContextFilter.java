package com.frank.filter;





import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.frank.utils.SystemContext;



public class SystemContextFilter implements Filter{
	private Integer pageSize;

	private FilterConfig config;
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		//将网站配置信息放入session
		/*Config obj = configService.load("1");
		((HttpServletRequest)req).getSession().setAttribute("config", obj);*/
		
		Integer offset = 0;
		try {
			offset = Integer.parseInt(req.getParameter("pager.offset"));
		} catch (NumberFormatException e) {}
		try {
			SystemContext.setOrder(req.getParameter("order"));
			SystemContext.setSort(req.getParameter("sort"));
			SystemContext.setPageOffset(offset);
			SystemContext.setPageSize(pageSize);
			SystemContext.setRealPath(((HttpServletRequest)req).getSession().getServletContext().getRealPath("/"));
			chain.doFilter(req,resp);
		} finally {
			SystemContext.removeOrder();
			SystemContext.removeSort();
			SystemContext.removePageOffset();
			SystemContext.removePageSize();
			SystemContext.removeRealPath();
		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		config = cfg;
		
		ServletContext sc = cfg.getServletContext();

		WebApplicationContext cxt =WebApplicationContextUtils.getWebApplicationContext(sc);

		/*if(cxt != null && cxt.getBean(ConfigService.class) != null && configService == null){
			configService = (ConfigService) cxt.getBean(ConfigService.class);
		}*/
		
		try {
			pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize = 15;
		}
	}

}
