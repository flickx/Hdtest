package com.ftoul.common;

import java.util.List;

/**
 * 查询filter参数对象
 * @author flick
 *
 */
public class Filters {

	//连接操作（OR/AND)
	private String groupOp;
	//查询对象集合
	private List<Rule> rules;
	
	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public List<Rule> getRules() {
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	
}
