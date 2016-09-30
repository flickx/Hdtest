package com.ftoul.manage.goods.vo;

import java.util.List;

import com.ftoul.manage.advert.vo.UploadPicVo;



public class GoodsTypePicVo {
	
	private String id;
	private String name;
	private String pid;
	private String level;
	private String info;
	private String createTime;
	private String createPerson;
	private String modifyTime;
	private String modifyPerson;
	private String state;
	private String picSrc;
	private String pid2;
	private String typeSort;
	private String thumbnailSrc;
	private List<UploadPicVo> uploadPicVoList;
	
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPicSrc() {
		return picSrc;
	}
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}
	public String getTypeSort() {
		return typeSort;
	}
	public void setTypeSort(String typeSort) {
		this.typeSort = typeSort;
	}
	public List<UploadPicVo> getUploadPicVoList() {
		return uploadPicVoList;
	}
	public void setUploadPicVoList(List<UploadPicVo> uploadPicVoList) {
		this.uploadPicVoList = uploadPicVoList;
	}
	public String getPid2() {
		return pid2;
	}
	public void setPid2(String pid2) {
		this.pid2 = pid2;
	}
	public String getThumbnailSrc() {
		return thumbnailSrc;
	}
	public void setThumbnailSrc(String thumbnailSrc) {
		this.thumbnailSrc = thumbnailSrc;
	}
	
	
}
