package org.alvin.cishan.sys.auth.adminrolefunc;

import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.auth.adminfunc.AdminFunc;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明:角色菜单(功能权限)数据访问层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Repository
public class AdminRoleFuncDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public AdminRoleFuncDao() {
		select.append("SELECT t.id,t.role_id,t.user_id");
		select.append(" FROM admin_role_func t WHERE 1=1");

		insert.append("INSERT INTO admin_role_func (role_id,user_id)");
		insert.append(" VALUES (:role_id,:user_id)");
	}

	/**
	 * @方法说明:批量插入角色菜单(功能权限)记录
	 **/
	public int[] insertBatch(List<AdminRoleFunc> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:按条件查询不分页角色菜单(功能权限)列表
	 **/
	public List<AdminRoleFunc> queryList(AdminRoleFuncCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(AdminRoleFunc.class));
	}

	/**
	 * @方法说明:物理删除角色菜单记录(多条)
	 **/
	public int deleteByRoleId(Integer role_id) {
		String sql = "DELETE FROM admin_role_func WHERE role_id=? ";
		return jdbcTemplate.update(sql, new Object[] { role_id });
	}

	/**
	 * @方法说明:某一用户所拥有的相关菜单
	 **/
	public List<AdminFunc> queryFunc(Integer user_id) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT DISTINCT af.func_id,af.type,af.color,af.icon,af.name,af.parent_id,af.tip,af.remark,");
		sb.append(" af.status,af.url,af.order_num ");
		sb.append(" FROM admin_user_role t JOIN admin_role_func f ON t.role_id=f.role_id ");
		sb.append(" JOIN admin_func af ON af.func_id = f.user_id  ");
		sb.append(" WHERE t.user_id = ? ");
		sb.append(" AND af.func_id<>1");
		sb.append(" ORDER BY af.func_id");
		logger.info(SqlUtil.showSql(sb.toString(), new Object[] { user_id }));
		return jdbcTemplate.query(sb.toString(), new Object[] { user_id }, new BeanPropertyRowMapper<>(AdminFunc.class));
	}
}