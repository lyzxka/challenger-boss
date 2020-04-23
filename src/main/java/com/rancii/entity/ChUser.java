package com.rancii.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.rancii.base.DataEntity;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@TableName("ch_user")
public class ChUser extends DataEntity<ChUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
	private String phone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 昵称
     */
	private String name;
    /**
     * 头像
     */
	private String icon;
    /**
     * 密码
     */
	private String password;
    /**
     * 加密盐
     */
	private String salt;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	@Override
	public String toString() {
		return "ChUser{" +
			", phone=" + phone +
			", email=" + email +
			", name=" + name +
			", icon=" + icon +
			", password=" + password +
			", salt=" + salt +
			"}";
	}
}
