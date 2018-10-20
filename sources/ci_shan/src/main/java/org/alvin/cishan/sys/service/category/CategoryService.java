package org.alvin.cishan.sys.service.category;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @类说明: 捐助物资--数据逻辑层
 * @author: 唐植超
 * @date : 2018-10-20 11:34:30
 **/
@Service
public class CategoryService {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CategoryDao dao; //注入捐助物资数据访问层

	/**
	 * @方法说明： 新增[捐助物资]记录
	 */
	@Transactional
	public int save(Category category) {
		return dao.save(category);
	}

	/**
	 * @方法说明： 删除捐助物资记录(多条)
	 */
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明： 更新捐助物资记录
	 */
	@Transactional
	public int update(Category category) {
		return dao.update(category);
	}

	/**
	 * @方法说明： 按条件查询分页捐助物资列表
	 */
	public Page<Category> queryPage(CategoryCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明： 按条件查询不分页捐助物资列表(使用范型)
	 */
	public List<Category> queryList(CategoryCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明： 按ID查找单个捐助物资记录
	 */
	public Category findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明： 按条件查询捐助物资记录个数
	 */
	public long queryCount(CategoryCond cond) {
		return dao.queryCount(cond);
	}
}