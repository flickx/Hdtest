package com.ftoul.web.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.ftoul.web.webservice package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _GetIntegral_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/", "getIntegral");
	private final static QName _ModifyIntegral_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/",
			"ModifyIntegral");
	private final static QName _SaveUser_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/", "saveUser");
	private final static QName _CheckUserExistsResponse_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/",
			"checkUserExistsResponse");
	private final static QName _CheckUserResponse_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/",
			"checkUserResponse");
	private final static QName _CheckUserExists_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/",
			"checkUserExists");
	private final static QName _GetIntegralResponse_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/",
			"getIntegralResponse");
	private final static QName _ModifyPwd_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/", "modifyPwd");
	private final static QName _ModifyIntegralResponse_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/",
			"ModifyIntegralResponse");
	private final static QName _SaveUserResponse_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/",
			"saveUserResponse");
	private final static QName _CheckUser_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/", "checkUser");
	private final static QName _ModifyPwdResponse_QNAME = new QName(
			"http://webservice.service.biz.member.mall.jq.com/",
			"modifyPwdResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.ftoul.web.webservice
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link ModifyIntegral }
	 * 
	 */
	public ModifyIntegral createModifyIntegral() {
		return new ModifyIntegral();
	}

	/**
	 * Create an instance of {@link CheckUserResponse }
	 * 
	 */
	public CheckUserResponse createCheckUserResponse() {
		return new CheckUserResponse();
	}

	/**
	 * Create an instance of {@link CheckUser }
	 * 
	 */
	public CheckUser createCheckUser() {
		return new CheckUser();
	}

	/**
	 * Create an instance of {@link CheckUserExists }
	 * 
	 */
	public CheckUserExists createCheckUserExists() {
		return new CheckUserExists();
	}

	/**
	 * Create an instance of {@link GetIntegralResponse }
	 * 
	 */
	public GetIntegralResponse createGetIntegralResponse() {
		return new GetIntegralResponse();
	}

	/**
	 * Create an instance of {@link SaveUserResponse }
	 * 
	 */
	public SaveUserResponse createSaveUserResponse() {
		return new SaveUserResponse();
	}

	/**
	 * Create an instance of {@link GetIntegral }
	 * 
	 */
	public GetIntegral createGetIntegral() {
		return new GetIntegral();
	}

	/**
	 * Create an instance of {@link CheckUserExistsResponse }
	 * 
	 */
	public CheckUserExistsResponse createCheckUserExistsResponse() {
		return new CheckUserExistsResponse();
	}

	/**
	 * Create an instance of {@link ModifyPwdResponse }
	 * 
	 */
	public ModifyPwdResponse createModifyPwdResponse() {
		return new ModifyPwdResponse();
	}

	/**
	 * Create an instance of {@link SaveUser }
	 * 
	 */
	public SaveUser createSaveUser() {
		return new SaveUser();
	}

	/**
	 * Create an instance of {@link ModifyIntegralResponse }
	 * 
	 */
	public ModifyIntegralResponse createModifyIntegralResponse() {
		return new ModifyIntegralResponse();
	}

	/**
	 * Create an instance of {@link ModifyPwd }
	 * 
	 */
	public ModifyPwd createModifyPwd() {
		return new ModifyPwd();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetIntegral }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "getIntegral")
	public JAXBElement<GetIntegral> createGetIntegral(GetIntegral value) {
		return new JAXBElement<GetIntegral>(_GetIntegral_QNAME,
				GetIntegral.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ModifyIntegral }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "ModifyIntegral")
	public JAXBElement<ModifyIntegral> createModifyIntegral(ModifyIntegral value) {
		return new JAXBElement<ModifyIntegral>(_ModifyIntegral_QNAME,
				ModifyIntegral.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveUser }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "saveUser")
	public JAXBElement<SaveUser> createSaveUser(SaveUser value) {
		return new JAXBElement<SaveUser>(_SaveUser_QNAME, SaveUser.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CheckUserExistsResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "checkUserExistsResponse")
	public JAXBElement<CheckUserExistsResponse> createCheckUserExistsResponse(
			CheckUserExistsResponse value) {
		return new JAXBElement<CheckUserExistsResponse>(
				_CheckUserExistsResponse_QNAME, CheckUserExistsResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CheckUserResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "checkUserResponse")
	public JAXBElement<CheckUserResponse> createCheckUserResponse(
			CheckUserResponse value) {
		return new JAXBElement<CheckUserResponse>(_CheckUserResponse_QNAME,
				CheckUserResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CheckUserExists }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "checkUserExists")
	public JAXBElement<CheckUserExists> createCheckUserExists(
			CheckUserExists value) {
		return new JAXBElement<CheckUserExists>(_CheckUserExists_QNAME,
				CheckUserExists.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetIntegralResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "getIntegralResponse")
	public JAXBElement<GetIntegralResponse> createGetIntegralResponse(
			GetIntegralResponse value) {
		return new JAXBElement<GetIntegralResponse>(_GetIntegralResponse_QNAME,
				GetIntegralResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ModifyPwd }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "modifyPwd")
	public JAXBElement<ModifyPwd> createModifyPwd(ModifyPwd value) {
		return new JAXBElement<ModifyPwd>(_ModifyPwd_QNAME, ModifyPwd.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ModifyIntegralResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "ModifyIntegralResponse")
	public JAXBElement<ModifyIntegralResponse> createModifyIntegralResponse(
			ModifyIntegralResponse value) {
		return new JAXBElement<ModifyIntegralResponse>(
				_ModifyIntegralResponse_QNAME, ModifyIntegralResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SaveUserResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "saveUserResponse")
	public JAXBElement<SaveUserResponse> createSaveUserResponse(
			SaveUserResponse value) {
		return new JAXBElement<SaveUserResponse>(_SaveUserResponse_QNAME,
				SaveUserResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CheckUser }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "checkUser")
	public JAXBElement<CheckUser> createCheckUser(CheckUser value) {
		return new JAXBElement<CheckUser>(_CheckUser_QNAME, CheckUser.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ModifyPwdResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.service.biz.member.mall.jq.com/", name = "modifyPwdResponse")
	public JAXBElement<ModifyPwdResponse> createModifyPwdResponse(
			ModifyPwdResponse value) {
		return new JAXBElement<ModifyPwdResponse>(_ModifyPwdResponse_QNAME,
				ModifyPwdResponse.class, null, value);
	}

}
