package org.alvin.cishan.common.acl;

import org.alvin.cishan.sys.auth.adminfunc.AdminFunc;
import org.alvin.cishan.sys.auth.adminsysuser.AdminSysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
public class AdminUserSessionSubject extends User {

	private static final long serialVersionUID = 1L;
	private AdminSysUser user;
	private List<AdminFunc> menus;
	private Date lastLoginTime;

	public AdminUserSessionSubject(AdminSysUser user, List<AdminFunc> menus, Collection<? extends GrantedAuthority> authorities) {
		super(user.getName(), user.getPassword(), authorities);
		this.setMenus(menus);
		this.setUser(user);
		this.setLastLoginTime(new Date());
	}

	public AdminSysUser getUser() {
		return user;
	}

	public void setUser(AdminSysUser user) {
		this.user = user;
	}

	public List<AdminFunc> getMenus() {
		return menus;
	}

	public void setMenus(List<AdminFunc> menus) {
		this.menus = menus;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	public boolean isAdmin() {
		return this.user.getUser_id() == 1;
	}
}
