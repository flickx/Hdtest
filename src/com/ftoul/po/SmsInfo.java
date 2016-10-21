package com.ftoul.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SmsInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sms_info", catalog = "ftoul_shop")
public class SmsInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String threadId;
	private String address;
	private String person;
	private String date;
	private String protocol;
	private String read;
	private String status;
	private String type;
	private String body;
	private String serviceCenter;

	// Constructors

	/** default constructor */
	public SmsInfo() {
	}

	/** full constructor */
	public SmsInfo(String threadId, String address, String person, String date,
			String protocol, String read, String status, String type,
			String body, String serviceCenter) {
		this.threadId = threadId;
		this.address = address;
		this.person = person;
		this.date = date;
		this.protocol = protocol;
		this.read = read;
		this.status = status;
		this.type = type;
		this.body = body;
		this.serviceCenter = serviceCenter;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "thread_id", length = 32)
	public String getThreadId() {
		return this.threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	@Column(name = "address", length = 32)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "person", length = 32)
	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Column(name = "date", length = 32)
	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(name = "protocol", length = 10)
	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Column(name = "read", length = 10)
	public String getRead() {
		return this.read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	@Column(name = "status", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "type", length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "body", length = 2000)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "service_center", length = 32)
	public String getServiceCenter() {
		return this.serviceCenter;
	}

	public void setServiceCenter(String serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

}