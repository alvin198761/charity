package org.alvin.cishan.sys.auth.adminsysuser;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @类说明:用户实体类
 * @author:高振中
 * @date:2018-08-10 09:21:48
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminSysUser {

	// 数据库中的字段
	private Integer user_id;// 主键
	private String name;// 姓名
	// private Integer code;// 会员编号
	private String password;// 密码
	private String phone;// 手机号
	private Byte gender;// 性别
	private Byte status;// 状态
	private Integer department_id;// 部门ID
	private String wechat;// 微信号
	private String remark;// 备注

	// 此处可添加查询显示辅助字段
	private String dept_name;// 部门名称
	// 修改辅助字段
	private String old_password;// 原密码

}