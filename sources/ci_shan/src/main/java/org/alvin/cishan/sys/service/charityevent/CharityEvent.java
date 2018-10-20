package org.alvin.cishan.sys.service.charityevent;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @类说明: 善行活动--实体类
 * @author: 唐植超
 * @date : 2018-10-20 11:58:00
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CharityEvent", description = "善行活动实体")
public class CharityEvent {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "type", dataType = "Byte")
	private Byte type;//  type

	@ApiModelProperty(value = "name", dataType = "String")
	private String name;//  name

	@ApiModelProperty(value = "content", dataType = "String")
	private String content;//  content

	@ApiModelProperty(value = "chairman_id", dataType = "Long")
	private Long chairman_id;//  chairman_id

	@ApiModelProperty(value = "target_id", dataType = "Long")
	private Long target_id;//  target_id

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "event_time", dataType = "Date")
	private Date event_time;//  event_time

	@ApiModelProperty(value = "author", dataType = "Long")
	private Long author;//  author

	@ApiModelProperty(value = "remark", dataType = "String")
	private String remark;//  remark

	@ApiModelProperty(value = "chairmain_pnone", dataType = "String")
	private String chairmain_pnone;//  chairmain_pnone

	@ApiModelProperty(value = "target_phone", dataType = "String")
	private String target_phone;//  target_phone

	@ApiModelProperty(value = "status", dataType = "Byte")
	private Byte status;//  status

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "create_time", dataType = "Date")
	private Date create_time;//  create_time

	//此处可添加查询显示辅助字段

}