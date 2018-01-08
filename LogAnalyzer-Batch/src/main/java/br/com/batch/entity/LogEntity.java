package br.com.batch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbg_log")
public class LogEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "date")
	private String date;

	@Column(name = "ip")
	private String ip;

	@Column(name = "requet")
	private String requet;

	@Column(name = "status")
	private String status;

	@Column(name = "userAgent")
	private String userAgent;

	public LogEntity() {

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequet() {
		return requet;
	}

	public void setRequet(String requet) {
		this.requet = requet;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}