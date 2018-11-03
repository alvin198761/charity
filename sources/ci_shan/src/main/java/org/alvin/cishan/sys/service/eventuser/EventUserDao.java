package org.alvin.cishan.sys.service.eventuser;

import com.google.common.base.Joiner;
import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* @类说明: 活动参与方--数据访问层
* @author: 高振中
* @date : 2018-10-30 11:50:24
**/
@Repository
public class EventUserDao extends BaseDao {

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public EventUserDao () {
        insert.append("INSERT INTO event_user (event_id,user_id) ");
        insert.append(" VALUES (:event_id,:user_id)");
    }

    /**
    * @方法说明：  新增活动参与方记录
    */
    public int save(EventUser vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO event_user (id,event_id,user_id)");
        sql.append(" VALUES (?,?,?) ");
        Object[] params ={ vo.getId(),vo.getEvent_id(),vo.getUser_id() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增活动参与方记录并返回自增涨主键值
    */
    public long saveReturnPK(EventUser vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入活动参与方记录
    */
    public int[] insertBatch(List<EventUser> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除活动参与方记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM event_user WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新活动参与方记录
    */
    public int update(EventUser vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE event_user SET event_id=?,user_id=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getEvent_id(),vo.getUser_id(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页活动参与方列表
        */
    public Page<EventUser> queryPage(EventUserCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM event_user t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
        sb.append(cond.getCondition());
        sb.append(" order by id desc");
        //sb.append(cond.getOrderSql());//增加排序子句;
        logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, EventUser.class);
    }
    
    /**
    * @方法说明：按条件查询不分页活动参与方列表
    */
    public List<EventUser> queryList(EventUserCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM event_user t ");
        sb.append(getJoinTables());
        sb.append(" WHERE 1=1 ");
    	sb.append(cond.getCondition());
    	sb.append(" order by id desc");
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(EventUser.class));
    }
    
    /**
    * @方法说明：按ID查找单个活动参与方实体
    */
    public EventUser findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM event_user t WHERE 1=1");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(EventUser.class));
    }
    
    /**
    * @方法说明：按条件查询活动参与方记录个数
    */
    public long queryCount(EventUserCond cond) {
    	String countSql = "SELECT COUNT(1) FROM event_user t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询活动参与方记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE event_user SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(EventUserCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append(" t.id,t.event_id,t.user_id, ");
            sb.append(" ce.`name` as event_name ,c.charity_name as join_user_name ");
            return sb.toString();
        }
        return Joiner.on(",").join(cond.getSelectedFields());
    }

    /**
     * @return
     * @方法说明：表连接代码
     */
    public String getJoinTables() {
        StringBuilder sb = new StringBuilder();
        sb.append(" LEFT JOIN charity_event ce on t.event_id = ce.id ");
        sb.append(" LEFT JOIN charity c on t.user_id = c.id and c.category = 1 ");
        return sb.toString();
    }
}