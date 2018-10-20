package org.alvin.cishan.sys.service.category;

import java.util.List;

import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


import com.google.common.base.Joiner;

/**
 * @类说明: 捐助物资--数据访问层
 * @author: 唐植超
 * @date : 2018-10-20 11:34:30
 **/
@Repository
public class CategoryDao extends BaseDao {

	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明：  构造方法,用于拼加SQL及初始化工作
	 */
	public CategoryDao () {
		insert.append("INSERT INTO category (type,name,count,create_date,author,remark,c_id,status) ");
		insert.append(" VALUES (:type,:name,:count,:create_date,:author,:remark,:c_id,:status)");
	}

	/**
	 * @方法说明：  新增捐助物资记录
	 */
	public int save(Category vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO category (id,type,name,count,create_date,author,remark,c_id,status)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
		Object[] params ={ vo.getId(),vo.getType(),vo.getName(),vo.getCount(),vo.getCreate_date(),vo.getAuthor(),vo.getRemark(),vo.getC_id(),vo.getStatus() };
		//logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：新增捐助物资记录并返回自增涨主键值
	 */
	public long saveReturnPK(Category vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明：批量插入捐助物资记录
	 */
	public int[] insertBatch(List<Category> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明：物理删除捐助物资记录(多条)
	 */
	public int delete(Long ids[]) {
		String sql = "DELETE FROM category WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：更新捐助物资记录
	 */
	public int update(Category vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE category SET type=?,name=?,count=?,create_date=?,author=?,remark=?,c_id=?,status=? ");
		sql.append(" WHERE id=? ");
		Object[] params = {vo.getType(),vo.getName(),vo.getCount(),vo.getCreate_date(),vo.getAuthor(),vo.getRemark(),vo.getC_id(),vo.getStatus(),vo.getId()};
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：按条件查询分页捐助物资列表
	 */
	public Page<Category> queryPage(CategoryCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(cond));
		sb.append(" FROM category t WHERE 1=1");
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Category.class);
	}

	/**
	 * @方法说明：按条件查询不分页捐助物资列表
	 */
	public List<Category> queryList(CategoryCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(cond));
		sb.append(" FROM category t WHERE 1=1");
		sb.append(cond.getCondition());
		//sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Category.class));
	}

	/**
	 * @方法说明：按ID查找单个捐助物资实体
	 */
	public Category findById(Long id) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(null));
		sb.append(" FROM category t WHERE 1=1");
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Category.class));
	}

	/**
	 * @方法说明：按条件查询捐助物资记录个数
	 */
	public long queryCount(CategoryCond cond) {
		String countSql = "SELECT COUNT(1) FROM category t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明：按条件查询捐助物资记录个数
	 */
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE category SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：查询参数定制
	 */
	public String getSelectedItems(CategoryCond cond){
		if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
			return "t.id,t.type,t.name,t.count,t.create_date,t.author,t.remark,t.c_id,t.status"; //默认所有字段
		}
		return Joiner.on(",").join(cond.getSelectedFields());
	}
}