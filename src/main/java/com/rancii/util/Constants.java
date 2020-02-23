package com.rancii.util;

public class Constants {
	/**
	 * shiro采用加密算法
	 */
	public static final String HASH_ALGORITHM = "SHA-1";
	/**
	 * 生成Hash值的迭代次数 
	 */
	public static final int HASH_INTERATIONS = 1024;
	/**
	 * 生成盐的长度
	 */
	public static final int SALT_SIZE = 8;

	/**
	 * 验证码
	 */
	public static final String VALIDATE_CODE = "validateCode";

	/**
	 *系统用户默认密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";

	/**
	 * 定时任务状态:正常
	 */
	public static final Integer QUARTZ_STATUS_NOMAL = 0;
	/**
	 * 定时任务状态:暂停
	 */
	public static final Integer QUARTZ_STATUS_PUSH = 1;

	/**
	 * 评论类型：1文章评论
	 */
	public static final Integer COMMENT_TYPE_ARTICLE_COMMENT = 1;
	/**
	 * 评论类型：2.系统留言
	 */
	public static final Integer COMMENT_TYPE_LEVING_A_MESSAGE = 2;

	/**
	 * 字典-API签名key主键
	 */
	public static final String DICT_API_KEY = "SIGN_KEY";
	/**
	 * 字典-代付单笔限额
	 */
	public static final String DICT_DF_SINGLE_QUOTA = "DF_SINGLE";
	/**
	 * 字典-代付卡日限额
	 */
	public static final String DICT_DF_CARD_QUOTA = "DF_CARD_QUOTA";
	/**
	 * 默认角色id
	 */
	public static final Long DETAUAL_ROLE_ID = 7L;
	/**
	 * 字典-代付商户签名key
	 */
	public static final String SIGN_KEY_DF ="SIGN_KEY_DF";
	/**
	 * 字典-商户代付手续费
	 */
	public static final String DF_FEE ="DF_FEE";
}
