package com.rancii.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.rancii.base.DataEntity;

/**
 * <p>
 * 队伍
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public class ChGroup extends DataEntity<ChGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 队伍编号
     */
	private String groupNo;
    /**
     * 队名
     */
	private String groupName;
    /**
     * 用户
     */
	private Long userId;
    /**
     * 身份
     */
	private String userRole;
    /**
     * 比赛
     */
	private Long matchId;
    /**
     * 类别
     */
	private Long categoryId;
    /**
     * 状态
     */
	private String status;

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ChGroup{" +
			", groupNo=" + groupNo +
			", groupName=" + groupName +
			", userId=" + userId +
			", userRole=" + userRole +
			", matchId=" + matchId +
			", categoryId=" + categoryId +
			", status=" + status +
			"}";
	}
}
