package org.alvin.cishan.sys.service.charity;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明: 慈善方--数据访问层
 * @author: 唐植超
 * @date : 2018-10-20 11:34:30
 **/
@Repository
public class CharityDao extends BaseDao {

	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明： 构造方法, 用于拼加SQL及初始化工作
	 */
	public CharityDao() {
		insert.append("INSERT INTO charity (type,charity_name,phone_no,gender,address,create_date,author,remark,p_id,");
		insert.append("category,status) ");
		insert.append(" VALUES (:type,:charity_name,:phone_no,:gender,:address,:create_date,:author,:remark,:p_id,");
		insert.append(":category,:status)");
	}

	/**
	 * @方法说明： 新增慈善方记录
	 */
	public int save(Charity vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO charity (id,type,charity_name,phone_no,gender,address,create_date,author,remark,p_id,");
		sql.append("category,status)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ");
		Object[] params = {vo.getId(), vo.getType(), vo.getCharity_name(), vo.getPhone_no(), vo.getGender(), vo.getAddress(), vo.getCreate_date(), vo.getAuthor(), vo.getRemark(), vo.getP_id(), vo.getCategory(), vo.getStatus()};
		//logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：新增慈善方记录并返回自增涨主键值
	 */
	public long saveReturnPK(Charity vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明：批量插入慈善方记录
	 */
	public int[] insertBatch(List<Charity> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明：物理删除慈善方记录(多条)
	 */
	public int delete(Long ids[]) {
		String sql = "DELETE FROM charity WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：更新慈善方记录
	 */
	public int update(Charity vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE charity SET type=?,charity_name=?,phone_no=?,gender=?,address=?,create_date=?,author=?,remark=?,p_id=?,");
		sql.append("category=?,status=? ");
		sql.append(" WHERE id=? ");
		Object[] params = {vo.getType(), vo.getCharity_name(), vo.getPhone_no(), vo.getGender(), vo.getAddress(), vo.getCreate_date(), vo.getAuthor(), vo.getRemark(), vo.getP_id(), vo.getCategory(), vo.getStatus(), vo.getId()};
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明：按条件查询分页慈善方列表
	 */
	public Page<Charity> queryPage(CharityCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(cond));
		sb.append(" FROM charity t WHERE 1=1");
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Charity.class);
	}

	/**
	 * @方法说明：按条件查询不分页慈善方列表
	 */
	public List<Charity> queryList(CharityCond cond) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(cond));
		sb.append(" FROM charity t WHERE 1=1");
		sb.append(cond.getCondition());
		//sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Charity.class));
	}

	/**
	 * @方法说明：按ID查找单个慈善方实体
	 */
	public Charity findById(Long id) {
		StringBuilder sb = new StringBuilder("SELECT ");
		sb.append(this.getSelectedItems(null));
		sb.append(" FROM charity t WHERE 1=1");
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Charity.class));
	}

	/**
	 * @方法说明：按条件查询慈善方记录个数
	 */
	public long queryCount(CharityCond cond) {
		String countSql = "SELECT COUNT(1) FROM charity t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明：按条件查询慈善方记录个数
	 */
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE charity SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明：查询参数定制
	 */
	public String getSelectedItems(CharityCond cond) {
		if (cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()) {
			return "t.id,t.type,t.charity_name,t.phone_no,t.gender,t.address,t.create_date,t.author,t.remark,t.p_id,t.category,t.status"; //默认所有字段
		}
		return Joiner.on(",").join(cond.getSelectedFields());
	}
}