package org.alvin.cishan.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "响应结果实体")
@Data
public class RestfullResp<T> {

	@ApiModelProperty(value = "是否成功", notes = "true = 成功，false=不成功", required = true)
	private boolean success = true;
	@ApiModelProperty(value = "失败原因", notes = "失败内容")
	private String errorMsg;
	@ApiModelProperty(value = "成功响应结果实体", notes = "错误信息", required = true)
	private Integer code;
	@ApiModelProperty(value = "成功响应结果实体", notes = "错误")
	private T data;

	public RestfullResp() {
	}

	public RestfullResp(Integer code, String errorMsg, T data) {
		this.errorMsg = errorMsg;
		this.code = code;
		this.data = data;
	}

	public RestfullResp(T data) {
		this.success = true;
		this.errorMsg = null;
		this.data = data;
		this.code = 0;
	}

	public RestfullResp(Integer code, String errorMsg) {
		this.success = false;
		this.errorMsg = errorMsg;
		this.code = code;
		this.data = null;
	}


}