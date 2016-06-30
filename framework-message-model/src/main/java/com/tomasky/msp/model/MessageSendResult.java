package com.tomasky.msp.model;

/**
 *
 */
public class MessageSendResult {
	private String code;
	private String info;
	private boolean status;

	public MessageSendResult() {
	}

	public MessageSendResult(boolean status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
