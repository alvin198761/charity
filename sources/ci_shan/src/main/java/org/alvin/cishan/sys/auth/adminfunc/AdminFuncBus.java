package org.alvin.cishan.sys.auth.adminfunc;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @类说明:菜单业务逻辑层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Service
public class AdminFuncBus {
	//	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminFuncDao dao; // 注入菜单数据访问层

	/**
	 * @方法说明:新增菜单记录
	 **/
	@Transactional
	public int save(AdminFunc adminFunc) {
		return dao.save(adminFunc);
	}

	/**
	 * @方法说明:删除菜单记录(多条)
	 **/
	@Transactional
	public int delete(Integer ids[]) {
		return dao.delete(ids);// 物理删除
	}

	/**
	 * @方法说明:更新菜单记录
	 **/
	@Transactional
	public int update(AdminFunc adminFunc) {
		return dao.update(adminFunc);
	}

	/**
	 * @方法说明:按条件查询不分页菜单列表
	 **/
	public List<AdminFunc> queryList(AdminFuncCond cond) {
		List<AdminFunc> list = dao.queryList(cond);
		Map<Integer, AdminFunc> treeMap = Maps.newHashMap();// 用map来定位上级结点
		List<AdminFunc> treeList = Lists.newArrayList();// 最终返回的treelist
		for (AdminFunc dept : list) {
			treeMap.put(dept.getFunc_id(), dept);// 将vo放到map中
			if (dept.getParent_id() == 0) {// 如果是根结点直接放到treeList中
				treeList.add(dept);
			}
			if (treeMap.get(dept.getParent_id()) != null) {
				treeMap.get(dept.getParent_id()).getChildren().add(dept);// children
			}
		}
		treeMap.clear();
		return treeList;
	}

	/**
	 * @方法说明:按条件查询菜单记录个数
	 **/
	public long queryCount(AdminFuncCond cond) {
		return dao.queryCount(cond);
	}

	/**
	 * @param curFunc 当前拖动节点的 ID
	 * @param tarFunc 目标节点的 ID
	 * @param type    放置位置 （inner，before，after）
	 */
	@Transactional
	public void dragMove(AdminFunc curFunc, AdminFunc tarFunc, String type) {
		// before 放在目标节点前面
		/**
		 * 1.获得目标节点的父节点
		 * 2.把当前节点的父节点改为 目标节点的父节点
		 * 3.把当前节点移动到目标节点的前面
		 *     ？
		 *     当前节点的排序编号等于 目标排序编号，先不存库
		 *     父节点为目标的父节点ID的，并且编号大于 等于 目标节点的都加1
		 *     然后把当前节点的排序编号更新
		 */
		if (type.equals("before")) {//放 前面
			curFunc.setParent_id(tarFunc.getParent_id());
			curFunc.setOrder_num(tarFunc.getOrder_num());
			this.dao.updateOrderNo(curFunc);
			this.update(curFunc);
		}

		//after 放在目标节点的后面
		/**
		 * 1.获得目标节点的父节点
		 * 2.把当前节点的父节点改为 目标节点的父节点
		 * 3.把当前节点移动到目标节点的后面
		 *     ？
		 *     等于目标
		 */

		if (type.equals("after")) {//放后面
			curFunc.setParent_id(tarFunc.getParent_id());
			curFunc.setOrder_num((short) (tarFunc.getOrder_num() + 1));
			this.dao.updateOrderNoAfter(tarFunc);
			this.update(curFunc);
		}
		// inner 放在目标节点里面
		/**
		 * 吧当前节点的父节点ID 改为目标节点的ID ,修改 排序编号
		 */

		if (type.equals("inner")) {
			curFunc.setParent_id(tarFunc.getFunc_id());
			this.update(curFunc);
		}

	}
}