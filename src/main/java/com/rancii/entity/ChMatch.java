package com.rancii.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.rancii.base.DataEntity;

/**
 * <p>
 * 比赛
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@TableName("ch_match")
public class ChMatch extends DataEntity<ChMatch> {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
	private String title;
    /**
     * 比赛编号
     */
	private String matchNo;
    /**
     * 主图
     */
	private String imgUrl;
    /**
     * 内容
     */
	private String content;
    /**
     * 门类
     */
	private Long categoryId;
    /**
     * 浏览量
     */
	private Long views;
    /**
     * 开始时间
     */
	private Date beginDate;
    /**
     * 截止时间
     */
	private Date endDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "ChMatch{" +
			", title=" + title +
			", matchNo=" + matchNo +
			", imgUrl=" + imgUrl +
			", content=" + content +
			", categoryId=" + categoryId +
			", views=" + views +
			", beginDate=" + beginDate +
			", endDate=" + endDate +
			"}";
	}
}
