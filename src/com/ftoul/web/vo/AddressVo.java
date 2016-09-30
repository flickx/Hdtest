package com.ftoul.web.vo;

import java.util.List;

public class AddressVo {
	
	private Long value;
	private String text;
	private List<AddressVo> children;
	
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<AddressVo> getChildren() {
		return children;
	}
	public void setChildren(List<AddressVo> children) {
		this.children = children;
	}
}
