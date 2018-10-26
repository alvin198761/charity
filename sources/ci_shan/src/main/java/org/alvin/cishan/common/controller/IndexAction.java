package org.alvin.cishan.common.controller;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.alvin.cishan.common.acl.AdminUserSessionSubject;
import org.alvin.cishan.common.acl.UserAuthenticationProvider;
import org.alvin.cishan.config.UserSession;
import org.alvin.cishan.sys.auth.adminfunc.AdminFunc;
import org.alvin.cishan.sys.util.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/8/27.
 */
@Controller
public class IndexAction {


	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/index")
	public String toIndex() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/api/heartbeat")
	@ResponseBody
	public Long heartbeat() {
		return System.currentTimeMillis();
	}


	@GetMapping("/api/session")
	@ResponseBody
	public UserSession getUserInfo(Principal principal) {
		Assert.notNull(principal, "principal must not be null");
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
		AdminUserSessionSubject subject = (AdminUserSessionSubject) token.getPrincipal();
		List<AdminFunc> menus = parseMenu(subject.getMenus());
		return new UserSession(subject.getUser().getName(), subject.getUser().getName(), subject.getUser().getUser_id(), menus, false);
	}

	private List<AdminFunc> parseMenu(List<AdminFunc> sourceMenus) {
		// 转成二级菜单，用来做前台显示
		HashMap<Integer, AdminFunc> treeBeanHashMap = Maps.newHashMap();
		for (AdminFunc adminMenu : sourceMenus) {
			if (adminMenu.getParent_id() == 1) {
				treeBeanHashMap.put(adminMenu.getFunc_id(), adminMenu);
				adminMenu.setChildren(null);
				continue;
			}
			AdminFunc parentMenu = treeBeanHashMap.get(adminMenu.getParent_id());
			if (parentMenu == null) {
				continue;
			}
			List<AdminFunc> children = parentMenu.getChildren();
			if (children == null) {
				children = Lists.newArrayList();
				parentMenu.setChildren(children);
			}
			children.add(adminMenu);
			treeBeanHashMap.put(adminMenu.getFunc_id(), adminMenu);
			adminMenu.setChildren(null);
		}
		return treeBeanHashMap.values().stream().collect(Collectors.toList());
	}

	/**
	 * 登陆验证
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/api/loginValidate")
	@ResponseBody
	public MessageInfo loginValidate(@RequestParam("j_username") String username, @RequestParam("j_password") String password) {
		MessageInfo messageInfo = new MessageInfo();
		try {
			UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
			userAuthenticationProvider.authenticate(upToken);
		} catch (Exception ex) {
			ex.printStackTrace();
			messageInfo.setMessage(1, ex.getMessage());
		}
		return messageInfo;
	}

}
