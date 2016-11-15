package com.ftoul.web.business.vo;

import com.ftoul.po.BusinessStore;

public class BusinessVo {
		//Business
		private String id;
		private String operateId;
		private String operateTime;
		private String createId;
		private String createTime;
		private String state;
		//BusinessManage
		private String businessManageId;
		private String legalPerson;
		private String idCard;
		private String idCardFaceImg;
		private String idCardConImg;
		private String businessLicenceNumber;
		private String establishTime;
		private String manageScope;
		private String businessLicenceImg;
		private String businesSlicenceDate;
		//BusinessBase
		private String businessBaseId;
		private String companyAddress;
		private String companyName;
		private String registeredCapital;
		private String linkmanName;
		private String linkmanNumber;
		private String email;
		//BusinessBank
		private String businessBankId;
		private String bankAccountName;
		private String bankAccount;
		private String bankName;
		private String bankAccountImg;
		//BusinessStore
		private String businessStoreId;
		private String pic;
		private String storeName;
		private String storeDuration;
		private String verifyId;
		private String verifyTime;
		//BusinessStoreManageCategory
		private String businessStoreManageCategoryId;
		private String firstCategory;
		private String twoCategory;
		private String threeCategory;
		//BusinessStoreLogin
		private String businessStoreLoginId;
		private String storeAccount;
		private String password;
		private String loginTIme;
		private String loginIp;
		//BusinessStoreClassify
		private String businessStoreClassifyId;
		private String storeType;
		private String storeClassify;
		private String classifyComment;
		//BusinessStoreSummary
		private String businessStoreSummaryId;
		private String summary;
		
		private Integer goodsNum;//店铺商铺数量
		private Integer goodsMonthNum;//上新商品总计
		private Integer goodsSaleNum;//店铺销售商品数量
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getOperateId() {
			return operateId;
		}
		public void setOperateId(String operateId) {
			this.operateId = operateId;
		}
		public String getOperateTime() {
			return operateTime;
		}
		public void setOperateTime(String operateTime) {
			this.operateTime = operateTime;
		}
		public String getCreateId() {
			return createId;
		}
		public void setCreateId(String createId) {
			this.createId = createId;
		}
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getBusinessManageId() {
			return businessManageId;
		}
		public void setBusinessManageId(String businessManageId) {
			this.businessManageId = businessManageId;
		}
		public String getLegalPerson() {
			return legalPerson;
		}
		public void setLegalPerson(String legalPerson) {
			this.legalPerson = legalPerson;
		}
		public String getIdCard() {
			return idCard;
		}
		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}
		public String getIdCardFaceImg() {
			return idCardFaceImg;
		}
		public void setIdCardFaceImg(String idCardFaceImg) {
			this.idCardFaceImg = idCardFaceImg;
		}
		public String getIdCardConImg() {
			return idCardConImg;
		}
		public void setIdCardConImg(String idCardConImg) {
			this.idCardConImg = idCardConImg;
		}
		public String getBusinessLicenceNumber() {
			return businessLicenceNumber;
		}
		public void setBusinessLicenceNumber(String businessLicenceNumber) {
			this.businessLicenceNumber = businessLicenceNumber;
		}
		public String getEstablishTime() {
			return establishTime;
		}
		public void setEstablishTime(String establishTime) {
			this.establishTime = establishTime;
		}
		public String getManageScope() {
			return manageScope;
		}
		public void setManageScope(String manageScope) {
			this.manageScope = manageScope;
		}
		public String getBusinessLicenceImg() {
			return businessLicenceImg;
		}
		public void setBusinessLicenceImg(String businessLicenceImg) {
			this.businessLicenceImg = businessLicenceImg;
		}
		public String getBusinesSlicenceDate() {
			return businesSlicenceDate;
		}
		public void setBusinesSlicenceDate(String businesSlicenceDate) {
			this.businesSlicenceDate = businesSlicenceDate;
		}
		public String getBusinessBaseId() {
			return businessBaseId;
		}
		public void setBusinessBaseId(String businessBaseId) {
			this.businessBaseId = businessBaseId;
		}
		public String getCompanyAddress() {
			return companyAddress;
		}
		public void setCompanyAddress(String companyAddress) {
			this.companyAddress = companyAddress;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getRegisteredCapital() {
			return registeredCapital;
		}
		public void setRegisteredCapital(String registeredCapital) {
			this.registeredCapital = registeredCapital;
		}
		public String getLinkmanName() {
			return linkmanName;
		}
		public void setLinkmanName(String linkmanName) {
			this.linkmanName = linkmanName;
		}
		public String getLinkmanNumber() {
			return linkmanNumber;
		}
		public void setLinkmanNumber(String linkmanNumber) {
			this.linkmanNumber = linkmanNumber;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getBusinessBankId() {
			return businessBankId;
		}
		public void setBusinessBankId(String businessBankId) {
			this.businessBankId = businessBankId;
		}
		public String getBankAccountName() {
			return bankAccountName;
		}
		public void setBankAccountName(String bankAccountName) {
			this.bankAccountName = bankAccountName;
		}
		public String getBankAccount() {
			return bankAccount;
		}
		public void setBankAccount(String bankAccount) {
			this.bankAccount = bankAccount;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
		public String getBankAccountImg() {
			return bankAccountImg;
		}
		public void setBankAccountImg(String bankAccountImg) {
			this.bankAccountImg = bankAccountImg;
		}
		public String getBusinessStoreId() {
			return businessStoreId;
		}
		public void setBusinessStoreId(String businessStoreId) {
			this.businessStoreId = businessStoreId;
		}
		public String getPic() {
			return pic;
		}
		public void setPic(String pic) {
			this.pic = pic;
		}
		public String getStoreName() {
			return storeName;
		}
		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}
		public String getStoreDuration() {
			return storeDuration;
		}
		public void setStoreDuration(String storeDuration) {
			this.storeDuration = storeDuration;
		}
		public String getVerifyId() {
			return verifyId;
		}
		public void setVerifyId(String verifyId) {
			this.verifyId = verifyId;
		}
		public String getVerifyTime() {
			return verifyTime;
		}
		public void setVerifyTime(String verifyTime) {
			this.verifyTime = verifyTime;
		}
		public String getBusinessStoreManageCategoryId() {
			return businessStoreManageCategoryId;
		}
		public void setBusinessStoreManageCategoryId(
				String businessStoreManageCategoryId) {
			this.businessStoreManageCategoryId = businessStoreManageCategoryId;
		}
		public String getFirstCategory() {
			return firstCategory;
		}
		public void setFirstCategory(String firstCategory) {
			this.firstCategory = firstCategory;
		}
		public String getTwoCategory() {
			return twoCategory;
		}
		public void setTwoCategory(String twoCategory) {
			this.twoCategory = twoCategory;
		}
		public String getThreeCategory() {
			return threeCategory;
		}
		public void setThreeCategory(String threeCategory) {
			this.threeCategory = threeCategory;
		}
		public String getBusinessStoreLoginId() {
			return businessStoreLoginId;
		}
		public void setBusinessStoreLoginId(String businessStoreLoginId) {
			this.businessStoreLoginId = businessStoreLoginId;
		}
		public String getStoreAccount() {
			return storeAccount;
		}
		public void setStoreAccount(String storeAccount) {
			this.storeAccount = storeAccount;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getLoginTIme() {
			return loginTIme;
		}
		public void setLoginTIme(String loginTIme) {
			this.loginTIme = loginTIme;
		}
		public String getLoginIp() {
			return loginIp;
		}
		public void setLoginIp(String loginIp) {
			this.loginIp = loginIp;
		}
		public String getBusinessStoreClassifyId() {
			return businessStoreClassifyId;
		}
		public void setBusinessStoreClassifyId(String businessStoreClassifyId) {
			this.businessStoreClassifyId = businessStoreClassifyId;
		}
		public String getStoreType() {
			return storeType;
		}
		public void setStoreType(String storeType) {
			this.storeType = storeType;
		}
		public String getStoreClassify() {
			return storeClassify;
		}
		public void setStoreClassify(String storeClassify) {
			this.storeClassify = storeClassify;
		}
		public String getClassifyComment() {
			return classifyComment;
		}
		public void setClassifyComment(String classifyComment) {
			this.classifyComment = classifyComment;
		}
		public String getBusinessStoreSummaryId() {
			return businessStoreSummaryId;
		}
		public void setBusinessStoreSummaryId(String businessStoreSummaryId) {
			this.businessStoreSummaryId = businessStoreSummaryId;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public Integer getGoodsNum() {
			return goodsNum;
		}
		public void setGoodsNum(Integer goodsNum) {
			this.goodsNum = goodsNum;
		}
		public Integer getGoodsMonthNum() {
			return goodsMonthNum;
		}
		public void setGoodsMonthNum(Integer goodsMonthNum) {
			this.goodsMonthNum = goodsMonthNum;
		}
		public Integer getGoodsSaleNum() {
			return goodsSaleNum;
		}
		public void setGoodsSaleNum(Integer goodsSaleNum) {
			this.goodsSaleNum = goodsSaleNum;
		}
		
}
