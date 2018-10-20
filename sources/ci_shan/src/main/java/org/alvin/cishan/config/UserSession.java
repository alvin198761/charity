package org.alvin.cishan.config;

import org.alvin.cishan.sys.auth.adminfunc.AdminFunc;

import java.util.List;

public class UserSession {

    private String name;
    private String cname;
    private Integer id;
    private Boolean passwdAlert;
    private List<AdminFunc> adminMenus;

    public UserSession() {
    }

    public UserSession(String name, String cname, Integer id, List<AdminFunc> adminMenus, Boolean passwdAlert) {
        this.setName(name);
        this.setCname(cname);
        this.setId(id);
        this.setAdminMenus(adminMenus);
        this.setPasswdAlert(passwdAlert);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

 
 


    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<AdminFunc> getAdminMenus() {
		return adminMenus;
	}

	public void setAdminMenus(List<AdminFunc> adminMenus) {
		this.adminMenus = adminMenus;
	}

	public Boolean getPasswdAlert() {
        return passwdAlert;
    }

    public void setPasswdAlert(Boolean passwdAlert) {
        this.passwdAlert = passwdAlert;
    }
}
