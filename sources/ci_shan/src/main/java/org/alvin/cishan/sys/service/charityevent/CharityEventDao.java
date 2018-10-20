package org.alvin.cishan.sys.service.charityevent;
import java.util.List;

import org.alvin.cishan.common.BaseDao;
import org.alvin.cishan.sys.util.Page;
import org.alvin.cishan.sys.util.SqlUtil;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.google.common.base.Joiner;
/**
* @类说明: 善行活动--数据访问层
* @author: 唐植超
* @date : 2018-10-20 11:58:00
**/
@Repository
public class CharityEventDao extends BaseDao {

    private StringBuilder insert = new StringBuilder();

    /**
    * @方法说明：  构造方法,用于拼加SQL及初始化工作
    */
    public CharityEventDao () {
        insert.append("INSERT INTO charity_event (type,name,content,chairman_id,target_id,event_time,author,remark,chairmain_pnone,"); 
		insert.append("target_phone,status,create_time) ");
        insert.append(" VALUES (:type,:name,:content,:chairman_id,:target_id,:event_time,:author,:remark,:chairmain_pnone,"); 
		insert.append(":target_phone,:status,:create_time)");
    }

    /**
    * @方法说明：  新增善行活动记录
    */
    public int save(CharityEvent vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO charity_event (id,type,name,content,chairman_id,target_id,event_time,author,remark,chairmain_pnone,"); 
		sql.append("target_phone,status,create_time)");
        sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ");
        Object[] params ={ vo.getId(),vo.getType(),vo.getName(),vo.getContent(),vo.getChairman_id(),vo.getTarget_id(),vo.getEvent_time(),vo.getAuthor(),vo.getRemark(),vo.getChairmain_pnone(),vo.getTarget_phone(),vo.getStatus(),vo.getCreate_time() };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
    * @方法说明：新增善行活动记录并返回自增涨主键值
    */
    public long saveReturnPK(CharityEvent vo) {
         return saveKey(vo, insert.toString(), "id");
    }
    
    /**
    * @方法说明：批量插入善行活动记录
    */
    public int[] insertBatch(List<CharityEvent> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
    * @方法说明：物理删除善行活动记录(多条)
    */
    public int delete(Long ids[]) {
        String sql = "DELETE FROM charity_event WHERE id" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
    * @方法说明：更新善行活动记录
    */
    public int update(CharityEvent vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE charity_event SET type=?,name=?,content=?,chairman_id=?,target_id=?,event_time=?,author=?,remark=?,chairmain_pnone=?,"); 
		sql.append("target_phone=?,status=?,create_time=? ");
        sql.append(" WHERE id=? ");
        Object[] params = {vo.getType(),vo.getName(),vo.getContent(),vo.getChairman_id(),vo.getTarget_id(),vo.getEvent_time(),vo.getAuthor(),vo.getRemark(),vo.getChairmain_pnone(),vo.getTarget_phone(),vo.getStatus(),vo.getCreate_time(),vo.getId()};
        return jdbcTemplate.update(sql.toString(), params);
      }

        /**
        * @方法说明：按条件查询分页善行活动列表
        */
    public Page<CharityEvent> queryPage(CharityEventCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM charity_event t WHERE 1=1");
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, CharityEvent.class);
    }
    
    /**
    * @方法说明：按条件查询不分页善行活动列表
    */
    public List<CharityEvent> queryList(CharityEventCond cond) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(cond));
        sb.append(" FROM charity_event t WHERE 1=1");
    	sb.append(cond.getCondition());
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(CharityEvent.class));
    }
    
    /**
    * @方法说明：按ID查找单个善行活动实体
    */
    public CharityEvent findById(Long id) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(this.getSelectedItems(null));
        sb.append(" FROM charity_event t WHERE 1=1");
    	sb.append(" AND t.id=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(CharityEvent.class));
    }
    
    /**
    * @方法说明：按条件查询善行活动记录个数
    */
    public long queryCount(CharityEventCond cond) {
    	String countSql = "SELECT COUNT(1) FROM charity_event t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
    * @方法说明：按条件查询善行活动记录个数
    */
    public int deleteLogic(Long ids[]) {
    	String sql = "UPDATE charity_event SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }

    /**
    * @方法说明：查询参数定制
    */
    public String getSelectedItems(CharityEventCond cond){
        if(cond == null || cond.getSelectedFields() == null || cond.getSelectedFields().isEmpty()){
        return "t.id,t.type,t.name,t.content,t.chairman_id,t.target_id,t.event_time,t.author,t.remark,t.chairmain_pnone,t.target_phone,t.status,t.create_time"; //默认所有字段
        }
        return Joiner.on(",").join(cond.getSelectedFields());
    }
}