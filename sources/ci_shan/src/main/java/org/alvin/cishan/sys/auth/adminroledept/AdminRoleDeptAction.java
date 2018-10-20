package org.alvin.cishan.sys.auth.adminroledept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @类说明:角色部门前端控制器层
 * @author:唐植超
 * @date:2018-08-10 09:21:48
 **/
@RestController
@RequestMapping("api/adminRoleDept")
public class AdminRoleDeptAction {
//	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminRoleDeptBus bus; // 角色部门Business层

	/**
	 * @方法说明:按条件查询不分页角色部门列表
	 **/
	@PostMapping("queryList")
	public List<AdminRoleDept> queryList(@RequestBody AdminRoleDeptCond cond, Principal principal) {
		// cond.setBranch_id(getDefaultBranchId(principal));
		return bus.queryList(cond);
	}

	/**
	 * @方法说明:删除角色部门记录
	 **/
	@PostMapping("insertBatch")
	public int[] insertBatch(@RequestBody List<AdminRoleDept> list, @RequestParam("role_id") Integer role_id) {
		return bus.insertBatch(list, role_id);
	}


}