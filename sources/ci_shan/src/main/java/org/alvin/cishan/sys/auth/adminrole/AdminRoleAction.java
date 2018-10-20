package org.alvin.cishan.sys.auth.adminrole;

import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.ResInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @类说明:角色前端控制器层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@RestController
@RequestMapping("api/adminRole")
public class AdminRoleAction {
//	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminRoleBus bus; // 角色Business层

	/**
	 * @方法说明:新增角色记录
	 **/
	@PostMapping("save")
	public int save(@RequestBody AdminRole adminRole, Principal principal) {
		return bus.save(adminRole);
	}

	/**
	 * @方法说明:验证方法
	 **/
	@PostMapping("validateName")
	public ResInfo<?> validate(@RequestBody AdminRoleCond cond, Principal principal) {
		if (bus.queryCount(cond) > 0) {
			return new ResInfo<>(1, "角色名称不能重复!");
		}
		return new ResInfo<>(0);
	}

	/**
	 * @方法说明:删除角色记录(多条)
	 **/
	@DeleteMapping("delete")
	public int delete(@RequestParam("ids[]") Integer ids[]) {
		return bus.delete(ids);
	}

	/**
	 * @方法说明:修改角色记录
	 **/
	@PostMapping("update")
	public int update(@RequestBody AdminRole adminRole, Principal principal) {
		return bus.update(adminRole);
	}

	/**
	 * @方法说明:按条件查询分页角色列表
	 **/
	@PostMapping("queryPage")
	public Page<AdminRole> queryPage(@RequestBody AdminRoleCond cond, Principal principal) {
		// cond.setBranch_id(getDefaultBranchId(principal));
		return bus.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页角色列表
	 **/
	 @PostMapping("queryList")
	public List<AdminRole> queryList(@RequestBody AdminRoleCond cond, Principal principal) {
		// cond.setBranch_id(getDefaultBranchId(principal));
		return bus.queryList(cond);
	}
 
}