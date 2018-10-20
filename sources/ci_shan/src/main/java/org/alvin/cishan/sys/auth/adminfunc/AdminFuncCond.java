package org.alvin.cishan.sys.auth.adminfunc;

import lombok.*;
import lombok.experimental.Accessors;
import org.alvin.cishan.sys.util.base.BaseCondition;

/**
 * @类说明:菜单查询条件实体类
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminFuncCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(func_id, "AND t.func_id <> ?");
		// add(type, "AND t.type = ?");
		// add(color, "AND t.color LIKE ?", 3);
		// add(icon, "AND t.icon LIKE ?", 3);
		add(name, "AND t.name = ?");
		// add(parent_id, "AND t.parent_id = ?");
		// add(tip, "AND t.tip LIKE ?", 3);
		// add(remark, "AND t.remark LIKE ?", 3);
		// add(status, "AND t.status = ?");
		// add(url, "AND t.url LIKE ?", 3);
		// add(order_num, "AND t.order_num = ?");
		// add(ids, "AND t.id IN ");
	}

	// 查询条件,把不用的条件清理掉
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
	// private List<Long> ids;// 主键列表

}