package org.alvin.cishan.sys.service.charity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.Date;
import java.util.List;

/**
 * @类说明: 慈善方--查询条件实体类
 * @author: 唐植超
 * @date : 2018-10-20 11:34:30
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Charity", description = "慈善方查询条件实体")
public class CharityCond extends BaseCondition {


	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, " AND t.id = ? ");
		add(type, " AND t.type = ? ");
		add(charity_name, " AND t.charity_name LIKE ? ", 3);
		add(phone_no, " AND t.phone_no LIKE ? ", 3);
		add(gender, " AND t.gender LIKE ? ", 3);
		add(address, " AND t.address LIKE ? ", 3);
		add(create_date, " AND t.create_date = ? ");
		add(author, " AND t.author = ? ");
		add(remark, " AND t.remark LIKE ? ", 3);
		add(p_id, " AND t.p_id = ? ");
		add(category, " AND t.category = ? ");
		add(status, " AND t.status = ? ");
		// add(ids, "AND t.id IN ");");
	}

	//feilds
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

	//private List<Long> ids;// 主键列表
	private List<String> selectedFields; //暂时不放入父类
}