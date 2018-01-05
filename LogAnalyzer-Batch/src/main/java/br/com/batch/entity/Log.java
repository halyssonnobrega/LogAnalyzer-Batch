package br.com.batch.entity;

public class Log {
	private String date;
	private String ip;
	private String requet;
	private String status;
	private String userAgent;

	public Log() {

	}

	public Log(String date, String ip, String requet, String status, String userAgent) {
		this.date = date;
		this.ip = ip;
		this.requet = requet;
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

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Date: ").append(this.date);
		result.append(", IP: ").append(this.ip);
		result.append(", Request: ").append(this.requet);
		result.append(", Status: ").append(this.status);
		result.append(", User Agent: ").append(this.userAgent);
		return result.toString();
	}
}
