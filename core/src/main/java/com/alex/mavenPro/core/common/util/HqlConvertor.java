package com.alex.mavenPro.core.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 对前台的查询条件进行处理，拼装成带占位符的hql，并将生成的占位符与value值放入hashmap中
 * 
 * 条件的格式 "QUERY_t.userName_EQ_S" QUERY表示查询语句，t.userName表示字段，EQ表示sql中的"="操作符，S表示值的类型(String型)；具体操作符规则查看类中的initOperator方法 值的类型 S:String,I:Integer,D:Date,F:Float
 * 
 * @author Administrator
 * 
 */
public class HqlConvertor {
	private Map<String, String> operator = new HashMap<String, String>();
	private Map<String, Object> params = new HashMap<String, Object>();
	private String entityName;
	private StringBuffer hql = new StringBuffer();
	private String sort;
	private String order = "desc";

	/**
	 * 复杂查询使用此构造函数，在特定的service中做进一步hql拼装处理
	 */
	public HqlConvertor() {
		initOperator();
	}

	/**
	 * 传入要查询的实体类名称(适合单表简单查询，获得自动生成的hql后在Controller中直接调用baseservice相关方法)
	 * 
	 * @param entityName
	 */
	public HqlConvertor(String entityName) {
		this.entityName = entityName;
		hql.append(" from " + this.entityName + " t where 1=1");
		initOperator();
	}

	private void initOperator() {
		operator.put("EQ", " = ");// 相等
		operator.put("NEQ", " <> ");// 不相等
		operator.put("LT", " < ");// 小于(less than)
		operator.put("GT", " > ");// 大于(greater than)
		operator.put("LTE", " <= ");// 小于等于
		operator.put("GTE", " >= ");// 大于等于
		operator.put("LK", " like ");// 相似
		operator.put("RLK", " like ");// 右相似
		operator.put("LLK", " like ");// 左相似
	}

	private String getOperator(String key) {
		return operator.get(key);
	}

	/**
	 * 添加排序字段
	 * 
	 * @param sort
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 添加排序规则，"asc"或"desc"
	 * 
	 * @param order
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 添加查询条件
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public void addCondition(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			addCondition(name, value);
		}
	}

	/**
	 * 添加查询条件
	 * 
	 * @param name
	 *            字段名称
	 * @param value
	 *            值
	 */
	public void addCondition(String name, String... value) {
		if (StringUtils.hasText(name) && ArrayUtils.isNotEmpty(value)) {
			if (name.startsWith("QUERY_")) {
				String[] filterParams = name.split("_");
				if (filterParams.length == 4) {
					String columnName = filterParams[1];// 列名
					String operatorKey = filterParams[2];// 排作符
					String columnType = filterParams[3];// 列类型

					if (hql.toString().indexOf(" where 1=1") < 0) {
						hql.append("  where 1=1 ");
					}
					String[] columnNames = columnName.split("\\|");
					if (columnNames.length > 1) {// 表示 多个字段 or连接
						if (columnNames.length == value.length) {
							String s = "(";
							for (int i = 0; i < columnNames.length; i++) {
								String uuid = UUID.randomUUID().toString().replace("-", "");
								s += (columnNames[i] + " " + getOperator(operatorKey) + " :param" + uuid + " ");
								if(i!=columnNames.length-1)
									s+= " or ";
								params.put("param" + uuid, getObjValue(operatorKey, value[i], columnType));// 添加参数
							}
							hql.append(" and " + (s + ")"));
						}
					} else {//单个字段
						String placeholder = UUID.randomUUID().toString().replace("-", "");// 生成一个随机的参数名称
						hql.append(" and " + columnName + " " + getOperator(operatorKey) + " :param" + placeholder + " ");// 拼HQL
						params.put("param" + placeholder, getObjValue(operatorKey, value[0], columnType));// 添加参数
					}

				}
			}
		}
	}

	public String getWhereHql() {
		return hql.toString();
	}

	public String getCountHql() {
		return "select count(distinct t) " + hql;
	}

	public String getOrderHql() {
		if (StringUtils.hasText(sort) && StringUtils.hasText(order)) {
			if (!sort.contains(".")) {
				sort = "t." + sort;
			}
			return " order by " + sort + " " + order;
		}
		return "";
	}

	public String getWhereAndOrderHql() {
		if (StringUtils.hasText(sort) && StringUtils.hasText(order)) {
			if (!sort.contains(".")) {
				sort = " t." + sort;
			}
			hql.append(" order by " + sort + " " + order);
		}
		return hql.toString();
	}

	/**
	 * 转换value S:String,I:Integer,D:Date,F:Float
	 * 
	 * @param operator
	 * @param value
	 * @return
	 */
	private Object getObjValue(String operator, String value, String columnType) {
		if (columnType.equals("S")) {
			if (operator.equals("LK"))
				value = "%%" + value + "%%";
			else if (operator.equals("RLK"))
				value = value + "%%";
			else if (operator.equals("LLK"))
				value = "%%" + value;
			return value;
		} else if (columnType.equals("I")) {
			return Integer.parseInt(value);
		} else if (columnType.equals("F")) {
			return Float.parseFloat(value);
		} else if (columnType.equals("D")) {
			try {
				return DateUtils.parseDate(value, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy/MM/dd" });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	/**
	 * 获得封装后的查询条件Map
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		return params;
	}

}
