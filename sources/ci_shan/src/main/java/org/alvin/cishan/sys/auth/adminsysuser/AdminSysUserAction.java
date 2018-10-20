package org.alvin.cishan.sys.auth.adminsysuser;

import org.alvin.cishan.common.controller.PrincipalAction;
import org.alvin.cishan.sys.util.MD5Util;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.ResInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @类说明:用户前端控制器层
 * @author:唐植超
 * @date:2018-08-10 09:21:48
 **/
@RestController
@RequestMapping("api/adminSysUser")
public class AdminSysUserAction extends PrincipalAction {
	// private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminSysUserBus bus; // 用户Business层

	/**
	 * @方法说明:新增用户记录
	 **/
	@PostMapping("save")
	public int save(@RequestBody AdminSysUser adminSysUser) {
		return bus.save(adminSysUser);
	}

	/**
	 * @方法说明:验证方法
	 **/
	@PostMapping("validatePhone")
	public ResInfo<?> validate(@RequestBody AdminSysUserCond cond) {
		// 此处写验证逻辑
		if (bus.queryCount(cond) > 0) {
			return new ResInfo<>(1, "手机号不能重复!");
		}
		return new ResInfo<>(0);
	}

	/**
	 * @方法说明:删除用户记录(多条)
	 **/
	@DeleteMapping("delete")
	public int delete(@RequestParam("ids[]") Integer ids[]) {
		return bus.delete(ids);
	}

	/**
	 * @方法说明:修改用户记录
	 **/
	@PostMapping("update")
	public int update(@RequestBody AdminSysUser adminSysUser) {
		return bus.update(adminSysUser);
	}

	/**
	 * @方法说明:按条件查询分页用户列表
	 **/
	@PostMapping("queryPage")
	public Page<AdminSysUser> queryPage(@RequestBody AdminSysUserCond cond) {
		// cond.setBranch_id(getDefaultBranchId(principal));
		return bus.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页用户列表
	 **/
	@PostMapping("queryList")
	public List<AdminSysUser> queryList(@RequestBody AdminSysUserCond cond) {
		// cond.setBranch_id(getDefaultBranchId(principal));
		return bus.queryList(cond);
	}

	/**
	 * @方法说明:按主键查找单个用户记录
	 **/
	// @GetMapping("findById")
	// public AdminSysUser findById(@RequestParam("id") Integer id) {
	// return bus.findById(id);
	// }

	/**
	 * @方法说明:按条件查询用户记录个数
	 **/
	// @PostMapping("queryCount")
	// public long queryCount(@RequestBody AdminSysUserCond cond){
	// return bus.queryCount(cond);
	// }

	/**
	 * @方法说明:重置用户密码
	 **/
	@PostMapping("updatePassword")
	public int updatePassword(@RequestBody AdminSysUser adminSysUser) {
		return bus.updatePassword(adminSysUser.getUser_id(), adminSysUser.getPassword());
	}

	/**
	 * @方法说明:重置用户密码
	 **/
	@PostMapping("updatePas")
	public int updatePas(@RequestBody AdminSysUser adminSysUser, Principal principal) {
		AdminSysUser user = bus.findById(getUserId(principal));
		if (!MD5Util.getMD5(adminSysUser.getOld_password()).equals(user.getPassword())) {
			return 2;
		}
		return bus.updatePassword(user.getUser_id(), adminSysUser.getPassword());
	}
}