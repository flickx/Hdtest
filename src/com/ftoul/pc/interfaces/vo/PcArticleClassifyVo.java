package com.ftoul.pc.interfaces.vo;

import java.util.ArrayList;
import java.util.List;

public class PcArticleClassifyVo {
	private List<PcArticleClassifyVo> articleClassifyList = new ArrayList<PcArticleClassifyVo>();
	private String id;
	private String name;
	/**
	 * @return the articleClassifyList
	 */
	public List<PcArticleClassifyVo> getArticleClassifyList() {
		return articleClassifyList;
	}
	/**
	 * @param articleClassifyList the articleClassifyList to set
	 */
	public void setArticleClassifyList(List<PcArticleClassifyVo> articleClassifyList) {
		this.articleClassifyList = articleClassifyList;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
