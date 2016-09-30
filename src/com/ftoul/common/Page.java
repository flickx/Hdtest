package com.ftoul.common;

import java.util.List;

/**
 * 分页对象 count:总数 ，pageSize：每页数量 ，pageNum：当前页数， maxPage：总页数
 * 
 * @author flick
 * @version 1.0 2015-12-03
 */
public class Page {

	/**
	 * 记录总数
	 */
	private Integer count;
	/**
	 * 每页记录数
	 */
	private Integer pageSize;
	/**
	 * 当前页数
	 */
	private Integer pageNum;
	/**
	 * 最大页码数
	 */
	private Integer maxPage;
	/**
	 * 每页记录对象集合
	 */
	private List<Object> objList;
	
	/**
	 * vo每页记录对象集合
	 */
	private List<?> voList;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public List<Object> getObjList() {
		return objList;
	}

	public void setObjList(List<Object> objList) {
		this.objList = objList;
	}

	public List<?> getVoList() {
		return voList;
	}

	public void setVoList(List<?> voList) {
		this.voList = voList;
	}

}
