/**
 * 
 */
package com.ftoul.manage.advert.vo;

/**
 * @author 李丁
 * @date:2016年9月2日 下午5:22:10
 * @类说明 :
 */

public class UploadPicVo {
	private String folderName;
	private String picAddress;
	private boolean hasUpload;
	private String picName;
	private String thumbnailSrc;
	
	
	/**
	 * @return the thumbnailSrc
	 */
	public String getThumbnailSrc() {
		return thumbnailSrc;
	}
	/**
	 * @param thumbnailSrc the thumbnailSrc to set
	 */
	public void setThumbnailSrc(String thumbnailSrc) {
		this.thumbnailSrc = thumbnailSrc;
	}
	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}
	/**
	 * @param folderName the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	/**
	 * @return the picAddress
	 */
	public String getPicAddress() {
		return picAddress;
	}
	/**
	 * @param picAddress the picAddress to set
	 */
	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}
	/**
	 * @return the hasUpload
	 */
	public boolean getHasUpload() {
		return hasUpload;
	}
	/**
	 * @param hasUpload the hasUpload to set
	 */
	public void setHasUpload(boolean hasUpload) {
		this.hasUpload = hasUpload;
	}
	/**
	 * @return the picName
	 */
	public String getPicName() {
		return picName;
	}
	/**
	 * @param picName the picName to set
	 */
	public void setPicName(String picName) {
		this.picName = picName;
	}
	
	
}
