package br.com.batch.entity;

import java.util.Comparator;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="log")
public class LogEntity implements Comparator<LogEntity>{

	private String date;

	private String ip;

	private String request;

	private String status;

	private String userAgent;

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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
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
	
	@Override
	public int compare(LogEntity arg0, LogEntity arg1) {
		return arg0.getIp().compareToIgnoreCase(arg1.getIp());
	}
}