package org.alvin.cishan.sys.service.dict;

import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @类说明: 类型字典--数据控制器层
* @author: 唐植超
* @date : 2018-10-20 11:34:30
**/
@RestController
@RequestMapping("api/dict")
public class DictController {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private DictService service; //注入类型字典数据逻辑层

    /**
    * @方法说明：  新增[类型字典]记录
    */
	@PostMapping("save")
	public int save(@RequestBody Dict dict) {
		return service.save(dict);
	}

    /**
    * @方法说明： 删除类型字典记录(多条)
    */
	@PostMapping("delete")
	public int delete(@RequestParam("ids[]") Long ids[]) {
		return service.delete(ids);
	}

    /**
    * @方法说明： 修改类型字典记录
    */
	@PostMapping("update")
	public int update(@RequestBody Dict dict) {
		return service.update(dict);
	}

    /**
    * @方法说明： 按条件查询分页类型字典列表
    */
	@PostMapping("queryPage")
	public Page<Dict> queryPage(@RequestBody DictCond cond ){
		return service.queryPage(cond);
	}

    /**
    * @方法说明： 按条件查询不分页类型字典列表
    */
	@PostMapping("queryList")
	public List<Dict> queryList(@RequestBody DictCond cond ){
		return service.queryList(cond);
	}

    /**
    * @方法说明： 按主键查单个类型字典记录
    */
	@PostMapping("findById")
	public Dict findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

    /**
    * @方法说明： 按条件查询类型字典记录个数
    */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody DictCond cond ){
		return service.queryCount(cond);
	}
}