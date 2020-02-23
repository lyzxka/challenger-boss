package com.rancii.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rancii.entity.Role;
import com.rancii.entity.User;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hantw
 * @since 2017-10-31
 */
public interface UserService extends IService<User> {

	User findUserByLoginName(String name);

	User findUserById(Long id);

	User saveUser(User user);

	User updateUser(User user);

	void saveUserRoles(Long id, Set<Role> roleSet);

	void dropUserRolesByUserId(Long id);

	int userCount(String param);

	void deleteUser(User user);

	Long findUserRoleByCurrent();

}
