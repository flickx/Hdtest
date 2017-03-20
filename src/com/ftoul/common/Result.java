package com.ftoul.common;

/**
 * 带列表及分页的JSON返回对象
 * 用于包装返回带列表及分页的JSON对象
 * @author flick
 * @version 1.0
 *
 */
public class Result implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061886362019159930L;
	/**
	 * 返回状态（1：成功；0：失败；2：无权限）
	 */
	private Integer result;
	/**
	 * 返回消息提示
	 */
	private String message;
	/**
	 * 总数量
	 */
	private Long totalNum;
	/**
	 * 是否分页
	 */
	private Boolean isPage;
	/**
	 * 总页数
	 */
	private Integer maxPage;
	/**
	 * 每页多少条
	 */
	private Integer pageSize;
	/**
	 * 当前页码
	 */
	private Integer pageNum;
	/**
	 * 结果集合
	 */
	private Object obj;
	
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	public Boolean getIsPage() {
		return isPage;
	}
	public void setIsPage(Boolean isPage) {
		this.isPage = isPage;
	}
	public Integer getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
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
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public String toString(){
		return Common.beanToJson(this);
	}
}
