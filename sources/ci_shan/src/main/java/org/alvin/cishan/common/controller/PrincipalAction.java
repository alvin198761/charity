package org.alvin.cishan.common.controller;

import org.alvin.cishan.common.acl.AdminUserSessionSubject;
import org.alvin.cishan.sys.auth.adminsysuser.AdminSysUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

/**
 * @author 唐植超
 * @功能说明 controller共用的取权限的方法, 需要权限时Controller继承本类即可
 * @date 2017-10-20
 */

public abstract class PrincipalAction {

	protected AdminUserSessionSubject getUserSubject(Principal principal) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
		return (AdminUserSessionSubject) token.getPrincipal();
	}

	/**
	 * @功能说明 获取当前用户信息
	 */
	protected AdminSysUser getUser(Principal principal) {
		return getUserSubject(principal).getUser();
	}

	/**
	 * @功能说明 获取当前用户ID
	 */
	protected Integer getUserId(Principal principal) {
		return getUserSubject(principal).getUser().getUser_id();
	}
}
