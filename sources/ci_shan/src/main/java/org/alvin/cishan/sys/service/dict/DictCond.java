package org.alvin.cishan.sys.service.dict;


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
 * @类说明: 类型字典--查询条件实体类
 * @author: 唐植超
 * @date : 2018-10-20 11:34:30
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Dict", description = "类型字典查询条件实体")
public class DictCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(type, " AND t.type = ? ");
		add(name, " AND t.name LIKE ? ", 3);
		// add(ids, "AND t.id IN ");");
	}

	//feilds
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;//  主键
	@ApiModelProperty(value = "type", dataType = "Integer")
	private Integer type;//  type
	@ApiModelProperty(value = "name", dataType = "String")
	private String name;//  name

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}