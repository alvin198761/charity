package org.alvin.cishan.sys.service.eventuser;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.List;

/**
 * @类说明: 活动参与方--查询条件实体类
 * @author: 高振中
 * @date : 2018-10-30 11:50:24
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "EventUser", description = "活动参与方查询条件实体")
public class EventUserCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(event_id, " AND t.event_id = ? ");
		add(user_id, " AND t.user_id = ? ");
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "活动ID", dataType = "Long")
	private Long event_id;//  活动ID
	@ApiModelProperty(value = "参与人ID", dataType = "Long")
	private Long user_id;//  参与人ID

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}