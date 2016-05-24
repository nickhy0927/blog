package com.cako.platform.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cako.platform.role.entity.Role;
import com.cako.platform.utils.BaseEntity;
import com.orm.enums.SysEnum;
import com.orm.enums.SysEnum.Status;
import com.orm.enums.SysEnum.UserType;

@Entity
@Table(name = "t_p_user")
public class User extends BaseEntity {

	private String brithday;// 出生日期
	private String email;// 电子邮箱
	private String loginName;// 登录名称
	private String nickName;// 用户昵称
	private String password;// 登录密码
	private String userTag;//用户手机端的标识
	private Set<Role> roles = new HashSet<Role>();
	private Set<User> friends = new HashSet<User>();
	private String resources = "1";
	private SysEnum.Status userStatus = Status.INIT;// 用户状态

	private SysEnum.UserType userType = UserType.GENERAL;// 用户类型

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "t_p_user_friend", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "f_id"))
	@Fetch(FetchMode.SUBSELECT)
	public Set<User> getFriends() {
		return friends;
	}
	
	public void setFriends(Set<User> friends) {
		this.friends = friends;
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

	public String getEmail() {
		return email;
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

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "t_platform_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public SysEnum.Status getUserStatus() {
		return userStatus;
	}

	public SysEnum.UserType getUserType() {
		return userType;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUserStatus(SysEnum.Status userStatus) {
		this.userStatus = userStatus;
	}

	public void setUserType(SysEnum.UserType userType) {
		this.userType = userType;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}
}
