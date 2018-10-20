package org.alvin.cishan.sys.auth.adminuserrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @类说明:用户角色业务逻辑层
 * @author:唐植超
 * @date:2018-08-10 09:21:48
 **/
@Service
public class AdminUserRoleBus {
	//	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminUserRoleDao dao; // 注入用户角色数据访问层

	/**
	 * @方法说明:按条件查询分页用户角色列表
	 **/
	public int[] insertBatch(List<AdminUserRole> list, int user_id) {
		dao.delete(user_id);// 物理删除
		return dao.insertBatch(list);
	}

	/**
	 * @方法说明:按条件查询不分页用户角色列表
	 **/
	public List<AdminUserRole> queryList(AdminUserRoleCond cond) {
		return dao.queryList(cond);
	}

}