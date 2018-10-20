package org.alvin.cishan.sys.service.dict;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* @类说明: 类型字典--数据访问层
* @author: 唐植超
* @date : 2018-10-20 11:34:30
**/
@Repository
public class DictDao extends BaseDao {

    private StringBuilder insert = new StringBuilder();

    /**
     * @方法说明：  构造方法,用于拼加SQL及初始化工作
     */
    public DictDao () {
        insert.append("INSERT INTO type_dict (type,name) ");
        insert.append(" VALUES (:type,:name)");
    }

    /**
     * @方法说明：  新增类型字典记录
     */
    public int save(Dict vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO type_dict (id,type,name)");
        sql.append(" VALUES (?,?,?) ");
        Object[] params ={ vo.getId(),vo.getType(),vo.getName() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }

    /**
     * @方法说明：新增类型字典记录并返回自增涨主键值
     */
    public long saveReturnPK(Dict vo) {
        return saveKey(vo, insert.toString(), "id");
    }

    /**
     * @方法说明：批量插入类型字典记录
     */
    public int[] insertBatch(List<Dict> list) {
        return batchOperate(list, insert.toString());
    }

    /**
     * @方法说明：物理删除类型字典记录(多条)
     */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM type_dict WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }

    /**
     * @方法说明：更新类型字典记录
     */
    public int update(Dict vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE type_dict SET type=?,name=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getType(),vo.getName(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
    }

    /**
     * @方法说明：按条件查询分页类型字典列表
     */
    public Page<Dict> queryPage(DictCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM type_dict t WHERE 1=1");
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, Dict.class);
    }

    /**
     * @方法说明：按条件查询不分页类型字典列表
     */
    public List<Dict> queryList(DictCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM type_dict t WHERE 1=1");
        sb.append(cond.getCondition());
        //sb.append(" ORDER BY operate_time DESC");
        return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Dict.class));
    }

    /**
     * @方法说明：按ID查找单个类型字典实体
     */
    public Dict findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM type_dict t WHERE 1=1");
        sb.append(" AND t.id=?");
        return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Dict.class));
    }

    /**
     * @方法说明：按条件查询类型字典记录个数
     */
    public long queryCount(DictCond cond) {
        String countSql = "SELECT COUNT(1) FROM type_dict t WHERE 1=1" + cond.getCondition();
        return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }

    /**
     * @方法说明：按条件查询类型字典记录个数
     */
    public int deleteLogic(Long ids[]) {
        String sql = "UPDATE type_dict SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }

    /**
     * @方法说明：查询参数定制
     */
    public String getSelectedItems(DictCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
            return "t.id,t.type,t.name"; //默认所有字段
        }
        return Joiner.on(",").join(cond.getSelectedFields());
    }
}