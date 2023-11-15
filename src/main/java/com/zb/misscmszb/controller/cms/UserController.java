package com.zb.misscmszb.controller.cms;

import com.zb.misscmszb.dto.user.LoginDTO;
import com.zb.misscmszb.core.exception.NotFoundException;
import com.zb.misscmszb.core.exception.ParameterException;
import com.zb.misscmszb.extension.token.DoubleJWT;
import com.zb.misscmszb.extension.token.Tokens;
import com.zb.misscmszb.model.UserDO;
import com.zb.misscmszb.service.UserIdentityService;
import com.zb.misscmszb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserIdentityService userIdentityService;

    @Autowired
    private DoubleJWT jwt;

    @PostMapping("/login")
    public Tokens login(@RequestBody LoginDTO loginDTO) {
        UserDO user = userService.getUserByUsername(loginDTO.getUsername());
        if (user == null) {
            throw new NotFoundException(10021);
        }
        boolean valid = userIdentityService.verifyUsernamePassword(
                user.getId(),
                user.getUsername(),
                loginDTO.getPassword());
        if (!valid) {
            throw new ParameterException(10031);
        }
        return jwt.generateTokens(user.getId());
    }
}
