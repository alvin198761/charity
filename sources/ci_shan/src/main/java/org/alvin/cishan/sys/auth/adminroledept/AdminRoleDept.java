package org.alvin.cishan.sys.auth.adminroledept;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @类说明:角色部门实体类
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleDept {

	//数据库中的字段
	private Long id;// 主键
	private Integer role_id;// 角色主键
	private Integer dept_id;// 部门主键

	//此处可添加查询显示辅助字段

}