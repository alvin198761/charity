package org.alvin.cishan.sys.auth.admindept;


import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明:部门数据访问层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Repository
public class AdminDeptDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public AdminDeptDao() {
		select.append("SELECT t.dept_id,t.parent_id,t.name,t.enable,t.remark,p.name parent_name");
		select.append(" FROM admin_dept t");
		select.append(" LEFT JOIN admin_dept p on t.parent_id=p.dept_id");
		select.append(" WHERE 1=1");

		insert.append("INSERT INTO admin_dept (parent_id,name,enable,remark)");
		insert.append(" VALUES (:parent_id,:name,:enable,:remark)");
	}

	/**
	 * @方法说明:新增部门记录
	 **/
	public int save(AdminDept vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO admin_dept (dept_id,parent_id,name,enable,remark)");
		sql.append(" VALUES (?,?,?,?,?) ");
		Object[] params = { vo.getDept_id(), vo.getParent_id(), vo.getName(), vo.getEnable(), vo.getRemark() };
		// logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:物理删除部门记录(多条)
	 **/
	public int delete(Integer ids[]) {
		String sql = "DELETE FROM admin_dept WHERE dept_id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:更新部门记录
	 **/
	public int update(AdminDept vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE admin_dept SET parent_id=?,name=?,enable=?,remark=?");
		sql.append(" WHERE dept_id=? ");
		Object[] params = { vo.getParent_id(), vo.getName(), vo.getEnable(), vo.getRemark(), vo.getDept_id() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:按条件查询不分页部门列表
	 **/
	public List<AdminDept> queryList(AdminDeptCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		sb.append(" ORDER BY t.dept_id ");
		logger.info(SqlUtil.showSql(sb.toString(), cond.getArray()));// 显示SQL语句
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(AdminDept.class));
	}

	/**
	 * @方法说明:按条件查询部门记录个数
	 **/
	public long queryCount(AdminDeptCond cond) {
		String countSql = "SELECT COUNT(1) FROM admin_dept t WHERE 1=1" + cond.getCondition();
		logger.info(SqlUtil.showSql(countSql, cond.getArray()));// 显示SQL语句
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

}