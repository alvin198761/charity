package org.alvin.cishan.sys.auth.adminrole;

import org.alvin.cishan.sys.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @类说明:角色业务逻辑层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Service
public class AdminRoleBus {
	//	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminRoleDao dao; // 注入角色数据访问层
	/**
	 * @方法说明:新增角色记录
	 **/
	@Transactional
	public int save(AdminRole adminRole) {
		return dao.save(adminRole);
	}

	/**
	 * @方法说明:删除角色记录(多条)
	 **/
	@Transactional
	public int delete(Integer ids[]) {
		return dao.delete(ids);// 物理删除
	}

	/**
	 * @方法说明:更新角色记录
	 **/
	@Transactional
	public int update(AdminRole adminRole) {
		return dao.update(adminRole);
	}

	/**
	 * @方法说明:按条件查询分页角色列表
	 **/
	public Page<AdminRole> queryPage(AdminRoleCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页角色列表
	 **/
	public List<AdminRole> queryList(AdminRoleCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询角色记录个数
	 **/
	public long queryCount(AdminRoleCond cond) {
		return dao.queryCount(cond);
	}


}