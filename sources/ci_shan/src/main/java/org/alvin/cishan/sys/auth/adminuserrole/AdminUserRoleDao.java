package org.alvin.cishan.sys.auth.adminuserrole;

import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明:用户角色数据访问层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Repository
public class AdminUserRoleDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public AdminUserRoleDao() {
		select.append("SELECT t.id,t.user_id,t.role_id");
		select.append(" FROM admin_user_role t WHERE 1=1");

		insert.append("INSERT INTO admin_user_role (user_id,role_id)");
		insert.append(" VALUES (:user_id,:role_id)");
	}

	/**
	 * @方法说明:批量插入用户角色记录
	 **/
	public int[] insertBatch(List<AdminUserRole> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除用户角色记录(多条)
	 **/
	public int delete(Integer user_id) {
		String sql = "DELETE FROM admin_user_role WHERE user_id=? ";
		return jdbcTemplate.update(sql, new Object[] { user_id });
	}

	/**
	 * @方法说明:按条件查询不分页用户角色列表
	 **/
	public List<AdminUserRole> queryList(AdminUserRoleCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY operate_time DESC");
		logger.info(SqlUtil.showSql(sb.toString(), cond.getArray()));
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(AdminUserRole.class));
	}

}