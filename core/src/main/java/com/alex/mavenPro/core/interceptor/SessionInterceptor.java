package com.alex.mavenPro.core.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SessionInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);

	private List<String> allowUrls;

	public List<String> getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}

	/*
	 * 在调用controller方法之前调用
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String servletPath = request.getServletPath();
		logger.info("进入SessionInfterceptor，访问路径为——>" + servletPath);
		if (allowUrls != null && allowUrls.size() > 0) {
			for (String url : allowUrls) {
				if (servletPath.contains(url))
					return true;
			}
		}
		if (servletPath.contains("/allowAccess"))
			return true;
		//SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ConfigUtil.getDefaultSessionInfoName());
		Object login = request.getSession().getAttribute("LOGIN");
		if (login == null) {
			request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
			response.setCharacterEncoding("utf-8");
			// 请求转发
			// request.getRequestDispatcher("/login").forward(request, response);
			// 请求重定向
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		return true;
	}

	/*
	 * 在调用controller之后调用
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	/*
	 * 在视图被渲染以后调用
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
