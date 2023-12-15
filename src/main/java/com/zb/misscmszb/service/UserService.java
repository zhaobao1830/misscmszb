package com.zb.misscmszb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.misscmszb.dto.user.ChangePasswordDTO;
import com.zb.misscmszb.dto.user.RegisterDTO;
import com.zb.misscmszb.dto.user.UpdateInfoDTO;
import com.zb.misscmszb.model.PermissionDO;
import com.zb.misscmszb.model.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 用户服务实现类
 */
public interface UserService extends IService<UserDO> {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    UserDO getUserByUsername(String username);

    /**
     * 获得用户所有权限
     *
     * @param userId 用户id
     * @return 权限
     */
    List<PermissionDO> getUserPermissions(Integer userId);

    /**
     * 获得用户所有权限
     *
     * @param userId 用户id
     * @return 权限
     */
    List<Map<String, List<Map<String, String>>>> getStructuralUserPermissions(Integer userId);

    /**
     * 更新用户
     *
     * @param dto 更新用户信息用户校验器
     */
    void updateUserInfo(UpdateInfoDTO dto);

    /**
     * 根据用户名检查用户是否存在
     *
     * @param username 用户名
     * @return true代表存在
     */
    boolean checkUserExistByUsername(String username);

    /**
     * 根据用户名检查用户是否存在
     *
     * @param email 邮箱
     * @return true代表存在
     */
    boolean checkUserExistByEmail(String email);

    /**
     * 修改用户密码
     *
     * @param validator 修改密码校验器
     */
    void changeUserPassword(ChangePasswordDTO validator);

    /**
     * 新建用户
     *
     * @param validator 新建用户校验器
     * @return 被创建的用户
     */
    UserDO createUser(RegisterDTO validator);
}
