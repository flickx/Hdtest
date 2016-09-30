package com.ftoul.common;

/**
 * 前端查询对象
 * @author flick
 *
 */
public class Rule {

	//字段名
	private String field;
	//操作
	private String op;
	//查询值
	private String data;
	//是否内置函数
	private Boolean fun;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the fun
	 */
	public Boolean getFun() {
		return fun;
	}
	/**
	 * @param fun the fun to set
	 */
	public void setFun(Boolean fun) {
		this.fun = fun;
	}
	
}
