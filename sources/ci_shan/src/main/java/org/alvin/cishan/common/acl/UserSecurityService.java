package org.alvin.cishan.common.acl;


import org.alvin.cishan.sys.auth.adminfunc.AdminFunc;
import org.alvin.cishan.sys.auth.adminrolefunc.AdminRoleFuncBus;
import org.alvin.cishan.sys.auth.adminsysuser.AdminSysUser;
import org.alvin.cishan.sys.auth.adminsysuser.AdminSysUserBus;
import org.alvin.cishan.sys.auth.adminsysuser.AdminSysUserCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tzc on 2017/8/22. update by gzz on 2018/8/15.
 */
@Component
public class UserSecurityService implements UserDetailsService {


	@Autowired
	private AdminSysUserBus adminSysUserBus;
	@Autowired
	private AdminRoleFuncBus adminRoleFuncBus;

	/**
	 * 权限具体实现，这里需要详细说明：<br/>
	 * 系统只有两种用户：超级管理员，普通管理员，<br/>
	 * 超级管理员对普通用户来说是隐形的，不可见，目前定为admin ，密码admin 系统启动的时候，会自动创建该用户，用户登录后自动修改密码
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Integer user_id = null;
		try {
			user_id = new Integer(username);
		} catch (Exception ex) {
			throw new UsernameNotFoundException("用户名格式错误!");
		}
		List<AdminSysUser> users = adminSysUserBus.queryList(AdminSysUserCond.builder().user_id_eq(user_id).build());
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("用户不存在!");
		}
		AdminSysUser user = users.get(0);

		if (user.getStatus().intValue() == 0) {
			throw new UsernameNotFoundException("用户被禁用!");
		}
//		SimpleGrantedAuthority auth = new SimpleGrantedAuthority(username.equals("1") ? RoleType.ADMIN.name() : RoleType.EMPLOYEE.name());
//		List<AdminFunc> menus = adminRoleFuncBus.queryFunc(user_id);
//
//		List<BranchRule> rules = this.branchRuleClient.queryList(BranchRuleCond.builder().user_id(user.getUser_id()).build());
//		List<Long> bids = rules.stream().map(BranchRule::getBid).collect(Collectors.toList());
//
//		BranchCond branchCond = new BranchCond();
//		branchCond.setIds(bids);
//		List<Branch> branches = this.branchBus.queryList(branchCond);
//		return new AdminUserSessionSubject(user, menus, Arrays.asList(auth), branches);
		return null;

	}

}
