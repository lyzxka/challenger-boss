package com.rancii.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.rancii.base.DataEntity;

/**
 * <p>
 * 门类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public class ChCategory extends DataEntity<ChCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 门类名称
     */
	private String categoryName;
    /**
     * 门类编号
     */
	private String categoryNo;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}


	@Override
	public String toString() {
		return "ChCategory{" +
			", categoryName=" + categoryName +
			", categoryNo=" + categoryNo +
			"}";
	}
}
