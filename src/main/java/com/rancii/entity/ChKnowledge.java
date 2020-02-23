package com.rancii.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.rancii.base.DataEntity;

/**
 * <p>
 * 知识资料
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public class ChKnowledge extends DataEntity<ChKnowledge> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户
     */
	private Long userId;
    /**
     * 标题
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 门类
     */
	private Long categoryId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	@Override
	public String toString() {
		return "ChKnowledge{" +
			", userId=" + userId +
			", title=" + title +
			", content=" + content +
			", categoryId=" + categoryId +
			"}";
	}
}
