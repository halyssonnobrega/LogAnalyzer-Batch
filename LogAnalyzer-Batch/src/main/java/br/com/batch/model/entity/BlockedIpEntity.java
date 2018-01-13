package br.com.batch.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blockedIP")
public class BlockedIpEntity {

	private String ip;

	private Integer quantity;

	private String message;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
