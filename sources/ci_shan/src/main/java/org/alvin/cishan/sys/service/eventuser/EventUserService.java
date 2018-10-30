package org.alvin.cishan.sys.service.eventuser;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 活动参与方--数据逻辑层
* @author: 高振中
* @date : 2018-10-30 11:50:24
**/
@Service
public class EventUserService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private EventUserDao dao; //注入活动参与方数据访问层

	/**
	* @方法说明：  新增[活动参与方]记录
	*/
	@Transactional
	public int save(EventUser eventUser) {
			return dao.save(eventUser);
	}

	/**
	* @方法说明：  删除活动参与方记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新活动参与方记录
	*/
	@Transactional
	public int update(EventUser eventUser) {
		return dao.update(eventUser); 
	}

	/**
	* @方法说明： 按条件查询分页活动参与方列表
	*/
	public Page<EventUser> queryPage(EventUserCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页活动参与方列表(使用范型)
	*/
	public List<EventUser> queryList(EventUserCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个活动参与方记录
	*/
	public EventUser findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询活动参与方记录个数
	*/
	public long queryCount(EventUserCond cond) {
		return dao.queryCount(cond);
	}
}