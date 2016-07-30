package com.alex.mavenPro.core.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA. User: lx Date: 14-9-17 Time: 下午1:44 To change this template use File | Settings | File Templates.
 */
public class SessionFilter extends OncePerRequestFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal( javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// 不过滤的uri
		String[] notFilter = new String[] { "login.jsp", "index.jsp" };

		// 请求的uri
		String uri = request.getRequestURI();
		String[] methodArr = uri.split("/");
		String method = methodArr[methodArr.length - 1];

		// uri中包含background时才进行过滤
		if (uri.indexOf("background") != -1) {
			// 是否过滤
			boolean doFilter = true;
			for (String s : notFilter) {
				if (uri.indexOf(s) != -1) {
					// 如果uri中包含不过滤的uri，则不进行过滤
					doFilter = false;
					break;
				}
			}
			if (doFilter) {
				// 执行过滤
				// 从session中获取登录者实体
				Object obj = request.getSession().getAttribute("loginedUser");
				if (null == obj) {
					// 如果session中不存在登录者实体，则弹出框提示重新登录
					// 设置request和response的字符集，防止乱码
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					String loginPage = "....";
					StringBuilder builder = new StringBuilder();
					builder.append("<script type=\"text/javascript\">");
					builder.append("alert('网页过期，请重新登录！');");
					builder.append("window.top.location.href='");
					builder.append(loginPage);
					builder.append("';");
					builder.append("</script>");
					out.print(builder.toString());
				} else {
					// 如果session中存在登录者实体，则继续
					filterChain.doFilter(request, response);
				}
			} else {
				// 如果不执行过滤，则继续
				filterChain.doFilter(request, response);
			}
		} else {
			// 如果uri中不包含background，则继续
			filterChain.doFilter(request, response);
		}

		if (method.equals("login") || method.equals("dologin")) {
			// 如果用户想登录，则使之通过
			filterChain.doFilter(request, response);
		} else {
			// 取得Session。
			Object obj = request.getSession().getAttribute("loginedUser");
			/*
			 * if (obj == null) { // 如果Session为空，则让用户登陆。 return "globalLogin"; } else { String username = (String) session.get("loginName"); if (username == null) { // Session不为空，但Session中没有用户信息， // 则让用户登陆 return "globalLogin"; } else { // 用户已经登陆，放行~ return invocation.invoke(); } }
			 */
		}
	}

}
