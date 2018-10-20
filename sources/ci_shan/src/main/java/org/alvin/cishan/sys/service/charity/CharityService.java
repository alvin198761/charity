package org.alvin.cishan.sys.service.charity;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 慈善方--数据逻辑层
* @author: 唐植超
* @date : 2018-10-20 11:34:30
**/
@Service
public class CharityService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CharityDao dao; //注入慈善方数据访问层

	/**
	* @方法说明：  新增[慈善方]记录
	*/
	@Transactional
	public int save(Charity charity) {
			return dao.save(charity);
	}

	/**
	* @方法说明：  删除慈善方记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新慈善方记录
	*/
	@Transactional
	public int update(Charity charity) {
		return dao.update(charity); 
	}

	/**
	* @方法说明： 按条件查询分页慈善方列表
	*/
	public Page<Charity> queryPage(CharityCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页慈善方列表(使用范型)
	*/
	public List<Charity> queryList(CharityCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个慈善方记录
	*/
	public Charity findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询慈善方记录个数
	*/
	public long queryCount(CharityCond cond) {
		return dao.queryCount(cond);
	}
}