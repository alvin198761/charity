package org.alvin.cishan.sys.auth.adminrole;


import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明:角色数据访问层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Repository
public class AdminRoleDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public AdminRoleDao() {
		select.append("SELECT t.id,t.name,t.remark");
		select.append(" FROM admin_role t WHERE 1=1");

		insert.append("INSERT INTO admin_role (name,remark)");
		insert.append(" VALUES (:name,:remark)");
	}

	/**
	 * @方法说明:新增角色记录
	 **/
	public int save(AdminRole vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO admin_role (id,name,remark)");
		sql.append(" VALUES (?,?,?) ");
		Object[] params = { vo.getId(), vo.getName(), vo.getRemark() };
		// logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:物理删除角色记录(多条)
	 **/
	public int delete(Integer ids[]) {
		String sql = "DELETE FROM admin_role WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:更新角色记录
	 **/
	public int update(AdminRole vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE admin_role SET name=?,remark=?");
		sql.append(" WHERE id=? ");
		Object[] params = { vo.getName(), vo.getRemark(), vo.getId() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:按条件查询分页角色列表
	 **/
	public Page<AdminRole> queryPage(AdminRoleCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(cond.getOrderSql());//增加排序子句;
		logger.info(SqlUtil.showSql(sb.toString(), cond.getArray()));// 显示SQL语句
		return queryPage(sb.toString(), cond, AdminRole.class);
	}

	/**
	 * @方法说明:按条件查询角色记录个数
	 **/
	public long queryCount(AdminRoleCond cond) {
		String countSql = "SELECT COUNT(1) FROM admin_role t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:按条件查询不分页角色列表
	 **/
	public List<AdminRole> queryList(AdminRoleCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(AdminRole.class));
	}

}