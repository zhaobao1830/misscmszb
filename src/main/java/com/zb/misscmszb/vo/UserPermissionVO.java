package com.zb.misscmszb.vo;

import com.zb.misscmszb.model.UserDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;

/**
 * 用户 + 权限 view object
 */
@Data
public class UserPermissionVO {

    private Integer id;

    private String nickname;

    private String avatar;

    private Boolean admin;

    private String email;

    private List<Map<String, List<Map<String, String>>>> permissions;

    public UserPermissionVO() {
    }

    public UserPermissionVO(UserDO userDO) {
        BeanUtils.copyProperties(userDO, this);
    }

    public UserPermissionVO(UserDO userDO, List<Map<String, List<Map<String, String>>>> permissions) {
        BeanUtils.copyProperties(userDO, this);
        this.permissions = permissions;
    }
}
