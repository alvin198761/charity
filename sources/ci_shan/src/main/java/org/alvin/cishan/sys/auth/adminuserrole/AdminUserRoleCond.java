package org.alvin.cishan.sys.auth.adminuserrole;

import lombok.Getter;
import lombok.Setter;
import org.alvin.cishan.sys.util.base.BaseCondition;

/**
 * @类说明:用户角色查询条件实体类
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Setter
@Getter
 
public class AdminUserRoleCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		// add(id, "AND t.id = ?");
		add(user_id, "AND t.user_id = ?");
		// add(role_id, "AND t.role_id = ?");
		// add(ids, "AND t.id IN ");
	}

	// 查询条件,把不用的条件清理掉
	private Long id;// 主键
	private Integer user_id;// 用户主键
	private Integer role_id;// 角色主键
	// private List<Long> ids;// 主键列表

}