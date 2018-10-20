package org.alvin.cishan.sys.util;

public class ResInfo<T> {

	private boolean success;
	private String errorMsg;
	private Integer code;
	private T data;

	public ResInfo() {
	}

	public ResInfo(boolean success, String errorMsg, Integer code, T data) {
		this.success = success;
		this.errorMsg = errorMsg;
		this.code = code;
		this.data = data;
	}

	public ResInfo(T data) {
		this.success = true;
		this.errorMsg = null;
		this.code = 0;
		this.data = data;
	}

	public ResInfo(boolean success, String errorMsg, T data) {
		this.success = success;
		this.errorMsg = errorMsg;
		this.data = data;
	}

	public ResInfo(Integer code, String errorMsg) {
		this.success = false;
		this.errorMsg = errorMsg;
		this.code = code;
		this.data = null;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
