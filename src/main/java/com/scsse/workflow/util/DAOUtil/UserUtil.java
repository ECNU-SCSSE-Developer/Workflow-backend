package com.scsse.workflow.util.DAOUtil;

import com.scsse.workflow.constant.ErrorMessage;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.handler.WrongUsageException;
import com.scsse.workflow.repository.UserRepository;
import com.scsse.workflow.util.MVCUtil.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alfred Fu
 * Created on 2019/9/16 7:55 下午
 */
@Component
public class UserUtil {
    private final UserRepository userRepository;

    @Autowired
    public UserUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoginUser() {
        // This will throw null pointer exception
        return userRepository.findByOpenid(RequestUtil.getOpenId());
    }

    public Integer findUserIdByOpenid(String openId) throws Exception {
        User result = userRepository.findByOpenid(openId);
        if (result == null) {
            throw new WrongUsageException(ErrorMessage.USER_NOT_FOUND);
        }
        return result.getUserId();
    }

    public User getUserByUserId(Integer userId) throws Exception {
        User result = userRepository.findByUserId(userId);
        if (result == null) {
            throw new WrongUsageException(ErrorMessage.USER_NOT_FOUND);
        }
        return result;
    }

    public Integer getLoginUserId() throws WrongUsageException {
        User result = userRepository.findByOpenid(RequestUtil.getOpenId());
        // This will throw null pointer exception
        if (result == null)
            throw new WrongUsageException(ErrorMessage.USER_NOT_FOUND);
        return result.getUserId();
    }
}
