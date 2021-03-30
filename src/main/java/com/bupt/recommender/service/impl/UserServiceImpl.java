package com.bupt.recommender.service.impl;

import com.bupt.recommender.entity.AdminUserPO;
import com.bupt.recommender.mapper.AdminUserMapper;
import com.bupt.recommender.service.UserService;
import com.bupt.recommender.vo.AdminUserReqVO;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUserPO findByUsername(String username) throws Exception {
        return adminUserMapper.getUserByUsername(username);
    }

    @Override
    public int register(AdminUserReqVO userReq) throws Exception {
        String username = userReq.getUsername();
        String password = userReq.getPassword();

        AdminUserPO user = new AdminUserPO();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        if (username.equals("") || password.equals("")) {
            return 0;
        }

        boolean exist = isExist(username);
        if (exist) {
            return 2;
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        adminUserMapper.save(user);

        return 1;
    }

    private boolean isExist(String username) throws Exception {
        AdminUserPO user = adminUserMapper.getUserByUsername(username);
        return null != user;
    }
}
