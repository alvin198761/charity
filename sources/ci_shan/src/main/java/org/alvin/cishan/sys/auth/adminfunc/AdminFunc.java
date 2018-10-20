package org.alvin.cishan.sys.auth.adminfunc;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @类说明:菜单实体类
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Setter
@Getter
public class AdminFunc {

	// 数据库中的字段
	private Integer func_id;// 主键
	private Byte type;// 类型
	private String color;// 颜色
	private String icon;// 图标
	private String name;// 名称
	private Integer parent_id;// 父结点ID
	private String tip;// 提示信息
	private String remark;// 备注
	private Byte status;// 状态
	private String url;// 地址
	private Short order_num;// 排序编号

	// 此处可添加查询显示辅助字段
	private String parent_name;// 上级名称
	private List<AdminFunc> children = Lists.newArrayList();

}