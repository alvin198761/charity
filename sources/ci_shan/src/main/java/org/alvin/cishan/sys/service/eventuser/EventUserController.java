package org.alvin.cishan.sys.service.eventuser;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @类说明: 活动参与方--数据控制器层
* @author: 高振中
* @date : 2018-10-30 11:50:24
**/
@RestController
@RequestMapping("eventUser")
public class EventUserController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private EventUserService service; //注入活动参与方数据逻辑层

    /**
    * @方法说明：  新增[活动参与方]记录
    */
	@PostMapping("save")
	public int save(@RequestBody EventUser eventUser) {
		return service.save(eventUser);
	}

    /**
    * @方法说明： 删除活动参与方记录(多条)
    */
	@PostMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改活动参与方记录
    */
	@PostMapping("update")
	public int update(@RequestBody EventUser eventUser) {
		return service.update(eventUser);
	}

    /**
    * @方法说明： 按条件查询分页活动参与方列表
    */
	@PostMapping("queryPage")
	public Page<EventUser> queryPage(@RequestBody EventUserCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页活动参与方列表
    */
	@PostMapping("queryList")
	public List<EventUser> queryList(@RequestBody EventUserCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个活动参与方记录
    */
	@PostMapping("findById")
	public EventUser findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询活动参与方记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody EventUserCond cond ){
		return service.queryCount(cond);
	}
}