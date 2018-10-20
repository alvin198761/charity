package org.alvin.cishan.sys.auth.adminroledept;

import org.alvin.cishan.sys.auth.admindept.AdminDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @类说明:角色部门业务逻辑层
 * @author:唐植超
 * @date:2018-08-10 09:21:48
 **/
@Service
public class AdminRoleDeptBus {
	//	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminRoleDeptDao dao; // 注入角色部门数据访问层

	/**
	 * @方法说明:按条件查询不分页角色部门列表
	 **/
	public List<AdminRoleDept> queryList(AdminRoleDeptCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:删除角色部门记录
	 **/
	@Transactional
	public int[] insertBatch(List<AdminRoleDept> list, Integer role_id) {
		dao.delete(role_id);
		return dao.insertBatch(list);
	}

	/**
	 * @方法说明:按人员主键查询数据授权信息
	 **/
	public List<AdminDept> queryAuthDepts(Integer user_id) {
		return dao.queryAuthDepts(user_id);
	}
}