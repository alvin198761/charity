package org.alvin.cishan.sys.auth.adminfunc;

import org.alvin.cishan.sys.util.ResInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @类说明:菜单前端控制器层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@RestController
@RequestMapping("api/adminFunc")
// public class AdminFuncAction extends PrincipalAction {
public class AdminFuncAction {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminFuncBus bus; // 菜单Business层

	/**
	 * @方法说明:新增菜单记录
	 **/
	@PostMapping("save")
	public int save(@RequestBody AdminFunc adminFunc, Principal principal) {
		return bus.save(adminFunc);
	}

	/**
	 * @方法说明:验证方法
	 **/
	@PostMapping("validate")
	public ResInfo<?> validate(@RequestBody AdminFuncCond cond, Principal principal) {
		if (bus.queryCount(cond) > 0) {
			return new ResInfo<>(1, "部门名称不能重复!");
		}
		return new ResInfo<>(0);
	}

	/**
	 * @方法说明:删除菜单记录(多条)
	 **/
	@DeleteMapping("delete")
	public int delete(@RequestParam("ids[]") Integer ids[]) {
		return bus.delete(ids);
	}

	/**
	 * @方法说明:修改菜单记录
	 **/
	@PostMapping("update")
	public int update(@RequestBody AdminFunc adminFunc, Principal principal) {
		return bus.update(adminFunc);
	}

	/**
	 * @方法说明 树结构 来
	 */
	@PostMapping("getTree")
	public List<AdminFunc> getTree() {
		return bus.queryList(new AdminFuncCond());
	}

	/**
	 *
	 * @param cur_id 当前拖动节点的ID
	 * @param tar_id 目标节点的ID
	 * @param type 位置 （inner，before，after）
	 */
	@RequestMapping("/dragMove")
	public void dragMove(@RequestParam("type") String type, @RequestBody List<AdminFunc> curFunc) {
		logger.info(type+curFunc.get(0).getName()+curFunc.get(1).getName());
		this.bus.dragMove(curFunc.get(0), curFunc.get(1), type);
	}


}