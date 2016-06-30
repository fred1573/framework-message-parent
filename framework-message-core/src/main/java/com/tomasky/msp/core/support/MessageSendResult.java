package com.tomasky.msp.core.support;

/**
 * ��Ϣ���ͽ��
 */
public class MessageSendResult {
	// ��Ϣ����״̬��Ϣ����
	private String code;
	// ��Ϣ����״̬��Ϣ����
	private String info;
	// ��Ϣ����״̬
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
