package org.alvin.cishan.sys.auth.adminroledept;

import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.auth.admindept.AdminDept;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明:角色部门数据访问层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Repository
public class AdminRoleDeptDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public AdminRoleDeptDao() {
		select.append("SELECT t.id,t.role_id,t.dept_id");
		select.append(" FROM admin_role_dept t WHERE 1=1");

		insert.append("INSERT INTO admin_role_dept (role_id,dept_id)");
		insert.append(" VALUES (:role_id,:dept_id)");
	}

	/**
	 * @方法说明:批量插入角色部门记录
	 **/
	public int[] insertBatch(List<AdminRoleDept> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除角色部门记录(多条)
	 **/
	public int delete(Integer role_id) {
		String sql = "DELETE FROM admin_role_dept WHERE role_id=?";
		return jdbcTemplate.update(sql, new Object[] { role_id });
	}

	/**
	 * @方法说明:按条件查询不分页角色部门列表
	 **/
	public List<AdminRoleDept> queryList(AdminRoleDeptCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(AdminRoleDept.class));
	}

	/**
	 * @方法说明:按人员主键查询数据授权信息
	 **/
	public List<AdminDept> queryAuthDepts(Integer user_id) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT t.dept_id,t.parent_id,t.name,t.enable,t.remark");
		sb.append(" FROM admin_user_role ur");
		sb.append(" JOIN admin_role_dept rd ON ur.role_id = rd.role_id");
		sb.append(" JOIN admin_dept t ON t.dept_id = rd.dept_id");
		sb.append(" WHERE ur.user_id=?");
		return jdbcTemplate.query(sb.toString(), new Object[] { user_id }, new BeanPropertyRowMapper<>(AdminDept.class));
	}
}