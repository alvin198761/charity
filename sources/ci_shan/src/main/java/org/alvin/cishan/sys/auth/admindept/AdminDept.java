package org.alvin.cishan.sys.auth.admindept;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @类说明:部门实体类
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Setter
@Getter
public class AdminDept {

	// 数据库中的字段
	private Integer dept_id;// 主键
	// private Long branch_id;// 门店主键
	private Integer parent_id;// 上级ID
	private String name;// 部门名称
	private String parent_name;// 上级部门
	private Byte enable;// 可用标志
	private String remark;// 备注

	// 此处可添加查询显示辅助字段
	private List<AdminDept> children = Lists.newArrayList();

}