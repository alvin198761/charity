package org.alvin.cishan.sys.service.charityevent;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 善行活动--数据逻辑层
* @author: 唐植超
* @date : 2018-10-20 11:58:00
**/
@Service
public class CharityEventService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CharityEventDao dao; //注入善行活动数据访问层

	/**
	* @方法说明：  新增[善行活动]记录
	*/
	@Transactional
	public int save(CharityEvent charityEvent) {
			return dao.save(charityEvent);
	}

	/**
	* @方法说明：  删除善行活动记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新善行活动记录
	*/
	@Transactional
	public int update(CharityEvent charityEvent) {
		return dao.update(charityEvent); 
	}

	/**
	* @方法说明： 按条件查询分页善行活动列表
	*/
	public Page<CharityEvent> queryPage(CharityEventCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页善行活动列表(使用范型)
	*/
	public List<CharityEvent> queryList(CharityEventCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个善行活动记录
	*/
	public CharityEvent findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询善行活动记录个数
	*/
	public long queryCount(CharityEventCond cond) {
		return dao.queryCount(cond);
	}
}