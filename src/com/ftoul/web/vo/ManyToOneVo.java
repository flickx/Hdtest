package com.ftoul.web.vo;

import java.util.List;

/**
 * 多对一关系视图
 * @author flick
 *
 */
public class ManyToOneVo {

	private Object obj;
	private List<Object> list;
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	
	
}
