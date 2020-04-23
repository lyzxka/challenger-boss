package com.rancii.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.rancii.base.DataEntity;

/**
 * <p>
 * 组队请求
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@TableName("ch_group_search")
public class ChGroupSearch extends DataEntity<ChGroupSearch> {

    private static final long serialVersionUID = 1L;

    /**
     * 队伍
     */
	private String groupNo;
    /**
     * 标题
     */
	private String title;
    /**
     * 比赛
     */
	private Long matchId;
    /**
     * 详情
     */
	private String content;
    /**
     * 成员人数
     */
	private Integer userNum;
    /**
     * 类别
     */
	private Long categoryId;

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	@Override
	public String toString() {
		return "ChGroupSearch{" +
			", groupNo=" + groupNo +
			", title=" + title +
			", matchId=" + matchId +
			", content=" + content +
			", userNum=" + userNum +
			", categoryId=" + categoryId +
			"}";
	}
}
