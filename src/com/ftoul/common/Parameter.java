package com.ftoul.common;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ftoul.manage.advert.vo.UploadPicVo;
import com.ftoul.manage.user.vo.ManageTokenVo;
import com.ftoul.po.BaseResource;
import com.ftoul.po.UserToken;

/**
 * 前端提交参数封装类
 * @author flick
 *
 */
public class Parameter {

	private Object id;
	private Object parentId;
	private UserToken userToken;
	private ManageTokenVo manageToken;
	private String userId;
	private Object obj;
	private String action;
	private String key;
	//上传文件对象
	private UploadPicVo uploadPicVo;
	private Integer pageNum;
	private Integer pageSize;
	//排序方式（asc,desc)
	private String sord;
	//排序字段
	private String sidx;
	//排序语句（order by id desc)
	private String orderBy;
	//查询对象集合
	private Filters filters;
	//查询条件字符串
	private String whereStr;
	private Map<?,?> query;
	private Boolean admin;
	private List<BaseResource> BaseResourceList;
	private List<UploadPicVo> uploadPicMainVoList;
	private List<UploadPicVo> uploadPicVoList;
	private List<UploadPicVo> uploadPicInfoVoList;
	private List<Object> objList;
	/**
	 * @return the uploadPicMainVoList
	 */
	public List<UploadPicVo> getUploadPicMainVoList() {
		return uploadPicMainVoList;
	}
	/**
	 * @param uploadPicMainVoList the uploadPicMainVoList to set
	 */
	public void setUploadPicMainVoList(List<UploadPicVo> uploadPicMainVoList) {
		this.uploadPicMainVoList = uploadPicMainVoList;
	}
	/**
	 * @return the uploadPicInfoVoList
	 */
	public List<UploadPicVo> getUploadPicInfoVoList() {
		return uploadPicInfoVoList;
	}
	/**
	 * @param uploadPicInfoVoList the uploadPicInfoVoList to set
	 */
	public void setUploadPicInfoVoList(List<UploadPicVo> uploadPicInfoVoList) {
		this.uploadPicInfoVoList = uploadPicInfoVoList;
	}
	/**
	 * @return the uploadPicVoList
	 */
	public List<UploadPicVo> getUploadPicVoList() {
		return uploadPicVoList;
	}
	/**
	 * @param uploadPicVoList the uploadPicVoList to set
	 */
	public void setUploadPicVoList(List<UploadPicVo> uploadPicVoList) {
		this.uploadPicVoList = uploadPicVoList;
	}
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public Object getParentId() {
		return parentId;
	}
	public void setParentId(Object parentId) {
		this.parentId = parentId;
	}
	public ManageTokenVo getManageToken() {
		return manageToken;
	}
	public void setManageToken(ManageTokenVo manageToken) {
		this.manageToken = manageToken;
	}
	public Object getObj() {
		
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Map<?, ?> getQuery() {
		return query;
	}
	public void setQuery(Map<?, ?> query) {
		this.query = query;
	}
	public UserToken getUserToken() {
		return userToken;
	}
	public void setUserToken(UserToken userToken) {
		this.userToken = userToken;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public List<BaseResource> getBaseResourceList() {
		return BaseResourceList;
	}
	public void setBaseResourceList(List<BaseResource> baseResourceList) {
		BaseResourceList = baseResourceList;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Filters getFilters() {
		return filters;
	}
	public void setFilters(Filters filters) {
		this.filters = filters;
	}
	public String getWhereStr() {
		return whereStr;
	}
	public void setWhereStr(String whereStr) {
		this.whereStr = whereStr;
	}
	
	public String toString(){
		return Common.beanToJson(this);
	}
	
	public JSONObject toJson(){
		return JSONObject.fromObject(this);
	}
	/**
	 * @return the uploadPicVo
	 */
	public UploadPicVo getUploadPicVo() {
		return uploadPicVo;
	}
	/**
	 * @param uploadPicVo the uploadPicVo to set
	 */
	public void setUploadPicVo(UploadPicVo uploadPicVo) {
		this.uploadPicVo = uploadPicVo;
	}
	public List<Object> getObjList() {
		return objList;
	}
	public void setObjList(List<Object> objList) {
		this.objList = objList;
	}
	
}
