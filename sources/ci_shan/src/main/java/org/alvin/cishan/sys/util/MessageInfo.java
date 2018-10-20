package org.alvin.cishan.sys.util;

import java.util.HashMap;
import java.util.Map;

public class MessageInfo {

	private Boolean success = true;
	private Map<Integer, String> msgMap = new HashMap<>();

	public void setMessage(Integer code, String msg) {
		setSuccess(false);
		msgMap.put(code, msg);
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Map<Integer, String> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<Integer, String> msgMap) {
		this.msgMap = msgMap;
	}
	
	

}
