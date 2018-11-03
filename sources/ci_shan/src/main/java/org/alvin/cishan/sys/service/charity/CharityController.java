package org.alvin.cishan.sys.service.charity;

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
* @类说明: 慈善方--数据控制器层
* @author: 唐植超
* @date : 2018-10-20 11:34:30
**/
@RestController
@RequestMapping("api/charity")
public class CharityController extends PrincipalAction {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CharityService service; //注入慈善方数据逻辑层

    /**
    * @方法说明：  新增[慈善方]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Charity charity , Principal principal) {
		charity.setStatus((byte)0);
		charity.setCreate_date(new Date());
		charity.setAuthor(getUser(principal).getUser_id().longValue());
		return service.save(charity);
	}

    /**
    * @方法说明： 删除慈善方记录(多条)
    */
	@RequestMapping("delete")
	public int delete(@RequestParam("id") Long id) {
		return service.delete(new Long[]{id});
	}

    /**
    * @方法说明： 修改慈善方记录
    */
	@PostMapping("update")
	public int update(@RequestBody Charity charity) {
		return service.update(charity);
	}

    /**
    * @方法说明： 按条件查询分页慈善方列表
    */
	@PostMapping("queryPage")
	public Page<Charity> queryPage(@RequestBody CharityCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页慈善方列表
    */
	@PostMapping("queryList")
	public List<Charity> queryList(@RequestBody CharityCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个慈善方记录
    */
	@PostMapping("findById")
	public Charity findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询慈善方记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody CharityCond cond ){
		return service.queryCount(cond);
	}
}