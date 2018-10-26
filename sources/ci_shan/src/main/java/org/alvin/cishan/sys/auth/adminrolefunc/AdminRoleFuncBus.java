package org.alvin.cishan.sys.auth.adminrolefunc;

import org.alvin.cishan.sys.auth.adminfunc.AdminFunc;
import org.alvin.cishan.sys.auth.adminfunc.AdminFuncCond;
import org.alvin.cishan.sys.auth.adminfunc.AdminFuncDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @类说明:角色菜单(功能权限)业务逻辑层
 * @author:唐植超
 * @date:2018-08-10 09:21:48
 **/
@Service
public class AdminRoleFuncBus {
	//	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminRoleFuncDao dao; // 注入角色菜单(功能权限)数据访问层

	@Autowired
	private AdminFuncDao adminFuncDao; // 注入菜单数据访问层

	/**
	 * @方法说明:按条件查询分页角色菜单(功能权限)列表
	 **/
	public List<AdminRoleFunc> queryList(AdminRoleFuncCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:
	 **/
	@Transactional
	public int[] insertBatch(List<AdminRoleFunc> list, int role_id) {
		dao.deleteByRoleId(role_id);// 物理删除
		return dao.insertBatch(list);
	}

	/**
	 * @方法说明:某一用户所拥有的相关菜单
	 **/
	public List<AdminFunc> queryFunc(Integer user_id) {
		if (user_id.equals(1)) {
			return adminFuncDao.queryList(AdminFuncCond.builder().func_id(1).build());
		}
		return dao.queryFunc(user_id);
	}

}