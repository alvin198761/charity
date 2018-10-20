package org.alvin.cishan.sys.auth.adminuserrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @类说明:用户角色前端控制器层
 * @author:唐植超
 * @date:2018-08-10 09:21:48
 **/
@RestController
@RequestMapping("api/adminUserRole")
 
public class AdminUserRoleAction {
//	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminUserRoleBus bus; // 用户角色Business层
 

	/**
	 * @方法说明:按条件查询不分页用户角色列表
	 **/
	@PostMapping("queryList")
	public List<AdminUserRole> queryList(@RequestBody AdminUserRoleCond cond ) {
		return bus.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询分页用户角色列表
	 **/
	@PostMapping("insertBatch")
	public int[] insertBatch(@RequestBody List<AdminUserRole> list, @RequestParam("user_id") int user_id) {
		return bus.insertBatch(list, user_id);
	}

}