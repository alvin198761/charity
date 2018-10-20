package org.alvin.cishan.sys.auth.admindept;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @类说明:部门数据逻辑层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Service
public class AdminDeptBus {
	//	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminDeptDao dao; // 注入部门数据访问层

	/**
	 * @方法说明:新增部门记录
	 **/
	@Transactional
	public int save(AdminDept adminDept) {
		return dao.save(adminDept);
	}

	/**
	 * @方法说明:删除部门记录(多条)
	 **/
	@Transactional
	public int delete(Integer ids[]) {
		// return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);// 物理删除
	}

	/**
	 * @方法说明:更新部门记录
	 **/
	@Transactional
	public int update(AdminDept adminDept) {
		return dao.update(adminDept);
	}

	/**
	 * @方法说明:按条件查询不分页部门列表(使用范型)
	 **/
	public List<AdminDept> queryList(AdminDeptCond cond) {
		List<AdminDept> list = dao.queryList(cond);
		Map<Integer, AdminDept> treeMap = Maps.newHashMap();// 用map来定位上级结点
		List<AdminDept> treeList = Lists.newArrayList();// 最终返回的treelist
		for (AdminDept dept : list) {
			treeMap.put(dept.getDept_id(), dept);// 将vo放到map中
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
	 * @方法说明:按条件查询部门记录个数
	 **/
	public long queryCount(AdminDeptCond cond) {
		return dao.queryCount(cond);
	}
}