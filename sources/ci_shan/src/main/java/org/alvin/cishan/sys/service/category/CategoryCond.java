package org.alvin.cishan.sys.service.category;


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
 * @类说明: 捐助物资--查询条件实体类
 * @author: 唐植超
 * @date : 2018-10-20 11:34:30
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Category", description = "捐助物资查询条件实体")
public class CategoryCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(type, " AND t.type = ? ");
		add(name, " AND t.name LIKE ? ", 3);
		add(count, " AND t.count = ? ");
		add(create_date, " AND t.create_date = ? ");
		add(author, " AND t.author = ? ");
		add(remark, " AND t.remark LIKE ? ", 3);
		add(c_id, " AND t.c_id = ? ");
		add(status, " AND t.status = ? ");
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "type", dataType = "Byte")
	private Byte type;//  type
	@ApiModelProperty(value = "name", dataType = "String")
	private String name;//  name
	@ApiModelProperty(value = "count", dataType = "Integer")
	private Integer count;//  count
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

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}