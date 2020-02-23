package com.rancii.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rancii.entity.Role;
import com.rancii.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Set;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hantw
 * @since 2017-10-31
 */
public interface UserDao extends BaseMapper<User> {
	User selectUserByMap(Map<String, Object> map);

	void saveUserRoles(@Param("userId") Long id, @Param("roleIds") Set<Role> roles);

	void dropUserRolesByUserId(@Param("userId") Long userId);

	Long selectRoleByUserId(@Param("userId") Long userId);
}