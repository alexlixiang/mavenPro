package com.alex.mavenPro.core.controller;


import com.alex.mavenPro.core.common.util.StringEscapeEditor;
import com.alex.mavenPro.core.domain.page.Json;
import net.sf.json.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/base")
public class BaseController {
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(false,
				false));
	}

	/**
	 * 用户跳转JSP页面
	 * 
	 * 此方法不考虑权限控制
	 * 
	 * @param folder
	 *            路径
	 * @param jspName
	 *            JSP名称(不加后缀)
	 * @return 指定JSP页面
	 */
	@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable String folder,
			@PathVariable String jspName) {
		return "/" + folder + "/" + jspName;
	}

	/**
	 * 打印json
	 * 
	 * @author xq
	 * @param obj
	 * @param resp
	 */
	void outJson(Json obj, HttpServletResponse resp) {
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			JSONObject json = JSONObject.fromObject(obj);
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	/**
	 * 返回josn
	 * 
	 * @author xq
	 * @param obj
	 * @return
	 */
	JSONObject getJson(Json obj) {
		JSONObject json = JSONObject.fromObject(obj);
		return json;
	}

}
