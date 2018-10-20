package org.alvin.cishan.sys.service.dict;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @类说明: 类型字典--数据逻辑层
* @author: 唐植超
* @date : 2018-10-20 11:34:30
**/
@Service
public class DictService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private DictDao dao; //注入类型字典数据访问层

	/**
	* @方法说明：  新增[类型字典]记录
	*/
	@Transactional
	public int save(Dict dict) {
			return dao.save(dict);
	}

	/**
	* @方法说明：  删除类型字典记录(多条)
	*/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	* @方法说明：  更新类型字典记录
	*/
	@Transactional
	public int update(Dict dict) {
		return dao.update(dict); 
	}

	/**
	* @方法说明： 按条件查询分页类型字典列表
	*/
	public Page<Dict> queryPage(DictCond cond) {
		return dao.queryPage(cond);
	}

	/**
	* @方法说明： 按条件查询不分页类型字典列表(使用范型)
	*/
	public List<Dict> queryList(DictCond cond) {
		return dao.queryList(cond);
	}

	/**
	* @方法说明： 按ID查找单个类型字典记录
	*/
	public Dict findById(Long id) {
		return dao.findById(id);
	}

	/**
	* @方法说明： 按条件查询类型字典记录个数
	*/
	public long queryCount(DictCond cond) {
		return dao.queryCount(cond);
	}
}