package org.alvin.cishan.common.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/22.
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserSecurityService userSecurityService;

	public UserAuthenticationProvider() {
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String username = token.getName();
		// 从数据库找到的用户
		UserDetails userDetails = null;
		if (username != null) {
			userDetails = this.userSecurityService.loadUserByUsername(username);
		}
		if (userDetails == null) {
			throw new UsernameNotFoundException("用户名不存在");
		} else if (!userDetails.isEnabled()) {
			throw new DisabledException("用户已被禁用");
		} else if (!userDetails.isAccountNonExpired()) {
			throw new AccountExpiredException("账号已过期");
		} else if (!userDetails.isAccountNonLocked()) {
			throw new LockedException("账号已被锁定");
		} else if (!userDetails.isCredentialsNonExpired()) {
			throw new LockedException("凭证已过期");
		}
		// 数据库用户的密码
		String password = userDetails.getPassword();
		// 与authentication里面的credentials相比较
		if (!password.equals(token.getCredentials())) {
			throw new BadCredentialsException("用户名或密码不存在");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities()); // 授权

	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}
}