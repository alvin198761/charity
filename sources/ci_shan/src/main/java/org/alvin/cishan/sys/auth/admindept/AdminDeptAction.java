package org.alvin.cishan.sys.auth.admindept;

import org.alvin.cishan.sys.util.ResInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @类说明:部门前端控制器层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@RestController
@RequestMapping("api/adminDept")
public class AdminDeptAction {
//	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminDeptBus bus; // 部门Business层

	/**
	 * @方法说明:新增部门记录
	 **/
	@PostMapping("save")
	public int save(@RequestBody AdminDept adminDept, Principal principal) {
		return bus.save(adminDept);
	}

	/**
	 * @方法说明:验证方法
	 **/
	@PostMapping("validate")
	public ResInfo<?> validate(@RequestBody AdminDeptCond cond, Principal principal) {
		if (bus.queryCount(cond) > 0) {
			return new ResInfo<>(1, "菜单名称不能重复!");
		}
		return new ResInfo<>(0);
	}

	/**
	 * @方法说明:删除部门记录(多条)
	 **/
	@DeleteMapping("delete")
	public int delete(@RequestParam("ids[]") Integer ids[]) {
		return bus.delete(ids);
	}

	/**
	 * @方法说明:修改部门记录
	 **/
	@PostMapping("update")
	public int update(@RequestBody AdminDept adminDept, Principal principal) {
		return bus.update(adminDept);
	}

	/**
	 * @方法说明:按条件查询分页部门列表
	 **/
	@PostMapping("getTree")
	public List<AdminDept> getTree() {
		return bus.queryList(new AdminDeptCond());
	}

}