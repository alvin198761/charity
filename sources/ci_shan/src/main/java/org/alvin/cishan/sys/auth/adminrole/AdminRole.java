package org.alvin.cishan.sys.auth.adminrole;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @类说明:角色实体类
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRole {

	//数据库中的字段
	private Integer id;// 主键
	private String name;// 名称
	private String remark;// 备注

	//此处可添加查询显示辅助字段

}