package org.alvin.cishan.sys.auth.adminsysuser;

import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明:用户数据访问层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Repository
public class AdminSysUserDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public AdminSysUserDao() {
		select.append("SELECT t.user_id,t.name,t.password,t.phone,t.gender,t.status,t.department_id,t.wechat,t.remark,d.name dept_name ");
		select.append(" FROM admin_sys_user t ");
		select.append(" left join admin_dept d ON t.department_id=d.dept_id WHERE 1=1 ");

		insert.append("INSERT INTO admin_sys_user (name,password,phone,gender,status,department_id,wechat,remark)");
		insert.append(" VALUES (:name,:password,:phone,:gender,:status,:department_id,:wechat,:remark)");
	}

	/**
	 * @方法说明:新增用户记录
	 **/
	public int save(AdminSysUser vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO admin_sys_user (user_id,name,password,phone,gender,status,department_id,wechat,remark)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
		Object[] params = { vo.getUser_id(), vo.getName(), vo.getPassword(), vo.getPhone(), vo.getGender(), vo.getStatus(), vo.getDepartment_id(), vo.getWechat(), vo.getRemark() };
		// logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:新增用户记录并返回自增涨主键值
	 **/
	public long saveReturnPK(AdminSysUser vo) {
		return saveKey(vo, insert.toString(), "user_id");
	}

	/**
	 * @方法说明:批量插入用户记录
	 **/
	public int[] insertBatch(List<AdminSysUser> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除用户记录(多条)
	 **/
	public int delete(Integer ids[]) {
		String sql = "DELETE FROM admin_sys_user WHERE user_id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:更新用户记录
	 **/
	public int update(AdminSysUser vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE admin_sys_user SET name=?,password=?,phone=?,gender=?,status=?,department_id=?,wechat=?,remark=?");
		sql.append(" WHERE user_id=? ");
		Object[] params = { vo.getName(), vo.getPassword(), vo.getPhone(), vo.getGender(), vo.getStatus(), vo.getDepartment_id(), vo.getWechat(), vo.getRemark(), vo.getUser_id() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:按条件查询分页用户列表
	 **/
	public Page<AdminSysUser> queryPage(AdminSysUserCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		sb.append(" order by user_id desc ");
		// sb.append(cond.getOrderSql());//增加排序子句;
		logger.info(SqlUtil.showSql(sb.toString(), cond.getArray()));// 显示SQL语句
		return queryPage(sb.toString(), cond, AdminSysUser.class);
	}

	/**
	 * @方法说明:按条件查询用户记录个数
	 **/
	public long queryCount(AdminSysUserCond cond) {
		String countSql = "SELECT COUNT(1) FROM admin_sys_user t WHERE 1=1" + cond.getCondition();
		logger.info(SqlUtil.showSql(countSql, cond.getArray()));// 显示SQL语句
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:按ID查找单个用户实体
	 **/
	public AdminSysUser findById(Integer id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.user_id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[] { id }, new BeanPropertyRowMapper<>(AdminSysUser.class));
	}

	/**
	 * @方法说明:按条件查询不分页用户列表
	 **/
	public List<AdminSysUser> queryList(AdminSysUserCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		sb.append(" order by user_id desc ");
		// sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(AdminSysUser.class));
	}

	/**
	 * @方法说明:逻辑删除用户记录(多条)
	 **/
	// public int deleteLogic(Integer ids[]) {
	// String sql = "UPDATE admin_sys_user SET delete_remark=1 WHERE user_id" +
	// SqlUtil.ArrayToIn(ids);
	// return jdbcTemplate.update(sql);
	// }

	/**
	 * @方法说明:重置密码
	 **/
	public int updatePassword(Integer user_id, String password) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE admin_sys_user SET password=? WHERE user_id=?");
		Object[] params = { password, user_id };
		return jdbcTemplate.update(sql.toString(), params);
	}
}