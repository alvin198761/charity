package org.alvin.cishan.sys.service.category;


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
 * @类说明: 捐助物资--实体类
 * @author: 唐植超
 * @date : 2018-10-20 11:34:30
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Category", description = "捐助物资实体")
public class Category {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "type", dataType = "Byte")
	private Byte type;//  type

	@ApiModelProperty(value = "name", dataType = "String")
	private String name;//  name

	@ApiModelProperty(value = "count", dataType = "Integer")
	private Integer count;//  count

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "create_date", dataType = "Date")
	private Date create_date;//  create_date

	@ApiModelProperty(value = "author", dataType = "Long")
	private Long author;//  author

	@ApiModelProperty(value = "remark", dataType = "String")
	private String remark;//  remark

	@ApiModelProperty(value = "c_id", dataType = "Long")
	private Long c_id;//  c_id

	@ApiModelProperty(value = "status", dataType = "Byte")
	private Byte status;//  status

	//此处可添加查询显示辅助字段

}