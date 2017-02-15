package com.ftoul.pc.store.vo;

import java.util.List;

import com.ftoul.po.Goods;

public class StoreVo {
	private String id;
	private String name;
	private List<Object> objList;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Object> getObjList() {
		return objList;
	}
	public void setObjList(List<Object> objList) {
		this.objList = objList;
	}
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
	
}
