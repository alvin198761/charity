package org.alvin.cishan.sys.auth.adminsysuser;

import org.alvin.cishan.sys.util.MD5Util;
import org.alvin.cishan.sys.util.Page;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @类说明:用户业务逻辑层
 * @author:唐植超
 * @date:2018-08-10 09:21:48
 **/
@Service
public class AdminSysUserBus implements InitializingBean {
	// private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AdminSysUserDao dao; // 注入用户数据访问层

	/**
	 * @方法说明:新增用户记录
	 **/
	@Transactional
	public int save(AdminSysUser adminSysUser) {
		return dao.save(adminSysUser);
	}

	/**
	 * @方法说明:删除用户记录(多条)
	 **/
	@Transactional
	public int delete(Integer ids[]) {
		return dao.delete(ids);// 物理删除
	}

	/**
	 * @方法说明:更新用户记录
	 **/
	@Transactional
	public int update(AdminSysUser adminSysUser) {
		return dao.update(adminSysUser);
	}

	/**
	 * @方法说明:按条件查询分页用户列表
	 **/
	public Page<AdminSysUser> queryPage(AdminSysUserCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明:按条件查询不分页用户列表
	 **/
	public List<AdminSysUser> queryList(AdminSysUserCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明:按条件查询用户记录个数
	 **/
	public long queryCount(AdminSysUserCond cond) {
		return dao.queryCount(cond);
	}

	/**
	 * @方法说明:按主键查找单个用户记录
	 **/
	public AdminSysUser findById(Integer id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:重置用户密码
	 **/
	@Transactional
	public int updatePassword(Integer user_id, String password) {
		return dao.updatePassword(user_id, MD5Util.getMD5(password));
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		AdminSysUserCond cond = new AdminSysUserCond();
		if (this.dao.queryCount(cond) == 0) {
			AdminSysUser adminSysUser = new AdminSysUser();
			adminSysUser.setName("admin");
			adminSysUser.setPassword(MD5Util.getMD5("admin"));
			adminSysUser.setGender((byte) 1);
			adminSysUser.setStatus((byte) 1);
			this.save(adminSysUser);
		}
	}
}