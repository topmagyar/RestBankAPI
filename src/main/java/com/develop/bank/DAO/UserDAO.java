package com.develop.bank.DAO;

import com.develop.bank.model.User;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface UserDAO {

    long save(User user);
}
