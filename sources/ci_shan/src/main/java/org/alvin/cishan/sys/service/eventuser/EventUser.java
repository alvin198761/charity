package org.alvin.cishan.sys.service.eventuser;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明: 活动参与方--实体类
 * @author: 高振中
 * @date : 2018-10-30 11:50:24
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "EventUser", description = "活动参与方实体")
public class EventUser {

	//数据库中的字段

	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键

	@ApiModelProperty(value = "活动ID", dataType = "Long")
	private Long event_id;//  活动ID

	@ApiModelProperty(value = "参与人ID", dataType = "Long")
	private Long user_id;//  参与人ID

	//此处可添加查询显示辅助字段
	@ApiModelProperty(value = "活动名称", dataType = "String")
	private String event_name; //活动名称
	@ApiModelProperty(value = "参与人名称", dataType = "String")
	private String join_user_name;//参与人名称


}