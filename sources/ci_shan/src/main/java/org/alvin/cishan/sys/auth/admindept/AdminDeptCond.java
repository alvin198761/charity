package org.alvin.cishan.sys.auth.admindept;

import lombok.Getter;
import lombok.Setter;
import org.alvin.cishan.sys.util.base.BaseCondition;

import java.util.List;

/**
 * @类说明:部门查询条件实体类
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Setter
@Getter
public class AdminDeptCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(dept_id, "AND t.dept_id <> ?");
		// add(parent_id, "AND t.parent_id = ?");
		add(name, "AND t.name = ?");
		// add(enable, "AND t.enable = ?");
		// add(remark, "AND t.remark LIKE ?", 3);
		// add(ids, "AND t.id IN ");
	}

	// 查询条件,把不用的条件清理掉
	private Integer dept_id;// 主键
	// private Long branch_id;// 门店主键
	private Integer parent_id;// 上级ID
	private String name;// 部门名称
	private Byte enable;// 可用标志
	private String remark;// 备注
	private List<Integer> ids;// 主键列表

}