package org.alvin.cishan.sys.service.charityevent;


import java.util.Date;

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
 * @类说明: 善行活动--查询条件实体类
 * @author: 唐植超
 * @date : 2018-10-20 11:58:00
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CharityEvent", description = "善行活动查询条件实体")
public class CharityEventCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(type, " AND t.type = ? ");
		add(name, " AND t.name LIKE ? ", 3);
		add(content, " AND t.content LIKE ? ", 3);
		add(chairman_id, " AND t.chairman_id = ? ");
		add(target_id, " AND t.target_id = ? ");
		add(event_time, " AND t.event_time = ? ");
		add(author, " AND t.author = ? ");
		add(remark, " AND t.remark LIKE ? ", 3);
		add(chairmain_pnone, " AND t.chairmain_pnone LIKE ? ", 3);
		add(target_phone, " AND t.target_phone LIKE ? ", 3);
		add(status, " AND t.status = ? ");
		add(create_time, " AND t.create_time = ? ");
		// add(ids, "AND t.id IN ");");
	}

	//feilds
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
	@ApiModelProperty(value = "create_time", dataType = "Date")
	private Date create_time;//  create_time

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}