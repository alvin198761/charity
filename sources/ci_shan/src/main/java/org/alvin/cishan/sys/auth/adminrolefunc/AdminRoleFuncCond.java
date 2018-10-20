package org.alvin.cishan.sys.auth.adminrolefunc;

import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

/**
 * @类说明:角色菜单(功能权限)查询条件实体类
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleFuncCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件 
	 **/
	@Override
	public void addCondition() { 
		//add(id, "AND t.id = ?");
		add(role_id, "AND t.role_id = ?");
		//add(user_id, "AND t.user_id = ?");
		//add(ids, "AND t.id IN ");
	}

	//查询条件,把不用的条件清理掉
	private Long id;// 主键
	private Integer role_id;// 角色主键
	private Integer user_id;// 菜单主键
	//private List<Long> ids;// 主键列表

}