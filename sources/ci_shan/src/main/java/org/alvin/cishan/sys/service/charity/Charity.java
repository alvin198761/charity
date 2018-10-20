package org.alvin.cishan.sys.service.charity;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明: 慈善方--实体类
 * @author: 唐植超
 * @date : 2018-10-20 11:34:30
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Charity", description = "慈善方实体")
public class Charity {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "type", dataType = "Byte")
	private Byte type;//  type

	@ApiModelProperty(value = "charity_name", dataType = "String")
	private String charity_name;//  charity_name

	@ApiModelProperty(value = "phone_no", dataType = "String")
	private String phone_no;//  phone_no

	@ApiModelProperty(value = "gender", dataType = "String")
	private String gender;//  gender

	@ApiModelProperty(value = "address", dataType = "String")
	private String address;//  address

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "create_date", dataType = "Date")
	private Date create_date;//  create_date

	@ApiModelProperty(value = "author", dataType = "Long")
	private Long author;//  author

	@ApiModelProperty(value = "remark", dataType = "String")
	private String remark;//  remark

	@ApiModelProperty(value = "p_id", dataType = "Long")
	private Long p_id;//  p_id

	@ApiModelProperty(value = "category", dataType = "Byte")
	private Byte category;//  category

	@ApiModelProperty(value = "status", dataType = "Byte")
	private Byte status;//  status

	//此处可添加查询显示辅助字段

}