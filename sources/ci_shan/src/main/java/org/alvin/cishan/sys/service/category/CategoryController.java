package org.alvin.cishan.sys.service.category;

import org.alvin.cishan.common.controller.PrincipalAction;
import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * @类说明: 捐助物资--数据控制器层
 * @author: 唐植超
 * @date : 2018-10-20 11:34:30
 **/
@RestController
@RequestMapping("api/category")
public class CategoryController extends PrincipalAction {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CategoryService service; //注入捐助物资数据逻辑层

	/**
	 * @方法说明： 新增[捐助物资]记录
	 */
	@PostMapping("save")
	public int save(@RequestBody Category category, Principal principal) {
		category.setAuthor(getUser(principal).getUser_id().longValue());
		category.setCreate_date(new Date());
		category.setStatus((byte) 0);
		return service.save(category);
	}

	/**
	 * @方法说明： 删除捐助物资记录(多条)
	 */
	@RequestMapping("delete")
	public int delete(@RequestParam("id") Long id) {
		return service.delete(new Long[]{id});
	}

	/**
	 * @方法说明： 修改捐助物资记录
	 */
	@PostMapping("update")
	public int update(@RequestBody Category category) {
		return service.update(category);
	}

	/**
	 * @方法说明： 按条件查询分页捐助物资列表
	 */
	@PostMapping("queryPage")
	public Page<Category> queryPage(@RequestBody CategoryCond cond) {
		return service.queryPage(cond);
	}

	/**
	 * @方法说明： 按条件查询不分页捐助物资列表
	 */
	@PostMapping("queryList")
	public List<Category> queryList(@RequestBody CategoryCond cond) {
		return service.queryList(cond);
	}

	/**
	 * @方法说明： 按主键查单个捐助物资记录
	 */
	@PostMapping("findById")
	public Category findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

	/**
	 * @方法说明： 按条件查询捐助物资记录个数
	 */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody CategoryCond cond) {
		return service.queryCount(cond);
	}
}