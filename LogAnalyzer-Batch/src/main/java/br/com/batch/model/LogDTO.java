package br.com.batch.model;

public class LogDTO {
	
	private String date;
	private String ip;	
	private String request;
	private String status;
	private String userAgent;

	public LogDTO() {

	}

	public LogDTO(String date, String ip, String request, String status, String userAgent) {
		this.date = date;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.userAgent = userAgent;
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
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Date: ").append(this.date);
		result.append(", IP: ").append(this.ip);
		result.append(", Request: ").append(this.request);
		result.append(", Status: ").append(this.status);
		result.append(", User Agent: ").append(this.userAgent);
		return result.toString();
	}
}
