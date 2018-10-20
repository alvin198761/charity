package org.alvin.cishan.sys.auth.adminfunc;

import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明:菜单数据访问层
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Repository
public class AdminFuncDao extends BaseDao {
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public AdminFuncDao() {
		select.append("SELECT t.func_id,t.type,t.color,t.icon,t.name,t.parent_id,t.tip,t.remark,t.status,t.url,");
		select.append("t.order_num,p.name parent_name");
		select.append(" FROM admin_func t LEFT JOIN admin_func p on t.parent_id=p.func_id  WHERE 1=1");

		insert.append("INSERT INTO admin_func (type,color,icon,name,parent_id,tip,remark,status,url,");
		insert.append("order_num)");
		insert.append(" VALUES (:type,:color,:icon,:name,:parent_id,:tip,:remark,:status,:url,");
		insert.append(":order_num)");
	}

	/**
	 * @方法说明:新增菜单记录
	 **/
	public int save(AdminFunc vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO admin_func (func_id,type,color,icon,name,parent_id,tip,remark,status,url,");
		sql.append("order_num)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?) ");
		Object[] params = { vo.getFunc_id(), vo.getType(), vo.getColor(), vo.getIcon(), vo.getName(), vo.getParent_id(), vo.getTip(), vo.getRemark(), vo.getStatus(), vo.getUrl(), vo.getOrder_num() };

		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:物理删除菜单记录(多条)
	 **/
	public int delete(Integer ids[]) {
		String sql = "DELETE FROM admin_func WHERE func_id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:更新菜单记录
	 **/
	public int update(AdminFunc vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE admin_func SET type=?,color=?,icon=?,name=?,parent_id=?,tip=?,remark=?,status=?,url=?,");
		sql.append("order_num=?");
		sql.append(" WHERE func_id=? ");
		Object[] params = { vo.getType(), vo.getColor(), vo.getIcon(), vo.getName(), vo.getParent_id(), vo.getTip(), vo.getRemark(), vo.getStatus(), vo.getUrl(), vo.getOrder_num(), vo.getFunc_id() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:按条件查询不分页菜单列表
	 **/
	public List<AdminFunc> queryList(AdminFuncCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		sb.append(" ORDER BY func_id");
		logger.info(SqlUtil.showSql(sb.toString(), cond.getArray()));// 显示SQL语句
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(AdminFunc.class));
	}

	/**
	 * @方法说明:按条件查询菜单记录个数
	 **/
	public long queryCount(AdminFuncCond cond) {
		String countSql = "SELECT COUNT(1) FROM admin_func t WHERE 1=1" + cond.getCondition();
		logger.info(SqlUtil.showSql(countSql.toString(), cond.getArray()));
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:修改菜单顺序（before）
	 **/
	public int updateOrderNo(AdminFunc vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE admin_func SET order_num = order_num + 1 ");
		sql.append(" WHERE order_num >=? AND parent_id =?");
		Object[] params = { vo.getOrder_num(),vo.getParent_id() };
		return jdbcTemplate.update(sql.toString(), params);
	}
	/**
	 * @方法说明:更新菜单顺序(after)
	 **/
	public int updateOrderNoAfter(AdminFunc vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE admin_func SET order_num = order_num + 1 ");
		sql.append(" WHERE order_num >? AND parent_id =?");
		Object[] params = { vo.getOrder_num(),vo.getParent_id() };
		return jdbcTemplate.update(sql.toString(), params);
	}
}