package com.cako.platform.user.tree;

import java.util.Date;

import com.cako.platform.user.entity.User;
import com.orm.enums.SysEnum.DeleteStatus;
import com.orm.enums.SysEnum.Display;
import com.orm.enums.SysEnum.Status;
import com.orm.enums.SysEnum.UserType;

public class UserClazz {

	private String brithday;// 出生日期
	protected Date createTime;
	private String deleteStatus;
	private String displayStatus;
	private String email;// 电子邮箱
	private String id;// ID
	private String loginName;// 登录名称
	private String nickName;// 用户昵称
	private String password;// 登录密码
	protected Date updateTime;
	private String userStatus;// 用户状态
	private String userType;// 用户类型
	private String userTag;//用户手机端的标识
	private String resources;

	public UserClazz(User user) {
		this.id = user.getId();
		this.password = user.getPassword();
		this.loginName = user.getLoginName();
		this.updateTime = user.getUpdateTime();
		this.brithday = user.getBrithday();
		this.nickName = user.getNickName();
		this.createTime = user.getCreateTime();
		this.email = user.getEmail();
		if (user.getDeleteStatus() == DeleteStatus.NO) {
			this.deleteStatus = "NO";
		} else {
			this.deleteStatus = "YES";
		}
		if (user.getDisplayStatus() == Display.DISPLAY) {
			this.displayStatus = "DISPLAY";
		} else {
			this.displayStatus = "NONE";
		}
		if (user.getUserType() == UserType.ADMIN) {
			this.userType = "ADMIN";
		} else if (user.getUserType() == UserType.GENERAL) {
			this.userType = "GENERAL";
		} else if (user.getUserType() == UserType.LEAGUER_MEMBER) {
			this.userType = "LEAGUER_MEMBER";
		} else if (user.getUserType() == UserType.MEMBER) {
			this.userType = "MEMBER";
		}
		if (user.getUserStatus() == Status.INIT) {
			this.userStatus = "INIT";
		} else if (user.getUserStatus() == Status.LOCKED) {
			this.userStatus = "LOCKED";
		} else if (user.getUserStatus() == Status.NORMAL) {
			this.userStatus = "NORMAL";
		}
		this.userTag = user.getUserTag();
		this.resources = user.getResources();
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getUserTag() {
		return userTag;
	}

	public void setUserTag(String userTag) {
		this.userTag = userTag;
	}

	public String getBrithday() {
		return brithday;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPassword() {
		return password;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
